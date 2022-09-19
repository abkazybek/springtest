/*
 * Copyright (c) 2022 LTD Haulmont Samara. All Rights Reserved.
 * Haulmont Samara proprietary and confidential.
 * Use is subject to license terms.
 */

package com.company.thesissummer.service.parsingorders;

import com.company.thesissummer.entity.ExtEmployee;
import com.company.thesissummer.entity.OrderCenBumaga;
import com.company.thesissummer.entity.OrderPogashOb;
import com.haulmont.cuba.core.app.UniqueNumbersService;
import com.haulmont.cuba.core.global.*;
import com.haulmont.cuba.security.entity.User;
import com.haulmont.thesis.core.entity.Department;
import com.haulmont.thesis.core.entity.DocKind;
import com.haulmont.workflow.core.entity.CardProc;
import com.haulmont.workflow.core.entity.CardRole;
import com.haulmont.workflow.core.entity.Proc;
import com.haulmont.workflow.core.entity.ProcRole;
import org.springframework.stereotype.Service;
import org.w3c.dom.Document;
import org.xml.sax.SAXException;

import javax.inject.Inject;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.Base64;
import java.util.List;

@Service(OrderCennayaBumagaParseService.NAME)
public class OrderCennayaBumagaParseServiceBean implements OrderCennayaBumagaParseService {

    @Inject
    private DataManager dataManager;

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
        //открытие документа
        Document document;
        try {

            document = factory.newDocumentBuilder().parse(new ByteArrayInputStream(decodeBytes));

            //Создание классса распоряжние по переоценке
            OrderCenBumaga orderCenBumaga = dataManager.create(OrderCenBumaga.class);


            //Подгрузка вида документа
            DocKind docKindOrder = dataManager.load(DocKind.class)
                    .query("select e from df$DocKind e where e.code = :code")
                    .parameter("code", "11")
                    .view("browse")
                    .one();

            CommitContext commitContext = new CommitContext();

            //Комит вида документа
            orderCenBumaga.setDocKind(docKindOrder);

            //Парсинг документа
            if (document != null) {
                orderCenBumaga.setNomerOrder(document.getElementsByTagName("arg").item(0).getTextContent());

                orderCenBumaga.setDataOrder(document.getElementsByTagName("arg").item(1).getTextContent());

                orderCenBumaga.setContragent(document.getElementsByTagName("arg").item(2).getTextContent());

                orderCenBumaga.setDogovor(document.getElementsByTagName("arg").item(3).getTextContent());

                orderCenBumaga.setDataOpen(document.getElementsByTagName("arg").item(4).getTextContent());

                orderCenBumaga.setVid(document.getElementsByTagName("arg").item(5).getTextContent());

                orderCenBumaga.setCennayaBumaga(document.getElementsByTagName("arg").item(6).getTextContent());

                orderCenBumaga.setNin(document.getElementsByTagName("arg").item(7).getTextContent());

                orderCenBumaga.setEmitent(document.getElementsByTagName("arg").item(8).getTextContent());

                orderCenBumaga.setDataClose(document.getElementsByTagName("arg").item(9).getTextContent());

                orderCenBumaga.setBaseNach(document.getElementsByTagName("arg").item(10).getTextContent());

                orderCenBumaga.setDohodnostRepo(document.getElementsByTagName("arg").item(11).getTextContent());

                orderCenBumaga.setVoznagrazhdenie(document.getElementsByTagName("arg").item(12).getTextContent());

                orderCenBumaga.setKolvoOblig(document.getElementsByTagName("arg").item(13).getTextContent());

                orderCenBumaga.setSummaSdelki(document.getElementsByTagName("arg").item(14).getTextContent());

                List<ExtEmployee> employees = dataManager.load(ExtEmployee.class)
                        .query("select e from thesissummer$ExtEmployee e where e.email = :email")
                        .parameter("email",document.getElementsByTagName("arg").item(15).getTextContent())
                        .view("extEmployee-view")
                        .list();

                orderCenBumaga.setOwner(employees.get(0));

                //устанавливаем текущую дату
                orderCenBumaga.setDate(timeSource.currentTimestamp());

                //устанавливаем порядкувую нумерацию
                if (PersistenceHelper.isNew(orderCenBumaga))
                    orderCenBumaga.setNumber(String.valueOf(uniqueNumbersService1.getNextNumber("NUMBER_")));


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

                //Подгрузка процесса согласовнаия для карточки распорядение
                CardProc cardProc = dataManager.create(CardProc.class);

                cardProc.setCard(orderCenBumaga);

                cardProc.setProc(proc);

                commitContext.addInstanceToCommit(cardProc);


                User user = userSessionSource.getUserSession().getCurrentOrSubstitutedUser();

                //роль инициатора в карточке
                CardRole cardInitiatior = dataManager.create(CardRole.class);

                cardInitiatior.setCode("Initiator");

                cardInitiatior.setCard(orderCenBumaga);

                cardInitiatior.setProcRole(initiatorRole);

                cardInitiatior.setUser(user);

                commitContext.addInstanceToCommit(cardInitiatior);

                List<User> approver = dataManager.load(User.class).query("select e from sec$User e where " +
                        "e.email = :email").parameter("email", document.getElementsByTagName("arg").item(16).getTextContent()).list();


                //роль утверждающего в карточке
                CardRole cardApprover = dataManager.create(CardRole.class);

                cardApprover.setCode("Approver");

                cardApprover.setCard(orderCenBumaga);

                cardApprover.setProcRole(approverRole);

                cardApprover.setUser(approver.get(0));

                commitContext.addInstanceToCommit(cardApprover);


                List<User> endorsement = dataManager.load(User.class).query("select e from sec$User e where " +
                        "e.email = :email").parameter("email", document.getElementsByTagName("arg").item(17).getTextContent()).list();

                CardRole cardEnsorsed = dataManager.create(CardRole.class);

                cardEnsorsed.setCode("Endorsement");

                cardEnsorsed.setCard(orderCenBumaga);

                cardEnsorsed.setProcRole(endorseRole);

                cardEnsorsed.setUser(endorsement.get(0));

                commitContext.addInstanceToCommit(cardEnsorsed);

            }

            document.getDocumentElement().normalize();

            commitContext.addInstanceToCommit(orderCenBumaga);

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