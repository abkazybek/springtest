/*
 * Copyright (c) 2022 LTD Haulmont Samara. All Rights Reserved.
 * Haulmont Samara proprietary and confidential.
 * Use is subject to license terms.
 */

package com.company.thesissummer.service.parsingorders;

import com.company.thesissummer.entity.OrderPosting;
import com.company.thesissummer.entity.OrderRembursement;
import com.company.thesissummer.entity.table1c.OrderPosting1C;
import com.company.thesissummer.entity.table1c.OrderRembursement1c;
import com.haulmont.cuba.core.app.UniqueNumbersService;
import com.haulmont.cuba.core.global.*;
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
import java.util.*;

@Service(OrderPostingParseService.NAME)
public class OrderPostingParseServiceBean implements OrderPostingParseService {
    @Inject
    private DataManager dataManager;


    DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();


    @Inject
    protected UserSessionSource userSessionSource;

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

            CommitContext commitContext = new CommitContext();

            //Создание классса РАспоряжние по переоценке
            OrderPosting orderPosting = dataManager.create(OrderPosting.class);

            //Подгрузка вида документа
            DocKind docKindOrder = dataManager.load(DocKind.class)
                    .query("select e from df$DocKind e where e.code = :code")
                    .parameter("code", "6")
                    .view("browse")
                    .one();

            //Комит вида документа
            orderPosting.setDocKind(docKindOrder);

            //Парсинг документа
            if (document != null) {
                orderPosting.setNomerOrder(document.getElementsByTagName("arg").item(0).getTextContent());

                orderPosting.setDataOrder(document.getElementsByTagName("arg").item(1).getTextContent());

                orderPosting.setNomerProtocola(document.getElementsByTagName("arg").item(2).getTextContent());

                orderPosting.setPodrazdelenie(document.getElementsByTagName("arg").item(3).getTextContent());

                orderPosting.setDogovorRamochSogl(document.getElementsByTagName("arg").item(4).getTextContent());

                orderPosting.setZaemshik(document.getElementsByTagName("arg").item(5).getTextContent());

                orderPosting.setKonechniyZaemchik(document.getElementsByTagName("arg").item(6).getTextContent());


                List<Employee> employees = dataManager.load(Employee.class).query("select e from df$Employee e where " +
                        "e.email = :email").parameter("email", document.getElementsByTagName("arg").item(7).getTextContent()).list();

                orderPosting.setOwner(employees.get(0));


                //устанавливаем текущую дату
                orderPosting.setDate(timeSource.currentTimestamp());

                //устанавливаем порядкувую нумерацию
                if (PersistenceHelper.isNew(orderPosting))
                    orderPosting.setNumber(String.valueOf(uniqueNumbersService1.getNextNumber("NUMBER_")));

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

                cardProc.setCard(orderPosting);

                cardProc.setProc(proc);

                commitContext.addInstanceToCommit(cardProc);

                User user = userSessionSource.getUserSession().getCurrentOrSubstitutedUser();

                //роль инициатора в карточке
                CardRole cardInitiatior = dataManager.create(CardRole.class);

                cardInitiatior.setCode("Initiator");

                cardInitiatior.setCard(orderPosting);

                cardInitiatior.setProcRole(initiatorRole);

                cardInitiatior.setUser(user);

                commitContext.addInstanceToCommit(cardInitiatior);

                List<User> approver = dataManager.load(User.class).query("select e from sec$User e where " +
                        "e.email = :email").parameter("email", document.getElementsByTagName("arg").item(8).getTextContent()).list();

                //роль утверждающего в карточке
                CardRole cardApprover = dataManager.create(CardRole.class);

                cardApprover.setCode("Approver");

                cardApprover.setCard(orderPosting);

                cardApprover.setProcRole(approverRole);

                cardApprover.setUser(approver.get(0));

                commitContext.addInstanceToCommit(cardApprover);

                orderPosting.setObshayaSumma(document.getElementsByTagName("arg").item(9).getTextContent());

                //роль согласующего в карточке
                NodeList nodeList2 = document.getElementsByTagName("Structura2");

                for (int i = 0; nodeList2.getLength() > i; i++) {

                    Node node = nodeList2.item(i);

                    if (node.getNodeType() == Node.ELEMENT_NODE) {

                        Element element = (Element) node;

                        CardRole cardEnsorsed = dataManager.create(CardRole.class);

                        cardEnsorsed.setCode("Endorsement");

                        cardEnsorsed.setCard(orderPosting);

                        cardEnsorsed.setProcRole(endorseRole);

                        List<User> endorsement = dataManager.load(User.class).query("select e from sec$User e where " +
                                "e.email = :email").parameter("email", element.getElementsByTagName("Soglasovanti").item(0).getTextContent()).list();

                        cardEnsorsed.setUser(endorsement.get(0));

                        commitContext.addInstanceToCommit(cardEnsorsed);

                    }

                }

            }


            OrderPosting1C orderPosting1C = dataManager.create(OrderPosting1C.class);

            NodeList nodeList = document.getElementsByTagName("Structura");

            Set<OrderPosting1C> list  = new HashSet<>();

            for (int i = 0; nodeList.getLength() > i; i++) {

                Node node = nodeList.item(i);

                if (node.getNodeType() == Node.ELEMENT_NODE) {

                    Element element = (Element) node;

                    orderPosting1C.setNumber(element.getElementsByTagName("Nomer").item(0).getTextContent());

                    orderPosting1C.setNameofpledger(element.getElementsByTagName("Zalogodatel").item(0).getTextContent());

                    orderPosting1C.setDogovorwithcollateral(element.getElementsByTagName("DogovorZalogaGarantii").item(0).getTextContent());

                    orderPosting1C.setObespecheniye(element.getElementsByTagName("Obespechenie").item(0).getTextContent());

                    orderPosting1C.setZalogStoimost(element.getElementsByTagName("ZalogovayStoimost").item(0).getTextContent());

                    orderPosting1C.setDocumentUsl(element.getElementsByTagName("DokumentUsloviy").item(0).getTextContent());

                    orderPosting1C.setOrderPosting(orderPosting);

                    list.add(orderPosting1C);

                    orderPosting.setOrderPosting1C(list);

                    commitContext.addInstanceToCommit(orderPosting1C);

                }

            }

            commitContext.addInstanceToCommit(orderPosting);

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