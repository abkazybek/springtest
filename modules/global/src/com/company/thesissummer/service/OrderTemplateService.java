/*
 * Copyright (c) 2022 LTD Haulmont Samara. All Rights Reserved.
 * Haulmont Samara proprietary and confidential.
 * Use is subject to license terms.
 */

package com.company.thesissummer.service;

import com.company.thesissummer.entity.OrderLoan;
import com.haulmont.cuba.core.entity.Entity;
import com.haulmont.cuba.core.global.FileStorageException;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;

import java.io.IOException;

public interface OrderTemplateService {
    String NAME = "thesissummer_OrderTemplateService";

    void generateOrderLoan(OrderLoan entityLoan);
}