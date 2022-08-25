/*
 * Copyright (c) 2022 LTD Haulmont Samara. All Rights Reserved.
 * Haulmont Samara proprietary and confidential.
 * Use is subject to license terms.
 */

package com.company.thesissummer.core.app.reassignment.commands;

import com.haulmont.thesis.core.app.reassignment.commands.AbstractDocReassignmentCommand;
import com.company.thesissummer.entity.OrderWriteOff;

import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component(OrderWriteOffReassignmentCommand.NAME)
public class OrderWriteOffReassignmentCommand extends AbstractDocReassignmentCommand<OrderWriteOff> {
    protected static final String NAME = "thesissummer_OrderWriteOffReassignmentCommand";

    @PostConstruct
    protected void postInit() {
        type = "OrderWriteOff";
        docQuery = String.format(DOC_QUERY_TEMPLATE, "thesissummer$OrderWriteOff");
    }

    @Override
    public String getName() {
        return NAME;
    }
}