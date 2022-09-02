/*
 * Copyright (c) 2022 LTD Haulmont Samara. All Rights Reserved.
 * Haulmont Samara proprietary and confidential.
 * Use is subject to license terms.
 */

package com.company.thesissummer.web.ui.orderloansi;

import com.company.thesissummer.service.parsingorders.OrderLoanSiParsService;
import com.haulmont.thesis.web.ui.basicdoc.browse.AbstractDocBrowser;
import com.company.thesissummer.entity.OrderLoanSI;
import org.junit.experimental.categories.Categories;

import javax.inject.Inject;
import java.util.Map;

public class OrderLoanSIBrowse<T extends OrderLoanSI> extends AbstractDocBrowser<T> {
    @Override
    public void init(Map<String, Object> params) {
        super.init(params);
        entityName = "thesissummer$OrderLoanSI";
    }
}