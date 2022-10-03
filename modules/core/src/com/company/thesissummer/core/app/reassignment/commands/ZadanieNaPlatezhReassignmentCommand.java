/*
 * Copyright (c) 2022 LTD Haulmont Samara. All Rights Reserved.
 * Haulmont Samara proprietary and confidential.
 * Use is subject to license terms.
 */

package com.company.thesissummer.core.app.reassignment.commands;

import com.haulmont.thesis.core.app.reassignment.commands.AbstractDocReassignmentCommand;
import com.company.thesissummer.entity.ZadanieNaPlatezh;

import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component(ZadanieNaPlatezhReassignmentCommand.NAME)
public class ZadanieNaPlatezhReassignmentCommand extends AbstractDocReassignmentCommand<ZadanieNaPlatezh> {
    protected static final String NAME = "thesissummer_ZadanieNaPlatezhReassignmentCommand";

    @PostConstruct
    protected void postInit() {
        type = "ZadanieNaPlatezh";
        docQuery = String.format(DOC_QUERY_TEMPLATE, "thesissummer$ZadanieNaPlatezh");
    }

    @Override
    public String getName() {
        return NAME;
    }
}