/*
 * Copyright (c) 2022 LTD Haulmont Samara. All Rights Reserved.
 * Haulmont Samara proprietary and confidential.
 * Use is subject to license terms.
 */

package com.company.thesissummer.web.ui.orderins;

import com.haulmont.thesis.web.ui.basicdoc.browse.AbstractDocBrowser;
import com.company.thesissummer.entity.OrderIns;

import java.util.Map;

public class OrderInsBrowse<T extends OrderIns> extends AbstractDocBrowser<T> {
    @Override
    public void init(Map<String, Object> params) {
        super.init(params);
        entityName = "thesissummer$OrderIns";
    }
}