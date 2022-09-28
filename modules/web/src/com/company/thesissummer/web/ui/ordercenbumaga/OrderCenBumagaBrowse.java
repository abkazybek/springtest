/*
 * Copyright (c) 2022 LTD Haulmont Samara. All Rights Reserved.
 * Haulmont Samara proprietary and confidential.
 * Use is subject to license terms.
 */

package com.company.thesissummer.web.ui.ordercenbumaga;

import com.haulmont.cuba.core.global.DataManager;
import com.haulmont.cuba.gui.components.Table;
import com.haulmont.cuba.gui.screen.Subscribe;
import com.haulmont.thesis.web.ui.basicdoc.browse.AbstractDocBrowser;
import com.company.thesissummer.entity.OrderCenBumaga;
import com.haulmont.workflow.core.entity.CardProc;
import com.haulmont.workflow.core.entity.Proc;
import com.haulmont.workflow.core.entity.ProcRole;

import javax.inject.Inject;
import java.util.Map;

public class OrderCenBumagaBrowse<T extends OrderCenBumaga> extends AbstractDocBrowser<T> {

    @Inject
    public DataManager dataManager;

    @Override
    public void init(Map<String, Object> params) {
        super.init(params);
        entityName = "thesissummer$OrderCenBumaga";
    }



}