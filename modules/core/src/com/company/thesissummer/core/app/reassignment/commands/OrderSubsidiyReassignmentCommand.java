/*
 * Copyright (c) 2022 LTD Haulmont Samara. All Rights Reserved.
 * Haulmont Samara proprietary and confidential.
 * Use is subject to license terms.
 */

package com.company.thesissummer.core.app.reassignment.commands;

import com.haulmont.thesis.core.app.reassignment.commands.AbstractDocReassignmentCommand;
import com.company.thesissummer.entity.OrderSubsidiy;

import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component(OrderSubsidiyReassignmentCommand.NAME)
public class OrderSubsidiyReassignmentCommand extends AbstractDocReassignmentCommand<OrderSubsidiy> {
    protected static final String NAME = "thesissummer_OrderSubsidiyReassignmentCommand";

    @PostConstruct
    protected void postInit() {
        type = "OrderSubsidiy";
        docQuery = String.format(DOC_QUERY_TEMPLATE, "thesissummer$OrderSubsidiy");
    }

    @Override
    public String getName() {
        return NAME;
    }
}