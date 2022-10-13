/*
 * Copyright (c) 2022 LTD Haulmont Samara. All Rights Reserved.
 * Haulmont Samara proprietary and confidential.
 * Use is subject to license terms.
 */

package com.company.thesissummer.core.app.reassignment.commands;

import com.haulmont.thesis.core.app.reassignment.commands.AbstractDocReassignmentCommand;
import com.company.thesissummer.entity.OrderLoan;

import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component(OrderLoanReassignmentCommand.NAME)
public class OrderLoanReassignmentCommand extends AbstractDocReassignmentCommand<OrderLoan> {
    protected static final String NAME = "thesissummer_OrderLoanReassignmentCommand";

    @PostConstruct
    protected void postInit() {
        type = "OrderLoan";
        docQuery = String.format(DOC_QUERY_TEMPLATE, "thesissummer$OrderLoan");
    }

    @Override
    public String getName() {
        return NAME;
    }
}