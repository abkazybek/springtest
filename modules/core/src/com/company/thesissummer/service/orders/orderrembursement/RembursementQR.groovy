/*
 * Copyright (c) 2022 LTD Haulmont Samara. All Rights Reserved.
 * Haulmont Samara proprietary and confidential.
 * Use is subject to license terms.
 */

package com.company.thesissummer.service.orders.orderrembursement

import com.company.thesissummer.entity.OrderIns
import com.company.thesissummer.entity.OrderLoan
import com.company.thesissummer.entity.OrderRembursement
import com.google.zxing.BarcodeFormat
import com.google.zxing.EncodeHintType
import com.google.zxing.WriterException
import com.google.zxing.common.BitMatrix
import com.google.zxing.qrcode.QRCodeWriter
import com.haulmont.cuba.core.global.DataManager
import com.haulmont.thesis.core.app.barcode.BarcodeAPI
import com.haulmont.workflow.core.entity.CardAttachment
import com.sun.star.xml.sax.InputSource
import org.apache.commons.codec.binary.Base64
import org.apache.commons.lang.StringUtils
import org.w3c.dom.Document
import org.w3c.dom.Node
import org.w3c.dom.NodeList
import sun.security.x509.X509CertImpl

import javax.imageio.ImageIO
import javax.inject.Inject
import javax.xml.parsers.DocumentBuilder
import javax.xml.parsers.DocumentBuilderFactory
import java.awt.image.BufferedImage
import java.security.cert.CertPath
import java.security.cert.CertificateException
import java.security.cert.CertificateFactory

@SuppressWarnings("GroovyUnusedDeclaration")
class RembursementQR {

    private CertPath decodeCertPath(String certPathBase64) {
        if (!StringUtils.isBlank(certPathBase64)) {
            byte[] bytes = Base64.decodeBase64(certPathBase64.getBytes());
            try {
                ByteArrayInputStream bis = new ByteArrayInputStream(bytes);
                CertificateFactory certFactory = CertificateFactory.getInstance("X.509");
                CertPath certPath = certFactory.generateCertPath(bis);
                return certPath;
            } catch (CertificateException e) {
                System.out.println("Ошибка");
            }
        }

        return null;
    }


    @Inject
    public DataManager dataManager;

    public Object getQR(Map<String, Object> params) {

        OrderRembursement orderRembursement = params['entity'] as OrderRembursement;

        //Подгрузка вида документа
        CardAttachment cardAttachment = dataManager.load(CardAttachment.class)
                .query("select e from wf\$CardAttachment e where e.card.id = :cardId")
                .parameter("cardId", orderRembursement)
                .view("att2")
                .one();

        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();

        DocumentBuilder db = dbf.newDocumentBuilder();


        if (cardAttachment.signatures != null) {
            //Создаем документ org.w3c.dom.Document

            Document doc = db.parse(new org.xml.sax.InputSource(new StringReader(cardAttachment.signatures)));

            NodeList nodeList = doc.getElementsByTagName("signatureEntry");

            List<String> stringList = new ArrayList<>();

            //int sogl;

            for (int i = 0; nodeList.getLength() > i; i++) {

                Node node = nodeList.item(i);

                if (node.getNodeType() == Node.ELEMENT_NODE) {

                    org.w3c.dom.Element element = (org.w3c.dom.Element) node;

                    CertPath certPath = decodeCertPath(element.getElementsByTagName("cert").item(0).getTextContent());


                    X509CertImpl x509Cert = certPath.getCertificates().get(0);

                    String s = x509Cert.subjectDN.name


                    s = s.replaceAll("GIVENNAME=", "");

                    s = s.replaceAll("C=KZ, SERIALNUMBER=", "");

                    s = s.replaceAll("SURNAME=", "");

                    s = s.replaceAll("CN=", "");

                    String data = element.getElementsByTagName("date").item(0).getTextContent();
                    String str = s + " " + data;
                    stringList.add(str);


                }
            }

            return [['QRcodeimage': getPDF417Barcode(stringList.get(stringList.size() - 1))
                    ]]


        }
    }


    private static Document convertStringToDocument(String xmlStr) {
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder;
        try {
            builder = factory.newDocumentBuilder();
            Document doc = builder.parse(new InputSource(new StringReader(xmlStr)));
            return doc;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }


    public byte[] getPDF417Barcode(String entity) throws IOException {

        final int size = 1000;

        final int white = 255 << 16 | 255 << 8 | 255;

        final int black = 0;

        BitMatrix qrCode;
        try {
            Hashtable<EncodeHintType, String> hints = new Hashtable<>();
            hints.put(EncodeHintType.CHARACTER_SET, "UTF-8");

            BufferedImage image;
            qrCode = new QRCodeWriter().encode(entity, BarcodeFormat.QR_CODE, size, size, hints);

            image = new BufferedImage(size, size, BufferedImage.TYPE_INT_RGB);
            for (int i = 0; i < size; i++) {
                for (int j = 0; j < size; j++) {
                    image.setRGB(i, j, qrCode.get(i, j) ? black : white); // set pixel one by one
                }
            }
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            ImageIO.write(image, "png", baos);
            return baos.toByteArray();
        } catch (WriterException e) {
            throw new RuntimeException(e.getMessage(), e);
        }
    }

}
