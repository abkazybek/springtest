/*
 * Copyright (c) 2022 LTD Haulmont Samara. All Rights Reserved.
 * Haulmont Samara proprietary and confidential.
 * Use is subject to license terms.
 */

package com.company.thesissummer.entity;

import com.haulmont.chile.core.datatypes.Datatypes;
import com.haulmont.chile.core.model.MetaClass;
import com.haulmont.cuba.core.entity.FileDescriptor;
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

@DiscriminatorValue("2100")
@Table(name = "THESISSUMMER_ORDER_INS")
@Entity(name = "thesissummer$OrderIns")
@Listeners("thesis_DocEntityListener")
@EnableRestore
@TrackEditScreenHistory
@PrimaryKeyJoinColumn(name = "CARD_ID", referencedColumnName = "ID")
public class OrderIns extends Doc implements HasDetailedDescription {

    private static final long serialVersionUID = -1924327924264399384L;

    @Column(name = "PODRAZDELENIE")
    protected String podrazdelenie;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "PROTOCOL_TEMPLATE_ID")
    protected FileDescriptor protocolTemplate;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ORDER_RECEIVER_ID")
    protected ExtEmployee orderReceiver;

    @Column(name = "DATA_ORDER")
    protected String dataOrder;

    @Column(name = "NOMER_ORDER")
    protected String nomerOrder;

    @Column(name = "RESHENIE_OPERATORA")
    protected String reshenieOperatora;

    @Column(name = "DOGOVOR_STRAH")
    protected String dogovorStrah;

    @Column(name = "IIK_OBSH")
    protected String iikObsh;

    @Column(name = "SUMMA_DLYA_PERECH")
    protected String summaDlyaPerech;

    @Column(name = "NAIMENOVANIE_POLUCH")
    protected String naimenovaniePoluch;

    @Column(name = "BIN")
    protected String bin;

    @Column(name = "IIK")
    protected String iik;

    @Column(name = "BIK")
    protected String bik;

    @Column(name = "KBE")
    protected String kbe;

    @Column(name = "BANK_POLUCH")
    protected String bankPoluch;

    @Column(name = "BIK_POLUCHATEL")
    protected String bikPoluchatel;

    @Column(name = "KBE_POLUCHATEL")
    protected String kbePoluchatel;

    @Column(name = "SUMMA_NA_SPEC")
    protected String summaNaSpec;

    public FileDescriptor getProtocolTemplate() {
        return protocolTemplate;
    }

    public void setProtocolTemplate(FileDescriptor protocolTemplate) {
        this.protocolTemplate = protocolTemplate;
    }

    public ExtEmployee getOrderReceiver() {
        return orderReceiver;
    }

    public void setOrderReceiver(ExtEmployee orderReceiver) {
        this.orderReceiver = orderReceiver;
    }

    public String getKbePoluchatel() {
        return kbePoluchatel;
    }

    public void setKbePoluchatel(String kbePoluchatel) {
        this.kbePoluchatel = kbePoluchatel;
    }

    public String getBikPoluchatel() {
        return bikPoluchatel;
    }

    public void setBikPoluchatel(String bikPoluchatel) {
        this.bikPoluchatel = bikPoluchatel;
    }

    public String getNomerOrder() {
        return nomerOrder;
    }

    public void setNomerOrder(String nomerOrder) {
        this.nomerOrder = nomerOrder;
    }

    public String getDataOrder() {
        return dataOrder;
    }

    public void setDataOrder(String dataOrder) {
        this.dataOrder = dataOrder;
    }

    public String getSummaNaSpec() {
        return summaNaSpec;
    }

    public void setSummaNaSpec(String summaNaSpec) {
        this.summaNaSpec = summaNaSpec;
    }

    public String getKbe() {
        return kbe;
    }

    public void setKbe(String kbe) {
        this.kbe = kbe;
    }

    public String getBik() {
        return bik;
    }

    public void setBik(String bik) {
        this.bik = bik;
    }

    public String getBankPoluch() {
        return bankPoluch;
    }

    public void setBankPoluch(String bankPoluch) {
        this.bankPoluch = bankPoluch;
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

    public String getNaimenovaniePoluch() {
        return naimenovaniePoluch;
    }

    public void setNaimenovaniePoluch(String naimenovaniePoluch) {
        this.naimenovaniePoluch = naimenovaniePoluch;
    }

    public String getSummaDlyaPerech() {
        return summaDlyaPerech;
    }

    public void setSummaDlyaPerech(String summaDlyaPerech) {
        this.summaDlyaPerech = summaDlyaPerech;
    }


    public String getIikObsh() {
        return iikObsh;
    }

    public void setIikObsh(String iikObsh) {
        this.iikObsh = iikObsh;
    }

    public String getDogovorStrah() {
        return dogovorStrah;
    }

    public void setDogovorStrah(String dogovorStrah) {
        this.dogovorStrah = dogovorStrah;
    }

    public String getReshenieOperatora() {
        return reshenieOperatora;
    }

    public void setReshenieOperatora(String reshenieOperatora) {
        this.reshenieOperatora = reshenieOperatora;
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
}