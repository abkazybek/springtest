/*
 * Copyright (c) 2022 LTD Haulmont Samara. All Rights Reserved.
 * Haulmont Samara proprietary and confidential.
 * Use is subject to license terms.
 */

package com.company.thesissummer.web.ui.orderloan;

import com.company.thesissummer.service.OrderMessageService;
import com.company.thesissummer.service.orders.signature.ParseSignatureService;
import com.company.thesissummer.service.parsingorders.OrderLoanParsService;
import com.haulmont.cuba.core.global.CommitContext;
import com.haulmont.cuba.core.global.DataManager;
import com.haulmont.cuba.core.global.Events;
import com.haulmont.cuba.core.global.UserSessionSource;
import com.haulmont.cuba.gui.Notifications;
import com.haulmont.cuba.gui.components.Action;
import com.haulmont.cuba.gui.components.GroupTable;
import com.haulmont.cuba.gui.screen.StandardEditor;
import com.haulmont.cuba.gui.screen.Subscribe;
import com.haulmont.cuba.security.entity.User;
import com.haulmont.thesis.gui.processaction.task.NotificationAction;
import com.haulmont.thesis.web.gui.components.NotificationPanel;
import com.haulmont.thesis.web.ui.basicdoc.browse.AbstractDocBrowser;
import com.company.thesissummer.entity.OrderLoan;
import com.haulmont.workflow.core.entity.CardProc;
import com.haulmont.workflow.core.entity.CardRole;
import com.haulmont.workflow.core.entity.Proc;
import com.haulmont.workflow.core.entity.ProcRole;
import com.vaadin.ui.Notification;
import org.checkerframework.checker.units.qual.C;

import javax.inject.Inject;
import javax.management.remote.NotificationResult;
import java.util.List;
import java.util.Map;

public class OrderLoanBrowse<T extends OrderLoan> extends AbstractDocBrowser<T> {
    @Override
    public void init(Map<String, Object> params) {
        super.init(params);
        entityName = "thesissummer$OrderLoan";
        parseSignatureService.saveXML();
    }

    @Inject
    private ParseSignatureService parseSignatureService;

    public DataManager dataManager;

    @Inject
    private Notifications notifications;

    @Inject
    protected NotificationPanel notificationPanel;

    @Inject
    private OrderMessageService orderMessageService;

    @Inject
    protected UserSessionSource userSessionSource;

    @Inject
    private Events events;


    @Subscribe("cardsTable.completeVisit")
    protected void completeVisit(Action.ActionPerformedEvent event) {
        OrderLoan orderLoan = cardsTable.getSingleSelected();
        boolean visitWasCompleted = orderMessageService.completeOrders(orderLoan);

        if (visitWasCompleted) {
            events.publish(new OrderClickEvent(this));
            notifications.create(Notifications.NotificationType.HUMANIZED)
                    .withCaption(messages.formatMessage(this.getClass(), "Распоряжение отправлено получателю"))
                    .show();}
        else {
            notifications.create(Notifications.NotificationType.ERROR)
                    .withCaption(messages.formatMessage(this.getClass(), "У вас нет получателя, поэтому вам хана"))
                    .show();
        }
    }



        //if (cardsTable.getSingleSelected() != null) {

}