/*
 * Copyright (c) 2022 LTD Haulmont Samara. All Rights Reserved.
 * Haulmont Samara proprietary and confidential.
 * Use is subject to license terms.
 */

package com.company.thesissummer.core.app.reassignment.commands;

import com.haulmont.thesis.core.app.reassignment.commands.AbstractDocReassignmentCommand;
import com.company.thesissummer.entity.ProtocolKK;

import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component(ProtocolKKReassignmentCommand.NAME)
public class ProtocolKKReassignmentCommand extends AbstractDocReassignmentCommand<ProtocolKK> {
    protected static final String NAME = "thesissummer_ProtocolKKReassignmentCommand";

    @PostConstruct
    protected void postInit() {
        type = "ProtocolKK";
        docQuery = String.format(DOC_QUERY_TEMPLATE, "thesissummer$ProtocolKK");
    }

    @Override
    public String getName() {
        return NAME;
    }
}