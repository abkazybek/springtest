/*
 * Copyright (c) 2022 LTD Haulmont Samara. All Rights Reserved.
 * Haulmont Samara proprietary and confidential.
 * Use is subject to license terms.
 */

package com.company.thesissummer.service;

import com.company.thesissummer.core.OrderReceiverEvent;
import com.company.thesissummer.entity.OrderLoan;
import com.haulmont.cuba.core.global.*;
import org.springframework.stereotype.Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.inject.Inject;

@Service(OrderMessageService.NAME)
public class OrderMessageServiceBean implements OrderMessageService {

    private static final Logger log = LoggerFactory.getLogger(OrderMessageServiceBean.class);

    @Inject
    private Events events;

    @Inject
    private DataManager dataManager;


    @Override
    public boolean completeOrders(OrderLoan orderLoan) {
        if (orderLoan.getOrderReceiver() != null) {
            markVisitAsComplete(orderLoan);
            notifyAboutVisitCompletion(orderLoan);
            return true;
        }
        else {
            log.warn("Visit {} is not active. Mark as complete is not possible", orderLoan);
            return false;
        }

    }

    private void notifyAboutVisitCompletion(OrderLoan orderLoan) {
        events.publish(new OrderReceiverEvent(this, orderLoan));
    }

    private void markVisitAsComplete(OrderLoan orderLoan) {
        orderLoan.getOrderReceiver();
        dataManager.commit(orderLoan);
        log.info("Visit {} marked as complete", orderLoan);
    }

}