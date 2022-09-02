/*
 * Copyright (c) 2022 LTD Haulmont Samara. All Rights Reserved.
 * Haulmont Samara proprietary and confidential.
 * Use is subject to license terms.
 */

package com.company.thesissummer.service.parsingorders;

import com.company.thesissummer.entity.ExtEmployee;
import com.company.thesissummer.entity.OrderLoan;
import com.company.thesissummer.entity.OrderPogashOb;
import com.haulmont.cuba.core.global.CommitContext;
import com.haulmont.cuba.core.global.DataManager;
import com.haulmont.cuba.core.global.UserSessionSource;
import com.haulmont.cuba.security.entity.User;
import com.haulmont.thesis.core.entity.Department;
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

@Service(OrderPogObParseService.NAME)
public class OrderPogObParseServiceBean implements OrderPogObParseService {

    @Inject
    private DataManager dataManager;


    @Inject
    protected UserSessionSource userSessionSource;

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
            OrderPogashOb orderPogashOb = dataManager.create(OrderPogashOb.class);


            //Подгрузка вида документа
            DocKind docKindOrder = dataManager.load(DocKind.class)
                    .query("select e from df$DocKind e where e.code = :code")
                    .parameter("code", "10")
                    .view("browse")
                    .one();

            CommitContext commitContext = new CommitContext();

            //Комит вида документа
            orderPogashOb.setDocKind(docKindOrder);

            //Парсинг документа
            if (document != null) {
                orderPogashOb.setNomerOrder(document.getElementsByTagName("arg").item(0).getTextContent());

                orderPogashOb.setDataOrder(document.getElementsByTagName("arg").item(1).getTextContent());

                List<Department> departments = dataManager.load(Department.class).query("select e from df$Department e where " +
                        "e.name = :name").parameter("name", document.getElementsByTagName("arg").item(2).getTextContent()).list();

                orderPogashOb.setDepartment(departments.get(0));

                orderPogashOb.setContragent(document.getElementsByTagName("arg").item(3).getTextContent());

                orderPogashOb.setDogovor(document.getElementsByTagName("arg").item(4).getTextContent());

                orderPogashOb.setDataPerech(document.getElementsByTagName("arg").item(5).getTextContent());

                orderPogashOb.setVidCzaimorachetov(document.getElementsByTagName("arg").item(6).getTextContent());

                orderPogashOb.setPoluchatel(document.getElementsByTagName("arg").item(7).getTextContent());

                orderPogashOb.setBankovsckiySchetPlatelshika(document.getElementsByTagName("arg").item(8).getTextContent());

                orderPogashOb.setDogovorPlat(document.getElementsByTagName("arg").item(9).getTextContent());

                orderPogashOb.setSummaKOplate(document.getElementsByTagName("arg").item(10).getTextContent());

                List<ExtEmployee> employees = dataManager.load(ExtEmployee.class)
                        .query("select e from thesissummer$ExtEmployee e where e.email = :email")
                        .parameter("email", document.getElementsByTagName("arg").item(11).getTextContent())
                        .view("extEmployee-view")
                        .list();

                orderPogashOb.setOwner(employees.get(0));

                orderPogashOb.setIik(document.getElementsByTagName("arg").item(12).getTextContent());

                orderPogashOb.setBik(document.getElementsByTagName("arg").item(13).getTextContent());

                orderPogashOb.setBankBenefeciar(document.getElementsByTagName("arg").item(14).getTextContent());

                orderPogashOb.setBin(document.getElementsByTagName("arg").item(15).getTextContent());

                orderPogashOb.setKbe(document.getElementsByTagName("arg").item(16).getTextContent());

                orderPogashOb.setKnp(document.getElementsByTagName("arg").item(17).getTextContent());

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

                cardProc.setCard(orderPogashOb);

                cardProc.setProc(proc);

                commitContext.addInstanceToCommit(cardProc);

                User user = userSessionSource.getUserSession().getCurrentOrSubstitutedUser();

                //роль инициатора в карточке
                CardRole cardInitiatior = dataManager.create(CardRole.class);

                cardInitiatior.setCode("Initiator");

                cardInitiatior.setCard(orderPogashOb);

                cardInitiatior.setProcRole(initiatorRole);

                cardInitiatior.setUser(user);

                commitContext.addInstanceToCommit(cardInitiatior);

                List<User> approver = dataManager.load(User.class).query("select e from sec$User e where " +
                        "e.email = :email").parameter("email", document.getElementsByTagName("arg").item(18).getTextContent()).list();


                //роль утверждающего в карточке
                CardRole cardApprover = dataManager.create(CardRole.class);

                cardApprover.setCode("Approver");

                cardApprover.setCard(orderPogashOb);

                cardApprover.setProcRole(approverRole);

                cardApprover.setUser(approver.get(0));

                commitContext.addInstanceToCommit(cardApprover);

                CardRole cardEnsorsed = dataManager.create(CardRole.class);

                cardEnsorsed.setCode("Endorsement");

                cardEnsorsed.setCard(orderPogashOb);

                cardEnsorsed.setProcRole(endorseRole);

                List<User> endorsement = dataManager.load(User.class).query("select e from sec$User e where " +
                                "e.email = :email").parameter("email", document.getElementsByTagName("arg").item(19).getTextContent()).list();

                cardEnsorsed.setUser(endorsement.get(0));

                commitContext.addInstanceToCommit(cardEnsorsed);


            }


            document.getDocumentElement().normalize();

            commitContext.addInstanceToCommit(orderPogashOb);

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