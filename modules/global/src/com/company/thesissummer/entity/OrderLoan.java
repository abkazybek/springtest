/*
 * Copyright (c) 2022 LTD Haulmont Samara. All Rights Reserved.
 * Haulmont Samara proprietary and confidential.
 * Use is subject to license terms.
 */

package com.company.thesissummer.entity;

import com.haulmont.chile.core.datatypes.Datatypes;
import com.haulmont.chile.core.model.MetaClass;
import com.haulmont.cuba.core.entity.annotation.EnableRestore;
import com.haulmont.cuba.core.entity.annotation.Listeners;
import com.haulmont.cuba.core.entity.annotation.PublishEntityChangedEvents;
import com.haulmont.cuba.core.entity.annotation.TrackEditScreenHistory;
import com.haulmont.cuba.core.global.AppBeans;
import com.haulmont.cuba.core.global.Messages;
import com.haulmont.cuba.core.global.Metadata;
import com.haulmont.thesis.core.entity.Doc;
import com.haulmont.thesis.core.entity.HasDetailedDescription;
import com.haulmont.thesis.core.global.EntityCopyUtils;
import org.apache.commons.lang.StringUtils;

import javax.persistence.*;
import java.text.SimpleDateFormat;
import java.util.Locale;
import java.util.Set;

@PublishEntityChangedEvents
@DiscriminatorValue("2000")
@Table(name = "THESISSUMMER_ORDER_LOAN")
@Entity(name = "thesissummer$OrderLoan")
@Listeners("thesis_DocEntityListener")
@EnableRestore
@TrackEditScreenHistory
@PrimaryKeyJoinColumn(name = "CARD_ID", referencedColumnName = "ID")
public class OrderLoan extends Doc implements HasDetailedDescription, HasSignatureOrderLoan {

    private static final long serialVersionUID = 4058041244022781874L;

    @Column(name = "PODRAZDELENIE")
    protected String podrazdelenie;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ORDER_RECEIVER_ID")
    protected ExtEmployee orderReceiver;

    @Column(name = "NOMER_ORDER")
    protected String nomerOrder;

    @Column(name = "DATA_ORDER")
    protected String dataOrder;

    @Column(name = "PODRAZDELENIE2")
    protected String podrazdelenie2;

    @Column(name = "NOMER_PROTOCOLA_KK")
    protected String nomerProtocolaKK;

    @Column(name = "CONTRAGENT")
    protected String contragent;

    @Column(name = "FULL_NAME")
    protected String fullName;

    @Column(name = "DOGOVOR")
    protected String dogovor;

    @Column(name = "SUMMA")
    protected String summa;

    @Column(name = "KREDIT_LINIYA")
    protected String kreditLiniya;

    @Column(name = "ISTOCHNIK")
    protected String istochnik;

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

    @Column(name = "UR_ADRESS")
    protected String urAdress;

    public ExtEmployee getOrderReceiver() {
        return orderReceiver;
    }

    public void setOrderReceiver(ExtEmployee orderReceiver) {
        this.orderReceiver = orderReceiver;
    }

    public String getUrAdress() {
        return urAdress;
    }

    public void setUrAdress(String urAdress) {
        this.urAdress = urAdress;
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

    public String getIstochnik() {
        return istochnik;
    }

    public void setIstochnik(String istochnik) {
        this.istochnik = istochnik;
    }

    public String getKreditLiniya() {
        return kreditLiniya;
    }

    public void setKreditLiniya(String kreditLiniya) {
        this.kreditLiniya = kreditLiniya;
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

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getContragent() {
        return contragent;
    }

    public void setContragent(String contragent) {
        this.contragent = contragent;
    }

    public String getNomerProtocolaKK() {
        return nomerProtocolaKK;
    }

    public void setNomerProtocolaKK(String nomerProtocolaKK) {
        this.nomerProtocolaKK = nomerProtocolaKK;
    }

    public String getPodrazdelenie2() {
        return podrazdelenie2;
    }

    public void setPodrazdelenie2(String podrazdelenie2) {
        this.podrazdelenie2 = podrazdelenie2;
    }

    public String getDataOrder() {
        return dataOrder;
    }

    public void setDataOrder(String dataOrder) {
        this.dataOrder = dataOrder;
    }

    public String getNomerOrder() {
        return nomerOrder;
    }

    public void setNomerOrder(String nomerOrder) {
        this.nomerOrder = nomerOrder;
    }

    public String getPodrazdelenie() {
        return podrazdelenie;
    }

    public void setPodrazdelenie(String podrazdelenie) {
        this.podrazdelenie = podrazdelenie;
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

    public void setSignatures(String signatures) {
        this.signatures = signatures;
    }
}