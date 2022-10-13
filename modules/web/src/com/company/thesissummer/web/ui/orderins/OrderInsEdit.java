/*
 * Copyright (c) 2022 LTD Haulmont Samara. All Rights Reserved.
 * Haulmont Samara proprietary and confidential.
 * Use is subject to license terms.
 */

package com.company.thesissummer.web.ui.orderins;

import com.company.thesissummer.entity.ExtEmployee;
import com.company.thesissummer.service.OrderSubsidyuTemplateService;
import com.haulmont.cuba.core.entity.FileDescriptor;
import com.haulmont.cuba.core.global.DataManager;
import com.haulmont.cuba.core.global.FileStorageException;
import com.haulmont.cuba.core.global.TimeSource;
import com.haulmont.cuba.core.global.UserSessionSource;
import com.haulmont.cuba.gui.Notifications;
import com.haulmont.cuba.gui.components.Action;
import com.haulmont.cuba.gui.components.FileUploadField;
import com.haulmont.cuba.gui.data.Datasource;
import com.haulmont.cuba.gui.export.ExportDisplay;
import com.haulmont.cuba.gui.screen.Subscribe;
import com.haulmont.cuba.gui.upload.FileUploadingAPI;
import com.haulmont.cuba.security.entity.User;
import com.haulmont.thesis.core.entity.Employee;
import com.haulmont.thesis.core.entity.ThesisCardAttachment;
import com.haulmont.thesis.web.actions.PrintReportAction;
import com.haulmont.thesis.web.ui.basicdoc.editor.AbstractDocEditor;
import com.haulmont.thesis.web.voice.VoiceActionPriorities;
import com.haulmont.cuba.gui.components.LookupPickerField;
import com.haulmont.thesis.core.entity.DocCategory;
import com.company.thesissummer.entity.OrderIns;
import com.haulmont.workflow.core.entity.*;

import javax.inject.Inject;
import java.util.List;
import java.util.Map;

import static com.haulmont.thesis.web.voice.VoiceCompanionsRepository.voiceCompanion;

public class OrderInsEdit<T extends OrderIns> extends AbstractDocEditor<T> {


    /*системные классы которые появляюся при создании документа */
    @Inject
    protected LookupPickerField<DocCategory> docCategory;

    @Override
    protected String getHiddenTabsConfig() {
        return "processTab";
    }

    @Override
    protected void addPrintDocActions() {
        super.addPrintDocActions();
        printButton.addAction(new PrintReportAction("printExecutionList", this, "printDocExecutionListReportName"));
    }

    protected void initVoiceControl() {
        voiceCompanion(docCategory).setPriorityOffset(VoiceActionPriorities.TAB_SHEET + 10);
    }

    @Override
    protected void fillHiddenTabs() {
        hiddenTabs.put("attachmentsTab", getMessage("attachmentsTab"));
        if (getAccessData().getNotVersion()) {
            hiddenTabs.put("cardCommentTab", getMessage("cardCommentTab"));
        }
        super.fillHiddenTabs();
    }

    /* доработанные классы */
    //сессия объекта
    @Inject
    protected UserSessionSource userSessionSource;

    //уведолмение
    @Inject
    private Notifications notifications;

    //получаетель в детали системы
    @Inject
    private LookupPickerField<ExtEmployee> orderReceiver;

    //автор документа
    @Inject
    public LookupPickerField owner;

    //показатель в браузере пока он не нужен потому что класс генерится при входе
    @Inject
    public ExportDisplay exportDisplay;

    //сервис создания шаблона
    @Inject
    public OrderSubsidyuTemplateService orderSubsidyuTemplateService;

    //событие после открытия экрана, работает только в блоке try/catch (в дальнейшем поменять в beforeshowevent)


