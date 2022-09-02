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

@DiscriminatorValue("2400")
@Table(name = "THESISSUMMER_ORDER_VOZVRATV_GU")
@Entity(name = "thesissummer$OrderVozvratvGu")
@Listeners("thesis_DocEntityListener")
@EnableRestore
@TrackEditScreenHistory
@PrimaryKeyJoinColumn(name = "CARD_ID", referencedColumnName = "ID")
public class OrderVozvratvGu extends Doc implements HasDetailedDescription {

    private static final long serialVersionUID = 9130623198217205916L;

    @Column(name = "DOGOVOR_PORUCH")
    protected String dogovorPoruch;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ORDER_RECEIVER_ID")
    protected ExtEmployee orderReceiver;

    @Column(name = "DATA_ORDER")
    protected String dataOrder;

    @Column(name = "NOMER_ORDER")
    protected String nomerOrder;

    @Column(name = "ONSOVNOI_DOLG")
    protected String onsovnoiDolg;

    @Column(name = "VOZNAGRAZHDENIE")
    protected String voznagrazhdenie;

    @Column(name = "PENYA")
    protected String penya;

    @Column(name = "UGD_RAYON")
    protected String ugdRayon;

    @Column(name = "DGD_OBLAST")
    protected String dgdOblast;

    @Column(name = "BIN")
    protected String bin;

    @Column(name = "IIK")
    protected String iik;

    @Column(name = "BIK")
    protected String bik;

    @Column(name = "BIK2")
    protected String bik2;

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

    public String getBik2() {
        return bik2;
    }

    public void setBik2(String bik2) {
        this.bik2 = bik2;
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

    public String getDgdOblast() {
        return dgdOblast;
    }

    public void setDgdOblast(String dgdOblast) {
        this.dgdOblast = dgdOblast;
    }

    public String getUgdRayon() {
        return ugdRayon;
    }

    public void setUgdRayon(String ugdRayon) {
        this.ugdRayon = ugdRayon;
    }

    public String getPenya() {
        return penya;
    }

    public void setPenya(String penya) {
        this.penya = penya;
    }

    public String getVoznagrazhdenie() {
        return voznagrazhdenie;
    }

    public void setVoznagrazhdenie(String voznagrazhdenie) {
        this.voznagrazhdenie = voznagrazhdenie;
    }

    public String getOnsovnoiDolg() {
        return onsovnoiDolg;
    }

    public void setOnsovnoiDolg(String onsovnoiDolg) {
        this.onsovnoiDolg = onsovnoiDolg;
    }

    public String getDogovorPoruch() {
        return dogovorPoruch;
    }

    public void setDogovorPoruch(String dogovorPoruch) {
        this.dogovorPoruch = dogovorPoruch;
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