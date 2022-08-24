/*
 * Copyright (c) 2022 LTD Haulmont Samara. All Rights Reserved.
 * Haulmont Samara proprietary and confidential.
 * Use is subject to license terms.
 */

package com.company.thesissummer.core.app.reassignment.commands;

import com.haulmont.thesis.core.app.reassignment.commands.AbstractDocReassignmentCommand;
import com.company.thesissummer.entity.OrderDocument;

import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component(OrderDocumentReassignmentCommand.NAME)
public class OrderDocumentReassignmentCommand extends AbstractDocReassignmentCommand<OrderDocument> {
    protected static final String NAME = "thesissummer_OrderDocumentReassignmentCommand";

    @PostConstruct
    protected void postInit() {
        type = "OrderDocument";
        docQuery = String.format(DOC_QUERY_TEMPLATE, "thesissummer$OrderDocument");
    }

    @Override
    public String getName() {
        return NAME;
    }
}