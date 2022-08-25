/*
 * Copyright (c) 2022 LTD Haulmont Samara. All Rights Reserved.
 * Haulmont Samara proprietary and confidential.
 * Use is subject to license terms.
 */

package com.company.thesissummer.entity.table1c;

import com.company.thesissummer.entity.OrderWriteOff;
import com.haulmont.cuba.core.entity.StandardEntity;

import javax.persistence.*;

@Table(name = "THESISSUMMER_ORDER_WRITE_OFF1C")
@Entity(name = "thesissummer$OrderWriteOff1C")
public class OrderWriteOff1C extends StandardEntity {
    private static final long serialVersionUID = -3253163342849166067L;

    @Column(name = "NUMBER_")
    protected String number;

    @Column(name = "NAIMENOVANIE_ZALOG")
    protected String naimenovanieZalog;

    @Column(name = "DOGOVOR_SVYAZ_S_OBESPECH")
    protected String dogovorSvyazSObespech;

    @Column(name = "OBESPECHENIYE")
    protected String obespecheniye;

    @Column(name = "ZALOG_STOIMOST")
    protected String zalogStoimost;

    @Column(name = "DOCUMENT_USLOVIY")
    protected String documentUsloviy;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "ORDER_WRITE_OFF_ID")
    protected OrderWriteOff orderWriteOff;

    public OrderWriteOff getOrderWriteOff() {
        return orderWriteOff;
    }

    public void setOrderWriteOff(OrderWriteOff orderWriteOff) {
        this.orderWriteOff = orderWriteOff;
    }

    public String getDocumentUsloviy() {
        return documentUsloviy;
    }

    public void setDocumentUsloviy(String documentUsloviy) {
        this.documentUsloviy = documentUsloviy;
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

    public String getDogovorSvyazSObespech() {
        return dogovorSvyazSObespech;
    }

    public void setDogovorSvyazSObespech(String dogovorSvyazSObespech) {
        this.dogovorSvyazSObespech = dogovorSvyazSObespech;
    }

    public String getNaimenovanieZalog() {
        return naimenovanieZalog;
    }

    public void setNaimenovanieZalog(String naimenovanieZalog) {
        this.naimenovanieZalog = naimenovanieZalog;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }
}