/*
 * Copyright (c) 2022 LTD Haulmont Samara. All Rights Reserved.
 * Haulmont Samara proprietary and confidential.
 * Use is subject to license terms.
 */

package com.company.thesissummer.entity.table1c;

import com.company.thesissummer.entity.OrderRevaluation;
import com.haulmont.cuba.core.entity.StandardEntity;

import javax.persistence.*;

@Table(name = "THESISSUMMER_ORDER_REVALUATION1C")
@Entity(name = "thesissummer$OrderRevaluation1C")
public class OrderRevaluation1C extends StandardEntity {
    private static final long serialVersionUID = 1691858048232362237L;

    @Column(name = "NUMBER_")
    protected String number;

    @Column(name = "ZALOGODATEL")
    protected String zalogodatel;

    @Column(name = "DOGOVORSWITHCOLLATERAL")
    protected String dogovorswithcollateral;

    @Column(name = "PREDMET")
    protected String predmet;

    @Column(name = "TEKUSHAYA_STOIMOST")
    protected String tekushayaStoimost;

    @Column(name = "PEREOCENENNYA_STOIMOST")
    protected String pereocenennyaStoimost;

    @Column(name = "ITOGOVYA_SUMMA")
    protected String itogovyaSumma;

    @Column(name = "DATA_PEREOCENKI")
    protected String dataPereocenki;

    @Column(name = "OSNOVANIYE")
    protected String osnovaniye;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "ORDER_REVALUATION_ID")
    protected OrderRevaluation orderRevaluation;

    public OrderRevaluation getOrderRevaluation() {
        return orderRevaluation;
    }

    public void setOrderRevaluation(OrderRevaluation orderRevaluation) {
        this.orderRevaluation = orderRevaluation;
    }

    public String getOsnovaniye() {
        return osnovaniye;
    }

    public void setOsnovaniye(String osnovaniye) {
        this.osnovaniye = osnovaniye;
    }

    public String getDataPereocenki() {
        return dataPereocenki;
    }

    public void setDataPereocenki(String dataPereocenki) {
        this.dataPereocenki = dataPereocenki;
    }

    public String getItogovyaSumma() {
        return itogovyaSumma;
    }

    public void setItogovyaSumma(String itogovyaSumma) {
        this.itogovyaSumma = itogovyaSumma;
    }

    public String getPereocenennyaStoimost() {
        return pereocenennyaStoimost;
    }

    public void setPereocenennyaStoimost(String pereocenennyaStoimost) {
        this.pereocenennyaStoimost = pereocenennyaStoimost;
    }

    public String getTekushayaStoimost() {
        return tekushayaStoimost;
    }

    public void setTekushayaStoimost(String tekushayaStoimost) {
        this.tekushayaStoimost = tekushayaStoimost;
    }

    public String getPredmet() {
        return predmet;
    }

    public void setPredmet(String predmet) {
        this.predmet = predmet;
    }

    public String getDogovorswithcollateral() {
        return dogovorswithcollateral;
    }

    public void setDogovorswithcollateral(String dogovorswithcollateral) {
        this.dogovorswithcollateral = dogovorswithcollateral;
    }

    public String getZalogodatel() {
        return zalogodatel;
    }

    public void setZalogodatel(String zalogodatel) {
        this.zalogodatel = zalogodatel;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }
}