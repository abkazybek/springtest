/*
 * Copyright (c) 2022 LTD Haulmont Samara. All Rights Reserved.
 * Haulmont Samara proprietary and confidential.
 * Use is subject to license terms.
 */

package com.company.thesissummer.service;

import com.company.thesissummer.entity.*;
import com.haulmont.cuba.core.global.CommitContext;
import com.haulmont.cuba.core.global.DataManager;
import com.haulmont.thesis.core.entity.DocKind;
import org.springframework.stereotype.Service;
import org.w3c.dom.*;
import org.xml.sax.SAXException;
import javax.inject.Inject;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;


@Service(OrderService.NAME)
public class OrderServiceBean implements OrderService {

    @Inject
    private DataManager dataManager;

    DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();


    @Override
    public void saveXML() {
/*
        //Открытие файла - только для теста
        File file = new File("C:\\Users\\a.kazybek\\Desktop\\Тезис\\АКК\\OrderReturn.xml");

        //открытие документа
        Document document = null;
        try {
            document = dbf.newDocumentBuilder().parse(file);


            //Создание классса РАспоряжние по переоценке
            OrderReturn1 orderReturn1 = dataManager.create(OrderReturn1.class);


            //Подгрузка вида документа
            DocKind docKindOrder = dataManager.load(DocKind.class)
                    .query("select e from df$DocKind e where e.code = :code")
                    .parameter("code", "14")
                    .view("browse")
                    .one();

            //Комит вида документа
            orderReturn1.setDocKind(docKindOrder);

            //Парсинг документа
            if (document != null) {
                orderReturn1.setNomer(document.getElementsByTagName("arg").item(0).getTextContent());


                orderReturn1.setData(document.getElementsByTagName("arg").item(1).getTextContent());

                orderReturn1.setPodrazdelenie(document.getElementsByTagName("arg").item(2).getTextContent());

                orderReturn1.setSchetKorporacii(document.getElementsByTagName("arg").item(3).getTextContent());

                orderReturn1.setBankOrganizacii(document.getElementsByTagName("arg").item(4).getTextContent());

                orderReturn1.setBikBanka(document.getElementsByTagName("arg").item(5).getTextContent());

                orderReturn1.setPodrazdelenieOblast(document.getElementsByTagName("arg").item(6).getTextContent());

                orderReturn1.setKvartal(document.getElementsByTagName("arg").item(7).getTextContent());

                orderReturn1.setBikPoluchatelya(document.getElementsByTagName("arg").item(8).getTextContent());

                orderReturn1.setShetPoluchatelya(document.getElementsByTagName("arg").item(9).getTextContent());

                orderReturn1.setDogovorSubsidirovaniyaOsnovnoi(document.getElementsByTagName("arg").item(10).getTextContent());

                orderReturn1.setRabochiyOrganOperator(document.getElementsByTagName("arg").item(11).getTextContent());

                orderReturn1.setKvartal2(document.getElementsByTagName("arg").item(12).getTextContent());

                orderReturn1.setPodrazdelenieOblast2(document.getElementsByTagName("arg").item(13).getTextContent());

                orderReturn1.setAvtor(document.getElementsByTagName("arg").item(15).getTextContent());

                orderReturn1.setSummaSubsidiyObshayaOtkloneniya(document.getElementsByTagName("arg").item(16).getTextContent());

            }


            CommitContext commitContext = new CommitContext();

            document.getDocumentElement().normalize();

            NodeList nodeList = document.getElementsByTagName("Structura");
            for (int i = 0; nodeList.getLength() > i; i++) {
                Node node = nodeList.item(i);

                if (node.getNodeType() == Node.ELEMENT_NODE) {

                    Element element = (Element) node;

                    orderReturn1.setNomer1C(element.getElementsByTagName("Nomer").item(0).getTextContent());

                    orderReturn1.setNaimenovanieZaemchika(element.getElementsByTagName("NaimenovanieZaemchika").item(0).getTextContent());

                    orderReturn1.setNomerDataKreditnogoDogovora(element.getElementsByTagName("NomerDataKreditnogoDogovora").item(0).getTextContent());

                    orderReturn1.setSummaSubsidiyOtkloneniya(element.getElementsByTagName("SummaSubsidiyOtkloneniya").item(0).getTextContent());

                    orderReturn1.setDogovorSubsidirovaniya(element.getElementsByTagName("DogovorSubsidirovaniya").item(0).getTextContent());

                    orderReturn1.setPoyasnenie(element.getElementsByTagName("Poyasnenie").item(0).getTextContent());


                    orderReturn1 = dataManager.commit(orderReturn1);

                }

            }

            dataManager.commit(commitContext);


            orderReturn1 = dataManager.commit(orderReturn1);

        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        } catch (SAXException e) {
            e.printStackTrace();
        }
    }

 */
    }
}











