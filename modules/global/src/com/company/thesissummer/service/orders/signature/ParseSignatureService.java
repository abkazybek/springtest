/*
 * Copyright (c) 2022 LTD Haulmont Samara. All Rights Reserved.
 * Haulmont Samara proprietary and confidential.
 * Use is subject to license terms.
 */

package com.company.thesissummer.service.orders.signature;

public interface ParseSignatureService {
    String NAME = "thesissummer_ParseSignatureService";

    //1C программист отправляет xml в base64
    void saveXML();
}