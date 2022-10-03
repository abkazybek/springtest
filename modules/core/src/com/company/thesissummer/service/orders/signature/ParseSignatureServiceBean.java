/*
 * Copyright (c) 2022 LTD Haulmont Samara. All Rights Reserved.
 * Haulmont Samara proprietary and confidential.
 * Use is subject to license terms.
 */

package com.company.thesissummer.service.orders.signature;

import com.company.thesissummer.entity.OrderLoan;
import com.haulmont.cuba.core.app.UniqueNumbersService;
import com.haulmont.cuba.core.global.DataManager;
import com.haulmont.cuba.core.global.TimeSource;
import com.haulmont.cuba.core.global.UserSessionSource;
import com.haulmont.cuba.security.entity.User;
import com.haulmont.workflow.core.entity.CardRole;
import jdk.internal.net.http.common.Logger;
import org.apache.commons.codec.binary.Base64;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;
import sun.security.x509.X509CertImpl;

import javax.inject.Inject;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.security.cert.*;
import java.util.List;

import static groovy.util.GroovyTestCase.assertEquals;

@Service(ParseSignatureService.NAME)
public class ParseSignatureServiceBean implements ParseSignatureService {

    @Inject
    private DataManager dataManager;

    @Inject
    protected UserSessionSource userSessionSource;

    @Inject
    protected TimeSource timeSource;

    @Inject
    public UniqueNumbersService uniqueNumbersService1;

    //1C программист отправляет xml в base64
    File file = new File("C:\\Users\\a.kazybek\\Desktop\\Тезис\\АКК\\signature.xml");


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

    @Override
    public void saveXML() {


        //переводим в массив байтов base64
        //byte[] decodeBytes = Base64.getDecoder().decode(xml);

        //создаем document по которому настроим парсинг
        //DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        //DocumentBuilder builder = null;
        //InputSource is = new InputSource(new StringReader(decodeBytes.);

        //открытие документа
        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();

        Document document;
        try {

            //создаем документ из массива байтов
            //document = factory.newDocumentBuilder().parse(new ByteArrayInputStream(decodeBytes));

            document = dbf.newDocumentBuilder().parse(file);

            //Создание классса распоряжние по выдачу займа
            //OrderLoan orderLoan = dataManager.create(OrderLoan.class);


            /*
            //Подгрузка вида документа (распоряжению по выдачи займа присваиваем код - 1)
            DocKind docKindOrder = dataManager.load(DocKind.class)
                    .query("select e from df$DocKind e where e.code = :code")
                    .parameter("code", "1")
                    .view("browse")
                    .one();



            //Создаем один комитконтекст так как у нас должно комитется все в одной транзакции
            CommitContext commitContext = new CommitContext();

            //запись вида документа, по которому происходит прасинг
            orderLoan.setDocKind(docKindOrder);
*/


            //Парсинг документа
            if (document != null) {
                //основные атрибуты которые парсятся с тегом arg Ұпо порядку
                //orderLoan.setNomerOrder(document)

                /*
                NodeList nodeList = document.getElementsByTagName("signatureEntry");

                for (int i = 0; nodeList.getLength() > i; i++) {

                    Node node = nodeList.item(i);

                    if (node.getNodeType() == Node.ELEMENT_NODE) {

                        Element element = (Element) node;


                 */

                        CertPath certPath = decodeCertPath(document.getElementsByTagName("cert").item(0).getTextContent());

                        X509CertImpl x509Cert = (X509CertImpl) certPath.getCertificates().get(0);

                        String subjectDn = x509Cert.SUBJECT_DN;

                        System.out.println(subjectDn);


                    }


        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (SAXException e) {
            e.printStackTrace();
        }

    }
}