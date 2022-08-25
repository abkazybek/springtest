/*
 * Copyright (c) 2022 LTD Haulmont Samara. All Rights Reserved.
 * Haulmont Samara proprietary and confidential.
 * Use is subject to license terms.
 */

package com.company.thesissummer.core.app.reassignment.commands;

import com.haulmont.thesis.core.app.reassignment.commands.AbstractDocReassignmentCommand;
import com.company.thesissummer.entity.OrderRembursement;

import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component(OrderRembursementReassignmentCommand.NAME)
public class OrderRembursementReassignmentCommand extends AbstractDocReassignmentCommand<OrderRembursement> {
    protected static final String NAME = "thesissummer_OrderRembursementReassignmentCommand";

    @PostConstruct
    protected void postInit() {
        type = "OrderRembursement";
        docQuery = String.format(DOC_QUERY_TEMPLATE, "thesissummer$OrderRembursement");
    }

    @Override
    public String getName() {
        return NAME;
    }
}