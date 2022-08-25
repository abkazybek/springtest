/*
 * Copyright (c) 2022 LTD Haulmont Samara. All Rights Reserved.
 * Haulmont Samara proprietary and confidential.
 * Use is subject to license terms.
 */

package com.company.thesissummer.entity.table1c;

import com.company.thesissummer.entity.OrderTranfer;
import com.haulmont.cuba.core.entity.StandardEntity;

import javax.persistence.*;

@Table(name = "THESISSUMMER_ORDER_TRANFER1C")
@Entity(name = "thesissummer$OrderTranfer1C")
public class OrderTranfer1C extends StandardEntity {
    private static final long serialVersionUID = -4660724010689587904L;

    @Column(name = "NUMBER_")
    protected String number;

    @Column(name = "ZAEMCHIK")
    protected String zaemchik;

    @Column(name = "IIN")
    protected String iin;

    @Column(name = "NOMERAND_DATA_KREDITNOGO_DOGOVORA")
    protected String nomerandDataKreditnogoDogovora;

    @Column(name = "SUMMA_SUBSIDII_KREDITNOGO_DOGOVORA")
    protected String summaSubsidiiKreditnogoDogovora;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "ORDER_TRANFER_ID")
    protected OrderTranfer orderTranfer;

    public OrderTranfer getOrderTranfer() {
        return orderTranfer;
    }

    public void setOrderTranfer(OrderTranfer orderTranfer) {
        this.orderTranfer = orderTranfer;
    }

    public String getSummaSubsidiiKreditnogoDogovora() {
        return summaSubsidiiKreditnogoDogovora;
    }

    public void setSummaSubsidiiKreditnogoDogovora(String summaSubsidiiKreditnogoDogovora) {
        this.summaSubsidiiKreditnogoDogovora = summaSubsidiiKreditnogoDogovora;
    }

    public String getNomerandDataKreditnogoDogovora() {
        return nomerandDataKreditnogoDogovora;
    }

    public void setNomerandDataKreditnogoDogovora(String nomerandDataKreditnogoDogovora) {
        this.nomerandDataKreditnogoDogovora = nomerandDataKreditnogoDogovora;
    }

    public String getIin() {
        return iin;
    }

    public void setIin(String iin) {
        this.iin = iin;
    }

    public String getZaemchik() {
        return zaemchik;
    }

    public void setZaemchik(String zaemchik) {
        this.zaemchik = zaemchik;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }
}