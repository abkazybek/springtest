/*
 * Copyright (c) 2022 LTD Haulmont Samara. All Rights Reserved.
 * Haulmont Samara proprietary and confidential.
 * Use is subject to license terms.
 */

package com.company.thesissummer.service.parsingorders;

import com.company.thesissummer.entity.OrderReturnNeispSub;
import com.company.thesissummer.entity.table1c.OrderReturnNeispSub1C;
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
import java.io.File;
import java.io.IOException;
import java.util.Base64;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service(OrderReturnNeispSubParseService.NAME)
public class OrderReturnNeispSubParseServiceBean implements OrderReturnNeispSubParseService {

    @Inject
    private DataManager dataManager;


    DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();

    @Inject
    protected TimeSource timeSource;

    @Inject
    public UniqueNumbersService uniqueNumbersService1;

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
            OrderReturnNeispSub orderReturnNeispSub = dataManager.create(OrderReturnNeispSub.class);

            //Подгрузка вида документа
            DocKind docKindOrder = dataManager.load(DocKind.class)
                    .query("select e from df$DocKind e where e.code = :code")
                    .parameter("code", "3")
                    .view("browse")
                    .one();

            //Комит вида документа
            orderReturnNeispSub.setDocKind(docKindOrder);

            //Парсинг документа
            if (document != null) {
                orderReturnNeispSub.setNomerOrder(document.getElementsByTagName("arg").item(0).getTextContent());

                orderReturnNeispSub.setDataOrder(document.getElementsByTagName("arg").item(1).getTextContent());

                List<Department> departments = dataManager.load(Department.class).query("select e from df$Department e where " +
                        "e.name = :name").parameter("name", document.getElementsByTagName("arg").item(2).getTextContent()).list();

                if(departments.size() > 0) {
                    orderReturnNeispSub.setDepartment(departments.get(0));

                }

                orderReturnNeispSub.setSchetKorporacii(document.getElementsByTagName("arg").item(3).getTextContent());

                orderReturnNeispSub.setBankOrganizacii(document.getElementsByTagName("arg").item(4).getTextContent());

                orderReturnNeispSub.setBikBanka(document.getElementsByTagName("arg").item(5).getTextContent());

                orderReturnNeispSub.setPodrazdelenie(document.getElementsByTagName("arg").item(6).getTextContent());

                orderReturnNeispSub.setKvartal1(document.getElementsByTagName("arg").item(7).getTextContent());

                orderReturnNeispSub.setBikPoluch(document.getElementsByTagName("arg").item(8).getTextContent());

                orderReturnNeispSub.setSchetPoluchatelya(document.getElementsByTagName("arg").item(9).getTextContent());

                orderReturnNeispSub.setDogovorSub(document.getElementsByTagName("arg").item(10).getTextContent());

                orderReturnNeispSub.setRabOrgan(document.getElementsByTagName("arg").item(11).getTextContent());

                orderReturnNeispSub.setKvartal2(document.getElementsByTagName("arg").item(12).getTextContent());

                orderReturnNeispSub.setOblastPodrazdel2(document.getElementsByTagName("arg").item(13).getTextContent());


                List<Employee> user = dataManager.load(Employee.class).query("select e from df$Employee e where " +
                        "e.email = :email").parameter("email", document.getElementsByTagName("arg").item(14).getTextContent()).list();


                orderReturnNeispSub.setOwner(user.get(0));

                orderReturnNeispSub.setSummaSub(document.getElementsByTagName("arg").item(15).getTextContent());

                //устанавливаем текущую дату
                orderReturnNeispSub.setDate(timeSource.currentTimestamp());

                //устанавливаем порядкувую нумерацию
                if (PersistenceHelper.isNew(orderReturnNeispSub))
                    orderReturnNeispSub.setNumber(String.valueOf(uniqueNumbersService1.getNextNumber("NUMBER_")));
            }


            CommitContext commitContext = new CommitContext();

            NodeList nodeList = document.getElementsByTagName("Structura");

            for (int i = 0; nodeList.getLength() > i; i++) {

                Node node = nodeList.item(i);

                if (node.getNodeType() == Node.ELEMENT_NODE) {

                    Element element = (Element) node;

                    OrderReturnNeispSub1C orderReturnNeispSub1C = dataManager.create(OrderReturnNeispSub1C.class);

                    Set<OrderReturnNeispSub1C> list  = new HashSet<>();

                    orderReturnNeispSub1C.setNomer(element.getElementsByTagName("Nomer").item(0).getTextContent());

                    orderReturnNeispSub1C.setNaimenovanieZaemchika(element.getElementsByTagName("NaimenovanieZaemchika").item(0).getTextContent());

                    orderReturnNeispSub1C.setNomerDataKreditnogoDogovora(element.getElementsByTagName("NomerDataKreditnogoDogovora").item(0).getTextContent());

                    orderReturnNeispSub1C.setSummaSubsidiyOtkloneniya(element.getElementsByTagName("SummaSubsidiyOtkloneniya").item(0).getTextContent());

                    orderReturnNeispSub1C.setDogovorSubsidirovaniya(element.getElementsByTagName("DogovorSubsidirovaniya").item(0).getTextContent());

                    orderReturnNeispSub1C.setOrderReturnNeispSub(orderReturnNeispSub);

                    list.add(orderReturnNeispSub1C);

                    orderReturnNeispSub.setOrderReturnNeispSub1C(list);

                    commitContext.addInstanceToCommit(orderReturnNeispSub1C);

                }

            }

            document.getDocumentElement().normalize();

            commitContext.addInstanceToCommit(orderReturnNeispSub);

            dataManager.commit(commitContext);




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