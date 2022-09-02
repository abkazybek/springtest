/*
 * Copyright (c) 2022 LTD Haulmont Samara. All Rights Reserved.
 * Haulmont Samara proprietary and confidential.
 * Use is subject to license terms.
 */

package com.company.thesissummer.service.parsingorders;

public interface OrderLoanSiParsService {
    String NAME = "thesissummer_OrderLoanSiParsService";

    String saveXML(String xml);
}