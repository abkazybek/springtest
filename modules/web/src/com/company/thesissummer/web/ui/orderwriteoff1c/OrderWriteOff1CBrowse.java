/*
 * Copyright (c) 2022 LTD Haulmont Samara. All Rights Reserved.
 * Haulmont Samara proprietary and confidential.
 * Use is subject to license terms.
 */

package com.company.thesissummer.web.ui.orderwriteoff1c;

import com.haulmont.cuba.gui.components.AbstractLookup;
import com.haulmont.cuba.gui.components.GroupTable;
import com.haulmont.cuba.gui.components.Table;
import com.haulmont.cuba.gui.screen.Subscribe;
import com.haulmont.thesis.core.entity.SimpleDoc;

import javax.ejb.Init;
import javax.inject.Inject;

public class OrderWriteOff1CBrowse extends AbstractLookup {

    @Inject
    public Table orderWriteOff1CsTable;
}