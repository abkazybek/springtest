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

@DiscriminatorValue("3700")
@Table(name = "THESISSUMMER_ZADANIE_NA_PLATEZH")
@Entity(name = "thesissummer$ZadanieNaPlatezh")
@Listeners("thesis_DocEntityListener")
@EnableRestore
@TrackEditScreenHistory
@PrimaryKeyJoinColumn(name = "CARD_ID", referencedColumnName = "ID")
public class ZadanieNaPlatezh extends Doc implements HasDetailedDescription {

    private static final long serialVersionUID = -3506383120055552677L;

    @Column(name = "NAIMENOVANIE_POL")
    protected String naimenovaniePol;

    @Column(name = "SUMMA_TENGE")
    protected String summaTenge;

    @Column(name = "SUMMA_DOLLAR")
    protected String summaDollar;

    @Column(name = "NOMER_AND_DATA_DOGOVORA")
    protected String nomerAndDataDogovora;

    @Column(name = "NAZNACH_PLATESZ")
    protected String naznachPlatesz;

    @Column(name = "SCHET_NA_OPLATU")
    protected String schetNaOplatu;

    @Column(name = "NOMER_STATI")
    protected String nomerStati;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ORDER_RECEIVER_ID")
    protected ExtEmployee orderReceiver;

    public ExtEmployee getOrderReceiver() {
        return orderReceiver;
    }

    public void setOrderReceiver(ExtEmployee orderReceiver) {
        this.orderReceiver = orderReceiver;
    }

    public String getNaznachPlatesz() {
        return naznachPlatesz;
    }

    public void setNaznachPlatesz(String naznachPlatesz) {
        this.naznachPlatesz = naznachPlatesz;
    }

    public String getNomerStati() {
        return nomerStati;
    }

    public void setNomerStati(String nomerStati) {
        this.nomerStati = nomerStati;
    }

    public String getSchetNaOplatu() {
        return schetNaOplatu;
    }

    public void setSchetNaOplatu(String schetNaOplatu) {
        this.schetNaOplatu = schetNaOplatu;
    }

    public String getNomerAndDataDogovora() {
        return nomerAndDataDogovora;
    }

    public void setNomerAndDataDogovora(String nomerAndDataDogovora) {
        this.nomerAndDataDogovora = nomerAndDataDogovora;
    }

    public String getSummaDollar() {
        return summaDollar;
    }

    public void setSummaDollar(String summaDollar) {
        this.summaDollar = summaDollar;
    }

    public String getSummaTenge() {
        return summaTenge;
    }

    public void setSummaTenge(String summaTenge) {
        this.summaTenge = summaTenge;
    }

    public String getNaimenovaniePol() {
        return naimenovaniePol;
    }

    public void setNaimenovaniePol(String naimenovaniePol) {
        this.naimenovaniePol = naimenovaniePol;
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