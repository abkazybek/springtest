/*
 * Copyright (c) 2022 LTD Haulmont Samara. All Rights Reserved.
 * Haulmont Samara proprietary and confidential.
 * Use is subject to license terms.
 */

package com.company.thesissummer.service;

import com.haulmont.cuba.core.entity.Entity;

public interface OrderTemplateService {
    String NAME = "thesissummer_OrderTemplateService";

    void generateTemplate(Entity nomerOrder);
}