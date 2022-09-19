/*
 * Copyright (c) 2022 LTD Haulmont Samara. All Rights Reserved.
 * Haulmont Samara proprietary and confidential.
 * Use is subject to license terms.
 */

package com.company.thesissummer.service.parsingorders;

import com.company.thesissummer.entity.OrderLoan;
import com.company.thesissummer.entity.OrderLoanSI;
import com.haulmont.cuba.core.app.UniqueNumbersService;
import com.haulmont.cuba.core.global.*;
import com.haulmont.cuba.security.entity.User;
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
import java.io.File;
import java.io.IOException;
import java.util.Base64;
import java.util.List;

@Service(OrderLoanSiParsService.NAME)
public class OrderLoanSiParsServiceBean implements OrderLoanSiParsService {

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

        byte[] decodeBytes = Base64.getDecoder().decode(xml);

        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = null;
        //InputSource is = new InputSource(new StringReader(decodeBytes.);
        //InputSource is = new InputSource(new StringReader(decodeBytes.);

        //открытие документа
        Document document;
        try {

            document = factory.newDocumentBuilder().parse(new ByteArrayInputStream(decodeBytes));


            CommitContext commitContext = new CommitContext();


            //Создание классса РАспоряжние по переоценке
            OrderLoanSI orderLoanSI = dataManager.create(OrderLoanSI.class);


            //Подгрузка вида документа
            DocKind docKindOrder = dataManager.load(DocKind.class)
                    .query("select e from df$DocKind e where e.code = :code")
                    .parameter("code", "2")
                    .view("browse")
                    .one();

            //Комит вида документа
            orderLoanSI.setDocKind(docKindOrder);

            //Парсинг документа
            if (document != null) {
                orderLoanSI.setNomerRasp(document.getElementsByTagName("arg").item(0).getTextContent());

                orderLoanSI.setDataRasp(document.getElementsByTagName("arg").item(1).getTextContent());

                orderLoanSI.setPodrazdelenie(document.getElementsByTagName("arg").item(2).getTextContent());

                orderLoanSI.setOsnovanie(document.getElementsByTagName("arg").item(3).getTextContent());

                List<Employee> employees = dataManager.load(Employee.class).query("select e from df$Employee e where " +
                        "e.email = :email").parameter("email", document.getElementsByTagName("arg").item(4).getTextContent()).list();


                orderLoanSI.setOwner(employees.get(0));

                //устанавливаем текущую дату
                orderLoanSI.setDate(timeSource.currentTimestamp());

                //устанавливаем порядкувую нумерацию
                if (PersistenceHelper.isNew(orderLoanSI))
                    orderLoanSI.setNumber(String.valueOf(uniqueNumbersService1.getNextNumber("NUMBER_")));

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

                cardProc.setCard(orderLoanSI);

                cardProc.setProc(proc);

                commitContext.addInstanceToCommit(cardProc);

                User user = userSessionSource.getUserSession().getCurrentOrSubstitutedUser();

                //роль инициатора в карточке
                CardRole cardInitiatior = dataManager.create(CardRole.class);

                cardInitiatior.setCode("Initiator");

                cardInitiatior.setCard(orderLoanSI);

                cardInitiatior.setProcRole(initiatorRole);

                cardInitiatior.setUser(user);

                commitContext.addInstanceToCommit(cardInitiatior);

                List<User> approver = dataManager.load(User.class).query("select e from sec$User e where " +
                        "e.email = :email").parameter("email", document.getElementsByTagName("arg").item(5).getTextContent()).list();

                //роль утверждающего в карточке
                CardRole cardApprover = dataManager.create(CardRole.class);

                cardApprover.setCode("Approver");

                cardApprover.setCard(orderLoanSI);

                cardApprover.setProcRole(approverRole);

                cardApprover.setUser(approver.get(0));

                commitContext.addInstanceToCommit(cardApprover);

                orderLoanSI.setKontragent(document.getElementsByTagName("arg").item(6).getTextContent());

                orderLoanSI.setIin(document.getElementsByTagName("arg").item(7).getTextContent());

                //этого поля нет
                //orderLoanSI.setNomerData(document.getElementsByTagName("arg").item(8).getTextContent());

                orderLoanSI.setSumma(document.getElementsByTagName("arg").item(9).getTextContent());

                orderLoanSI.setKonechZaemchik(document.getElementsByTagName("arg").item(10).getTextContent());

                //поле называется ИИН конечного заемщика
                orderLoanSI.setBin(document.getElementsByTagName("arg").item(11).getTextContent());

                orderLoanSI.setSrokKredita(document.getElementsByTagName("arg").item(12).getTextContent());

                orderLoanSI.setStavkaVoz(document.getElementsByTagName("arg").item(13).getTextContent());

                orderLoanSI.setIik(document.getElementsByTagName("arg").item(14).getTextContent());

                orderLoanSI.setBank(document.getElementsByTagName("arg").item(15).getTextContent());

                orderLoanSI.setBik(document.getElementsByTagName("arg").item(16).getTextContent());

                //роль согласующего в карточке
                NodeList nodeList = document.getElementsByTagName("otvLico");

                for (int i = 0; nodeList.getLength() > i; i++) {

                    Node node = nodeList.item(i);

                    if (node.getNodeType() == Node.ELEMENT_NODE) {

                        Element element = (Element) node;

                        CardRole cardEnsorsed = dataManager.create(CardRole.class);

                        cardEnsorsed.setCode("Endorsement");

                        cardEnsorsed.setCard(orderLoanSI);

                        cardEnsorsed.setProcRole(endorseRole);

                        cardEnsorsed.setSortOrder(Integer.valueOf(element.getElementsByTagName("Nomer").item(0).getTextContent()));

                        List<User> endorsement = dataManager.load(User.class).query("select e from sec$User e where " +
                                "e.email = :email").parameter("email", element.getElementsByTagName("OtvetstvennoeLico").item(0).getTextContent()).list();

                        cardEnsorsed.setUser(endorsement.get(0));

                        commitContext.addInstanceToCommit(cardEnsorsed);

                    }

                }

            }

            document.getDocumentElement().normalize();

            commitContext.addInstanceToCommit(orderLoanSI);

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