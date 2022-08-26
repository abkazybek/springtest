/*
 * Copyright (c) 2022 LTD Haulmont Samara. All Rights Reserved.
 * Haulmont Samara proprietary and confidential.
 * Use is subject to license terms.
 */

package com.company.thesissummer.core.app.reassignment.commands;

import com.haulmont.thesis.core.app.reassignment.commands.AbstractDocReassignmentCommand;
import com.company.thesissummer.entity.OrderNaVozvrat;

import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component(OrderNaVozvratReassignmentCommand.NAME)
public class OrderNaVozvratReassignmentCommand extends AbstractDocReassignmentCommand<OrderNaVozvrat> {
    protected static final String NAME = "thesissummer_OrderNaVozvratReassignmentCommand";

    @PostConstruct
    protected void postInit() {
        type = "OrderNaVozvrat";
        docQuery = String.format(DOC_QUERY_TEMPLATE, "thesissummer$OrderNaVozvrat");
    }

    @Override
    public String getName() {
        return NAME;
    }
}