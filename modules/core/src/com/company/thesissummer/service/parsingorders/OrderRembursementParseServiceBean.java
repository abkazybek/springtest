/*
 * Copyright (c) 2022 LTD Haulmont Samara. All Rights Reserved.
 * Haulmont Samara proprietary and confidential.
 * Use is subject to license terms.
 */

package com.company.thesissummer.service.parsingorders;

import com.company.thesissummer.entity.OrderRembursement;
import com.company.thesissummer.entity.table1c.OrderRembursement1c;
import com.haulmont.cuba.core.app.UniqueNumbersService;
import com.haulmont.cuba.core.global.CommitContext;
import com.haulmont.cuba.core.global.DataManager;
import com.haulmont.cuba.core.global.PersistenceHelper;
import com.haulmont.cuba.core.global.TimeSource;
import com.haulmont.thesis.core.entity.Department;
import com.haulmont.thesis.core.entity.DocKind;
import com.haulmont.thesis.core.entity.Employee;
import org.springframework.stereotype.Service;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.inject.Inject;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.Base64;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service(OrderRembursementParseService.NAME)
public class OrderRembursementParseServiceBean implements OrderRembursementParseService {
    @Inject
    private DataManager dataManager;

    @Inject
    protected TimeSource timeSource;

    @Inject
    public UniqueNumbersService uniqueNumbersService1;

    DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();


    @Override
    public String saveXML(String xml) {


        byte [] decodeBytes = Base64.getDecoder().decode(xml);

        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = null;

        //InputSource is = new InputSource(new StringReader(decodeBytes.);

        //открытие документа
        Document document;
        try {
            document = dbf.newDocumentBuilder().parse(new ByteArrayInputStream(decodeBytes));


            //Создание классса РАспоряжние по переоценке
            OrderRembursement orderRembursement = dataManager.create(OrderRembursement.class);

            //Подгрузка вида документа
            DocKind docKindOrder = dataManager.load(DocKind.class)
                    .query("select e from df$DocKind e where e.code = :code")
                    .parameter("code", "4")
                    .view("browse")
                    .one();

            //Комит вида документа
            orderRembursement.setDocKind(docKindOrder);

            //Парсинг документа
            if (document != null) {
                orderRembursement.setNomerOrder(document.getElementsByTagName("arg").item(0).getTextContent());

                orderRembursement.setDataOrder(document.getElementsByTagName("arg").item(1).getTextContent());

                List<Department> departments = dataManager.load(Department.class).query("select e from df$Department e where " +
                        "e.name = :name").parameter("name", document.getElementsByTagName("arg").item(2).getTextContent()).list();

                if(departments.size() > 0){
                    orderRembursement.setDepartment(departments.get(0));
                }
                orderRembursement.setSchetKorporacii(document.getElementsByTagName("arg").item(3).getTextContent());

                orderRembursement.setBankOrganizacii(document.getElementsByTagName("arg").item(4).getTextContent());

                orderRembursement.setBikBanka(document.getElementsByTagName("arg").item(5).getTextContent());

                orderRembursement.setOblastPodrazdeleniya(document.getElementsByTagName("arg").item(6).getTextContent());

                orderRembursement.setKvartal1(document.getElementsByTagName("arg").item(7).getTextContent());

                orderRembursement.setDogovorSub(document.getElementsByTagName("arg").item(8).getTextContent());

                orderRembursement.setDataDogovoraSub(document.getElementsByTagName("arg").item(9).getTextContent());

                orderRembursement.setRabOrgan(document.getElementsByTagName("arg").item(10).getTextContent());

                orderRembursement.setKvartal2(document.getElementsByTagName("arg").item(11).getTextContent());

                List<Employee> user = dataManager.load(Employee.class).query("select e from df$Employee e where " +
                        "e.email = :email").parameter("email", document.getElementsByTagName("arg").item(12).getTextContent()).list();

                orderRembursement.setOwner(user.get(0));

                orderRembursement.setSummaSub(document.getElementsByTagName("arg").item(13).getTextContent());

                //устанавливаем текущую дату
                orderRembursement.setDate(timeSource.currentTimestamp());

                //устанавливаем порядкувую нумерацию
                if (PersistenceHelper.isNew(orderRembursement)) {
                    orderRembursement.setNumber(String.valueOf(uniqueNumbersService1.getNextNumber("NUMBER_")));
                }


            }


            CommitContext commitContext1 = new CommitContext();

            document.getDocumentElement().normalize();

            NodeList nodeList = document.getElementsByTagName("Structura");


            for (int i = 0; nodeList.getLength() > i; i++) {

                Node node = nodeList.item(i);

                if (node.getNodeType() == Node.ELEMENT_NODE) {

                    Element element = (Element) node;

                    OrderRembursement1c orderRembursement1c = dataManager.create(OrderRembursement1c.class);

                    Set<OrderRembursement1c> list  = new HashSet<>();

                    orderRembursement1c.setNumber(element.getElementsByTagName("Nomer").item(0).getTextContent());

                    orderRembursement1c.setNameofborrower(element.getElementsByTagName("NaimenovanieZaemchika").item(0).getTextContent());

                    orderRembursement1c.setIin(element.getElementsByTagName("IINBIN").item(0).getTextContent());

                    orderRembursement1c.setNomerAndDataKreditnogogDogovora(element.getElementsByTagName("NomerDataKreditnogoDogovora").item(0).getTextContent());

                    orderRembursement1c.setSummaSub(element.getElementsByTagName("SummaSubsidiy").item(0).getTextContent());

                    orderRembursement1c.setIikZaem(element.getElementsByTagName("IIK").item(0).getTextContent());

                    orderRembursement1c.setBank(element.getElementsByTagName("Bank").item(0).getTextContent());

                    orderRembursement1c.setOrderRembursement(orderRembursement);

                    list.add(orderRembursement1c);

                    orderRembursement.setOrderRembursement1c(list);

                    commitContext1.addInstanceToCommit(orderRembursement1c);


                }

            }


            commitContext1.addInstanceToCommit(orderRembursement);

            dataManager.commit(commitContext1);




        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        } catch (SAXException e) {
            e.printStackTrace();
        }

        return xml;

    }
}

//распоряжение успешно прошло тест через POSTMAN