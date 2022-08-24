/*
 * Copyright (c) 2022 LTD Haulmont Samara. All Rights Reserved.
 * Haulmont Samara proprietary and confidential.
 * Use is subject to license terms.
 */

package com.company.thesissummer.core.app.reassignment.commands;

import com.haulmont.thesis.core.app.reassignment.commands.AbstractDocReassignmentCommand;
import com.company.thesissummer.entity.Memo;

import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component(MemoReassignmentCommand.NAME)
public class MemoReassignmentCommand extends AbstractDocReassignmentCommand<Memo> {
    protected static final String NAME = "thesissummer_MemoReassignmentCommand";

    @PostConstruct
    protected void postInit() {
        type = "Memo";
        docQuery = String.format(DOC_QUERY_TEMPLATE, "thesissummer$Memo");
    }

    @Override
    public String getName() {
        return NAME;
    }
}