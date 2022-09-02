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

@DiscriminatorValue("3300")
@Table(name = "THESISSUMMER_ORDER_POGASH_OB")
@Entity(name = "thesissummer$OrderPogashOb")
@Listeners("thesis_DocEntityListener")
@EnableRestore
@TrackEditScreenHistory
@PrimaryKeyJoinColumn(name = "CARD_ID", referencedColumnName = "ID")
public class OrderPogashOb extends Doc implements HasDetailedDescription {

    private static final long serialVersionUID = -7314528479967767687L;

    @Column(name = "DOGOVOR")
    protected String dogovor;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ORDER_RECEIVER_ID")
    protected ExtEmployee orderReceiver;

    @Column(name = "NAZNACHENIYE")
    protected String naznacheniye;

    @Column(name = "CONTRAGENT")
    protected String contragent;

    @Column(name = "DATA_PERECH")
    protected String dataPerech;

    @Column(name = "VID_CZAIMORACHETOV")
    protected String vidCzaimorachetov;

    @Column(name = "SUMMA_K_OPLATE")
    protected String summaKOplate;

    @Column(name = "POLUCHATEL")
    protected String poluchatel;

    @Column(name = "NOMER_ORDER")
    protected String nomerOrder;

    @Column(name = "DATA_ORDER")
    protected String dataOrder;

    @Column(name = "BANKOVSCKIY_SCHET_PLATELSHIKA")
    protected String bankovsckiySchetPlatelshika;

    @Column(name = "DOGOVOR_PLAT")
    protected String dogovorPlat;

    @Column(name = "BIN")
    protected String bin;

    @Column(name = "BANK_BENEFECIAR")
    protected String bankBenefeciar;

    @Column(name = "BIK")
    protected String bik;

    @Column(name = "IIK")
    protected String iik;

    @Column(name = "KBE")
    protected String kbe;

    @Column(name = "KNP")
    protected String knp;

    @Column(name = "KBK")
    protected String kbk;

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

    public String getKbk() {
        return kbk;
    }

    public void setKbk(String kbk) {
        this.kbk = kbk;
    }

    public String getKnp() {
        return knp;
    }

    public void setKnp(String knp) {
        this.knp = knp;
    }

    public String getKbe() {
        return kbe;
    }

    public void setKbe(String kbe) {
        this.kbe = kbe;
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

    public String getBankBenefeciar() {
        return bankBenefeciar;
    }

    public void setBankBenefeciar(String bankBenefeciar) {
        this.bankBenefeciar = bankBenefeciar;
    }

    public String getBin() {
        return bin;
    }

    public void setBin(String bin) {
        this.bin = bin;
    }

    public String getDogovorPlat() {
        return dogovorPlat;
    }

    public void setDogovorPlat(String dogovorPlat) {
        this.dogovorPlat = dogovorPlat;
    }

    public String getBankovsckiySchetPlatelshika() {
        return bankovsckiySchetPlatelshika;
    }

    public void setBankovsckiySchetPlatelshika(String bankovsckiySchetPlatelshika) {
        this.bankovsckiySchetPlatelshika = bankovsckiySchetPlatelshika;
    }

    public String getPoluchatel() {
        return poluchatel;
    }

    public void setPoluchatel(String poluchatel) {
        this.poluchatel = poluchatel;
    }

    public String getSummaKOplate() {
        return summaKOplate;
    }

    public void setSummaKOplate(String summaKOplate) {
        this.summaKOplate = summaKOplate;
    }

    public String getVidCzaimorachetov() {
        return vidCzaimorachetov;
    }

    public void setVidCzaimorachetov(String vidCzaimorachetov) {
        this.vidCzaimorachetov = vidCzaimorachetov;
    }

    public String getDataPerech() {
        return dataPerech;
    }

    public void setDataPerech(String dataPerech) {
        this.dataPerech = dataPerech;
    }

    public String getContragent() {
        return contragent;
    }

    public void setContragent(String contragent) {
        this.contragent = contragent;
    }

    public String getNaznacheniye() {
        return naznacheniye;
    }

    public void setNaznacheniye(String naznacheniye) {
        this.naznacheniye = naznacheniye;
    }

    public String getDogovor() {
        return dogovor;
    }

    public void setDogovor(String dogovor) {
        this.dogovor = dogovor;
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