/*
 * Copyright (c) 2022 LTD Haulmont Samara. All Rights Reserved.
 * Haulmont Samara proprietary and confidential.
 * Use is subject to license terms.
 */

package com.company.thesissummer.service.orders.protocolprav


import com.company.thesissummer.entity.ProtocolKK
import com.company.thesissummer.entity.ProtocolPrav
import com.google.zxing.BarcodeFormat
import com.google.zxing.EncodeHintType
import com.google.zxing.WriterException
import com.google.zxing.common.BitMatrix
import com.google.zxing.qrcode.QRCodeWriter
import com.haulmont.cuba.core.global.DataManager
import com.haulmont.thesis.core.app.SignatureService
import com.haulmont.thesis.core.app.barcode.BarcodeAPI
import com.haulmont.workflow.core.entity.CardAttachment
import org.apache.commons.codec.binary.Base64
import org.apache.commons.lang.StringUtils
import org.w3c.dom.Document
import org.w3c.dom.Element
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
class ProtocolPravQR {

    //Дата менеджер
    @Inject
    public DataManager dataManager;

    //Ращбиваю цепочку цертификатов (взял из сервиса в коробке)
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

    //основной груви класс для генерации qr кода
    Object getQR(Map<String, Object> params) {

        //подтягивание класса
        ProtocolPrav prav = params['entity'] as ProtocolPrav;

        //условие: имеется ли вложение в документе
        if (protocolKK.attachments != null) {

            //Подгрузку вложения из документа
            CardAttachment cardAttachment = dataManager.load(CardAttachment.class)
                    .query("select e from wf\$CardAttachment e where e.card.id = :cardId")
                    .parameter("cardId", prav)
                    .view("att2")
                    .one();

            //Вызовите метод DocumentBuilderFactory.newInstance (), чтобы получить фабрику, создавшую анализатор DOM
            DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();

            //DOM parser который создает объект класса
            DocumentBuilder db = dbf.newDocumentBuilder();

            if (cardAttachment.signatures != null) {
                //Создаем документ org.w3c.dom.Document

                Document doc = db.parse(new org.xml.sax.InputSource(new StringReader(cardAttachment.signatures)));

                NodeList nodeList = doc.getElementsByTagName("signatureEntry");

                List<String> stringList = new ArrayList<>();

                int sogl;

                for (int i = 0; nodeList.getLength() > i; i++) {

                    Node node = nodeList.item(i);

                    if (node.getNodeType() == Node.ELEMENT_NODE) {

                        Element element = (Element) node;

                        CertPath certPath = decodeCertPath(element.getElementsByTagName("cert").item(0).getTextContent());

                        //   Thread.sleep(1000);

                        X509CertImpl x509Cert = (X509CertImpl) certPath.getCertificates().get(0);

                        //  Thread.sleep(1000);

                        String s = x509Cert.subjectDN.name

                        s = s.replaceAll("GIVENNAME=", "");

                        s = s.replaceAll("C=KZ, SERIALNUMBER=", "");

                        s = s.replaceAll("SURNAME=", "");

                        s = s.replaceAll("CN=", "");

                        String data = element.getElementsByTagName("date").item(0).getTextContent();
                        String str = s + " " + data + "\n";
                        stringList.add(str);



                    }
                }

                return [[ 'QRcodeimage': getPDF417Barcode(stringList.get(0)),
                          'QRcodeimage1': getPDF417Barcode(stringList.subList(0,stringList.size() - 1) as String),
                          'QRcodeimage2': getPDF417Barcode(stringList.get(stringList.size() - 1))
                        ]]


            }
        }
    }


    byte[] getPDF417Barcode(String entity) throws IOException {

        final int size = 500;
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
