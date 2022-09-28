/*
 * Copyright (c) 2022 LTD Haulmont Samara. All Rights Reserved.
 * Haulmont Samara proprietary and confidential.
 * Use is subject to license terms.
 */

package com.company.thesissummer.service.parsingorders;

import com.company.thesissummer.entity.ExtEmployee;
import com.company.thesissummer.entity.OrderLoan;
import com.haulmont.chile.core.datatypes.Datatypes;
import com.haulmont.cuba.core.app.UniqueNumbersService;
import com.haulmont.cuba.core.global.*;
import com.haulmont.cuba.security.entity.User;
import com.haulmont.thesis.core.entity.Doc;
import com.haulmont.thesis.core.entity.DocKind;
import com.haulmont.thesis.core.entity.Employee;
import com.haulmont.workflow.core.entity.CardProc;
import com.haulmont.workflow.core.entity.CardRole;
import com.haulmont.workflow.core.entity.Proc;
import com.haulmont.workflow.core.entity.ProcRole;
import org.apache.commons.lang.StringUtils;
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
import java.text.SimpleDateFormat;
import java.util.Base64;
import java.util.List;
import java.util.Locale;


@Service(OrderLoanParsService.NAME)
public class OrderLoanParsServiceBean implements OrderLoanParsService {


    @Inject
    private DataManager dataManager;


    @Inject
    protected UserSessionSource userSessionSource;

    @Inject
    protected TimeSource timeSource;

    @Inject
    public UniqueNumbersService uniqueNumbersService1;

