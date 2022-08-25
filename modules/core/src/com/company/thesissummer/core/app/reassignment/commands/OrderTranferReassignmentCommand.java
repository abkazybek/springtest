/*
 * Copyright (c) 2022 LTD Haulmont Samara. All Rights Reserved.
 * Haulmont Samara proprietary and confidential.
 * Use is subject to license terms.
 */

package com.company.thesissummer.core.app.reassignment.commands;

import com.haulmont.thesis.core.app.reassignment.commands.AbstractDocReassignmentCommand;
import com.company.thesissummer.entity.OrderTranfer;

import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component(OrderTranferReassignmentCommand.NAME)
public class OrderTranferReassignmentCommand extends AbstractDocReassignmentCommand<OrderTranfer> {
    protected static final String NAME = "thesissummer_OrderTranferReassignmentCommand";

    @PostConstruct
    protected void postInit() {
        type = "OrderTranfer";
        docQuery = String.format(DOC_QUERY_TEMPLATE, "thesissummer$OrderTranfer");
    }

    @Override
    public String getName() {
        return NAME;
    }
}