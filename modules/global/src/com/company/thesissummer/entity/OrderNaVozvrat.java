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

@DiscriminatorValue("3200")
@Table(name = "THESISSUMMER_ORDER_NA_VOZVRAT")
@Entity(name = "thesissummer$OrderNaVozvrat")
@Listeners("thesis_DocEntityListener")
@EnableRestore
@TrackEditScreenHistory
@PrimaryKeyJoinColumn(name = "CARD_ID", referencedColumnName = "ID")
public class OrderNaVozvrat extends Doc implements HasDetailedDescription {

    private static final long serialVersionUID = 6049867578990856803L;

    @Column(name = "NOMER_ORDER")
    protected String nomerOrder;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ORDER_RECEIVER_ID")
    protected ExtEmployee orderReceiver;

    @Column(name = "DATA_ORDER")
    protected String dataOrder;

    @Column(name = "PODRAZDELENIE")
    protected String podrazdelenie;

    @Column(name = "ISH_PISMO")
    protected String ishPismo;

    @Column(name = "PODRAZDELENIE_AKIMATA")
    protected String podrazdelenieAkimata;

    @Column(name = "RAYON")
    protected String rayon;

    @Column(name = "OBLAST")
    protected String oblast;

    @Column(name = "BASE_DOUBT")
    protected String baseDoubt;

    @Column(name = "SUMMA_DLYA_PERECH")
    protected String summaDlyaPerech;

    @Column(name = "POLUCHATEL")
    protected String poluchatel;

    @Column(name = "BIN")
    protected String bin;

    @Column(name = "IIK")
    protected String iik;

    @Column(name = "BIK")
    protected String bik;

    @Column(name = "KNP")
    protected String knp;

    @Column(name = "KOD")
    protected String kod;

    public ExtEmployee getOrderReceiver() {
        return orderReceiver;
    }

    public void setOrderReceiver(ExtEmployee orderReceiver) {
        this.orderReceiver = orderReceiver;
    }

    public String getBaseDoubt() {
        return baseDoubt;
    }

    public void setBaseDoubt(String baseDoubt) {
        this.baseDoubt = baseDoubt;
    }

    public String getKod() {
        return kod;
    }

    public void setKod(String kod) {
        this.kod = kod;
    }

    public String getKnp() {
        return knp;
    }

    public void setKnp(String knp) {
        this.knp = knp;
    }

    public String getBik() {
        return bik;
    }

    public void setBik(String bik) {
        this.bik = bik;
    }

    public String getIik() {
        return iik;
    }

    public void setIik(String iik) {
        this.iik = iik;
    }

    public String getBin() {
        return bin;
    }

    public void setBin(String bin) {
        this.bin = bin;
    }

    public String getPoluchatel() {
        return poluchatel;
    }

    public void setPoluchatel(String poluchatel) {
        this.poluchatel = poluchatel;
    }

    public String getSummaDlyaPerech() {
        return summaDlyaPerech;
    }

    public void setSummaDlyaPerech(String summaDlyaPerech) {
        this.summaDlyaPerech = summaDlyaPerech;
    }

    public String getOblast() {
        return oblast;
    }

    public void setOblast(String oblast) {
        this.oblast = oblast;
    }

    public String getRayon() {
        return rayon;
    }

    public void setRayon(String rayon) {
        this.rayon = rayon;
    }

    public String getPodrazdelenieAkimata() {
        return podrazdelenieAkimata;
    }

    public void setPodrazdelenieAkimata(String podrazdelenieAkimata) {
        this.podrazdelenieAkimata = podrazdelenieAkimata;
    }

    public String getIshPismo() {
        return ishPismo;
    }

    public void setIshPismo(String ishPismo) {
        this.ishPismo = ishPismo;
    }

    public String getPodrazdelenie() {
        return podrazdelenie;
    }

    public void setPodrazdelenie(String podrazdelenie) {
        this.podrazdelenie = podrazdelenie;
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