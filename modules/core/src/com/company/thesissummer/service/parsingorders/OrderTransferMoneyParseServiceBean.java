/*
 * Copyright (c) 2022 LTD Haulmont Samara. All Rights Reserved.
 * Haulmont Samara proprietary and confidential.
 * Use is subject to license terms.
 */

package com.company.thesissummer.service.parsingorders;

import com.company.thesissummer.entity.ExtEmployee;
import com.company.thesissummer.entity.OrderLoan;
import com.company.thesissummer.entity.OrderTranferMoney;
import com.haulmont.cuba.core.app.UniqueNumbersService;
import com.haulmont.cuba.core.global.*;
import com.haulmont.cuba.security.entity.User;
import com.haulmont.thesis.core.entity.DocKind;
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
import java.util.List;

@Service(OrderTransferMoneyParseService.NAME)
public class OrderTransferMoneyParseServiceBean implements OrderTransferMoneyParseService {
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
            OrderTranferMoney orderTranferMoney = dataManager.create(OrderTranferMoney.class);


            //Подгрузка вида документа
            DocKind docKindOrder = dataManager.load(DocKind.class)
                    .query("select e from df$DocKind e where e.code = :code")
                    .parameter("code", "9")
                    .view("browse")
                    .one();

            CommitContext commitContext = new CommitContext();

            //Комит вида документа
            orderTranferMoney.setDocKind(docKindOrder);

            //Парсинг документа
            if (document != null) {
                orderTranferMoney.setNomerOrder(document.getElementsByTagName("arg").item(0).getTextContent());

                orderTranferMoney.setDataOrder(document.getElementsByTagName("arg").item(1).getTextContent());

                orderTranferMoney.setPoluchatel(document.getElementsByTagName("arg").item(2).getTextContent());

                orderTranferMoney.setSummaDlyAPerech(document.getElementsByTagName("arg").item(3).getTextContent());

                orderTranferMoney.setRasChetZach(document.getElementsByTagName("arg").item(4).getTextContent());

                orderTranferMoney.setRaschetSchet(document.getElementsByTagName("arg").item(5).getTextContent());


                List<ExtEmployee> employees = dataManager.load(ExtEmployee.class)
                        .query("select e from DF_EMPLOYEE e where e.email = :email")
                        .parameter("email", document.getElementsByTagName("arg").item(6).getTextContent())
                        .view("browse")
                        .list();

                if(employees.size() != 0) {
                    orderTranferMoney.setOwner(employees.get(0));
                }

                //устанавливаем текущую дату
                orderTranferMoney.setDate(timeSource.currentTimestamp());

                //устанавливаем порядкувую нумерацию
                if (PersistenceHelper.isNew(orderTranferMoney)) {
                    orderTranferMoney.setNumber(String.valueOf(uniqueNumbersService1.getNextNumber("NUMBER_")));
                }

                //Подгрузка процесса Согласования
                Proc proc = dataManager.load(Proc.class)
                        .query("select e from wf$Proc e where e.code = :code")
                        .parameter("code", "EndorseAKK")
                        .view("browse")
                        .one();

                //подтягивание роли инициатора
                ProcRole initiatorRole = dataManager.load(ProcRole.class)
                        .query("select e from wf$ProcRole e where e.code = 'Инициатор' and e.proc.id = :procId")
                        .parameter("procId", proc.getId())
                        .view("browse")
                        .one();

                //подтягивание роли согласующего
                ProcRole endorseRole = dataManager.load(ProcRole.class)
                        .query("select e from wf$ProcRole e where e.code = 'Согласующий' and e.proc.id = :procId")
                        .parameter("procId", proc.getId())
                        .view("browse")
                        .one();

                //подтягивание роли подписывающего
                ProcRole approverRole = dataManager.load(ProcRole.class)
                        .query("select e from wf$ProcRole e where e.code = 'Утверждающий' and e.proc.id = :procId")
                        .parameter("procId", proc.getId())
                        .view("browse")
                        .one();

                //Подгрузка процесса согласовнаия для карточки распорядение
                CardProc cardProc = dataManager.create(CardProc.class);

                cardProc.setCard(orderTranferMoney);

                cardProc.setProc(proc);

                commitContext.addInstanceToCommit(cardProc);

                User user = userSessionSource.getUserSession().getCurrentOrSubstitutedUser();

                //роль инициатора в карточке
                CardRole cardInitiatior = dataManager.create(CardRole.class);

                cardInitiatior.setCode("Инициатор");

                cardInitiatior.setCard(orderTranferMoney);

                cardInitiatior.setProcRole(initiatorRole);

                cardInitiatior.setUser(user);

                commitContext.addInstanceToCommit(cardInitiatior);

                List<User> approver = dataManager.load(User.class).query("select e from sec$User e where " +
                        "e.email = :email").parameter("email", document.getElementsByTagName("arg").item(8).getTextContent()).list();


                //роль утверждающего в карточке
                CardRole cardApprover = dataManager.create(CardRole.class);

                cardApprover.setCode("Утверждающий");

                cardApprover.setCard(orderTranferMoney);

                cardApprover.setProcRole(approverRole);

                cardApprover.setUser(approver.get(0));

                commitContext.addInstanceToCommit(cardApprover);

                CardRole cardEnsorsed = dataManager.create(CardRole.class);

                cardEnsorsed.setCode("Согласующий");

                cardEnsorsed.setCard(orderTranferMoney);

                cardEnsorsed.setSortOrder(1);

                cardEnsorsed.setProcRole(endorseRole);

                List<User> endorsement = dataManager.load(User.class).query("select e from sec$User e where " +
                        "e.email = :email").parameter("email", document.getElementsByTagName("arg").item(7).getTextContent()).list();

                cardEnsorsed.setUser(endorsement.get(0));

                commitContext.addInstanceToCommit(cardEnsorsed);

            }


            document.getDocumentElement().normalize();

            commitContext.addInstanceToCommit(orderTranferMoney);

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