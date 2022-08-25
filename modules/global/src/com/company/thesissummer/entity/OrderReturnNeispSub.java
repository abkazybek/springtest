/*
 * Copyright (c) 2022 LTD Haulmont Samara. All Rights Reserved.
 * Haulmont Samara proprietary and confidential.
 * Use is subject to license terms.
 */

package com.company.thesissummer.entity;

import com.company.thesissummer.entity.table1c.OrderReturnNeispSub1C;
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

@DiscriminatorValue("2500")
@Table(name = "THESISSUMMER_ORDER_RETURN_NEISP_SUB")
@Entity(name = "thesissummer$OrderReturnNeispSub")
@Listeners("thesis_DocEntityListener")
@EnableRestore
@TrackEditScreenHistory
@PrimaryKeyJoinColumn(name = "CARD_ID", referencedColumnName = "ID")
public class OrderReturnNeispSub extends Doc implements HasDetailedDescription {

    private static final long serialVersionUID = -461402265224009910L;

    @Column(name = "DATA_ORDER")
    protected String dataOrder;

    @Composition
    @OnDelete(DeletePolicy.CASCADE)
    @OneToMany(mappedBy = "orderReturnNeispSub")
    protected Set<OrderReturnNeispSub1C> orderReturnNeispSub1C;

    @Column(name = "PODRAZDELENIE")
    protected String podrazdelenie;

    @Column(name = "SCHET_KORPORACII")
    protected String schetKorporacii;

    @Column(name = "BANK_ORGANIZACII")
    protected String bankOrganizacii;

    @Column(name = "BIK_BANKA")
    protected String bikBanka;

    @Column(name = "OBLAST_PODRAZDELENIA")
    protected String oblastPodrazdelenia;

    @Column(name = "KVARTAL1")
    protected String kvartal1;

    @Column(name = "NAZNACHENIYA_PLATEZHA")
    protected String naznacheniyaPlatezha;

    @Column(name = "SUMMA_POSTUPLENIYA")
    protected String summaPostupleniya;

    @Column(name = "DATA_POSTUPLENIYA")
    protected String dataPostupleniya;

    @Column(name = "DOGOVOR_SUB")
    protected String dogovorSub;

    @Column(name = "RAB_ORGAN")
    protected String rabOrgan;

    @Column(name = "BIN_USH")
    protected String binUsh;

    @Column(name = "KVARTAL2")
    protected String kvartal2;

    @Column(name = "SUMMA_SUB")
    protected String summaSub;

    @Column(name = "SCHET_POLUCHATELYA")
    protected String schetPoluchatelya;

    @Column(name = "BIK_POLUCH")
    protected String bikPoluch;

    @Column(name = "KBK")
    protected String kbk;

    @Column(name = "OBLAST_PODRAZDEL2")
    protected String oblastPodrazdel2;

    public Set<OrderReturnNeispSub1C> getOrderReturnNeispSub1C() {
        return orderReturnNeispSub1C;
    }

    public void setOrderReturnNeispSub1C(Set<OrderReturnNeispSub1C> orderReturnNeispSub1C) {
        this.orderReturnNeispSub1C = orderReturnNeispSub1C;
    }

    public String getOblastPodrazdel2() {
        return oblastPodrazdel2;
    }

    public void setOblastPodrazdel2(String oblastPodrazdel2) {
        this.oblastPodrazdel2 = oblastPodrazdel2;
    }

    public String getKbk() {
        return kbk;
    }

    public void setKbk(String kbk) {
        this.kbk = kbk;
    }

    public String getBikPoluch() {
        return bikPoluch;
    }

    public void setBikPoluch(String bikPoluch) {
        this.bikPoluch = bikPoluch;
    }

    public String getSchetPoluchatelya() {
        return schetPoluchatelya;
    }

    public void setSchetPoluchatelya(String schetPoluchatelya) {
        this.schetPoluchatelya = schetPoluchatelya;
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

    public String getBinUsh() {
        return binUsh;
    }

    public void setBinUsh(String binUsh) {
        this.binUsh = binUsh;
    }

    public String getRabOrgan() {
        return rabOrgan;
    }

    public void setRabOrgan(String rabOrgan) {
        this.rabOrgan = rabOrgan;
    }

    public String getDogovorSub() {
        return dogovorSub;
    }

    public void setDogovorSub(String dogovorSub) {
        this.dogovorSub = dogovorSub;
    }

    public String getDataPostupleniya() {
        return dataPostupleniya;
    }

    public void setDataPostupleniya(String dataPostupleniya) {
        this.dataPostupleniya = dataPostupleniya;
    }

    public String getSummaPostupleniya() {
        return summaPostupleniya;
    }

    public void setSummaPostupleniya(String summaPostupleniya) {
        this.summaPostupleniya = summaPostupleniya;
    }

    public String getNaznacheniyaPlatezha() {
        return naznacheniyaPlatezha;
    }

    public void setNaznacheniyaPlatezha(String naznacheniyaPlatezha) {
        this.naznacheniyaPlatezha = naznacheniyaPlatezha;
    }

    public String getKvartal1() {
        return kvartal1;
    }

    public void setKvartal1(String kvartal1) {
        this.kvartal1 = kvartal1;
    }

    public String getOblastPodrazdelenia() {
        return oblastPodrazdelenia;
    }

    public void setOblastPodrazdelenia(String oblastPodrazdelenia) {
        this.oblastPodrazdelenia = oblastPodrazdelenia;
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