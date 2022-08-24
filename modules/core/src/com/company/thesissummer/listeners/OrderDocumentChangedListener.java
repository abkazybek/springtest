/*
 * Copyright (c) 2022 LTD Haulmont Samara. All Rights Reserved.
 * Haulmont Samara proprietary and confidential.
 * Use is subject to license terms.
 */

package com.company.thesissummer.listeners;

import com.company.thesissummer.entity.OrderDocument;

import java.util.UUID;

import com.haulmont.cuba.core.app.events.EntityChangedEvent;
import org.springframework.stereotype.Component;
import org.springframework.transaction.event.TransactionPhase;
import org.springframework.transaction.event.TransactionalEventListener;

import javax.swing.plaf.basic.BasicToolBarUI;

@Component("thesissummer_OrderDocumentChangedListener")
public class OrderDocumentChangedListener {

}