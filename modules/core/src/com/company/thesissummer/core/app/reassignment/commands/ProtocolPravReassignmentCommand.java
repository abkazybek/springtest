/*
 * Copyright (c) 2022 LTD Haulmont Samara. All Rights Reserved.
 * Haulmont Samara proprietary and confidential.
 * Use is subject to license terms.
 */

package com.company.thesissummer.core.app.reassignment.commands;

import com.haulmont.thesis.core.app.reassignment.commands.AbstractDocReassignmentCommand;
import com.company.thesissummer.entity.ProtocolPrav;

import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component(ProtocolPravReassignmentCommand.NAME)
public class ProtocolPravReassignmentCommand extends AbstractDocReassignmentCommand<ProtocolPrav> {
    protected static final String NAME = "thesissummer_ProtocolPravReassignmentCommand";

    @PostConstruct
    protected void postInit() {
        type = "ProtocolPrav";
        docQuery = String.format(DOC_QUERY_TEMPLATE, "thesissummer$ProtocolPrav");
    }

    @Override
    public String getName() {
        return NAME;
    }
}