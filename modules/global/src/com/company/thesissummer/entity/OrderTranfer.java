/*
 * Copyright (c) 2022 LTD Haulmont Samara. All Rights Reserved.
 * Haulmont Samara proprietary and confidential.
 * Use is subject to license terms.
 */

package com.company.thesissummer.entity;

import com.company.thesissummer.entity.table1c.OrderTranfer1C;
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

@DiscriminatorValue("2900")
@Table(name = "THESISSUMMER_ORDER_TRANFER")
@Entity(name = "thesissummer$OrderTranfer")
@Listeners("thesis_DocEntityListener")
@EnableRestore
@TrackEditScreenHistory
@PrimaryKeyJoinColumn(name = "CARD_ID", referencedColumnName = "ID")
public class OrderTranfer extends Doc implements HasDetailedDescription {

    private static final long serialVersionUID = -6550965891065456169L;

    @Column(name = "NOMER_ORDER")
    protected String nomerOrder;

    @Composition
    @OnDelete(DeletePolicy.CASCADE)
    @OneToMany(mappedBy = "orderTranfer")
    protected Set<OrderTranfer1C> orderTranfer1C;

    @Column(name = "DATA_ORDER")
    protected String dataOrder;

    @Column(name = "PODRAZDELENIE")
    protected String podrazdelenie;

    @Column(name = "SCHET_KORP")
    protected String schetKorp;

    @Column(name = "BANK_ORG")
    protected String bankOrg;

    @Column(name = "OBLAST_PODR")
    protected String oblastPodr;

    @Column(name = "KVARTAL1")
    protected String kvartal1;

    @Column(name = "DOGOVOR_SUB")
    protected String dogovorSub;

    @Column(name = "DATA_DOGOVOR_SUB")
    protected String dataDogovorSub;

    @Column(name = "RAB_OPERATOR")
    protected String rabOperator;

    @Column(name = "SUMMA_SUB")
    protected String summaSub;

    @Column(name = "SCHET_POL")
    protected String schetPol;

    @Column(name = "BANK_POLUCHA")
    protected String bankPolucha;

    public Set<OrderTranfer1C> getOrderTranfer1C() {
        return orderTranfer1C;
    }

    public void setOrderTranfer1C(Set<OrderTranfer1C> orderTranfer1C) {
        this.orderTranfer1C = orderTranfer1C;
    }

    public String getBankPolucha() {
        return bankPolucha;
    }

    public void setBankPolucha(String bankPolucha) {
        this.bankPolucha = bankPolucha;
    }

    public String getSchetPol() {
        return schetPol;
    }

    public void setSchetPol(String schetPol) {
        this.schetPol = schetPol;
    }

    public String getSummaSub() {
        return summaSub;
    }

    public void setSummaSub(String summaSub) {
        this.summaSub = summaSub;
    }

    public String getRabOperator() {
        return rabOperator;
    }

    public void setRabOperator(String rabOperator) {
        this.rabOperator = rabOperator;
    }

    public String getDataDogovorSub() {
        return dataDogovorSub;
    }

    public void setDataDogovorSub(String dataDogovorSub) {
        this.dataDogovorSub = dataDogovorSub;
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

    public String getOblastPodr() {
        return oblastPodr;
    }

    public void setOblastPodr(String oblastPodr) {
        this.oblastPodr = oblastPodr;
    }

    public String getBankOrg() {
        return bankOrg;
    }

    public void setBankOrg(String bankOrg) {
        this.bankOrg = bankOrg;
    }

    public String getSchetKorp() {
        return schetKorp;
    }

    public void setSchetKorp(String schetKorp) {
        this.schetKorp = schetKorp;
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