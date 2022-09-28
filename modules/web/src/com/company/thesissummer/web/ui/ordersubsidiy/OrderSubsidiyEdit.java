/*
 * Copyright (c) 2022 LTD Haulmont Samara. All Rights Reserved.
 * Haulmont Samara proprietary and confidential.
 * Use is subject to license terms.
 */

package com.company.thesissummer.web.ui.ordersubsidiy;

import com.company.thesissummer.entity.ExtEmployee;
import com.haulmont.cuba.core.global.CommitContext;
import com.haulmont.cuba.core.global.UserSessionSource;
import com.haulmont.cuba.gui.Notifications;
import com.haulmont.cuba.gui.components.Action;
import com.haulmont.cuba.gui.screen.Subscribe;
import com.haulmont.cuba.security.entity.User;
import com.haulmont.thesis.core.entity.Employee;
import com.haulmont.thesis.web.actions.PrintReportAction;
import com.haulmont.thesis.web.ui.basicdoc.editor.AbstractDocEditor;
import com.haulmont.thesis.web.voice.VoiceActionPriorities;
import com.haulmont.cuba.gui.components.LookupPickerField;
import com.haulmont.thesis.core.entity.DocCategory;
import com.company.thesissummer.entity.OrderSubsidiy;
import com.haulmont.workflow.core.entity.CardProc;
import com.haulmont.workflow.core.entity.CardRole;
import com.haulmont.workflow.core.entity.Proc;
import com.haulmont.workflow.core.entity.ProcRole;

import javax.inject.Inject;
import java.util.List;
import java.util.Map;

import static com.haulmont.thesis.web.voice.VoiceCompanionsRepository.voiceCompanion;

public class OrderSubsidiyEdit<T extends OrderSubsidiy> extends AbstractDocEditor<T> {

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
                .list();
        if(employees.size() != 0) {
            owner.setCaption(employees.get(0).getName());
        }  else {
            notifications.create().withCaption("Нет данного пользователя").show();
        }
    }

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
        //hiddenTabs.put("office", getMessage("office"));
        hiddenTabs.put("attachmentsTab", getMessage("attachmentsTab"));
        //hiddenTabs.put("docTreeTab", getMessage("docTreeTab"));
        if (getAccessData().getNotVersion()) {
            hiddenTabs.put("cardCommentTab", getMessage("cardCommentTab"));
        }
        super.fillHiddenTabs();
    }

    @Inject
    private Notifications notifications;

    @Inject
    private LookupPickerField<ExtEmployee> orderReceiver;

    @Inject
    public CommitContext commitContext;

    @Subscribe
    protected void onInit(BeforeShowEvent event) {

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

    }


    @Subscribe("orderReceiver.greeting")
    protected void onPickerFieldGreetingActionPerformed(Action.ActionPerformedEvent event) {

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


        if (orderReceiver.getValue() == null) {
            notifications.create().withCaption("Вы не выбрали получателя").show();
        } else {

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