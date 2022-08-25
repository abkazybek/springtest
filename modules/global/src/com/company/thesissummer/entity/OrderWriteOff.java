/*
 * Copyright (c) 2022 LTD Haulmont Samara. All Rights Reserved.
 * Haulmont Samara proprietary and confidential.
 * Use is subject to license terms.
 */

package com.company.thesissummer.entity;

import com.company.thesissummer.entity.table1c.OrderWriteOff1C;
import com.haulmont.chile.core.annotations.Composition;
import com.haulmont.chile.core.datatypes.Datatypes;
import com.haulmont.chile.core.model.MetaClass;
import com.haulmont.cuba.core.entity.annotation.EnableRestore;
import com.haulmont.cuba.core.entity.annotation.Listeners;
import com.haulmont.cuba.core.entity.annotation.OnDelete;
import com.haulmont.cuba.core.entity.annotation.TrackEditScreenHistory;
import com.haulmont.cuba.core.global.AppBeans;
import com.haulmont.cuba.core.global.DeletePolicy;
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

@DiscriminatorValue("3000")
@Table(name = "THESISSUMMER_ORDER_WRITE_OFF")
@Entity(name = "thesissummer$OrderWriteOff")
@Listeners("thesis_DocEntityListener")
@EnableRestore
@TrackEditScreenHistory
@PrimaryKeyJoinColumn(name = "CARD_ID", referencedColumnName = "ID")
public class OrderWriteOff extends Doc implements HasDetailedDescription {

    private static final long serialVersionUID = -1273140800110241044L;

    @Column(name = "NOMER_ORDER")
    protected String nomerOrder;

    @Composition
    @OnDelete(DeletePolicy.CASCADE)
    @OneToMany(mappedBy = "orderWriteOff")
    protected Set<OrderWriteOff1C> orderWriteOff1C;

    @Column(name = "DATA_ORDER")
    protected String dataOrder;

    @Column(name = "PODRAZDELENIE")
    protected String podrazdelenie;

    @Column(name = "NOMER_PROTOCOLA")
    protected String nomerProtocola;

    @Column(name = "ZAEMCHIK")
    protected String zaemchik;

    @Column(name = "DOGOVOR_RAMOCH")
    protected String dogovorRamoch;

    @Column(name = "KONECHNIY_ZAEMCHIK")
    protected String konechniyZaemchik;

    @Column(name = "OSNOVANIYE_SNIYATIA_OBESPECH")
    protected String osnovaniyeSniyatiaObespech;

    @Column(name = "OBSHAYA_SUMMA_ZALOG_OBESPECH")
    protected String obshayaSummaZalogObespech;

    public Set<OrderWriteOff1C> getOrderWriteOff1C() {
        return orderWriteOff1C;
    }

    public void setOrderWriteOff1C(Set<OrderWriteOff1C> orderWriteOff1C) {
        this.orderWriteOff1C = orderWriteOff1C;
    }

    public String getObshayaSummaZalogObespech() {
        return obshayaSummaZalogObespech;
    }

    public void setObshayaSummaZalogObespech(String obshayaSummaZalogObespech) {
        this.obshayaSummaZalogObespech = obshayaSummaZalogObespech;
    }

    public String getOsnovaniyeSniyatiaObespech() {
        return osnovaniyeSniyatiaObespech;
    }

    public void setOsnovaniyeSniyatiaObespech(String osnovaniyeSniyatiaObespech) {
        this.osnovaniyeSniyatiaObespech = osnovaniyeSniyatiaObespech;
    }

    public String getKonechniyZaemchik() {
        return konechniyZaemchik;
    }

    public void setKonechniyZaemchik(String konechniyZaemchik) {
        this.konechniyZaemchik = konechniyZaemchik;
    }

    public String getDogovorRamoch() {
        return dogovorRamoch;
    }

    public void setDogovorRamoch(String dogovorRamoch) {
        this.dogovorRamoch = dogovorRamoch;
    }

    public String getZaemchik() {
        return zaemchik;
    }

    public void setZaemchik(String zaemchik) {
        this.zaemchik = zaemchik;
    }

    public String getNomerProtocola() {
        return nomerProtocola;
    }

    public void setNomerProtocola(String nomerProtocola) {
        this.nomerProtocola = nomerProtocola;
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