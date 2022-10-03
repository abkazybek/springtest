/*
 * Copyright (c) 2022 LTD Haulmont Samara. All Rights Reserved.
 * Haulmont Samara proprietary and confidential.
 * Use is subject to license terms.
 */

package com.company.thesissummer.core;

import com.company.thesissummer.entity.OrderLoan;
import org.springframework.context.ApplicationEvent;

import javax.persistence.criteria.Order;

public class OrderReceiverEvent extends ApplicationEvent{

    private final OrderLoan orderLoan;
    /**
     * Create a new {@code ApplicationEvent}.
     *
     * @param source the object on which the event initially occurred or with
     *               which the event is associated (never {@code null})
     * @param orderLoan
     */
    public OrderReceiverEvent(Object source, OrderLoan orderLoan) {
        super(source);
        this.orderLoan = orderLoan;
    }

    public OrderLoan getOrderLoan() { return orderLoan;}
}