    //основное событие тезиса
    @Override
    public void init(Map<String, Object> params) {
        super.init(params);
        initVoiceControl();
        //определенный юзер в сессии
        User user = userSessionSource.getUserSession().getCurrentOrSubstitutedUser();
        List<ExtEmployee> employees = dataManager.load(ExtEmployee.class)
                .query("select e from DF_EMPLOYEE e where e.name = :name")
                .parameter("name", user.getName())
                .list();
        //условия если юзер является сотрудником то он система запишет его имя
        if (employees.size() != 0) {
            owner.setCaption(employees.get(0).getName());
        } else {
            notifications.create().withCaption("Нет данного пользователя").show();
        }
    }
 /*
    @Subscribe
    protected void onAfterClose(AfterCloseEvent event) {
        try {
            if (getEditedEntity().getState().equals(",Na_oznakomlenii,")) {
                orderSubsidyuTemplateService.generateOrderLoan(getEditedEntity());
            }
        } catch (NullPointerException e) {
            System.out.println("Ошбика");
        }


    }



  */
    //событие которое появляется до открытия экрана
    @Subscribe
    protected void onInit(BeforeShowEvent event) {
        System.out.println(getEditedEntity().getState());
        try {
            if (getEditedEntity().getState().equals(",ProcessCompleted,")) {
                orderSubsidyuTemplateService.generateOrderLoan(getEditedEntity());
                exportDisplay.show(getEditedEntity().getProtocolTemplate());
            }

        } catch (NullPointerException e) {
            System.out.println("Ошбика");
        }

        //условие что процесса в карточки нет и при нулевом процессе только создается карточка
        if (getEditedEntity().getProcs() == null) {
            Proc proc = dataManager.load(Proc.class)
                    .query("select e from wf$Proc e where e.code = :code")
                    .parameter("code", "EndorseAKK")
                    .view("browse")
                    .one();


            //Подгрузка процесса согласовнаия для карточки распорядение
            CardProc cardProc = dataManager.create(CardProc.class);

            cardProc.setCard(getEditedEntity());

            cardProc.setProc(proc);

            cardProcDs.addItem(cardProc);

            cardProcDs.refresh();

            System.out.println(getEditedEntity().getState());
        }
    }

    @Inject
    private FileUploadField protocolTemplate1;

    @Inject
    private FileUploadingAPI fileUploadingAPI;

    @Inject
    protected TimeSource timeSource;

    @Subscribe("attachAction")
    protected void onSomeActionActionPerformed(Action.ActionPerformedEvent event) {

        ThesisCardAttachment cardAttachment = dataManager.create(ThesisCardAttachment.class);

        AttachmentType attachmentType = dataManager.load(AttachmentType.class)
                .query("select e from wf$AttachmentType e where e.code = :code")
                .parameter("code", "AttachmentType.attachment")
                .view("attachmentType-view")
                .one();

        cardAttachment.setAttachType(attachmentType);

        cardAttachment.setName("Распоряжение на выплату страховой премии");

        cardAttachment.setCreateTs(timeSource.currentTimestamp());

        cardAttachment.setUuid(getEditedEntity().getUuid());

        cardAttachment.setSign(true);
        //cardAttachment.setAttachType(attachmentType);

        cardAttachment.setFile(getEditedEntity().getProtocolTemplate());

        cardAttachment.setCard(getEditedEntity());

        attachmentsDs.addItem(cardAttachment);
    }

    //добавления получателя в процесс
    @Subscribe("orderReceiver.greeting")
    protected void onPickerFieldGreetingActionPerformed(Action.ActionPerformedEvent event) {

        //создание процесса согласования с ЭЦЦ (нужно внимательно посмотреть код процесса в Адмнистрирование - процессы - код)
        Proc proc = dataManager.load(Proc.class)
                .query("select e from wf$Proc e where e.code = :code")
                .parameter("code", "EndorseAKK")
                .view("browse")
                .one();

        ProcRole oznakomitel = dataManager.load(ProcRole.class)
                .query("select e from wf$ProcRole e where e.code = 'Получатель' and e.proc.id = :procId")
                .parameter("procId", proc.getId())
                .view("browse")
                .one();
        //если получаетеля нет то выведит увеодмления что получателя нет
        if (orderReceiver.getValue() == null) {
            notifications.create().withCaption("Вы не выбрали получателя").show();
        } else {

            //создание роли "Получатель" в карточки процесса
            CardRole cardInitiatior = dataManager.create(CardRole.class);

            cardInitiatior.setCode("Получатель");

            cardInitiatior.setCard(getEditedEntity());

            cardInitiatior.setProcRole(oznakomitel);

            cardInitiatior.setUser(orderReceiver.getValue().getUser());

            cardRolesDs.addItem(cardInitiatior);

            notifications.create().withCaption("Получатель добавлен").show();

        }
    }

}