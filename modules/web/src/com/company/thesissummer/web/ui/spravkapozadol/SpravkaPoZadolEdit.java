/*
 * Copyright (c) 2022 LTD Haulmont Samara. All Rights Reserved.
 * Haulmont Samara proprietary and confidential.
 * Use is subject to license terms.
 */

package com.company.thesissummer.web.ui.spravkapozadol;

import com.company.thesissummer.entity.ExtEmployee;
import com.haulmont.cuba.core.global.UserSessionSource;
import com.haulmont.cuba.gui.Notifications;
import com.haulmont.cuba.gui.components.Action;
import com.haulmont.cuba.gui.screen.Subscribe;
import com.haulmont.cuba.security.entity.User;
import com.haulmont.thesis.web.actions.PrintReportAction;
import com.haulmont.thesis.web.ui.basicdoc.editor.AbstractDocEditor;
import com.haulmont.thesis.web.voice.VoiceActionPriorities;
import com.haulmont.cuba.gui.components.LookupPickerField;
import com.haulmont.thesis.core.entity.DocCategory;
import com.company.thesissummer.entity.SpravkaPoZadol;
import com.haulmont.workflow.core.entity.CardProc;
import com.haulmont.workflow.core.entity.CardRole;
import com.haulmont.workflow.core.entity.Proc;
import com.haulmont.workflow.core.entity.ProcRole;

import javax.inject.Inject;
import java.util.List;
import java.util.Map;

import static com.haulmont.thesis.web.voice.VoiceCompanionsRepository.voiceCompanion;

public class SpravkaPoZadolEdit<T extends SpravkaPoZadol> extends AbstractDocEditor<T> {

    @Inject
    protected LookupPickerField<DocCategory> docCategory;

    @Inject
    protected UserSessionSource userSessionSource;

    @Inject
    public LookupPickerField owner;

    @Override
    public void init(Map<String, Object> params) {
        super.init(params);
        initVoiceControl();
        User user = userSessionSource.getUserSession().getCurrentOrSubstitutedUser();
        List<ExtEmployee> employees = dataManager.load(ExtEmployee.class)
                .query("select e from DF_EMPLOYEE e where e.name = :name")
                .parameter("name", user.getName())
                .view("extEmployee-view")
                .list();
        if(employees.size() != 0) {
            owner.setCaption(employees.get(0).getName());
        }  else {
            notifications.create().withCaption("Нет данного пользователя").show();
        }
    }

    @Override
    protected String getHiddenTabsConfig() {
        return "";
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
        hiddenTabs.put("office", getMessage("office"));
        hiddenTabs.put("attachmentsTab", getMessage("attachmentsTab"));
        hiddenTabs.put("docTreeTab", getMessage("docTreeTab"));
        if (getAccessData().getNotVersion()) {
            hiddenTabs.put("cardCommentTab", getMessage("cardCommentTab"));
        }
        super.fillHiddenTabs();
    }

    @Inject
    private Notifications notifications;

    @Inject
    private LookupPickerField<ExtEmployee> poluchatel;


    @Subscribe
    protected void onInit(BeforeShowEvent event) {

        Proc proc = dataManager.load(Proc.class)
                .query("select e from wf$Proc e where e.code = :code")
                .parameter("code", "SpravkaKod")
                .view("browse")
                .one();

        //Подгрузка процесса согласовнаия для карточки распорядение
        CardProc cardProc = dataManager.create(CardProc.class);

        cardProc.setCard(getEditedEntity());

        cardProc.setProc(proc);

        cardProcDs.addItem(cardProc);

        cardProcDs.refresh();

    }


    @Subscribe("poluchatel.greeting")
    protected void onPickerFieldGreetingActionPerformed(Action.ActionPerformedEvent event) {

        Proc proc = dataManager.load(Proc.class)
                .query("select e from wf$Proc e where e.code = :code")
                .parameter("code", "EndorseAKK")
                .view("browse")
                .one();

        ProcRole initiator = dataManager.load(ProcRole.class)
                .query("select e from wf$ProcRole e where e.code = 'Инициатор' and e.proc.id = :procId")
                .parameter("procId", proc.getId())
                .view("browse")
                .one();

        ProcRole oznakomitel = dataManager.load(ProcRole.class)
                .query("select e from wf$ProcRole e where e.code = 'Получатель' and e.proc.id = :procId")
                .parameter("procId", proc.getId())
                .view("browse")
                .one();

        if (poluchatel.getValue() == null) {
            notifications.create().withCaption("Вы не выбрали получателя").show();
        } else {

            CardRole cardInitiator = dataManager.create(CardRole.class);

            cardInitiator.setCode("Инициатор");

            cardInitiator.setCard(getEditedEntity());

            cardInitiator.setProcRole(initiator);

            User user = userSessionSource.getUserSession().getCurrentOrSubstitutedUser();
            List<ExtEmployee> employees = dataManager.load(ExtEmployee.class)
                    .query("select e from DF_EMPLOYEE e where e.name = :name")
                    .parameter("name", user.getName())
                    .view("extEmployee-view")
                    .list();
            if(employees.size() != 0) {
                cardInitiator.setUser(employees.get(0).getUser());
            }  else {
                notifications.create().withCaption("Нет данного пользователя").show();
            }

            cardRolesDs.addItem(cardInitiator);

            CardRole cardOznakomitel = dataManager.create(CardRole.class);

            cardOznakomitel.setCode("Получатель");

            cardOznakomitel.setCard(getEditedEntity());

            cardOznakomitel.setProcRole(oznakomitel);

            cardOznakomitel.setUser(poluchatel.getValue().getUser());

            cardRolesDs.addItem(cardOznakomitel);

            notifications.create().withCaption("Получатель добавлен").show();


        }
    }
}