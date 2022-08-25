/*
 * Copyright (c) 2022 LTD Haulmont Samara. All Rights Reserved.
 * Haulmont Samara proprietary and confidential.
 * Use is subject to license terms.
 */

package com.company.thesissummer.entity.table1c;

import com.company.thesissummer.entity.OrderPosting;
import com.haulmont.cuba.core.entity.StandardEntity;

import javax.persistence.*;

@Table(name = "THESISSUMMER_ORDER_POSTING1C")
@Entity(name = "thesissummer$OrderPosting1C")
public class OrderPosting1C extends StandardEntity {
    private static final long serialVersionUID = -7493671449522849064L;

    @Column(name = "NUMBER_")
    protected String number;

    @Column(name = "NAMEOFPLEDGER")
    protected String nameofpledger;

    @Column(name = "DOGOVORWITHCOLLATERAL")
    protected String dogovorwithcollateral;

    @Column(name = "OBESPECHENIYE")
    protected String obespecheniye;

    @Column(name = "ZALOG_STOIMOST")
    protected String zalogStoimost;

    @Column(name = "DOCUMENT_USL")
    protected String documentUsl;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "ORDER_POSTING_ID")
    protected OrderPosting orderPosting;

    public OrderPosting getOrderPosting() {
        return orderPosting;
    }

    public void setOrderPosting(OrderPosting orderPosting) {
        this.orderPosting = orderPosting;
    }

    public String getDocumentUsl() {
        return documentUsl;
    }

    public void setDocumentUsl(String documentUsl) {
        this.documentUsl = documentUsl;
    }

    public String getZalogStoimost() {
        return zalogStoimost;
    }

    public void setZalogStoimost(String zalogStoimost) {
        this.zalogStoimost = zalogStoimost;
    }

    public String getObespecheniye() {
        return obespecheniye;
    }

    public void setObespecheniye(String obespecheniye) {
        this.obespecheniye = obespecheniye;
    }

    public String getDogovorwithcollateral() {
        return dogovorwithcollateral;
    }

    public void setDogovorwithcollateral(String dogovorwithcollateral) {
        this.dogovorwithcollateral = dogovorwithcollateral;
    }

    public String getNameofpledger() {
        return nameofpledger;
    }

    public void setNameofpledger(String nameofpledger) {
        this.nameofpledger = nameofpledger;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }
}