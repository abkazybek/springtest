/*
 * Copyright (c) 2022 LTD Haulmont Samara. All Rights Reserved.
 * Haulmont Samara proprietary and confidential.
 * Use is subject to license terms.
 */

package com.company.thesissummer.core.app.reassignment.commands;

import com.haulmont.thesis.core.app.reassignment.commands.AbstractDocReassignmentCommand;
import com.company.thesissummer.entity.OrderReturnNeispSub;

import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component(OrderReturnNeispSubReassignmentCommand.NAME)
public class OrderReturnNeispSubReassignmentCommand extends AbstractDocReassignmentCommand<OrderReturnNeispSub> {
    protected static final String NAME = "thesissummer_OrderReturnNeispSubReassignmentCommand";

    @PostConstruct
    protected void postInit() {
        type = "OrderReturnNeispSub";
        docQuery = String.format(DOC_QUERY_TEMPLATE, "thesissummer$OrderReturnNeispSub");
    }

    @Override
    public String getName() {
        return NAME;
    }
}