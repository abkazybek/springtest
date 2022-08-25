/*
 * Copyright (c) 2022 LTD Haulmont Samara. All Rights Reserved.
 * Haulmont Samara proprietary and confidential.
 * Use is subject to license terms.
 */

package com.company.thesissummer.entity.table1c;

import com.company.thesissummer.entity.OrderRembursement;
import com.haulmont.cuba.core.entity.StandardEntity;

import javax.persistence.*;

@Table(name = "THESISSUMMER_ORDER_REMBURSEMENT1C")
@Entity(name = "thesissummer$OrderRembursement1c")
public class OrderRembursement1c extends StandardEntity {
    private static final long serialVersionUID = 4545227640599879112L;

    @Column(name = "NUMBER_")
    protected String number;

    @Column(name = "NAMEOFBORROWER")
    protected String nameofborrower;

    @Column(name = "IIN")
    protected String iin;

    @Column(name = "NOMER_AND_DATA_KREDITNOGOG_DOGOVORA")
    protected String nomerAndDataKreditnogogDogovora;

    @Column(name = "SUMMA_SUB")
    protected String summaSub;

    @Column(name = "IIK_ZAEM")
    protected String iikZaem;

    @Column(name = "BANK")
    protected String bank;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "ORDER_REMBURSEMENT_ID")
    protected OrderRembursement orderRembursement;

    public OrderRembursement getOrderRembursement() {
        return orderRembursement;
    }

    public void setOrderRembursement(OrderRembursement orderRembursement) {
        this.orderRembursement = orderRembursement;
    }

    public String getBank() {
        return bank;
    }

    public void setBank(String bank) {
        this.bank = bank;
    }

    public String getIikZaem() {
        return iikZaem;
    }

    public void setIikZaem(String iikZaem) {
        this.iikZaem = iikZaem;
    }

    public String getSummaSub() {
        return summaSub;
    }

    public void setSummaSub(String summaSub) {
        this.summaSub = summaSub;
    }

    public String getNomerAndDataKreditnogogDogovora() {
        return nomerAndDataKreditnogogDogovora;
    }

    public void setNomerAndDataKreditnogogDogovora(String nomerAndDataKreditnogogDogovora) {
        this.nomerAndDataKreditnogogDogovora = nomerAndDataKreditnogogDogovora;
    }

    public String getIin() {
        return iin;
    }

    public void setIin(String iin) {
        this.iin = iin;
    }

    public String getNameofborrower() {
        return nameofborrower;
    }

    public void setNameofborrower(String nameofborrower) {
        this.nameofborrower = nameofborrower;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }
}