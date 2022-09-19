/*
 * Copyright (c) 2022 LTD Haulmont Samara. All Rights Reserved.
 * Haulmont Samara proprietary and confidential.
 * Use is subject to license terms.
 */

package com.company.thesissummer.service.parsingorders;

import com.company.thesissummer.entity.OrderVozvratvGu;
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
import org.xml.sax.SAXException;

import javax.inject.Inject;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.Base64;
import java.util.List;

@Service(OrderVozvratVGuParseService.NAME)
public class OrderVozvratVGuParseServiceBean implements OrderVozvratVGuParseService {

    @Inject
    private DataManager dataManager;

    CommitContext commitContext = new CommitContext();

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

            document = factory.newDocumentBuilder().parse(new ByteArrayInputStream(decodeBytes));

            //Создание классса РАспоряжние по переоценке
            OrderVozvratvGu orderVozvratvGu = dataManager.create(OrderVozvratvGu.class);

            //Подгрузка вида документа
            DocKind docKindOrder = dataManager.load(DocKind.class)
                    .query("select e from df$DocKind e where e.code = :code")
                    .parameter("code", "12")
                    .view("browse")
                    .one();

            //Комит вида документа
            orderVozvratvGu.setDocKind(docKindOrder);

            //Парсинг документа
            if (document != null) {
                orderVozvratvGu.setNomerOrder(document.getElementsByTagName("arg").item(0).getTextContent());

                orderVozvratvGu.setDataOrder(document.getElementsByTagName("arg").item(1).getTextContent());

                List<Department> departments = dataManager.load(Department.class).query("select e from df$Department e where " +
                        "e.name = :name").parameter("name", document.getElementsByTagName("arg").item(2).getTextContent()).list();

                orderVozvratvGu.setDepartment(departments.get(0));

                List<Employee> user = dataManager.load(Employee.class).query("select e from df$Employee e where " +
                        "e.email = :email").parameter("email", document.getElementsByTagName("arg").item(3).getTextContent()).list();

                orderVozvratvGu.setOwner(user.get(0));

                orderVozvratvGu.setKnp(document.getElementsByTagName("arg").item(4).getTextContent());

                orderVozvratvGu.setOnsovnoiDolg(document.getElementsByTagName("arg").item(5).getTextContent());

                orderVozvratvGu.setVoznagrazhdenie(document.getElementsByTagName("arg").item(6).getTextContent());

                orderVozvratvGu.setPenya(document.getElementsByTagName("arg").item(7).getTextContent());

                //устанавливаем текущую дату
                orderVozvratvGu.setDate(timeSource.currentTimestamp());

                //устанавливаем порядкувую нумерацию
                if (PersistenceHelper.isNew(orderVozvratvGu)) {
                    orderVozvratvGu.setNumber(String.valueOf(uniqueNumbersService1.getNextNumber("NUMBER_")));
                }
            }

            commitContext.addInstanceToCommit(orderVozvratvGu);

            document.getDocumentElement().normalize();

            dataManager.commit(commitContext);


        } catch (
                IOException e) {
            e.printStackTrace();
        } catch (
                ParserConfigurationException e) {
            e.printStackTrace();
        } catch (
                SAXException e) {
            e.printStackTrace();
        }
        return xml;
    }
}

//распоряжение успешно прошло тест через POSTMAN