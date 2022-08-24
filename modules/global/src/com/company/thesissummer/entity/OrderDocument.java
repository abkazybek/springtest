/*
 * Copyright (c) 2022 LTD Haulmont Samara. All Rights Reserved.
 * Haulmont Samara proprietary and confidential.
 * Use is subject to license terms.
 */

package com.company.thesissummer.entity;

import com.haulmont.chile.core.annotations.Composition;
import com.haulmont.chile.core.datatypes.Datatypes;
import com.haulmont.chile.core.model.MetaClass;
import com.haulmont.cuba.core.entity.FileDescriptor;
import com.haulmont.cuba.core.entity.annotation.*;
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
import java.util.List;
import java.util.Locale;
import java.util.Set;

@PublishEntityChangedEvents
@DiscriminatorValue("2000")
@Table(name = "THESISSUMMER_ORDER_DOCUMENT")
@Entity(name = "thesissummer$OrderDocument")
@Listeners("thesis_DocEntityListener")
@EnableRestore
@TrackEditScreenHistory
@PrimaryKeyJoinColumn(name = "CARD_ID", referencedColumnName = "ID")
public class OrderDocument extends Doc implements HasDetailedDescription {

    private static final long serialVersionUID = -2130394005726443920L;

    @Column(name = "DATA_ZAYAVKI")
    protected String dataZayavki;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "SET_ORDER_ID")
    protected FileDescriptor setOrder;

    @Composition
    @OnDelete(DeletePolicy.CASCADE)
    @OneToMany(mappedBy = "orderDocument")
    protected List<OrderDocumentFull1C> orderDocuentFull1C;

    @Column(name = "AVTOR_ZAYAVKI")
    protected String avtorZayavki;

    @Column(name = "PODRAZDELENIE")
    protected String podrazdelenie;

    @Column(name = "SUMMA_PERECHESLENIYA")
    protected String summaPerechesleniya;

    @Column(name = "DATA_PEREVODA")
    protected String dataPerevoda;

    @Column(name = "DATA_PRECHESLENIYA")
    protected String dataPrechesleniya;

    @Column(name = "VID_VZAIMORACHETOV")
    protected String vidVzaimorachetov;

    @Column(name = "SUMMA_K_OPLATE")
    protected String summaKOplate;

    @Column(name = "BANK_SCHER_PLATELSHIKA")
    protected String bankScherPlatelshika;

    @Column(name = "DOGOVOR_PLATELSHIKA")
    protected String dogovorPlatelshika;

    @Column(name = "POLUCHATEL")
    protected String poluchatel;

    @Column(name = "RASHCETNIYA_SCHET")
    protected String rashcetniyaSchet;

    @Column(name = "NOMER_RASPOR")
    protected String nomerRaspor;

    @Column(name = "DATA_RASPOR")
    protected String dataRaspor;

    @Column(name = "ISH_PISMO")
    protected String ishPismo;

    @Column(name = "PODRAZDELENIYA_AKIMATA")
    protected String podrazdeleniyaAkimata;

    @Column(name = "RAYON")
    protected String rayon;

    @Column(name = "OBLASTI")
    protected String oblasti;

    @Column(name = "SUMMA_DLYA_PERECHESLENIYA")
    protected String summaDlyaPerechesleniya;

    @Column(name = "TEMA")
    protected String tema;

    @Column(name = "DOGOVOR_PORUCHENIYA")
    protected String dogovorPorucheniya;

    @Column(name = "OSNOVNOI_DOLG")
    protected String osnovnoiDolg;

    @Column(name = "VOZNAGROZHEDNIYA")
    protected String voznagrozhedniya;

    @Column(name = "PENYA")
    protected String penya;

    @Column(name = "UGD_PO_RAYONU")
    protected String ugdPoRayonu;

    @Column(name = "DGDPO_RAOYNU")
    protected String dgdpoRaoynu;

    @Column(name = "PODRAZDELENIE2")
    protected String podrazdelenie2;

    @Column(name = "NOMER_PROTOCOLA")
    protected String nomerProtocola;

    @Column(name = "ZAEMCHIK")
    protected String zaemchik;

    @Column(name = "DOGOVOR_RAMOCH")
    protected String dogovorRamoch;

    @Column(name = "OBSHAYA_SUMMA_ZALOG")
    protected String obshayaSummaZalog;

    @Column(name = "OBSHYA_TEK_SUMMA")
    protected String obshyaTekSumma;

    @Column(name = "OBSHAYA_SUMMA_PEREOCENKI")
    protected String obshayaSummaPereocenki;

    @Column(name = "OBSHAYA_SUMMA_ITOGOVOY_PEREOCENKI")
    protected String obshayaSummaItogovoyPereocenki;

    @Column(name = "OSNOVANIYA_SNYATIYA_OBESPECH")
    protected String osnovaniyaSnyatiyaObespech;

    @Column(name = "SCHET_KORP")
    protected String schetKorp;

    @Column(name = "BANK_ORG")
    protected String bankOrg;

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

    @Column(name = "DOGOVOR_SUBSIDIROVANIYA")
    protected String dogovorSubsidirovaniya;

    @Column(name = "DATA_DOGOVORA_SUBSIDIROVANIYA")
    protected String dataDogovoraSubsidirovaniya;

    @Column(name = "RAB_ORGAN")
    protected String rabOrgan;

    @Column(name = "BIN_USH")
    protected String binUsh;

    @Column(name = "KVARTAL2")
    protected String kvartal2;

    @Column(name = "SUMMA_SUBSIDIY")
    protected String summaSubsidiy;

    @Column(name = "SCHET_POLUCHATELYA")
    protected String schetPoluchatelya;

    @Column(name = "BIK_POLUCHATELYA")
    protected String bikPoluchatelya;

    @Column(name = "BANK_POLUCHATELYA")
    protected String bankPoluchatelya;

    @Column(name = "KBK")
    protected String kbk;

    @Column(name = "OBLAST_PODRAZDELENIA2")
    protected String oblastPodrazdelenia2;

    @Column(name = "NOMER_PROTOKOLA_KK")
    protected String nomerProtokolaKK;

    @Column(name = "OSNOVANIYE")
    protected String osnovaniye;

    @Column(name = "CONTRAGENT")
    protected String contragent;

    @Column(name = "BIN_CONTRAGENTA")
    protected String binContragenta;

    @Column(name = "KONECHNIY_ZAEMCHIK")
    protected String konechniyZaemchik;

    @Column(name = "POLNOE_NAIMENOVANIE")
    protected String polnoeNaimenovanie;

    @Column(name = "DOGOVOR")
    protected String dogovor;

    @Column(name = "SUMMA")
    protected String summa;

    @Column(name = "KREDITNAYA_LINIYA")
    protected String kreditnayaLiniya;

    @Column(name = "ISTOCHNIK_FINANSIROVANIYA")
    protected String istochnikFinansirovaniya;

    @Column(name = "STAVKA_VOZ")
    protected String stavkaVoz;

    @Column(name = "SROK_KREDITA")
    protected String srokKredita;

    @Column(name = "BANK")
    protected String bank;

    @Column(name = "SUMMANA_SPEC_CHETEV_NB")
    protected String summanaSpecChetevNB;

    @Column(name = "BIK")
    protected String bik;

    @Column(name = "IIK")
    protected String iik;

    @Column(name = "BIN")
    protected String bin;

    @Column(name = "KNP")
    protected String knp;

    @Column(name = "KOD")
    protected String kod;

    @Column(name = "KBE")
    protected String kbe;

    @Column(name = "UR_ADRES")
    protected String urAdres;

    @Column(name = "NOMER_NUM")
    protected String nomerTable;

    @Column(name = "ZAEMCHIK_TABLE")
    protected String zaemchikTable;

    @Column(name = "BIN_TABLE")
    protected String binTable;

    @Column(name = "ZALOGODATEL_TABLE")
    protected String zalogodatelTable;

    @Column(name = "NAIMENOVANIYA_ZAEMCHIKA")
    protected String naimenovaniyaZaemchikaTable;

    @Column(name = "NAIMENOVANIYA_ZALOGODATELYA")
    protected String naimenovaniyaZalogodatelyaTable;

    @Column(name = "DOGOVOR_SVYAZ_S_OBESPECH")
    protected String dogovorSvyazSObespechTable;

    @Column(name = "PREDMET_TABLE")
    protected String predmetTable;

    @Column(name = "TEKUCHAYA_STOIMOST")
    protected String tekuchayaStoimostTable;

    @Column(name = "PREOCEN_STOIMOST")
    protected String preocenStoimostTable;

    @Column(name = "OBESPECHENIYA_TABLE")
    protected String obespecheniyaTable;

    @Column(name = "ZALOG_STOIMOST_TABLE")
    protected String zalogStoimostTable;

    @Column(name = "ITOG_SUMMA_K_SPISANIYA")
    protected String itogSummaKSpisaniya;

    @Column(name = "DATA_PEREOCEN")
    protected String dataPereocen;

    @Column(name = "OSNOVANIYA_TABLE")
    protected String osnovaniyaTable;

    @Column(name = "DOCUMENT_USLOVIY_TABLE")
    protected String documentUsloviyTable;

    @Column(name = "IIN_TABLE")
    protected String iinTable;

    @Column(name = "NOMER_AND_DATA_KREDITNIY_DOGOVOR")
    protected String nomerAndDataKreditniyDogovorTable;

    @Column(name = "SUMMA_SUBSIDIY_DLYA_PERECHESLENIYA")
    protected String summaSubsidiyDlyaPerechesleniyaTable;

    @Column(name = "OTKLONENIE_PO_SUMME_SUBSIDIY")
    protected String otkloneniePoSummeSubsidiyTable;

    @Column(name = "IIK_ZAEMCHIKA_DLYA_PERECHESLENIYA_SUB_TABLE")
    protected String iikZaemchikaDlyaPerechesleniyaSubTable;

    @Column(name = "BANK_TABLE")
    protected String bankTable;

    @Column(name = "POYASNENIYE")
    protected String poyasneniyeTable;

    @Composition
    @OnDelete(DeletePolicy.CASCADE)
    @OneToMany(mappedBy = "orderDocument")
    protected List<OrderDocument1C> orderDocument1C;

    public FileDescriptor getSetOrder() {
        return setOrder;
    }

    public void setSetOrder(FileDescriptor setOrder) {
        this.setOrder = setOrder;
    }

    public List<OrderDocumentFull1C> getOrderDocuentFull1C() {
        return orderDocuentFull1C;
    }

    public void setOrderDocuentFull1C(List<OrderDocumentFull1C> orderDocuentFull1C) {
        this.orderDocuentFull1C = orderDocuentFull1C;
    }

    public List<OrderDocument1C> getOrderDocument1C() {
        return orderDocument1C;
    }

    public void setOrderDocument1C(List<OrderDocument1C> orderDocument1C) {
        this.orderDocument1C = orderDocument1C;
    }

    public String getDogovorPlatelshika() {
        return dogovorPlatelshika;
    }

    public void setDogovorPlatelshika(String dogovorPlatelshika) {
        this.dogovorPlatelshika = dogovorPlatelshika;
    }

    public String getBankScherPlatelshika() {
        return bankScherPlatelshika;
    }

    public void setBankScherPlatelshika(String bankScherPlatelshika) {
        this.bankScherPlatelshika = bankScherPlatelshika;
    }

    public String getSummaKOplate() {
        return summaKOplate;
    }

    public void setSummaKOplate(String summaKOplate) {
        this.summaKOplate = summaKOplate;
    }

    public String getVidVzaimorachetov() {
        return vidVzaimorachetov;
    }

    public void setVidVzaimorachetov(String vidVzaimorachetov) {
        this.vidVzaimorachetov = vidVzaimorachetov;
    }

    public String getDataPrechesleniya() {
        return dataPrechesleniya;
    }

    public void setDataPrechesleniya(String dataPrechesleniya) {
        this.dataPrechesleniya = dataPrechesleniya;
    }

    public String getDgdpoRaoynu() {
        return dgdpoRaoynu;
    }

    public void setDgdpoRaoynu(String dgdpoRaoynu) {
        this.dgdpoRaoynu = dgdpoRaoynu;
    }

    public String getUgdPoRayonu() {
        return ugdPoRayonu;
    }

    public void setUgdPoRayonu(String ugdPoRayonu) {
        this.ugdPoRayonu = ugdPoRayonu;
    }

    public String getPenya() {
        return penya;
    }

    public void setPenya(String penya) {
        this.penya = penya;
    }

    public String getVoznagrozhedniya() {
        return voznagrozhedniya;
    }

    public void setVoznagrozhedniya(String voznagrozhedniya) {
        this.voznagrozhedniya = voznagrozhedniya;
    }

    public String getOsnovnoiDolg() {
        return osnovnoiDolg;
    }

    public void setOsnovnoiDolg(String osnovnoiDolg) {
        this.osnovnoiDolg = osnovnoiDolg;
    }

    public String getDogovorPorucheniya() {
        return dogovorPorucheniya;
    }

    public void setDogovorPorucheniya(String dogovorPorucheniya) {
        this.dogovorPorucheniya = dogovorPorucheniya;
    }

    public String getSummanaSpecChetevNB() {
        return summanaSpecChetevNB;
    }

    public void setSummanaSpecChetevNB(String summanaSpecChetevNB) {
        this.summanaSpecChetevNB = summanaSpecChetevNB;
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

    public String getSummaDlyaPerechesleniya() {
        return summaDlyaPerechesleniya;
    }

    public void setSummaDlyaPerechesleniya(String summaDlyaPerechesleniya) {
        this.summaDlyaPerechesleniya = summaDlyaPerechesleniya;
    }

    public String getOblasti() {
        return oblasti;
    }

    public void setOblasti(String oblasti) {
        this.oblasti = oblasti;
    }

    public String getRayon() {
        return rayon;
    }

    public void setRayon(String rayon) {
        this.rayon = rayon;
    }

    public String getPodrazdeleniyaAkimata() {
        return podrazdeleniyaAkimata;
    }

    public void setPodrazdeleniyaAkimata(String podrazdeleniyaAkimata) {
        this.podrazdeleniyaAkimata = podrazdeleniyaAkimata;
    }

    public String getIshPismo() {
        return ishPismo;
    }

    public void setIshPismo(String ishPismo) {
        this.ishPismo = ishPismo;
    }

    public String getTema() {
        return tema;
    }

    public void setTema(String tema) {
        this.tema = tema;
    }

    public String getOsnovaniyaSnyatiyaObespech() {
        return osnovaniyaSnyatiyaObespech;
    }

    public void setOsnovaniyaSnyatiyaObespech(String osnovaniyaSnyatiyaObespech) {
        this.osnovaniyaSnyatiyaObespech = osnovaniyaSnyatiyaObespech;
    }

    public String getBinTable() {
        return binTable;
    }

    public void setBinTable(String binTable) {
        this.binTable = binTable;
    }

    public String getZaemchikTable() {
        return zaemchikTable;
    }

    public void setZaemchikTable(String zaemchikTable) {
        this.zaemchikTable = zaemchikTable;
    }

    public String getBankPoluchatelya() {
        return bankPoluchatelya;
    }

    public void setBankPoluchatelya(String bankPoluchatelya) {
        this.bankPoluchatelya = bankPoluchatelya;
    }

    public String getOsnovaniyaTable() {
        return osnovaniyaTable;
    }

    public void setOsnovaniyaTable(String osnovaniyaTable) {
        this.osnovaniyaTable = osnovaniyaTable;
    }

    public String getDataPereocen() {
        return dataPereocen;
    }

    public void setDataPereocen(String dataPereocen) {
        this.dataPereocen = dataPereocen;
    }

    public String getItogSummaKSpisaniya() {
        return itogSummaKSpisaniya;
    }

    public void setItogSummaKSpisaniya(String itogSummaKSpisaniya) {
        this.itogSummaKSpisaniya = itogSummaKSpisaniya;
    }

    public String getPreocenStoimostTable() {
        return preocenStoimostTable;
    }

    public void setPreocenStoimostTable(String preocenStoimostTable) {
        this.preocenStoimostTable = preocenStoimostTable;
    }

    public String getTekuchayaStoimostTable() {
        return tekuchayaStoimostTable;
    }

    public void setTekuchayaStoimostTable(String tekuchayaStoimostTable) {
        this.tekuchayaStoimostTable = tekuchayaStoimostTable;
    }

    public String getPredmetTable() {
        return predmetTable;
    }

    public void setPredmetTable(String predmetTable) {
        this.predmetTable = predmetTable;
    }

    public String getZalogodatelTable() {
        return zalogodatelTable;
    }

    public void setZalogodatelTable(String zalogodatelTable) {
        this.zalogodatelTable = zalogodatelTable;
    }

    public String getObshayaSummaItogovoyPereocenki() {
        return obshayaSummaItogovoyPereocenki;
    }

    public void setObshayaSummaItogovoyPereocenki(String obshayaSummaItogovoyPereocenki) {
        this.obshayaSummaItogovoyPereocenki = obshayaSummaItogovoyPereocenki;
    }

    public String getObshayaSummaPereocenki() {
        return obshayaSummaPereocenki;
    }

    public void setObshayaSummaPereocenki(String obshayaSummaPereocenki) {
        this.obshayaSummaPereocenki = obshayaSummaPereocenki;
    }

    public String getObshyaTekSumma() {
        return obshyaTekSumma;
    }

    public void setObshyaTekSumma(String obshyaTekSumma) {
        this.obshyaTekSumma = obshyaTekSumma;
    }

    public String getRashcetniyaSchet() {
        return rashcetniyaSchet;
    }

    public void setRashcetniyaSchet(String rashcetniyaSchet) {
        this.rashcetniyaSchet = rashcetniyaSchet;
    }

    public String getPoluchatel() {
        return poluchatel;
    }

    public void setPoluchatel(String poluchatel) {
        this.poluchatel = poluchatel;
    }

    public String getDataPerevoda() {
        return dataPerevoda;
    }

    public void setDataPerevoda(String dataPerevoda) {
        this.dataPerevoda = dataPerevoda;
    }

    public String getSummaPerechesleniya() {
        return summaPerechesleniya;
    }

    public void setSummaPerechesleniya(String summaPerechesleniya) {
        this.summaPerechesleniya = summaPerechesleniya;
    }

    public String getDocumentUsloviyTable() {
        return documentUsloviyTable;
    }

    public void setDocumentUsloviyTable(String documentUsloviyTable) {
        this.documentUsloviyTable = documentUsloviyTable;
    }

    public String getZalogStoimostTable() {
        return zalogStoimostTable;
    }

    public void setZalogStoimostTable(String zalogStoimostTable) {
        this.zalogStoimostTable = zalogStoimostTable;
    }

    public String getObespecheniyaTable() {
        return obespecheniyaTable;
    }

    public void setObespecheniyaTable(String obespecheniyaTable) {
        this.obespecheniyaTable = obespecheniyaTable;
    }

    public String getDogovorSvyazSObespechTable() {
        return dogovorSvyazSObespechTable;
    }

    public void setDogovorSvyazSObespechTable(String dogovorSvyazSObespechTable) {
        this.dogovorSvyazSObespechTable = dogovorSvyazSObespechTable;
    }

    public String getNaimenovaniyaZalogodatelyaTable() {
        return naimenovaniyaZalogodatelyaTable;
    }

    public void setNaimenovaniyaZalogodatelyaTable(String naimenovaniyaZalogodatelyaTable) {
        this.naimenovaniyaZalogodatelyaTable = naimenovaniyaZalogodatelyaTable;
    }

    public String getObshayaSummaZalog() {
        return obshayaSummaZalog;
    }

    public void setObshayaSummaZalog(String obshayaSummaZalog) {
        this.obshayaSummaZalog = obshayaSummaZalog;
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

    public String getBinContragenta() {
        return binContragenta;
    }

    public void setBinContragenta(String binContragenta) {
        this.binContragenta = binContragenta;
    }

    public String getKonechniyZaemchik() {
        return konechniyZaemchik;
    }

    public void setKonechniyZaemchik(String konechniyZaemchik) {
        this.konechniyZaemchik = konechniyZaemchik;
    }

    public String getOsnovaniye() {
        return osnovaniye;
    }

    public void setOsnovaniye(String osnovaniye) {
        this.osnovaniye = osnovaniye;
    }

    public String getBankTable() {
        return bankTable;
    }

    public void setBankTable(String bankTable) {
        this.bankTable = bankTable;
    }

    public String getIikZaemchikaDlyaPerechesleniyaSubTable() {
        return iikZaemchikaDlyaPerechesleniyaSubTable;
    }

    public void setIikZaemchikaDlyaPerechesleniyaSubTable(String iikZaemchikaDlyaPerechesleniyaSubTable) {
        this.iikZaemchikaDlyaPerechesleniyaSubTable = iikZaemchikaDlyaPerechesleniyaSubTable;
    }

    public String getSummaSubsidiyDlyaPerechesleniyaTable() {
        return summaSubsidiyDlyaPerechesleniyaTable;
    }

    public void setSummaSubsidiyDlyaPerechesleniyaTable(String summaSubsidiyDlyaPerechesleniyaTable) {
        this.summaSubsidiyDlyaPerechesleniyaTable = summaSubsidiyDlyaPerechesleniyaTable;
    }

    public String getIinTable() {
        return iinTable;
    }

    public void setIinTable(String iinTable) {
        this.iinTable = iinTable;
    }

    public String getPoyasneniyeTable() {
        return poyasneniyeTable;
    }

    public void setPoyasneniyeTable(String poyasneniyeTable) {
        this.poyasneniyeTable = poyasneniyeTable;
    }

    public String getOtkloneniePoSummeSubsidiyTable() {
        return otkloneniePoSummeSubsidiyTable;
    }

    public void setOtkloneniePoSummeSubsidiyTable(String otkloneniePoSummeSubsidiyTable) {
        this.otkloneniePoSummeSubsidiyTable = otkloneniePoSummeSubsidiyTable;
    }

    public String getNomerAndDataKreditniyDogovorTable() {
        return nomerAndDataKreditniyDogovorTable;
    }

    public void setNomerAndDataKreditniyDogovorTable(String nomerAndDataKreditniyDogovorTable) {
        this.nomerAndDataKreditniyDogovorTable = nomerAndDataKreditniyDogovorTable;
    }

    public String getNaimenovaniyaZaemchikaTable() {
        return naimenovaniyaZaemchikaTable;
    }

    public void setNaimenovaniyaZaemchikaTable(String naimenovaniyaZaemchikaTable) {
        this.naimenovaniyaZaemchikaTable = naimenovaniyaZaemchikaTable;
    }

    public String getNomerTable() {
        return nomerTable;
    }

    public void setNomerTable(String nomerTable) {
        this.nomerTable = nomerTable;
    }

    public String getDataDogovoraSubsidirovaniya() {
        return dataDogovoraSubsidirovaniya;
    }

    public void setDataDogovoraSubsidirovaniya(String dataDogovoraSubsidirovaniya) {
        this.dataDogovoraSubsidirovaniya = dataDogovoraSubsidirovaniya;
    }

    public String getOblastPodrazdelenia2() {
        return oblastPodrazdelenia2;
    }

    public void setOblastPodrazdelenia2(String oblastPodrazdelenia2) {
        this.oblastPodrazdelenia2 = oblastPodrazdelenia2;
    }

    public String getKbk() {
        return kbk;
    }

    public void setKbk(String kbk) {
        this.kbk = kbk;
    }

    public String getBikPoluchatelya() {
        return bikPoluchatelya;
    }

    public void setBikPoluchatelya(String bikPoluchatelya) {
        this.bikPoluchatelya = bikPoluchatelya;
    }

    public String getSchetPoluchatelya() {
        return schetPoluchatelya;
    }

    public void setSchetPoluchatelya(String schetPoluchatelya) {
        this.schetPoluchatelya = schetPoluchatelya;
    }

    public String getSummaSubsidiy() {
        return summaSubsidiy;
    }

    public void setSummaSubsidiy(String summaSubsidiy) {
        this.summaSubsidiy = summaSubsidiy;
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

    public String getDogovorSubsidirovaniya() {
        return dogovorSubsidirovaniya;
    }

    public void setDogovorSubsidirovaniya(String dogovorSubsidirovaniya) {
        this.dogovorSubsidirovaniya = dogovorSubsidirovaniya;
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

    public String getUrAdres() {
        return urAdres;
    }

    public void setUrAdres(String urAdres) {
        this.urAdres = urAdres;
    }

    public String getKbe() {
        return kbe;
    }

    public void setKbe(String kbe) {
        this.kbe = kbe;
    }

    public String getBin() {
        return bin;
    }

    public void setBin(String bin) {
        this.bin = bin;
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

    public String getBank() {
        return bank;
    }

    public void setBank(String bank) {
        this.bank = bank;
    }

    public String getSrokKredita() {
        return srokKredita;
    }

    public void setSrokKredita(String srokKredita) {
        this.srokKredita = srokKredita;
    }

    public String getStavkaVoz() {
        return stavkaVoz;
    }

    public void setStavkaVoz(String stavkaVoz) {
        this.stavkaVoz = stavkaVoz;
    }

    public String getIstochnikFinansirovaniya() {
        return istochnikFinansirovaniya;
    }

    public void setIstochnikFinansirovaniya(String istochnikFinansirovaniya) {
        this.istochnikFinansirovaniya = istochnikFinansirovaniya;
    }

    public String getKreditnayaLiniya() {
        return kreditnayaLiniya;
    }

    public void setKreditnayaLiniya(String kreditnayaLiniya) {
        this.kreditnayaLiniya = kreditnayaLiniya;
    }

    public String getSumma() {
        return summa;
    }

    public void setSumma(String summa) {
        this.summa = summa;
    }

    public String getDogovor() {
        return dogovor;
    }

    public void setDogovor(String dogovor) {
        this.dogovor = dogovor;
    }

    public String getPolnoeNaimenovanie() {
        return polnoeNaimenovanie;
    }

    public void setPolnoeNaimenovanie(String polnoeNaimenovanie) {
        this.polnoeNaimenovanie = polnoeNaimenovanie;
    }

    public String getContragent() {
        return contragent;
    }

    public void setContragent(String contragent) {
        this.contragent = contragent;
    }

    public String getNomerProtokolaKK() {
        return nomerProtokolaKK;
    }

    public void setNomerProtokolaKK(String nomerProtokolaKK) {
        this.nomerProtokolaKK = nomerProtokolaKK;
    }

    public String getPodrazdelenie2() {
        return podrazdelenie2;
    }

    public void setPodrazdelenie2(String podrazdelenie2) {
        this.podrazdelenie2 = podrazdelenie2;
    }

    public String getDataRaspor() {
        return dataRaspor;
    }

    public void setDataRaspor(String dataRaspor) {
        this.dataRaspor = dataRaspor;
    }

    public String getNomerRaspor() {
        return nomerRaspor;
    }

    public void setNomerRaspor(String nomerRaspor) {
        this.nomerRaspor = nomerRaspor;
    }

    public String getPodrazdelenie() {
        return podrazdelenie;
    }

    public void setPodrazdelenie(String podrazdelenie) {
        this.podrazdelenie = podrazdelenie;
    }

    public String getAvtorZayavki() {
        return avtorZayavki;
    }

    public void setAvtorZayavki(String avtorZayavki) {
        this.avtorZayavki = avtorZayavki;
    }

    public String getDataZayavki() {
        return dataZayavki;
    }

    public void setDataZayavki(String dataZayavki) {
        this.dataZayavki = dataZayavki;
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