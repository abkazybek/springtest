/*
 * Copyright (c) 2022 LTD Haulmont Samara. All Rights Reserved.
 * Haulmont Samara proprietary and confidential.
 * Use is subject to license terms.
 */

package com.company.thesissummer.core.app.reassignment.commands;

import com.haulmont.thesis.core.app.reassignment.commands.AbstractDocReassignmentCommand;
import com.company.thesissummer.entity.OrderPogashOb;

import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component(OrderPogashObReassignmentCommand.NAME)
public class OrderPogashObReassignmentCommand extends AbstractDocReassignmentCommand<OrderPogashOb> {
    protected static final String NAME = "thesissummer_OrderPogashObReassignmentCommand";

    @PostConstruct
    protected void postInit() {
        type = "OrderPogashOb";
        docQuery = String.format(DOC_QUERY_TEMPLATE, "thesissummer$OrderPogashOb");
    }

    @Override
    public String getName() {
        return NAME;
    }
}