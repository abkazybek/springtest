/*
 * Copyright (c) 2022 LTD Haulmont Samara. All Rights Reserved.
 * Haulmont Samara proprietary and confidential.
 * Use is subject to license terms.
 */

package com.company.thesissummer.core.app.reassignment.commands;

import com.haulmont.thesis.core.app.reassignment.commands.AbstractDocReassignmentCommand;
import com.company.thesissummer.entity.OrderPosting;

import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component(OrderPostingReassignmentCommand.NAME)
public class OrderPostingReassignmentCommand extends AbstractDocReassignmentCommand<OrderPosting> {
    protected static final String NAME = "thesissummer_OrderPostingReassignmentCommand";

    @PostConstruct
    protected void postInit() {
        type = "OrderPosting";
        docQuery = String.format(DOC_QUERY_TEMPLATE, "thesissummer$OrderPosting");
    }

    @Override
    public String getName() {
        return NAME;
    }
}