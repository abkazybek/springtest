/*
 * Copyright (c) 2022 LTD Haulmont Samara. All Rights Reserved.
 * Haulmont Samara proprietary and confidential.
 * Use is subject to license terms.
 */

package com.company.thesissummer.core.app.reassignment.commands;

import com.haulmont.thesis.core.app.reassignment.commands.AbstractDocReassignmentCommand;
import com.company.thesissummer.entity.OrderCenBumaga;

import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component(OrderCenBumagaReassignmentCommand.NAME)
public class OrderCenBumagaReassignmentCommand extends AbstractDocReassignmentCommand<OrderCenBumaga> {
    protected static final String NAME = "thesissummer_OrderCenBumagaReassignmentCommand";

    @PostConstruct
    protected void postInit() {
        type = "OrderCenBumaga";
        docQuery = String.format(DOC_QUERY_TEMPLATE, "thesissummer$OrderCenBumaga");
    }

    @Override
    public String getName() {
        return NAME;
    }
}