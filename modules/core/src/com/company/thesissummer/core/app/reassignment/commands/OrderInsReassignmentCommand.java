/*
 * Copyright (c) 2022 LTD Haulmont Samara. All Rights Reserved.
 * Haulmont Samara proprietary and confidential.
 * Use is subject to license terms.
 */

package com.company.thesissummer.core.app.reassignment.commands;

import com.haulmont.thesis.core.app.reassignment.commands.AbstractDocReassignmentCommand;
import com.company.thesissummer.entity.OrderIns;

import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component(OrderInsReassignmentCommand.NAME)
public class OrderInsReassignmentCommand extends AbstractDocReassignmentCommand<OrderIns> {
    protected static final String NAME = "thesissummer_OrderInsReassignmentCommand";

    @PostConstruct
    protected void postInit() {
        type = "OrderIns";
        docQuery = String.format(DOC_QUERY_TEMPLATE, "thesissummer$OrderIns");
    }

    @Override
    public String getName() {
        return NAME;
    }
}