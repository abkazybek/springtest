/*
 * Copyright (c) 2022 LTD Haulmont Samara. All Rights Reserved.
 * Haulmont Samara proprietary and confidential.
 * Use is subject to license terms.
 */

package com.company.thesissummer.entity;

import com.haulmont.chile.core.datatypes.Datatypes;
import com.haulmont.chile.core.model.MetaClass;
import com.haulmont.cuba.core.global.AppBeans;
import com.haulmont.cuba.core.global.Metadata;
import com.haulmont.cuba.core.global.Messages;

import com.haulmont.thesis.core.entity.Doc;

import com.haulmont.thesis.core.global.EntityCopyUtils;
import org.apache.commons.lang.StringUtils;
import com.haulmont.thesis.core.entity.HasDetailedDescription;
import com.haulmont.cuba.core.entity.annotation.EnableRestore;
import com.haulmont.cuba.core.entity.annotation.TrackEditScreenHistory;

import java.text.SimpleDateFormat;
import java.util.Locale;
import java.util.Set;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.DiscriminatorValue;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Column;

import com.haulmont.cuba.core.entity.annotation.Listeners;

@DiscriminatorValue("2300")
@Table(name = "THESISSUMMER_ORDER_LOAN_SI")
@Entity(name = "thesissummer$OrderLoanSI")
@Listeners("thesis_DocEntityListener")
@EnableRestore
@TrackEditScreenHistory
@PrimaryKeyJoinColumn(name = "CARD_ID", referencedColumnName = "ID")
public class OrderLoanSI extends Doc implements HasDetailedDescription {

    private static final long serialVersionUID = 4521058267386229113L;
    @Column(name = "NOMER_RASP")
    protected String nomerRasp;
    @Column(name = "DATA_RASP")
    protected String dataRasp;
    @Column(name = "PODRAZDELENIE")
    protected String podrazdelenie;
    @Column(name = "OSNOVANIE")
    protected String osnovanie;
    @Column(name = "KONTRAGENT")
    protected String kontragent;
    @Column(name = "IIN")
    protected String iin;
    @Column(name = "KONECH_ZAEMCHIK")
    protected String konechZaemchik;
    @Column(name = "DOGOVOR")
    protected String dogovor;
    @Column(name = "SUMMA")
    protected String summa;
    @Column(name = "STAVKA_VOZ")
    protected String stavkaVoz;
    @Column(name = "SROK_KREDITA")
    protected String srokKredita;
    @Column(name = "BANK")
    protected String bank;
    @Column(name = "BIK")
    protected String bik;
    @Column(name = "IIK")
    protected String iik;
    @Column(name = "BIN")
    protected String bin;
    @Column(name = "KBE")
    protected String kbe;
    @Column(name = "UR_ADRES")
    protected String urAdres;

    public String getUrAdres() {
        return urAdres;
    }

    public void setUrAdres(String urAdres) {
        this.urAdres = urAdres;
    }

    public String getKbe() {
        return kbe;
    }

    public void setKbe(String kbe) {
        this.kbe = kbe;
    }

    public String getBin() {
        return bin;
    }

    public void setBin(String bin) {
        this.bin = bin;
    }

    public String getIik() {
        return iik;
    }

    public void setIik(String iik) {
        this.iik = iik;
    }

    public String getBik() {
        return bik;
    }

    public void setBik(String bik) {
        this.bik = bik;
    }

    public String getBank() {
        return bank;
    }

    public void setBank(String bank) {
        this.bank = bank;
    }

    public String getSrokKredita() {
        return srokKredita;
    }

    public void setSrokKredita(String srokKredita) {
        this.srokKredita = srokKredita;
    }

    public String getStavkaVoz() {
        return stavkaVoz;
    }

    public void setStavkaVoz(String stavkaVoz) {
        this.stavkaVoz = stavkaVoz;
    }

    public String getSumma() {
        return summa;
    }

    public void setSumma(String summa) {
        this.summa = summa;
    }

    public String getDogovor() {
        return dogovor;
    }

    public void setDogovor(String dogovor) {
        this.dogovor = dogovor;
    }

    public String getKonechZaemchik() {
        return konechZaemchik;
    }

    public void setKonechZaemchik(String konechZaemchik) {
        this.konechZaemchik = konechZaemchik;
    }

    public String getIin() {
        return iin;
    }

    public void setIin(String iin) {
        this.iin = iin;
    }

    public String getKontragent() {
        return kontragent;
    }

    public void setKontragent(String kontragent) {
        this.kontragent = kontragent;
    }

    public String getOsnovanie() {
        return osnovanie;
    }

    public void setOsnovanie(String osnovanie) {
        this.osnovanie = osnovanie;
    }

    public String getPodrazdelenie() {
        return podrazdelenie;
    }

    public void setPodrazdelenie(String podrazdelenie) {
        this.podrazdelenie = podrazdelenie;
    }

    public String getDataRasp() {
        return dataRasp;
    }

    public void setDataRasp(String dataRasp) {
        this.dataRasp = dataRasp;
    }

    public String getNomerRasp() {
        return nomerRasp;
    }

    public void setNomerRasp(String nomerRasp) {
        this.nomerRasp = nomerRasp;
    }

    @Override
    public void copyFrom(Doc srcDoc, Set<com.haulmont.cuba.core.entity.Entity> toCommit, boolean copySignatures,
                         boolean onlyLastAttachmentsVersion, boolean useOriginalAttachmentCreatorAndCreateTs,
                         boolean copyAllVersionMainAttachment) {
        super.copyFrom(srcDoc, toCommit, copySignatures, onlyLastAttachmentsVersion,
                useOriginalAttachmentCreatorAndCreateTs, copyAllVersionMainAttachment);
        Metadata metadata = AppBeans.get(Metadata.NAME);
        MetaClass metaClass = metadata.getClassNN(getClass());
        EntityCopyUtils.copyProperties(srcDoc, this, metaClass.getOwnProperties(), toCommit);
    }

    @SuppressWarnings("ConstantConditions")
    @Override
    public String getDetailedDescription(Locale locale, boolean includeState, boolean includeShortInfo) {
        Messages messages = AppBeans.get(Messages.NAME);
        String dateFormat = Datatypes.getFormatStrings(locale).getDateFormat();
        String description;
        String locState = includeState ? getLocState(locale) : StringUtils.EMPTY;

        description = getDocKind().getName() + " "
                + (StringUtils.isBlank(getNumber()) ? "" : messages.getMessage(Doc.class, "notification.number", locale) + " " + getNumber() + " ")
                + (getDate() == null ? "" : messages.getMessage(Doc.class, "notification.from", locale) + " "
                + new SimpleDateFormat(dateFormat).format(getDate()))
                + (includeState && StringUtils.isNotBlank(locState) ? " [" + locState + "]" : "");


        if (includeShortInfo && (StringUtils.isNotBlank(getTheme()) || StringUtils.isNotBlank(getComment()))) {
            int length = description.length();
            int infoLength = MAX_SUBJECT_LENGTH - length;

            if (infoLength < MIN_SHORT_INFO_LENGTH) return description;

            String shortInfo = StringUtils.defaultIfBlank(getTheme(), getComment());
            return description + " - " + StringUtils.abbreviate(shortInfo, infoLength);
        } else {
            return description;
        }
    }
}