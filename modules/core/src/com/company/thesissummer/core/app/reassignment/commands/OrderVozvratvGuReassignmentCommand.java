/*
 * Copyright (c) 2022 LTD Haulmont Samara. All Rights Reserved.
 * Haulmont Samara proprietary and confidential.
 * Use is subject to license terms.
 */

package com.company.thesissummer.core.app.reassignment.commands;

import com.haulmont.thesis.core.app.reassignment.commands.AbstractDocReassignmentCommand;
import com.company.thesissummer.entity.OrderVozvratvGu;

import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component(OrderVozvratvGuReassignmentCommand.NAME)
public class OrderVozvratvGuReassignmentCommand extends AbstractDocReassignmentCommand<OrderVozvratvGu> {
    protected static final String NAME = "thesissummer_OrderVozvratvGuReassignmentCommand";

    @PostConstruct
    protected void postInit() {
        type = "OrderVozvratvGu";
        docQuery = String.format(DOC_QUERY_TEMPLATE, "thesissummer$OrderVozvratvGu");
    }

    @Override
    public String getName() {
        return NAME;
    }
}