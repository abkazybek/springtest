/*
 * Copyright (c) 2022 LTD Haulmont Samara. All Rights Reserved.
 * Haulmont Samara proprietary and confidential.
 * Use is subject to license terms.
 */

package com.company.thesissummer.service;

import com.fasterxml.jackson.databind.exc.InvalidFormatException;
import com.haulmont.cuba.core.app.FileStorageService;
import com.haulmont.cuba.core.entity.Entity;
import com.haulmont.cuba.core.entity.FileDescriptor;
import com.haulmont.cuba.core.global.*;
import com.haulmont.yarg.formatters.impl.doc.connector.OfficeIntegration;
import com.haulmont.yarg.formatters.impl.doc.connector.OfficeIntegrationAPI;
import com.haulmont.yarg.formatters.impl.xls.DocumentConverter;
import com.haulmont.yarg.formatters.impl.xls.DocumentConverterImpl;
import org.apache.poi.util.Units;
import org.apache.poi.xwpf.usermodel.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.inject.Inject;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

@Service(OrderTemplateService.NAME)
public class OrderTemplateServiceBean implements OrderTemplateService {
    private static final Logger log = LoggerFactory.getLogger(OrderTemplateServiceBean.class);

    @Override
    public void generateTemplate(Entity nomerOrder) {

    }
/*
    @Inject
    private DataManager dataManager;

    @Inject
    private FileLoader fileLoader;

    @Inject
    private UserSessionSource userSessionSource;

    @Inject
    private Metadata metadata;

    @Inject
    private FileStorageService fileStorageService;

    @Value("${libreoffice.path}")
    private String libreOffice;

    @Inject
    protected TimeSource timeSource;

    @Inject
    private QRCodeGeneratorService qRCodeGeneratorService;


    @Override
    public void generateTemplate(Entity nomerOrder) {


        ProtocolFileTemplate fileTemp = dataManager.load(ProtocolFileTemplate.class)
                .query("select e from thesissummer$ProtocolFileTemplate e where e.code = :code")
                .parameter("code", "2")
                .view("protocolFileTemplate-view")
                .one();

        List<OrderDocument> userOrder = dataManager.load(OrderDocument.class)
                .query("select distinct d from thesissummer$OrderLoan d where d.nomerOrder = :nomerOrder")
                .parameter("nomerOrder", "1")
                .view("browse")
                .list();

        //OrderLoan orderLoan = dataManager.load(OrderLoan.class).id(nomerProtocola).view("browsr").one();

        for (OrderDocument orderDocument : userOrder) {

            try {

                InputStream inputStream = fileLoader.openStream(fileTemp.getFileDescriptor());
                XWPFDocument document = new XWPFDocument(inputStream);

                ReplaceTextInDoc(document, "nomerOrder", orderDocument.getNumber());
                ReplaceTextInDoc(document, "dateOrder", orderDocument.getDate().toString());
                ReplaceTextInDoc(document, "protocolNumber", orderDocument.getNomerProtocola());
                ReplaceTextInDoc(document, "dogovor", orderDocument.getDogovor());
                ReplaceTextInDoc(document, "credit", orderDocument.getKreditnayaLiniya());
                ReplaceTextInDoc(document, "borrow", orderDocument.getIstochnikFinansirovaniya());
                ReplaceTextInDoc(document, "o_sum", orderDocument.getSumma());
                ReplaceTextInDoc(document, "o_source", orderDocument.getIstochnikFinansirovaniya());
                ReplaceTextInDoc(document, "o_bid", orderDocument.getStavkaVoz());
                ReplaceTextInDoc(document, "date_expiration", orderDocument.getSrokKredita());
                ReplaceTextInDoc(document, "o_counterparty", orderDocument.getPolnoeNaimenovanie());
                ReplaceTextInDoc(document, "bin", orderDocument.getBin());
                ReplaceTextInDoc(document, "uradress", orderDocument.getUrAdres());
                ReplaceTextInDoc(document, "bank", orderDocument.getBank());
                ReplaceTextInDoc(document, "bik", orderDocument.getBik());
                ReplaceTextInDoc(document, "kbe", orderDocument.getKbe());
                ReplaceTextInDoc(document, "o_iik", orderDocument.getIik());

                //SetQuestionsList(document, nomerProtocola);
                //SetQuestionVoteResult(document, meetingId, userVoteResult);

                String qr = "SBid: " + orderDocument.getSignatures();

                FileDescriptor qrFileDescriptor = qRCodeGeneratorService.getQRCode(qr);
                if(qrFileDescriptor != null){
                    addQRToWordDocument(document,qrFileDescriptor);
                }

                ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
                document.write(outputStream);

                FileDescriptor fileDescriptorNew = metadata.create(FileDescriptor.class);
                fileDescriptorNew.setName("Шаблон.docx");
                fileDescriptorNew.setExtension("docx");
                fileDescriptorNew.setCreateDate(new Date());

                byte[] decodedBytes = outputStream.toByteArray();
                fileLoader.saveStream(fileDescriptorNew, () -> new ByteArrayInputStream(decodedBytes));

                FileDescriptor pdfFileDescriptor = Word2PdfConverter(fileDescriptorNew, "Лист голосования");

                if (pdfFileDescriptor != null) {
                    orderDocument.setSetOrder(pdfFileDescriptor);
                    dataManager.commit(orderDocument);
                }
            } catch (IOException | FileStorageException e) {
                log.error("Error ioe or filestorage", e);
            } catch (org.apache.poi.openxml4j.exceptions.InvalidFormatException e) {
                e.printStackTrace();
            }
        }
    }


    //метод формирования pdf из docx, excel
    private FileDescriptor Word2PdfConverter(FileDescriptor fileDescriptor, String fileName) throws IOException {

//        String libreOffice = "C:\\Program Files\\LibreOffice\\program";

        byte[] bytes = null;
        try {
            bytes = fileStorageService.loadFile(fileDescriptor);
        } catch (FileStorageException e) {
            e.printStackTrace();
        }
        if (bytes != null) {
            DocumentConverter.FileType type;
            if (fileDescriptor.getExtension().toLowerCase().equals("xls") ||
                    fileDescriptor.getExtension().toLowerCase().equals("xlsx")) {
                type = DocumentConverter.FileType.SPREADSHEET;
            } else type = DocumentConverter.FileType.DOCUMENT;

            OfficeIntegrationAPI officeIntegration = new OfficeIntegration(libreOffice, 8100, 8101, 8102, 8103);
            DocumentConverter pdfConverter = new DocumentConverterImpl(officeIntegration);
            ByteArrayOutputStream outputResult = new ByteArrayOutputStream();
            pdfConverter.convertToPdf(type, bytes, outputResult);

            FileDescriptor fileDescriptorNew = metadata.create(FileDescriptor.class);
            fileDescriptorNew.setName(fileName);
            fileDescriptorNew.setExtension("pdf");
            fileDescriptorNew.setCreateDate(new Date());

            try {
                byte[] decodedBytes = outputResult.toByteArray();
                fileLoader.saveStream(fileDescriptorNew, () -> new ByteArrayInputStream(decodedBytes));

                fileDescriptorNew = dataManager.commit(fileDescriptorNew);

            } catch (Exception e) {
                e.printStackTrace();
            }

            return fileDescriptorNew;
        }
        return null;
    }

    // метод который заменяет текст в документе
    private void ReplaceTextInDoc(XWPFDocument doc, String oldText, String newText) {
        // взял метод изинтернета, надо проверить и параграфы и таблицы, видимо для того что бы все
        // вхождения найти
        for (XWPFParagraph p : doc.getParagraphs()) {
            List<XWPFRun> runs = p.getRuns();
            if (runs != null) {
                for (XWPFRun r : runs) {
                    String text = r.getText(0);
                    if (text != null && text.contains(oldText)) {
                        //log.debug("------------------------------------");
                        //log.debug("------- Finding text------" + text);
                        //log.debug("------------------------------------");
                        text = text.replace(oldText, newText);
                        r.setText(text, 0);
                    }
                }
            }
        }

        for (XWPFTable tbl : doc.getTables()) {
            for (XWPFTableRow row : tbl.getRows()) {
                for (XWPFTableCell cell : row.getTableCells()) {
                    for (XWPFParagraph p : cell.getParagraphs()) {
                        for (XWPFRun r : p.getRuns()) {
                            String text = r.getText(0);
                            if (text != null && text.contains(oldText)) {
                                //log.debug("------------------------------------");
                                //log.debug("------- Finding text in table------" + text);
                                //log.debug("------------------------------------");
                                text = text.replace(oldText, newText);
                                r.setText(text, 0);
                            }
                        }
                    }
                }
            }
        }
    }

    // метод возвращает дату для заголовка в формате (01 января 2022)
    private String GetDateInHeader(Date date) {
        GregorianCalendar calendar = new GregorianCalendar();
        calendar.setTime(date);

        String dayStr = calendar.get(Calendar.DATE) < 10 ? "0" + calendar.get(Calendar.DATE) : String.valueOf(calendar.get(Calendar.DATE));
        String monthStr = GetMonthText(calendar.get(Calendar.MONTH));
        String yearStr = String.valueOf(calendar.get(Calendar.YEAR));

        return dayStr + " " + monthStr + " " + yearStr;
    }

    // возвращает текстовое представление месяца на русском
    private String GetMonthText(int month) {
        switch (month) {
            case (0):
                return "января";
            case (1):
                return "февраля";
            case (2):
                return "марта";
            case (3):
                return "апреля";
            case (4):
                return "мая";
            case (5):
                return "июня";
            case (6):
                return "июля";
            case (7):
                return "августа";
            case (8):
                return "сентябрь";
            case (9):
                return "октябрь";
            case (10):
                return "ноября";
            case (11):
                return "декабря";
            default:
                return "";
        }
    }
    private void addQRToWordDocument(XWPFDocument document , FileDescriptor imageFileDescriptor) throws IOException, FileStorageException, InvalidFormatException, org.apache.poi.openxml4j.exceptions.InvalidFormatException {

        XWPFParagraph qrParagraph = null;

        List<XWPFFooter> footerList = document.getFooterList();

        for(XWPFFooter f : footerList){
            for (XWPFParagraph p : f.getParagraphs()) {
                List<XWPFRun> runs = p.getRuns();
                if (runs != null) {
                    for (XWPFRun r : runs) {
                        String text = r.getText(0);
                        if (text != null && text.contains("time")) {

                            text = text.replace("time", "1111");
                            r.setText(text, 0);
                        }
                    }
                }
            }


            for (XWPFTable tbl : f.getTables()) {
                for (XWPFTableRow row : tbl.getRows()) {
                    for (XWPFTableCell cell : row.getTableCells()) {
                        for (XWPFParagraph p : cell.getParagraphs()) {
                            for (XWPFRun r : p.getRuns()) {
                                String text = r.getText(0);
                                if (text != null && text.contains("footerqr")) {

                                    text = text.replace("footerqr", "");
                                    r.setText(text, 0);

                                    qrParagraph = p;
                                    break;
                                }
                            }
                        }
                    }
                }
            }

        }

        if(qrParagraph != null){

            XWPFRun r = qrParagraph.createRun();
            r.addPicture(fileLoader.openStream(imageFileDescriptor), XWPFDocument.PICTURE_TYPE_PNG, imageFileDescriptor.getName(), Units.toEMU(80), Units.toEMU(80));
        }

    }
  /*
    private void addQRToWordDocument(XWPFDocument document, FileDescriptor imageFileDescriptor) throws IOException, FileStorageException, InvalidFormatException {

        XWPFParagraph qrParagraph = null;

        List<XWPFFooter> footerList = document.getFooterList();

        for (XWPFFooter f : footerList) {
//            for (XWPFParagraph p : f.getParagraphs()) {
//                List<XWPFRun> runs = p.getRuns();
//                if (runs != null) {
//                    for (XWPFRun r : runs) {
//                        String text = r.getText(0);
//                        if (text != null && text.contains("time")) {
//
//                            text = text.replace("time", "1111");
//                            r.setText(text, 0);
//                        }
//                    }
//                }
//            }


            for (XWPFTable tbl : f.getTables()) {
                for (XWPFTableRow row : tbl.getRows()) {
                    for (XWPFTableCell cell : row.getTableCells()) {
                        for (XWPFParagraph p : cell.getParagraphs()) {
                            for (XWPFRun r : p.getRuns()) {
                                String text = r.getText(0);
                                if (text != null && text.contains("footerqr")) {

                                    text = text.replace("footerqr", "");
                                    r.setText(text, 0);

                                    qrParagraph = p;
                                    break;
                                }
                            }
                        }
                    }
                }
            }

        }

        if (qrParagraph != null) {

            XWPFRun r = qrParagraph.createRun();
            //r.addPicture(fileLoader.openStream(imageFileDescriptor), XWPFDocument.PICTURE_TYPE_PNG, imageFileDescriptor.getName());
        }

        //метод который находит таблицу по ключу из шаблона
        private XWPFTable GetTable(XWPFDocument doc, String tableKey){
            for (XWPFTable tbl : doc.getTables()) {
                for (XWPFTableRow row : tbl.getRows()) {
                    for (XWPFTableCell cell : row.getTableCells()) {
                        for (XWPFParagraph p : cell.getParagraphs()) {
                            for (XWPFRun r : p.getRuns()) {
                                String text = r.getText(0);
                                if (text != null && text.contains(tableKey)) {
                                    return tbl;
                                }
                            }
                        }
                    }
                }
            }
        }

        /
 /*
    @Override
    public String getVoteListForSign(UUID meetingId) {
        // и тот и тот объект должны уже существовать по идее
        OrderLoan orderLoan = dataManager.load(OrderLoan.class)
                .query("select u from pko_UserVoteResult u where u.meeting.id = :meetingId and u.user = :user")
                .parameter("meetingId", meetingId)
                .parameter("user", userSessionSource.getUserSession().getUser())
                .view("userVoteResult-sign-res-view")
                .one();

        try {
            InputStream inputStream = fileLoader.openStream(orderLoan.getVoteList());
            byte[] byteAr = IOUtils.toByteArray(inputStream);
            String encoded = Base64.getEncoder().encodeToString(byteAr);
            return encoded;

        } catch (FileStorageException | IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void saveSignStatus(UUID meetingId, String base64, String signStatus) {

        if(signStatus.equals("3")) { //лист голосования

            try {
                OrderLoan orderLoan = dataManager.load(OrderLoan.class)
                        .query("select e from pko_UserVoteResult e where e.meeting.id = :meetingId and e.user = :user and e.isHere = '1'")
                        .parameter("meetingId", meetingId)
                        .parameter("user", userSessionSource.getUserSession().getUser())
                        .view("userVoteResult-sign-res-view")
                        .one();

                userVoteResult.setVoteListSignRes("1");

                // сохраняем файл
                // создаем FileDescriptor для сохранения файла, заполняем его поля
                FileDescriptor fileDescriptor = dataManager.create(FileDescriptor.class);
                fileDescriptor.setName("Подписанный лист голосования.pdf.cms");
                fileDescriptor.setCreateDate(new Date());
                fileDescriptor.setExtension("cms");

                // получаем и сохраняем файл в хранилище
                byte[] decodedBytes = Base64.getDecoder().decode(base64);

                fileLoader.saveStream(fileDescriptor, () -> new ByteArrayInputStream(decodedBytes));

                // сохраняем и присваиваем FileDescriptor
                fileDescriptor = dataManager.commit(fileDescriptor);
                userVoteResult.setSignedVoteList(fileDescriptor);

                // сохраняем основной объект
                dataManager.commit(userVoteResult);

            } catch (FileStorageException e) {
                e.printStackTrace();
            }

            List<UserVoteResult> listUser = dataManager.load(UserVoteResult.class)
                    .query("select e from pko_UserVoteResult e where e.meeting.id = :meetingId and e.isVoter = '1' and e.isHere = '1'")
                    .parameter("meetingId", meetingId)
                    .view("userVoteResult-react-res-view")
                    .list();

            boolean isAllSign = true;
            for (UserVoteResult userVoteResult : listUser) {
                if (!userVoteResult.getVoteListSignRes().equals("1")) {
                    isAllSign = false;
                    break;
                }
            }

            Meeting meeting = dataManager.load(Meeting.class).view("meeting-rest-view").id(meetingId).one();
            // обновим общий статус подписания, если все проголосовали
            if (isAllSign) {
                meeting.setSignStatus("4");
                dataManager.commit(meeting);
            }
        } else if (signStatus.equals("4")){//протокол секретарь

            try {

                Meeting meeting = dataManager.load(Meeting.class).id(meetingId).view("meeting-rest-view").one();

                // сохраняем файл
                // создаем FileDescriptor для сохранения файла, заполняем его поля
                FileDescriptor fileDescriptor = dataManager.create(FileDescriptor.class);
                fileDescriptor.setName("Подписанный протокол.pdf.cms");
                fileDescriptor.setCreateDate(new Date());
                fileDescriptor.setExtension("cms");

                // получаем и сохраняем файл в хранилище
                byte[] decodedBytes = Base64.getDecoder().decode(base64);

                fileLoader.saveStream(fileDescriptor, () -> new ByteArrayInputStream(decodedBytes));

                // сохраняем и присваиваем FileDescriptor
                fileDescriptor = dataManager.commit(fileDescriptor);
                meeting.setProtocolTemplate(fileDescriptor);

                //Меняем статус подписания в заседании
                meeting.setSignStatus("5");

                // сохраняем основной объект
                dataManager.commit(meeting);
            } catch (FileStorageException e) {
                e.printStackTrace();
            }
        } else if (signStatus.equals("5")){//протокол пред

            try {

                Meeting meeting = dataManager.load(Meeting.class).id(meetingId).view("meeting-rest-view").one();

                // сохраняем файл
                // создаем FileDescriptor для сохранения файла, заполняем его поля
                FileDescriptor fileDescriptor = dataManager.create(FileDescriptor.class);
                fileDescriptor.setName("Подписанный протокол.pdf.cms");
                fileDescriptor.setCreateDate(new Date());
                fileDescriptor.setExtension("cms");

                // получаем и сохраняем файл в хранилище
                byte[] decodedBytes = Base64.getDecoder().decode(base64);

                fileLoader.saveStream(fileDescriptor, () -> new ByteArrayInputStream(decodedBytes));

                // сохраняем и присваиваем FileDescriptor
                fileDescriptor = dataManager.commit(fileDescriptor);
                meeting.setProtocolTemplate(fileDescriptor);

                //Меняем статус подписания в заседании
                meeting.setSignStatus("2");

                //!!! тут должна быть функция отправки данных в SB
                sBIntegrationService.sendAnswerToSB(meetingId);

                // сохраняем основной объект
                dataManager.commit(meeting);
            } catch (FileStorageException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public SignFileTemplate getVoteListForPreview(UUID meetingId) {

        UserVoteResult userVoteResult = dataManager.load(UserVoteResult.class)
                .query("select u from pko_UserVoteResult u where u.meeting.id = :meetingId and u.user = :user and u.voteListSignRes = '1'")
                .parameter("meetingId", meetingId)
                .parameter("user", userSessionSource.getUserSession().getUser())
                .view("userVoteResult-sign-res-view")
                .one();

        SignFileTemplate signFileTemplate = dataManager.create(SignFileTemplate.class);

        try {
            InputStream inputStream = fileLoader.openStream(userVoteResult.getVoteList());
            byte[] byteAr = IOUtils.toByteArray(inputStream);

            signFileTemplate.setFileContent(byteAr);

        } catch (FileStorageException | IOException e) {
            throw new RuntimeException(e);
        }

        return signFileTemplate;
    }

    @Override
    public SignFileTemplate getProtocolForPreview(UUID meetingId) {

        Meeting meeting = dataManager.load(Meeting.class).id(meetingId).view("meeting-rest-view").one();

        SignFileTemplate signFileTemplate = dataManager.create(SignFileTemplate.class);

        try {
            InputStream inputStream = fileLoader.openStream(meeting.getProtocol());
            byte[] byteAr = IOUtils.toByteArray(inputStream);

            signFileTemplate.setFileContent(byteAr);

        } catch (FileStorageException | IOException e) {
            throw new RuntimeException(e);
        }

        return signFileTemplate;
    }

    @Override
    public String uploadProtocol(UUID meetingId, UUID fileId) {

        Meeting meeting = dataManager.load(Meeting.class).id(meetingId).view("meeting-rest-view").one();
        FileDescriptor fileDescriptor = dataManager.load(FileDescriptor.class).id(fileId).one();

        FileDescriptor pdfFileDescriptor = dataManager.create(FileDescriptor.class);

        try{
            String qr = "SBid: " + meeting.getMeetengSBId();

            InputStream inputStream = fileLoader.openStream(fileDescriptor);
            XWPFDocument document = new XWPFDocument(inputStream);
            FileDescriptor qrFileDescriptor = qRCodeGeneratorService.getQRCode(qr);
            if(qrFileDescriptor != null){
                addQRToWordDocument(document,qrFileDescriptor);
            }

            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
            document.write(outputStream);

            FileDescriptor fileDescriptorNew = metadata.create(FileDescriptor.class);
            fileDescriptorNew.setName("Протокол.docx");
            fileDescriptorNew.setExtension("docx");
            fileDescriptorNew.setCreateDate(new Date());

            byte[] decodedBytes = outputStream.toByteArray();
            fileLoader.saveStream(fileDescriptorNew, () -> new ByteArrayInputStream(decodedBytes));

            pdfFileDescriptor = Word2PdfConverter(fileDescriptorNew,"Протокол.pdf");
        }catch (Exception e){
            e.printStackTrace();
        }

        meeting.setProtocol(pdfFileDescriptor);
        dataManager.commit(meeting);

        return "1";
    }

    @Override
    public String getProtocolForSign(UUID meetingId) {

        Meeting meeting = dataManager.load(Meeting.class).id(meetingId).view("meeting-rest-view").one();

        try {
            InputStream inputStream = fileLoader.openStream(meeting.getProtocol());
            byte[] byteAr = IOUtils.toByteArray(inputStream);
            return Base64.getEncoder().encodeToString(byteAr);

        } catch (FileStorageException | IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public SignFileTemplate getMeetingFileById(UUID fileId) {

        FileDescriptor fileDescriptor = dataManager.load(FileDescriptor.class).id(fileId).one();

        SignFileTemplate signFileTemplate = dataManager.create(SignFileTemplate.class);

        try {

            InputStream inputStream = fileLoader.openStream(fileDescriptor);
            signFileTemplate.setFileContent(IOUtils.toByteArray(inputStream));
            signFileTemplate.setFileName(fileDescriptor.getName());
        } catch (FileStorageException | IOException e) {
            throw new RuntimeException(e);
        }

        return signFileTemplate;
    }

    @Override
    public List<SignFileTemplate> getMeetingFilesList(UUID meetingId) {

        List<UserVoteResult> userVoteResultList = dataManager.load(UserVoteResult.class)
                .query("select u from pko_UserVoteResult u where u.meeting.id = :meetingId")
                .parameter("meetingId", meetingId)
                .view("userVoteResult-sign-res-view")
                .list();

        List<SignFileTemplate> signFileTemplateList = new ArrayList<>();

        for(UserVoteResult u: userVoteResultList){

            SignFileTemplate signFileTemplate = dataManager.create(SignFileTemplate.class);

            if(u.getVoteList() != null){
                signFileTemplate.setFileName(u.getVoteList().getName());
                signFileTemplate.setFile(u.getVoteList());

                signFileTemplateList.add(signFileTemplate);
            }
        }
        return signFileTemplateList;
    }

    @Override
    public List<SignFileTemplate> getMeetingProtocolList(UUID meetingId) {

        Meeting meeting = dataManager.load(Meeting.class).id(meetingId).view("meeting-protocol-view").one();

        List<SignFileTemplate> signFileTemplateList = new ArrayList<>();

        SignFileTemplate signFileTemplate = dataManager.create(SignFileTemplate.class);

        if(meeting.getProtocolTemplate() != null){
            signFileTemplate.setFileName(meeting.getProtocol().getName());
            signFileTemplate.setFile(meeting.getProtocol());

            signFileTemplateList.add(signFileTemplate);
        }

        return signFileTemplateList;
    }

    @Override
    public SignFileTemplate generateMeetingProtocol(UUID meetingId) {
        return null;
    }

    //метод возвращает должность юзера
    private String getUserPosition(User user, Meeting meeting){

        if(meeting.getChairman() == user){
            return "Председатель Совета директоров";
        }
        return "Член Совета директоров";
    }

    //метод заполняет таблицу с повесткой
    private void SetQuestionsList(XWPFDocument doc, UUID meetingId){

        XWPFTable table = GetTable(doc, "questions"); // таблица с участниками
        if(table == null) return;

        List<Question> questionList = dataManager.load(Question.class)
                .query("select q from pko_Question q where q.meeting.id = :meetingId order by q.sorder asc")
                .parameter("meetingId", meetingId)
                .view("question-rest-view")
                .list();

        for (Question q: questionList) {

            XWPFTableRow newRow = table.createRow();
            newRow.setHeight(20);
            XWPFRun run = newRow.getCell(0).getParagraphArray(0).createRun();
            run.setText( "    " + q.getSorder() + ". " + q.getNameRu());
            run.setFontSize(12);
            run.setFontFamily("Times New Roman");

//            newRow.getCell(0).getParagraphs().get(0).setFirstLineIndent(5);
            newRow.getCell(0).getParagraphs().get(0).setAlignment(ParagraphAlignment.BOTH);
        }
        // удаляем первую строку с шаблоном (шапка тут есть)
        table.removeRow(0);
    }
/*
    //метод заполняет таблицу с результататми голосования за вопросы одного голосующего
    private void SetQuestionVoteResult(XWPFDocument doc, UUID meetingId, UserVoteResult userVoteResult){

        XWPFTable table = GetTable(doc, "voice"); // таблица с участниками
        if(table == null) return;

        List<Question> questionList = dataManager.load(Question.class)
                .query("select q from pko_Question q where q.meeting.id = :meetingId order by q.sorder asc")
                .parameter("meetingId", meetingId)
                .view("question-rest-view")
                .list();

        for(Question q: questionList){

            XWPFTableRow newRow = table.createRow();
            newRow.setHeight(20);

            QuestionVoteRes questionVoteRes = dataManager.load(QuestionVoteRes.class)
                    .query("select q from pko_QuestionVoteRes q where q.question = :question and q.user = :user")
                    .parameter("question", q)
                    .parameter("user", userVoteResult.getUser())
                    .one();

            XWPFRun run1 = newRow.getCell(0).getParagraphArray(0).createRun();
            run1.setText(q.getSorder() + ". " + q.getNameRu());
            run1.setFontSize(12);
            run1.setFontFamily("Times New Roman");

            newRow.getCell(0).getParagraphs().get(0).setAlignment(ParagraphAlignment.BOTH);

            XWPFRun run2 = newRow.addNewTableCell().getParagraphArray(0).createRun();
            XWPFRun run3 = newRow.addNewTableCell().getParagraphArray(0).createRun();
            XWPFRun run4 = newRow.addNewTableCell().getParagraphArray(0).createRun();

            if(questionVoteRes.getVoteRes().equals("1")){
                run2.setText("+");
                run2.setFontSize(12);
                run2.setFontFamily("Times New Roman");

                newRow.getCell(1).getParagraphs().get(0).setAlignment(ParagraphAlignment.CENTER);
            }
            if(questionVoteRes.getVoteRes().equals("2")){
                run3.setText("+");
                run3.setFontSize(12);
                run3.setFontFamily("Times New Roman");

                newRow.getCell(2).getParagraphs().get(0).setAlignment(ParagraphAlignment.CENTER);
            }
            if(questionVoteRes.getVoteRes().equals("3")){
                run4.setText("+");
                run4.setFontSize(12);
                run4.setFontFamily("Times New Roman");

                newRow.getCell(3).getParagraphs().get(0).setAlignment(ParagraphAlignment.CENTER);
            }
        }
        // удаляем первую строку с шаблоном (шапка тут есть)
        table.removeRow(2);
    }

    //метод который проставляет в таблице приглашенных на заседание заседания
    private void SetMeetingGuestList(XWPFDocument doc, UUID nomerProtokola){

        XWPFTable userTable = GetTable(doc, "user_guest_table"); // таблица с участниками
        if(userTable == null) return;

        // надо сделать порядок сортировки пользователей
        List<OrderLoan> listRes = getUserGuestList(nomerProtokola);

        XWPFTableRow newRow = userTable.createRow();
        XWPFRun numRun = newRow.getCell(0).getParagraphArray(0).createRun();
        numRun.setFontSize(11);
        numRun.setFontFamily("Times New Roman");
        XWPFRun userRun = newRow.getCell(1).getParagraphArray(0).createRun();
        userRun.setFontSize(11);
        userRun.setFontFamily("Times New Roman");

        for (int i=0; i < listRes.size(); i++) {

            String numStr = (i+1) + ". ";
            numRun.setText(numStr, i);
            numRun.addBreak();

            String userStr = getUserName(listRes.get(i).getUser(),"short") + " - " + listRes.get(i).getUser().getPosition();
            userRun.setText(userStr, i);
            userRun.addBreak();
        }
        // удаляем первую строку с шаблоном
        userTable.removeRow(0);
    }

    //метод заполняет таблицу с результататми голосования за повестку
    private void SetAgendaVoteResult(XWPFDocument doc, UUID meetingId){

        XWPFTable table = GetTable(doc, "voice"); // таблица с участниками
        if(table == null) return;

        List<UserVoteResult> userVoteResultList = getUserVoteList(meetingId);

        for (int i=0; i < userVoteResultList.size(); i++) {

            XWPFTableRow newRow = table.createRow();
            newRow.setHeight(20);
            XWPFRun run1 = newRow.getCell(0).getParagraphArray(0).createRun();
            run1.setText(getUserName(userVoteResultList.get(i).getUser(),"short"));
            run1.setFontSize(12);
            run1.setFontFamily("Times New Roman");
            if(userVoteResultList.get(i).getAgendaVoteRes().equals("1")){
                XWPFRun run2 = newRow.getCell(1).getParagraphArray(0).createRun();
                run2.setText("+");
                run2.setFontSize(12);
                run2.setFontFamily("Times New Roman");
            }
            if(userVoteResultList.get(i).getAgendaVoteRes().equals("2")){
                XWPFRun run3 = newRow.getCell(2).getParagraphArray(0).createRun();
                run3.setText("+");
                run3.setFontSize(12);
                run3.setFontFamily("Times New Roman");
            }
            if(userVoteResultList.get(i).getAgendaVoteRes().equals("3")){
                XWPFRun run4 = newRow.getCell(3).getParagraphArray(0).createRun();
                run4.setText("+");
                run4.setFontSize(12);
                run4.setFontFamily("Times New Roman");
            }
        }
        // удаляем первую строку с шаблоном (шапка тут есть)
        table.removeRow(1);
    }

    // метод возвращает списко голосований пользователей в порядке приоритета пользователей
    // по сути дублирует такой же метод из сервиса UserVoteServiceBean
    private List<UserVoteResult> getUserVoteList(UUID meetingId) {
        List<UserVoteResult> listRes = new ArrayList<>();

        List<UserVoteResult> listUser = dataManager.load(UserVoteResult.class)
                .query("select e from pko_UserVoteResult e where e.meeting.id = :meetingId and e.isVoter = '1'")
                .parameter("meetingId", meetingId)
                .view("userVoteResult-react-res-view")
                .list();

        List<UserOrder> listOrder = dataManager.load(UserOrder.class)
                .query("select e from pko_UserOrder e order by e.order asc")
                .view("userOrder-rest-view")
                .list();

        // в результирующий список сначала добавляем по порядку  тех кто есть
        for (int j = 0; j < listOrder.size(); j++) {
            for (int i = 0; i < listUser.size(); i++) {
                if (listUser.get(i).getUser().equals(listOrder.get(j).getUser())) {
                    listRes.add(listUser.get(i));
                }
            }
        }
        // потом тех кто остался
        for (int i = 0; i < listUser.size(); i++) {
            if (!listRes.contains(listUser.get(i))) {
                listRes.add(listUser.get(i));
            }
        }

        return listRes;
    }

    private List<UserVoteResult> getUserGuestList(UUID meetingId) {
        List<UserVoteResult> listRes = new ArrayList<UserVoteResult>();

        List<UserVoteResult> listUser = dataManager.load(UserVoteResult.class)
                .query("select e from pko_UserVoteResult e where e.meeting.id = :meetingId and e.isVoter = '0'")
                .parameter("meetingId", meetingId)
                .view("userVoteResult-react-res-view")
                .list();

        List<UserOrder> listOrder = dataManager.load(UserOrder.class)
                .query("select e from pko_UserOrder e order by e.order asc")
                .view("userOrder-rest-view")
                .list();

        // в результирующий список сначала добавляем по порядку  тех кто есть
        for (int j = 0; j < listOrder.size(); j++) {
            for (int i = 0; i < listUser.size(); i++) {
                if (listUser.get(i).getUser().equals(listOrder.get(j).getUser())) {
                    listRes.add(listUser.get(i));
                }
            }
        }
        // потом тех кто остался
        for (int i = 0; i < listUser.size(); i++) {
            if (!listRes.contains(listUser.get(i))) {
                listRes.add(listUser.get(i));
            }
        }

        return listRes;
    }
/*
    // метод возвращает фамилию и инициалы пользователя если заполнены
    private String getUserName(User user, String mode){

        String resStr = "";

        if (user != null) {
            if (mode.equals("short")) {

                if (user.getLastName() != null && !user.getLastName().isEmpty()) {
                    resStr += user.getLastName(); // хотя фамилия я думаю всегда будет
                }
                if (user.getFirstName() != null && !user.getFirstName().isEmpty()) {
                    resStr += " " + user.getFirstName().substring(0, 1).toUpperCase() + "."; // первая буква имения с точкой и прбелом
                }
                if (user.getMiddleName() != null && !user.getMiddleName().isEmpty()) {
                    resStr += user.getMiddleName().substring(0, 1).toUpperCase() + "."; // первая буква отчества с точкой
                }
            } else if (mode.equals("long")) {
                if (user.getLastName() != null && !user.getLastName().isEmpty()) {
                    resStr += user.getLastName(); // хотя фамилия я думаю всегда будет
                }
                if (user.getFirstName() != null && !user.getFirstName().isEmpty()) {
                    resStr += " " + user.getFirstName(); // первая буква имения с точкой и прбелом
                }
                if (user.getMiddleName() != null && !user.getMiddleName().isEmpty()) {
                    resStr += " " + user.getMiddleName(); // первая буква отчества с точкой
                }
            }
        }

        return resStr;
    }


 */


/*
    //метод формирования pdf из docx, excel
    private FileDescriptor Word2PdfConverter(FileDescriptor fileDescriptor, String fileName) throws IOException {

//        String libreOffice = "C:\\Program Files\\LibreOffice\\program";

        byte[] bytes = null;
        try {
            bytes = fileStorageService.loadFile(fileDescriptor);
        } catch (FileStorageException e) {
            e.printStackTrace();
        }
        if (bytes != null) {
            DocumentConverter.FileType type;
            if (fileDescriptor.getExtension().toLowerCase().equals("xls") ||
                    fileDescriptor.getExtension().toLowerCase().equals("xlsx")) {
                type = DocumentConverter.FileType.SPREADSHEET;
            } else type = DocumentConverter.FileType.DOCUMENT;

            OfficeIntegrationAPI officeIntegration = new OfficeIntegration(libreOffice, 8100,8101,8102,8103);
            DocumentConverter pdfConverter = new DocumentConverterImpl(officeIntegration);
            ByteArrayOutputStream outputResult = new ByteArrayOutputStream();
            pdfConverter.convertToPdf(type, bytes, outputResult);

            FileDescriptor fileDescriptorNew = metadata.create(FileDescriptor.class);
            fileDescriptorNew.setName(fileName);
            fileDescriptorNew.setExtension("pdf");
            fileDescriptorNew.setCreateDate(new Date());

            try{
                byte[] decodedBytes = outputResult.toByteArray();
                fileLoader.saveStream(fileDescriptorNew, () -> new ByteArrayInputStream(decodedBytes));

                fileDescriptorNew = dataManager.commit(fileDescriptorNew);
            }catch (Exception e){
                e.printStackTrace();
            }

            return fileDescriptorNew;
        }
        return null;
    }

    // метод возвращает дату для заголовка в формате (01 января 2022)
    private String GetDateInHeader(Date date){
        GregorianCalendar calendar = new GregorianCalendar();
        calendar.setTime(date);

        String dayStr = calendar.get(Calendar.DATE) < 10 ? "0" + calendar.get(Calendar.DATE) : String.valueOf(calendar.get(Calendar.DATE));
        String monthStr = GetMonthText(calendar.get(Calendar.MONTH));
        String yearStr = String.valueOf(calendar.get(Calendar.YEAR));

        return dayStr + " " + monthStr + " " + yearStr;
    }

    // возвращает текстовое представление месяца на русском
    private String GetMonthText(int month){
        switch (month) {
            case  (0):
                return "января";
            case  (1):
                return "февраля";
            case  (2):
                return "марта";
            case  (3):
                return "апреля";
            case  (4):
                return "мая";
            case  (5):
                return "июня";
            case  (6):
                return "июля";
            case  (7):
                return "августа";
            case  (8):
                return "сентябрь";
            case  (9):
                return "октябрь";
            case  (10):
                return "ноября";
            case  (11):
                return "декабря";
            default:
                return "";
        }
    }
       */

}