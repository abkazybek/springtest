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

@DiscriminatorValue("3400")
@Table(name = "THESISSUMMER_ORDER_CEN_BUMAGA")
@Entity(name = "thesissummer$OrderCenBumaga")
@Listeners("thesis_DocEntityListener")
@EnableRestore
@TrackEditScreenHistory
@PrimaryKeyJoinColumn(name = "CARD_ID", referencedColumnName = "ID")
public class OrderCenBumaga extends Doc implements HasDetailedDescription {

    private static final long serialVersionUID = 7899871681743718469L;

    @Column(name = "NOMER_ORDER")
    protected String nomerOrder;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ORDER_RECEIVER_ID")
    protected ExtEmployee orderReceiver;

    @Column(name = "DATA_ORDER")
    protected String dataOrder;

    @Column(name = "CONTRAGENT")
    protected String contragent;

    @Column(name = "EMITENT")
    protected String emitent;

    @Column(name = "NIN")
    protected String nin;

    @Column(name = "DOGOVOR")
    protected String dogovor;

    @Column(name = "NOMER_SDELKI")
    protected String nomerSdelki;

    @Column(name = "VID")
    protected String vid;

    @Column(name = "CENNAYA_BUMAGA")
    protected String cennayaBumaga;

    @Column(name = "BASE_NACH")
    protected String baseNach;

    @Column(name = "DOHODNOST_REPO")
    protected String dohodnostRepo;

    @Column(name = "DATA_OPEN")
    protected String dataOpen;

    @Column(name = "KOLVO_OBLIG")
    protected String kolvoOblig;

    @Column(name = "SUMMA_SDELKI")
    protected String summaSdelki;

    @Column(name = "VOZNAGRAZHDENIE")
    protected String voznagrazhdenie;

    @Column(name = "DATA_CLOSE")
    protected String dataClose;

    public ExtEmployee getOrderReceiver() {
        return orderReceiver;
    }

    public void setOrderReceiver(ExtEmployee orderReceiver) {
        this.orderReceiver = orderReceiver;
    }

    public String getDataClose() {
        return dataClose;
    }

    public void setDataClose(String dataClose) {
        this.dataClose = dataClose;
    }

    public String getVoznagrazhdenie() {
        return voznagrazhdenie;
    }

    public void setVoznagrazhdenie(String voznagrazhdenie) {
        this.voznagrazhdenie = voznagrazhdenie;
    }

    public String getSummaSdelki() {
        return summaSdelki;
    }

    public void setSummaSdelki(String summaSdelki) {
        this.summaSdelki = summaSdelki;
    }

    public String getKolvoOblig() {
        return kolvoOblig;
    }

    public void setKolvoOblig(String kolvoOblig) {
        this.kolvoOblig = kolvoOblig;
    }

    public String getDataOpen() {
        return dataOpen;
    }

    public void setDataOpen(String dataOpen) {
        this.dataOpen = dataOpen;
    }

    public String getDohodnostRepo() {
        return dohodnostRepo;
    }

    public void setDohodnostRepo(String dohodnostRepo) {
        this.dohodnostRepo = dohodnostRepo;
    }

    public String getBaseNach() {
        return baseNach;
    }

    public void setBaseNach(String baseNach) {
        this.baseNach = baseNach;
    }

    public String getCennayaBumaga() {
        return cennayaBumaga;
    }

    public void setCennayaBumaga(String cennayaBumaga) {
        this.cennayaBumaga = cennayaBumaga;
    }

    public String getVid() {
        return vid;
    }

    public void setVid(String vid) {
        this.vid = vid;
    }

    public String getNomerSdelki() {
        return nomerSdelki;
    }

    public void setNomerSdelki(String nomerSdelki) {
        this.nomerSdelki = nomerSdelki;
    }

    public String getDogovor() {
        return dogovor;
    }

    public void setDogovor(String dogovor) {
        this.dogovor = dogovor;
    }

    public String getNin() {
        return nin;
    }

    public void setNin(String nin) {
        this.nin = nin;
    }

    public String getEmitent() {
        return emitent;
    }

    public void setEmitent(String emitent) {
        this.emitent = emitent;
    }

    public String getContragent() {
        return contragent;
    }

    public void setContragent(String contragent) {
        this.contragent = contragent;
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