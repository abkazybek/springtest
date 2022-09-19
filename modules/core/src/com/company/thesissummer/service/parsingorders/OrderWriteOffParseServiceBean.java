/*
 * Copyright (c) 2022 LTD Haulmont Samara. All Rights Reserved.
 * Haulmont Samara proprietary and confidential.
 * Use is subject to license terms.
 */

package com.company.thesissummer.service.parsingorders;

import com.company.thesissummer.entity.OrderWriteOff;
import com.company.thesissummer.entity.table1c.OrderTranfer1C;
import com.company.thesissummer.entity.table1c.OrderWriteOff1C;
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

@Service(OrderWriteOffParseService.NAME)
public class OrderWriteOffParseServiceBean implements OrderWriteOffParseService {
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
            OrderWriteOff orderWriteOff = dataManager.create(OrderWriteOff.class);

            //Подгрузка вида документа
            DocKind docKindOrder = dataManager.load(DocKind.class)
                    .query("select e from df$DocKind e where e.code = :code")
                    .parameter("code", "7")
                    .view("browse")
                    .one();

            //Комит вида документа
            orderWriteOff.setDocKind(docKindOrder);

            //Парсинг документа
            if (document != null) {
                orderWriteOff.setNumber(document.getElementsByTagName("arg").item(0).getTextContent());

                orderWriteOff.setDataOrder(document.getElementsByTagName("arg").item(1).getTextContent());

                orderWriteOff.setNomerProtocola(document.getElementsByTagName("arg").item(2).getTextContent());


                List<Department> departments = dataManager.load(Department.class).query("select e from df$Department e where " +
                        "e.name = :name").parameter("name", document.getElementsByTagName("arg").item(3).getTextContent()).list();

                if (departments.size() > 0) {

                    orderWriteOff.setDepartment(departments.get(0));

                }
                orderWriteOff.setDogovorRamoch(document.getElementsByTagName("arg").item(4).getTextContent());

                orderWriteOff.setZaemchik(document.getElementsByTagName("arg").item(5).getTextContent());

                orderWriteOff.setKonechniyZaemchik(document.getElementsByTagName("arg").item(6).getTextContent());

                orderWriteOff.setOsnovaniyeSniyatiaObespech(document.getElementsByTagName("arg").item(7).getTextContent());

                List<Employee> employees = dataManager.load(Employee.class).query("select e from df$Employee e where " +
                        "e.email = :email").parameter("email", document.getElementsByTagName("arg").item(8).getTextContent()).list();

                orderWriteOff.setOwner(employees.get(0));

                orderWriteOff.setObshayaSummaZalogObespech(document.getElementsByTagName("arg").item(9).getTextContent());


            }


            //подтягиваю таблицу из xml
            OrderWriteOff1C orderWriteOff1C = dataManager.create(OrderWriteOff1C.class);

            NodeList nodeList = document.getElementsByTagName("Structura");

            Set<OrderWriteOff1C> list  = new HashSet<>();

            for (int i = 0; nodeList.getLength() > i; i++) {

                Node node = nodeList.item(i);

                if (node.getNodeType() == Node.ELEMENT_NODE) {

                    Element element = (Element) node;

                    orderWriteOff1C.setNumber(element.getElementsByTagName("Nomer").item(0).getTextContent());

                    orderWriteOff1C.setZalogStoimost(element.getElementsByTagName("Zalogodatel").item(0).getTextContent());

                    orderWriteOff1C.setDogovorSvyazSObespech(element.getElementsByTagName("DogovorZaloga").item(0).getTextContent());

                    orderWriteOff1C.setObespecheniye(element.getElementsByTagName("Obespechenie").item(0).getTextContent());

                    orderWriteOff1C.setZalogStoimost(element.getElementsByTagName("ZalogovayaStoimost").item(0).getTextContent());

                    orderWriteOff1C.setDocumentUsloviy(element.getElementsByTagName("DocumentUsloviy").item(0).getTextContent());

                    orderWriteOff1C.setOrderWriteOff(orderWriteOff);

                    list.add(orderWriteOff1C);

                    orderWriteOff.setOrderWriteOff1C(list);

                    commitContext.addInstanceToCommit(orderWriteOff1C);

                }

            }

            //Подгрузка процесса Согласования
            Proc proc = dataManager.load(Proc.class)
                    .query("select e from wf$Proc e where e.code = :code")
                    .parameter("code", "Endorsement")
                    .view("browse")
                    .one();

            ProcRole initiatorRole = dataManager.load(ProcRole.class)
                    .query("select e from wf$ProcRole e where e.code = 'Initiator' and e.proc.id = :procId")
                    .parameter("procId", proc.getId())
                    .view("browse")
                    .one();

            ProcRole endorseRole = dataManager.load(ProcRole.class)
                    .query("select e from wf$ProcRole e where e.code = 'Endorsement' and e.proc.id = :procId")
                    .parameter("procId", proc.getId())
                    .view("browse")
                    .one();

            ProcRole approverRole = dataManager.load(ProcRole.class)
                    .query("select e from wf$ProcRole e where e.code = 'Approver' and e.proc.id = :procId")
                    .parameter("procId", proc.getId())
                    .view("browse")
                    .one();

            CardProc cardProc = dataManager.create(CardProc.class);

            cardProc.setCard(orderWriteOff);

            cardProc.setProc(proc);

            commitContext.addInstanceToCommit(cardProc);

            User user = userSessionSource.getUserSession().getCurrentOrSubstitutedUser();

            //роль инициатора в карточке
            CardRole cardInitiatior = dataManager.create(CardRole.class);

            cardInitiatior.setCode("Initiator");

            cardInitiatior.setCard(orderWriteOff);

            cardInitiatior.setProcRole(initiatorRole);

            cardInitiatior.setUser(user);

            commitContext.addInstanceToCommit(cardInitiatior);


            //роль согласующего в карточке
            NodeList nodeList2 = document.getElementsByTagName("Structura2");

            for (int i = 0; nodeList2.getLength() > i; i++) {

                Node node = nodeList2.item(i);

                if (node.getNodeType() == Node.ELEMENT_NODE) {

                    Element element = (Element) node;

                    CardRole cardEnsorsed = dataManager.create(CardRole.class);

                    cardEnsorsed.setCode("Endorsement");

                    cardEnsorsed.setCard(orderWriteOff);

                    cardEnsorsed.setProcRole(endorseRole);

                    cardEnsorsed.setSortOrder(1);

                    List<User> endorsement = dataManager.load(User.class).query("select e from sec$User e where " +
                            "e.email = :email").parameter("email", element.getElementsByTagName("Soglasovanti").item(0).getTextContent()).list();

                    cardEnsorsed.setUser(endorsement.get(0));

                    commitContext.addInstanceToCommit(cardEnsorsed);

                }

            }


            commitContext.addInstanceToCommit(orderWriteOff);

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