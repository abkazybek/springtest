/*
 * Copyright (c) 2022 LTD Haulmont Samara. All Rights Reserved.
 * Haulmont Samara proprietary and confidential.
 * Use is subject to license terms.
 */

package com.company.thesissummer.entity;

import com.haulmont.cuba.core.entity.StandardEntity;

import javax.persistence.*;

@Table(name = "THESISSUMMER_ORDER_DOCUMENT1C")
@Entity(name = "thesissummer$OrderDocument1C")
public class OrderDocument1C extends StandardEntity {
    private static final long serialVersionUID = 3623573510669365597L;

    @Column(name = "NOMER_TABLE")
    protected String nomerTable;

    @Column(name = "ZAEMCHIK_TABLE")
    protected String zaemchikTable;

    @Column(name = "BIN_TABLE")
    protected String binTable;

    @Column(name = "ZALOGODATEL_TABLE")
    protected String zalogodatelTable;

    @Column(name = "NAIMENOVANIYA_ZAEMCHIKA_TABLE")
    protected String naimenovaniyaZaemchikaTable;

    @Column(name = "NAIMENOVANIYA_ZALOGODATELYA_TABLE")
    protected String naimenovaniyaZalogodatelyaTable;

    @Column(name = "DOGOVOR_SVYAZ_S_OBESPECH_TABLE")
    protected String dogovorSvyazSObespechTable;

    @Column(name = "PREDMET_TABLE")
    protected String predmetTable;

    @Column(name = "TEKUCHAYA_STOIMOST_TABLE")
    protected String tekuchayaStoimostTable;

    @Column(name = "PREOCEN_STOIMOST_TABLE")
    protected String preocenStoimostTable;

    @Column(name = "OBESPECHENIYA_TABLE")
    protected String obespecheniyaTable;

    @Column(name = "ZALOG_STOIMOST_TABLE")
    protected String zalogStoimostTable;

    @Column(name = "ITOG_SUMMA_K_SPISANIYA")
    protected String itogSummaKSpisaniya;

    @Column(name = "DATA_PEREOCEN")
    protected String dataPereocen;

    @Column(name = "OSNOVANIYA_TABLE")
    protected String osnovaniyaTable;

    @Column(name = "DOCUMENT_USLOVIY_TABLE")
    protected String documentUsloviyTable;

    @Column(name = "IIN_TABLE")
    protected String iinTable;

    @Column(name = "NOMER_AND_DATA_KREDITNIY_DOGOVOR_TABLE")
    protected String nomerAndDataKreditniyDogovorTable;

    @Column(name = "SUMMA_SUBSIDIY_DLYA_PERECHESLENIYA_TABLE")
    protected String summaSubsidiyDlyaPerechesleniyaTable;

    @Column(name = "OTKLONENIE_PO_SUMME_SUBSIDIY_TABLE")
    protected String otkloneniePoSummeSubsidiyTable;

    @Column(name = "IIK_ZAEMCHIKA_DLYA_PERECHESLENIYA_SUB_TABLE")
    protected String iikZaemchikaDlyaPerechesleniyaSubTable;

    @Column(name = "BANK_TABLE")
    protected String bankTable;

