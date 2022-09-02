/*
 * Copyright (c) 2022 LTD Haulmont Samara. All Rights Reserved.
 * Haulmont Samara proprietary and confidential.
 * Use is subject to license terms.
 */

package com.company.thesissummer.service.parsingorders;

public interface OrderLoanParsService {
    String NAME = "thesissummer_OrderLoanParsService";

    String saveXML(String xml);
}