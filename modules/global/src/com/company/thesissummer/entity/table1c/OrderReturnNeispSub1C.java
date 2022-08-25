/*
 * Copyright (c) 2022 LTD Haulmont Samara. All Rights Reserved.
 * Haulmont Samara proprietary and confidential.
 * Use is subject to license terms.
 */

package com.company.thesissummer.entity.table1c;

import com.company.thesissummer.entity.OrderReturnNeispSub;
import com.haulmont.cuba.core.entity.StandardEntity;

import javax.persistence.*;

@Table(name = "THESISSUMMER_ORDER_RETURN_NEISP_SUB1C")
@Entity(name = "thesissummer$OrderReturnNeispSub1C")
public class OrderReturnNeispSub1C extends StandardEntity {
    private static final long serialVersionUID = 1306426067804240551L;

    @Column(name = "NOMER")
    protected String nomer;

    @Column(name = "NAIMENOVANIE_ZAEMCHIKA")
    protected String naimenovanieZaemchika;

    @Column(name = "NOMER_DATA_KREDITNOGO_DOGOVORA")
    protected String nomerDataKreditnogoDogovora;

    @Column(name = "SUMMA_SUBSIDIY_OTKLONENIYA")
    protected String summaSubsidiyOtkloneniya;

    @Column(name = "DOGOVOR_SUBSIDIROVANIYA")
    protected String dogovorSubsidirovaniya;

    @Column(name = "POYASNENIE")
    protected String poyasnenie;
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "ORDER_RETURN_NEISP_SUB_ID")
    protected OrderReturnNeispSub orderReturnNeispSub;

    public OrderReturnNeispSub getOrderReturnNeispSub() {
        return orderReturnNeispSub;
    }

    public void setOrderReturnNeispSub(OrderReturnNeispSub orderReturnNeispSub) {
        this.orderReturnNeispSub = orderReturnNeispSub;
    }

    public String getPoyasnenie() {
        return poyasnenie;
    }

    public void setPoyasnenie(String poyasnenie) {
        this.poyasnenie = poyasnenie;
    }

    public String getDogovorSubsidirovaniya() {
        return dogovorSubsidirovaniya;
    }

    public void setDogovorSubsidirovaniya(String dogovorSubsidirovaniya) {
        this.dogovorSubsidirovaniya = dogovorSubsidirovaniya;
    }

    public String getSummaSubsidiyOtkloneniya() {
        return summaSubsidiyOtkloneniya;
    }

    public void setSummaSubsidiyOtkloneniya(String summaSubsidiyOtkloneniya) {
        this.summaSubsidiyOtkloneniya = summaSubsidiyOtkloneniya;
    }

    public String getNomerDataKreditnogoDogovora() {
        return nomerDataKreditnogoDogovora;
    }

    public void setNomerDataKreditnogoDogovora(String nomerDataKreditnogoDogovora) {
        this.nomerDataKreditnogoDogovora = nomerDataKreditnogoDogovora;
    }

    public String getNaimenovanieZaemchika() {
        return naimenovanieZaemchika;
    }

    public void setNaimenovanieZaemchika(String naimenovanieZaemchika) {
        this.naimenovanieZaemchika = naimenovanieZaemchika;
    }

    public String getNomer() {
        return nomer;
    }

    public void setNomer(String nomer) {
        this.nomer = nomer;
    }
}