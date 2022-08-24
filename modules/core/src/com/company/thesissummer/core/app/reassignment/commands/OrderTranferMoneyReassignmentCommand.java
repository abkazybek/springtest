/*
 * Copyright (c) 2022 LTD Haulmont Samara. All Rights Reserved.
 * Haulmont Samara proprietary and confidential.
 * Use is subject to license terms.
 */

package com.company.thesissummer.core.app.reassignment.commands;

import com.haulmont.thesis.core.app.reassignment.commands.AbstractDocReassignmentCommand;
import com.company.thesissummer.entity.OrderTranferMoney;

import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component(OrderTranferMoneyReassignmentCommand.NAME)
public class OrderTranferMoneyReassignmentCommand extends AbstractDocReassignmentCommand<OrderTranferMoney> {
    protected static final String NAME = "thesissummer_OrderTranferMoneyReassignmentCommand";

    @PostConstruct
    protected void postInit() {
        type = "OrderTranferMoney";
        docQuery = String.format(DOC_QUERY_TEMPLATE, "thesissummer$OrderTranferMoney");
    }

    @Override
    public String getName() {
        return NAME;
    }
}