/*
 * Copyright (c) 2022 LTD Haulmont Samara. All Rights Reserved.
 * Haulmont Samara proprietary and confidential.
 * Use is subject to license terms.
 */

package com.company.thesissummer.entity;

import com.haulmont.chile.core.annotations.NamePattern;
import com.haulmont.cuba.core.entity.annotation.EnableRestore;
import com.haulmont.cuba.core.entity.annotation.Listeners;
import com.haulmont.cuba.core.entity.annotation.TrackEditScreenHistory;
import com.haulmont.thesis.core.entity.TsCard;

import javax.persistence.*;

@DiscriminatorValue("2900")
@Table(name = "THESISSUMMER_ORDER_DOCUMENT_FULL1C")
@EnableRestore
@TrackEditScreenHistory
@Entity(name = "thesissummer$OrderDocumentFull1C")
@Listeners("thesissummer_OrderDocumentFull1CListener")
@PrimaryKeyJoinColumn(name = "CARD_ID", referencedColumnName = "ID")
@NamePattern("%s|description")
public class OrderDocumentFull1C extends TsCard {
    private static final long serialVersionUID = -2928596074895387852L;
    @Column(name = "NUMBER_")
    protected String number;
    @Column(name = "ZAEMCHIK")
    protected String zaemchik;
    @Column(name = "BIN")
    protected String bin;
    @Column(name = "ZALOGODATEL")
    protected String zalogodatel;
    @Column(name = "NAIMENOVANIE_ZAEMCHIKA")
    protected String naimenovanieZaemchika;
    @Column(name = "NAIMENOVANIE_ZALOGODATELYA")
    protected String naimenovanieZalogodatelya;
    @Column(name = "DOGOVOR_SVYAZANNI_S_OBESPECHENIEM")
    protected String dogovorSvyazanniSObespecheniem;
    @Column(name = "PREDMET")
    protected String predmet;
    @Column(name = "TEKUSHAYA_STOIMOST")
    protected String tekushayaStoimost;
    @Column(name = "PEREOCEN_STOIMOST")
    protected String pereocenStoimost;
    @Column(name = "OBESPECHENIE")
    protected String obespechenie;
    @Column(name = "ZALOGOVAYA_STOIMOST")
    protected String zalogovayaStoimost;
    @Column(name = "ITOG_SUMMA_K_SPISANIYU")
    protected String itogSummaKSpisaniyu;
    @Column(name = "DATAPEREOCEN")
    protected String datapereocen;
    @Column(name = "OSNOVANIYE")
    protected String osnovaniye;
    @Column(name = "DOCUMENT_USLOVIY")
    protected String documentUsloviy;
    @Column(name = "IIN")
    protected String iin;
    @Column(name = "NOMER_AND_DATA")
    protected String nomerAndData;
    @Column(name = "SUMMA_SUBSIDI_DLYA_PERECH")
    protected String summaSubsidiDlyaPerech;
    @Column(name = "OTKONENIYE_PO_SUMME_SUBSIDIY")
    protected String otkoneniyePoSummeSubsidiy;
    @Column(name = "IIK_ZAEMCHIKA")
    protected String iikZaemchika;
    @Column(name = "BANK")
    protected String bank;
    @Column(name = "POYASNENIE")
    protected String poyasnenie;
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "ORDER_DOCUMENT_ID")
    protected OrderDocument orderDocument;

    public OrderDocument getOrderDocument() {
        return orderDocument;
    }

    public void setOrderDocument(OrderDocument orderDocument) {
        this.orderDocument = orderDocument;
    }

    public String getPoyasnenie() {
        return poyasnenie;
    }

    public void setPoyasnenie(String poyasnenie) {
        this.poyasnenie = poyasnenie;
    }

    public String getBank() {
        return bank;
    }

    public void setBank(String bank) {
        this.bank = bank;
    }

    public String getIikZaemchika() {
        return iikZaemchika;
    }

    public void setIikZaemchika(String iikZaemchika) {
        this.iikZaemchika = iikZaemchika;
    }

    public String getOtkoneniyePoSummeSubsidiy() {
        return otkoneniyePoSummeSubsidiy;
    }

    public void setOtkoneniyePoSummeSubsidiy(String otkoneniyePoSummeSubsidiy) {
        this.otkoneniyePoSummeSubsidiy = otkoneniyePoSummeSubsidiy;
    }

    public String getSummaSubsidiDlyaPerech() {
        return summaSubsidiDlyaPerech;
    }

    public void setSummaSubsidiDlyaPerech(String summaSubsidiDlyaPerech) {
        this.summaSubsidiDlyaPerech = summaSubsidiDlyaPerech;
    }

    public String getNomerAndData() {
        return nomerAndData;
    }

    public void setNomerAndData(String nomerAndData) {
        this.nomerAndData = nomerAndData;
    }

    public String getIin() {
        return iin;
    }

    public void setIin(String iin) {
        this.iin = iin;
    }

    public String getDocumentUsloviy() {
        return documentUsloviy;
    }

    public void setDocumentUsloviy(String documentUsloviy) {
        this.documentUsloviy = documentUsloviy;
    }

    public String getOsnovaniye() {
        return osnovaniye;
    }

    public void setOsnovaniye(String osnovaniye) {
        this.osnovaniye = osnovaniye;
    }

    public String getDatapereocen() {
        return datapereocen;
    }

    public void setDatapereocen(String datapereocen) {
        this.datapereocen = datapereocen;
    }

    public String getItogSummaKSpisaniyu() {
        return itogSummaKSpisaniyu;
    }

    public void setItogSummaKSpisaniyu(String itogSummaKSpisaniyu) {
        this.itogSummaKSpisaniyu = itogSummaKSpisaniyu;
    }

    public String getZalogovayaStoimost() {
        return zalogovayaStoimost;
    }

    public void setZalogovayaStoimost(String zalogovayaStoimost) {
        this.zalogovayaStoimost = zalogovayaStoimost;
    }

    public String getObespechenie() {
        return obespechenie;
    }

    public void setObespechenie(String obespechenie) {
        this.obespechenie = obespechenie;
    }

    public String getPereocenStoimost() {
        return pereocenStoimost;
    }

    public void setPereocenStoimost(String pereocenStoimost) {
        this.pereocenStoimost = pereocenStoimost;
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

    public String getDogovorSvyazanniSObespecheniem() {
        return dogovorSvyazanniSObespecheniem;
    }

    public void setDogovorSvyazanniSObespecheniem(String dogovorSvyazanniSObespecheniem) {
        this.dogovorSvyazanniSObespecheniem = dogovorSvyazanniSObespecheniem;
    }

    public String getNaimenovanieZalogodatelya() {
        return naimenovanieZalogodatelya;
    }

    public void setNaimenovanieZalogodatelya(String naimenovanieZalogodatelya) {
        this.naimenovanieZalogodatelya = naimenovanieZalogodatelya;
    }

    public String getNaimenovanieZaemchika() {
        return naimenovanieZaemchika;
    }

    public void setNaimenovanieZaemchika(String naimenovanieZaemchika) {
        this.naimenovanieZaemchika = naimenovanieZaemchika;
    }

    public String getZalogodatel() {
        return zalogodatel;
    }

    public void setZalogodatel(String zalogodatel) {
        this.zalogodatel = zalogodatel;
    }

    public String getBin() {
        return bin;
    }

    public void setBin(String bin) {
        this.bin = bin;
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