    @Column(name = "POYASNENIYE_TABLE")
    protected String poyasneniyeTable;
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "ORDER_DOCUMENT_ID")
    protected OrderDocument orderDocument;

    public OrderDocument getOrderDocument() {
        return orderDocument;
    }

    public void setOrderDocument(OrderDocument orderDocument) {
        this.orderDocument = orderDocument;
    }

    public String getPoyasneniyeTable() {
        return poyasneniyeTable;
    }

    public void setPoyasneniyeTable(String poyasneniyeTable) {
        this.poyasneniyeTable = poyasneniyeTable;
    }

    public String getBankTable() {
        return bankTable;
    }

    public void setBankTable(String bankTable) {
        this.bankTable = bankTable;
    }

    public String getIikZaemchikaDlyaPerechesleniyaSubTable() {
        return iikZaemchikaDlyaPerechesleniyaSubTable;
    }

    public void setIikZaemchikaDlyaPerechesleniyaSubTable(String iikZaemchikaDlyaPerechesleniyaSubTable) {
        this.iikZaemchikaDlyaPerechesleniyaSubTable = iikZaemchikaDlyaPerechesleniyaSubTable;
    }

    public String getOtkloneniePoSummeSubsidiyTable() {
        return otkloneniePoSummeSubsidiyTable;
    }

    public void setOtkloneniePoSummeSubsidiyTable(String otkloneniePoSummeSubsidiyTable) {
        this.otkloneniePoSummeSubsidiyTable = otkloneniePoSummeSubsidiyTable;
    }

    public String getSummaSubsidiyDlyaPerechesleniyaTable() {
        return summaSubsidiyDlyaPerechesleniyaTable;
    }

    public void setSummaSubsidiyDlyaPerechesleniyaTable(String summaSubsidiyDlyaPerechesleniyaTable) {
        this.summaSubsidiyDlyaPerechesleniyaTable = summaSubsidiyDlyaPerechesleniyaTable;
    }

    public String getNomerAndDataKreditniyDogovorTable() {
        return nomerAndDataKreditniyDogovorTable;
    }

    public void setNomerAndDataKreditniyDogovorTable(String nomerAndDataKreditniyDogovorTable) {
        this.nomerAndDataKreditniyDogovorTable = nomerAndDataKreditniyDogovorTable;
    }

    public String getIinTable() {
        return iinTable;
    }

    public void setIinTable(String iinTable) {
        this.iinTable = iinTable;
    }

    public String getDocumentUsloviyTable() {
        return documentUsloviyTable;
    }

    public void setDocumentUsloviyTable(String documentUsloviyTable) {
        this.documentUsloviyTable = documentUsloviyTable;
    }

    public String getOsnovaniyaTable() {
        return osnovaniyaTable;
    }

    public void setOsnovaniyaTable(String osnovaniyaTable) {
        this.osnovaniyaTable = osnovaniyaTable;
    }

    public String getDataPereocen() {
        return dataPereocen;
    }

    public void setDataPereocen(String dataPereocen) {
        this.dataPereocen = dataPereocen;
    }

    public String getItogSummaKSpisaniya() {
        return itogSummaKSpisaniya;
    }

    public void setItogSummaKSpisaniya(String itogSummaKSpisaniya) {
        this.itogSummaKSpisaniya = itogSummaKSpisaniya;
    }

    public String getZalogStoimostTable() {
        return zalogStoimostTable;
    }

    public void setZalogStoimostTable(String zalogStoimostTable) {
        this.zalogStoimostTable = zalogStoimostTable;
    }

    public String getObespecheniyaTable() {
        return obespecheniyaTable;
    }

    public void setObespecheniyaTable(String obespecheniyaTable) {
        this.obespecheniyaTable = obespecheniyaTable;
    }

    public String getPreocenStoimostTable() {
        return preocenStoimostTable;
    }

    public void setPreocenStoimostTable(String preocenStoimostTable) {
        this.preocenStoimostTable = preocenStoimostTable;
    }

    public String getTekuchayaStoimostTable() {
        return tekuchayaStoimostTable;
    }

    public void setTekuchayaStoimostTable(String tekuchayaStoimostTable) {
        this.tekuchayaStoimostTable = tekuchayaStoimostTable;
    }

    public String getPredmetTable() {
        return predmetTable;
    }

    public void setPredmetTable(String predmetTable) {
        this.predmetTable = predmetTable;
    }

    public String getDogovorSvyazSObespechTable() {
        return dogovorSvyazSObespechTable;
    }

    public void setDogovorSvyazSObespechTable(String dogovorSvyazSObespechTable) {
        this.dogovorSvyazSObespechTable = dogovorSvyazSObespechTable;
    }

    public String getNaimenovaniyaZalogodatelyaTable() {
        return naimenovaniyaZalogodatelyaTable;
    }

    public void setNaimenovaniyaZalogodatelyaTable(String naimenovaniyaZalogodatelyaTable) {
        this.naimenovaniyaZalogodatelyaTable = naimenovaniyaZalogodatelyaTable;
    }

    public String getNaimenovaniyaZaemchikaTable() {
        return naimenovaniyaZaemchikaTable;
    }

    public void setNaimenovaniyaZaemchikaTable(String naimenovaniyaZaemchikaTable) {
        this.naimenovaniyaZaemchikaTable = naimenovaniyaZaemchikaTable;
    }

    public String getZalogodatelTable() {
        return zalogodatelTable;
    }

    public void setZalogodatelTable(String zalogodatelTable) {
        this.zalogodatelTable = zalogodatelTable;
    }

    public String getBinTable() {
        return binTable;
    }

    public void setBinTable(String binTable) {
        this.binTable = binTable;
    }

    public String getZaemchikTable() {
        return zaemchikTable;
    }

    public void setZaemchikTable(String zaemchikTable) {
        this.zaemchikTable = zaemchikTable;
    }

    public String getNomerTable() {
        return nomerTable;
    }

    public void setNomerTable(String nomerTable) {
        this.nomerTable = nomerTable;
    }
}