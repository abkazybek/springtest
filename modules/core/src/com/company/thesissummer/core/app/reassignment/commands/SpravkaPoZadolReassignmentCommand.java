/*
 * Copyright (c) 2022 LTD Haulmont Samara. All Rights Reserved.
 * Haulmont Samara proprietary and confidential.
 * Use is subject to license terms.
 */

package com.company.thesissummer.core.app.reassignment.commands;

import com.haulmont.thesis.core.app.reassignment.commands.AbstractDocReassignmentCommand;
import com.company.thesissummer.entity.SpravkaPoZadol;

import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component(SpravkaPoZadolReassignmentCommand.NAME)
public class SpravkaPoZadolReassignmentCommand extends AbstractDocReassignmentCommand<SpravkaPoZadol> {
    protected static final String NAME = "thesissummer_SpravkaPoZadolReassignmentCommand";

    @PostConstruct
    protected void postInit() {
        type = "SpravkaPoZadol";
        docQuery = String.format(DOC_QUERY_TEMPLATE, "thesissummer$SpravkaPoZadol");
    }

    @Override
    public String getName() {
        return NAME;
    }
}