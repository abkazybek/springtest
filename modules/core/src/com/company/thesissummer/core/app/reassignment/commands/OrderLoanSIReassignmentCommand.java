/*
 * Copyright (c) 2022 LTD Haulmont Samara. All Rights Reserved.
 * Haulmont Samara proprietary and confidential.
 * Use is subject to license terms.
 */

package com.company.thesissummer.core.app.reassignment.commands;

import com.haulmont.thesis.core.app.reassignment.commands.AbstractDocReassignmentCommand;
import com.company.thesissummer.entity.OrderLoanSI;

import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component(OrderLoanSIReassignmentCommand.NAME)
public class OrderLoanSIReassignmentCommand extends AbstractDocReassignmentCommand<OrderLoanSI> {
    protected static final String NAME = "thesissummer_OrderLoanSIReassignmentCommand";

    @PostConstruct
    protected void postInit() {
        type = "OrderLoanSI";
        docQuery = String.format(DOC_QUERY_TEMPLATE, "thesissummer$OrderLoanSI");
    }

    @Override
    public String getName() {
        return NAME;
    }
}