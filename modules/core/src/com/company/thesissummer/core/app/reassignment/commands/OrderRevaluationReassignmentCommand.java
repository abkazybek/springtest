/*
 * Copyright (c) 2022 LTD Haulmont Samara. All Rights Reserved.
 * Haulmont Samara proprietary and confidential.
 * Use is subject to license terms.
 */

package com.company.thesissummer.core.app.reassignment.commands;

import com.haulmont.thesis.core.app.reassignment.commands.AbstractDocReassignmentCommand;
import com.company.thesissummer.entity.OrderRevaluation;

import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component(OrderRevaluationReassignmentCommand.NAME)
public class OrderRevaluationReassignmentCommand extends AbstractDocReassignmentCommand<OrderRevaluation> {
    protected static final String NAME = "thesissummer_OrderRevaluationReassignmentCommand";

    @PostConstruct
    protected void postInit() {
        type = "OrderRevaluation";
        docQuery = String.format(DOC_QUERY_TEMPLATE, "thesissummer$OrderRevaluation");
    }

    @Override
    public String getName() {
        return NAME;
    }
}