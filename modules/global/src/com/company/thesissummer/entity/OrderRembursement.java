/*
 * Copyright (c) 2022 LTD Haulmont Samara. All Rights Reserved.
 * Haulmont Samara proprietary and confidential.
 * Use is subject to license terms.
 */

package com.company.thesissummer.entity;

import com.company.thesissummer.entity.table1c.OrderRembursement1c;
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

@DiscriminatorValue("2600")
@Table(name = "THESISSUMMER_ORDER_REMBURSEMENT")
@Entity(name = "thesissummer$OrderRembursement")
@Listeners("thesis_DocEntityListener")
@EnableRestore
@TrackEditScreenHistory
@PrimaryKeyJoinColumn(name = "CARD_ID", referencedColumnName = "ID")
public class OrderRembursement extends Doc implements HasDetailedDescription {

    private static final long serialVersionUID = -2604621609597226429L;

    @Column(name = "NOMER_ORDER")
    protected String nomerOrder;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ORDER_RECEIVER_ID")
    protected ExtEmployee orderReceiver;

    @Composition
    @OnDelete(DeletePolicy.CASCADE)
    @OneToMany(mappedBy = "orderRembursement")
    protected Set<OrderRembursement1c> orderRembursement1c;

    @Column(name = "DATA_ORDER")
    protected String dataOrder;

    @Column(name = "PODRAZDELENIE")
    protected String podrazdelenie;

    @Column(name = "SCHET_KORPORACII")
    protected String schetKorporacii;

    @Column(name = "BANK_ORGANIZACII")
    protected String bankOrganizacii;

    @Column(name = "BIK_BANKA")
    protected String bikBanka;

    @Column(name = "OBLAST_PODRAZDELENIYA")
    protected String oblastPodrazdeleniya;

    @Column(name = "KVARTAL1")
    protected String kvartal1;

    @Column(name = "DOGOVOR_SUB")
    protected String dogovorSub;

    @Column(name = "DATA_DOGOVORA_SUB")
    protected String dataDogovoraSub;

    @Column(name = "RAB_ORGAN")
    protected String rabOrgan;

    @Column(name = "KVARTAL2")
    protected String kvartal2;

    @Column(name = "SUMMA_SUB")
    protected String summaSub;

    public ExtEmployee getOrderReceiver() {
        return orderReceiver;
    }

    public void setOrderReceiver(ExtEmployee orderReceiver) {
        this.orderReceiver = orderReceiver;
    }

    public Set<OrderRembursement1c> getOrderRembursement1c() {
        return orderRembursement1c;
    }

    public void setOrderRembursement1c(Set<OrderRembursement1c> orderRembursement1c) {
        this.orderRembursement1c = orderRembursement1c;
    }

    public String getSummaSub() {
        return summaSub;
    }

    public void setSummaSub(String summaSub) {
        this.summaSub = summaSub;
    }

    public String getKvartal2() {
        return kvartal2;
    }

    public void setKvartal2(String kvartal2) {
        this.kvartal2 = kvartal2;
    }

    public String getRabOrgan() {
        return rabOrgan;
    }

    public void setRabOrgan(String rabOrgan) {
        this.rabOrgan = rabOrgan;
    }

    public String getDataDogovoraSub() {
        return dataDogovoraSub;
    }

    public void setDataDogovoraSub(String dataDogovoraSub) {
        this.dataDogovoraSub = dataDogovoraSub;
    }

    public String getDogovorSub() {
        return dogovorSub;
    }

    public void setDogovorSub(String dogovorSub) {
        this.dogovorSub = dogovorSub;
    }

    public String getKvartal1() {
        return kvartal1;
    }

    public void setKvartal1(String kvartal1) {
        this.kvartal1 = kvartal1;
    }

    public String getOblastPodrazdeleniya() {
        return oblastPodrazdeleniya;
    }

    public void setOblastPodrazdeleniya(String oblastPodrazdeleniya) {
        this.oblastPodrazdeleniya = oblastPodrazdeleniya;
    }

    public String getBikBanka() {
        return bikBanka;
    }

    public void setBikBanka(String bikBanka) {
        this.bikBanka = bikBanka;
    }

    public String getBankOrganizacii() {
        return bankOrganizacii;
    }

    public void setBankOrganizacii(String bankOrganizacii) {
        this.bankOrganizacii = bankOrganizacii;
    }

    public String getSchetKorporacii() {
        return schetKorporacii;
    }

    public void setSchetKorporacii(String schetKorporacii) {
        this.schetKorporacii = schetKorporacii;
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