/*
 * Copyright (c) 2022 LTD Haulmont Samara. All Rights Reserved.
 * Haulmont Samara proprietary and confidential.
 * Use is subject to license terms.
 */

package com.company.thesissummer.service.parsingorders;

public interface OrderTransferParseService {
    String NAME = "thesissummer_OrderTransferParseService";

    String saveXML(String xml);
}