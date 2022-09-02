/*
 * Copyright (c) 2022 LTD Haulmont Samara. All Rights Reserved.
 * Haulmont Samara proprietary and confidential.
 * Use is subject to license terms.
 */

package com.company.thesissummer.service.parsingorders;

import com.company.thesissummer.entity.OrderPosting;
import com.company.thesissummer.entity.OrderTranfer;
import com.company.thesissummer.entity.table1c.OrderPosting1C;
import com.company.thesissummer.entity.table1c.OrderTranfer1C;
import com.haulmont.cuba.core.global.CommitContext;
import com.haulmont.cuba.core.global.DataManager;
import com.haulmont.cuba.core.global.UserSessionSource;
import com.haulmont.cuba.security.entity.User;
import com.haulmont.thesis.core.entity.Department;
import com.haulmont.thesis.core.entity.DocKind;
import com.haulmont.thesis.core.entity.Employee;
import com.haulmont.workflow.core.entity.CardProc;
import com.haulmont.workflow.core.entity.CardRole;
import com.haulmont.workflow.core.entity.Proc;
import com.haulmont.workflow.core.entity.ProcRole;
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

@Service(OrderTransferParseService.NAME)
public class OrderTransferParseServiceBean implements OrderTransferParseService {
    @Inject
    private DataManager dataManager;


    DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();


    @Inject
    protected UserSessionSource userSessionSource;


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

            CommitContext commitContext = new CommitContext();

            //Создание классса РАспоряжние по переоценке
            OrderTranfer orderTranfer = dataManager.create(OrderTranfer.class);

            //Подгрузка вида документа
            DocKind docKindOrder = dataManager.load(DocKind.class)
                    .query("select e from df$DocKind e where e.code = :code")
                    .parameter("code", "5")
                    .view("browse")
                    .one();

            //Комит вида документа
            orderTranfer.setDocKind(docKindOrder);

            //Парсинг документа
            if (document != null) {
                orderTranfer.setNumber(document.getElementsByTagName("arg").item(0).getTextContent());

                orderTranfer.setDataOrder(document.getElementsByTagName("arg").item(1).getTextContent());

                List<Department> departments = dataManager.load(Department.class).query("select e from df$Department e where " +
                        "e.name = :name").parameter("name", document.getElementsByTagName("arg").item(2).getTextContent()).list();


                orderTranfer.setDepartment(departments.get(0));

                orderTranfer.setNomerOrder(document.getElementsByTagName("arg").item(3).getTextContent());

                orderTranfer.setDataDogovorSub(document.getElementsByTagName("arg").item(4).getTextContent());

                orderTranfer.setRabOperator(document.getElementsByTagName("arg").item(5).getTextContent());

                orderTranfer.setKvartal1(document.getElementsByTagName("arg").item(6).getTextContent());

                orderTranfer.setSchetKorp(document.getElementsByTagName("arg").item(7).getTextContent());

                orderTranfer.setBankOrg(document.getElementsByTagName("arg").item(8).getTextContent());

                orderTranfer.setSchetPol(document.getElementsByTagName("arg").item(9).getTextContent());

                orderTranfer.setSchetPol(document.getElementsByTagName("arg").item(10).getTextContent());

                List<Employee> employees = dataManager.load(Employee.class).query("select e from df$Employee e where " +
                        "e.email = :email").parameter("email", document.getElementsByTagName("arg").item(11).getTextContent()).list();

                orderTranfer.setOwner(employees.get(0));

                orderTranfer.setSummaSub(document.getElementsByTagName("arg").item(12).getTextContent());


            }


            OrderTranfer1C orderTranfer1C = dataManager.create(OrderTranfer1C.class);

            NodeList nodeList = document.getElementsByTagName("Structura");

            Set<OrderTranfer1C> list  = new HashSet<>();

            for (int i = 0; nodeList.getLength() > i; i++) {

                Node node = nodeList.item(i);

                if (node.getNodeType() == Node.ELEMENT_NODE) {

                    Element element = (Element) node;

                    orderTranfer1C.setNumber(element.getElementsByTagName("Nomer").item(0).getTextContent());

                    orderTranfer1C.setZaemchik(element.getElementsByTagName("NaimenovanieZaemchika").item(0).getTextContent());

                    orderTranfer1C.setIin(element.getElementsByTagName("IINBIN").item(0).getTextContent());

                    orderTranfer1C.setNomerandDataKreditnogoDogovora(element.getElementsByTagName("NomerDataKreditnogoDogovora").item(0).getTextContent());

                    orderTranfer1C.setSummaSubsidiiKreditnogoDogovora(element.getElementsByTagName("SummaSubsidiy").item(0).getTextContent());

                    orderTranfer1C.setOrderTranfer(orderTranfer);

                    list.add(orderTranfer1C);

                    orderTranfer.setOrderTranfer1C(list);

                    commitContext.addInstanceToCommit(orderTranfer1C);

                }

            }


            commitContext.addInstanceToCommit(orderTranfer);

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