    //1C программист отправляет xml в base64
    @Override
    public String saveXML(String xml) {

        //переводим в массив байтов base64
        byte[] decodeBytes = Base64.getDecoder().decode(xml);

        //создаем document по которому настроим парсинг
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = null;
        //InputSource is = new InputSource(new StringReader(decodeBytes.);

        //открытие документа
        Document document;
        try {

            //создаем документ из массива байтов
            document = factory.newDocumentBuilder().parse(new ByteArrayInputStream(decodeBytes));

            //Создание классса распоряжние по выдачу займа
            OrderLoan orderLoan = dataManager.create(OrderLoan.class);


            //Подгрузка вида документа (распоряжению по выдачи займа присваиваем код - 1)
            DocKind docKindOrder = dataManager.load(DocKind.class)
                    .query("select e from df$DocKind e where e.code = :code")
                    .parameter("code", "1")
                    .view("browse")
                    .one();

            //Создаем один комитконтекст так как у нас должно комитется все в одной транзакции
            CommitContext commitContext = new CommitContext();

            //запись вида документа, по которому происходит прасинг
            orderLoan.setDocKind(docKindOrder);

            //Парсинг документа
            if (document != null) {
                //основные атрибуты которые парсятся с тегом arg Ұпо порядку
                orderLoan.setNomerOrder(document.getElementsByTagName("arg").item(0).getTextContent());

                orderLoan.setDataOrder(document.getElementsByTagName("arg").item(1).getTextContent());

                orderLoan.setPodrazdelenie(document.getElementsByTagName("arg").item(2).getTextContent());

                orderLoan.setNomerProtocolaKK(document.getElementsByTagName("arg").item(3).getTextContent());

                orderLoan.setContragent(document.getElementsByTagName("arg").item(4).getTextContent());

                //orderLoan.setZa(document.getElementsByTagName("arg").item(5).getTextContent());

                orderLoan.setFullName(document.getElementsByTagName("arg").item(6).getTextContent());

                orderLoan.setDogovor(document.getElementsByTagName("arg").item(7).getTextContent());

                //dataokonchaniya нет поля
                //orderLoan.setDataOrder(document.getElementsByTagName("arg").item(8).getTextContent());

                orderLoan.setSumma(document.getElementsByTagName("arg").item(9).getTextContent());

                //другое поле
                //orderLoan.setDogovor(document.getElementsByTagName("arg").item(10).getTextContent());

                orderLoan.setIstochnik(document.getElementsByTagName("arg").item(11).getTextContent());

                orderLoan.setStavkaVoz(document.getElementsByTagName("arg").item(12).getTextContent());

                orderLoan.setBank(document.getElementsByTagName("arg").item(13).getTextContent());

                orderLoan.setBik(document.getElementsByTagName("arg").item(14).getTextContent());

                orderLoan.setIik(document.getElementsByTagName("arg").item(15).getTextContent());

                orderLoan.setBik(document.getElementsByTagName("arg").item(16).getTextContent());

                //orderLoan.setBin(document.getElementsByTagName("arg").item(17).getTextContent());

                //записываем автора документа по почте
                List<ExtEmployee> employees = dataManager.load(ExtEmployee.class)
                        .query("select e from DF_EMPLOYEE e where e.email = :email")
                        .parameter("email", document.getElementsByTagName("arg").item(18).getTextContent()).list();

                orderLoan.setOwner(employees.get(0));

                //устанавливаем текущую дату
                orderLoan.setDate(timeSource.currentTimestamp());

                //устанавливаем порядкувую нумерацию
                if (PersistenceHelper.isNew(orderLoan))
                    orderLoan.setNumber(String.valueOf(uniqueNumbersService1.getNextNumber("NUMBER_")));

                //теперь переходим к созданию процесса документа

                //подтягивание процесса согласования из коробки тезис
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

                //создание процесса внутри карточки документа и комит его
                CardProc cardProc = dataManager.create(CardProc.class);

                cardProc.setCard(orderLoan);

                cardProc.setProc(proc);

                commitContext.addInstanceToCommit(cardProc);


                //создание инициатора внутри карточки cardProc документа и комит его
                //здесь указываем на то что кто вошел в сессию тот и является инициатором
                User user = userSessionSource.getUserSession().getCurrentOrSubstitutedUser();

                CardRole cardInitiatior = dataManager.create(CardRole.class);

                cardInitiatior.setCode("Инициатор");

                cardInitiatior.setCard(orderLoan);

                cardInitiatior.setProcRole(initiatorRole);

                cardInitiatior.setUser(user);

                commitContext.addInstanceToCommit(cardInitiatior);

                //создание подписывающего внутри карточки cardProc документа и комит его (подтягивается по почте)
                List<User> approver = dataManager.load(User.class).query("select e from sec$User e where " +
                        "e.email = :email").parameter("email", document.getElementsByTagName("arg").item(19).getTextContent()).list();

                CardRole cardApprover = dataManager.create(CardRole.class);

                cardApprover.setCode("Утверждающий");

                cardApprover.setCard(orderLoan);

                cardApprover.setProcRole(approverRole);

                cardApprover.setUser(approver.get(0));

                commitContext.addInstanceToCommit(cardApprover);

                //создание подписывающего внутри карточки cardProc документа и комит его (подтягивается по почте)
                //так как несколько людей согласуют распоряжение то в xml передаются несколько согласующих. Через ноды (по SAX парсеру) создаю цикл и записываю несколько согласующих
                NodeList nodeList = document.getElementsByTagName("otvLico");

                for (int i = 0; nodeList.getLength() > i; i++) {

                    Node node = nodeList.item(i);

                    if (node.getNodeType() == Node.ELEMENT_NODE) {

                        Element element = (Element) node;

                        CardRole cardEnsorsed = dataManager.create(CardRole.class);

                        cardEnsorsed.setCode("Согласующий");

                        cardEnsorsed.setCard(orderLoan);

                        cardEnsorsed.setProcRole(endorseRole);

                        cardEnsorsed.setSortOrder(Integer.valueOf(element.getElementsByTagName("Nomer").item(0).getTextContent()));

                        List<User> endorsement = dataManager.load(User.class).query("select e from sec$User e where " +
                                "e.email = :email").parameter("email", element.getElementsByTagName("OtvetstvennoeLico").item(0).getTextContent()).list();

                        cardEnsorsed.setUser(endorsement.get(0));

                        commitContext.addInstanceToCommit(cardEnsorsed);

                    }

                }

            }

            //нормализация документа
            document.getDocumentElement().normalize();

            commitContext.addInstanceToCommit(orderLoan);

            //комит комитконтекста который сохраняет все комиты в одной транзакции
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