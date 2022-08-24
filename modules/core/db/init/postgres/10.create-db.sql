-- begin THESISSUMMER_EXT_CARD_TYPE_FIELD_INFO_DATASOURCE
create table THESISSUMMER_EXT_CARD_TYPE_FIELD_INFO_DATASOURCE (
    ID uuid,
    VERSION integer not null,
    CREATE_TS timestamp,
    CREATED_BY varchar(50),
    UPDATE_TS timestamp,
    UPDATED_BY varchar(50),
    DELETE_TS timestamp,
    DELETED_BY varchar(50),
    --
    primary key (ID)
)^
-- end THESISSUMMER_EXT_CARD_TYPE_FIELD_INFO_DATASOURCE

-- begin THESISSUMMER_MEMO
create table THESISSUMMER_MEMO (
    CARD_ID uuid,
    --
    primary key (CARD_ID)
)^
-- end THESISSUMMER_MEMO
-- begin DF_DEPARTMENT
alter table DF_DEPARTMENT add column PODRAZDELENIE varchar(50) ^
alter table DF_DEPARTMENT add column DESCRIBE_ varchar(255) ^
-- end DF_DEPARTMENT
-- begin THESISSUMMER_ORDER_DOCUMENT1C
create table THESISSUMMER_ORDER_DOCUMENT1C (
    ID uuid,
    VERSION integer not null,
    CREATE_TS timestamp,
    CREATED_BY varchar(50),
    UPDATE_TS timestamp,
    UPDATED_BY varchar(50),
    DELETE_TS timestamp,
    DELETED_BY varchar(50),
    --
    NOMER_TABLE varchar(255),
    ZAEMCHIK_TABLE varchar(255),
    BIN_TABLE varchar(255),
    ZALOGODATEL_TABLE varchar(255),
    NAIMENOVANIYA_ZAEMCHIKA_TABLE varchar(255),
    NAIMENOVANIYA_ZALOGODATELYA_TABLE varchar(255),
    DOGOVOR_SVYAZ_S_OBESPECH_TABLE varchar(255),
    PREDMET_TABLE varchar(255),
    TEKUCHAYA_STOIMOST_TABLE varchar(255),
    PREOCEN_STOIMOST_TABLE varchar(255),
    OBESPECHENIYA_TABLE varchar(255),
    ZALOG_STOIMOST_TABLE varchar(255),
    ITOG_SUMMA_K_SPISANIYA varchar(255),
    DATA_PEREOCEN varchar(255),
    OSNOVANIYA_TABLE varchar(255),
    DOCUMENT_USLOVIY_TABLE varchar(255),
    IIN_TABLE varchar(255),
    NOMER_AND_DATA_KREDITNIY_DOGOVOR_TABLE varchar(255),
    SUMMA_SUBSIDIY_DLYA_PERECHESLENIYA_TABLE varchar(255),
    OTKLONENIE_PO_SUMME_SUBSIDIY_TABLE varchar(255),
    IIK_ZAEMCHIKA_DLYA_PERECHESLENIYA_SUB_TABLE varchar(255),
    BANK_TABLE varchar(255),
    POYASNENIYE_TABLE varchar(255),
    ORDER_DOCUMENT_ID uuid not null,
    --
    primary key (ID)
)^
-- end THESISSUMMER_ORDER_DOCUMENT1C
-- begin THESISSUMMER_ORDER_DOCUMENT_FULL1C
create table THESISSUMMER_ORDER_DOCUMENT_FULL1C (
    CARD_ID uuid,
    --
    NUMBER_ varchar(255),
    ZAEMCHIK varchar(255),
    BIN varchar(255),
    ZALOGODATEL varchar(255),
    NAIMENOVANIE_ZAEMCHIKA varchar(255),
    NAIMENOVANIE_ZALOGODATELYA varchar(255),
    DOGOVOR_SVYAZANNI_S_OBESPECHENIEM varchar(255),
    PREDMET varchar(255),
    TEKUSHAYA_STOIMOST varchar(255),
    PEREOCEN_STOIMOST varchar(255),
    OBESPECHENIE varchar(255),
    ZALOGOVAYA_STOIMOST varchar(255),
    ITOG_SUMMA_K_SPISANIYU varchar(255),
    DATAPEREOCEN varchar(255),
    OSNOVANIYE varchar(255),
    DOCUMENT_USLOVIY varchar(255),
    IIN varchar(255),
    NOMER_AND_DATA varchar(255),
    SUMMA_SUBSIDI_DLYA_PERECH varchar(255),
    OTKONENIYE_PO_SUMME_SUBSIDIY varchar(255),
    IIK_ZAEMCHIKA varchar(255),
    BANK varchar(255),
    POYASNENIE varchar(255),
    ORDER_DOCUMENT_ID uuid not null,
    --
    primary key (CARD_ID)
)^
-- end THESISSUMMER_ORDER_DOCUMENT_FULL1C
-- begin THESISSUMMER_ORDER_DOCUMENT
create table THESISSUMMER_ORDER_DOCUMENT (
    CARD_ID uuid,
    --
    DATA_ZAYAVKI varchar(255),
    SET_ORDER_ID uuid,
    AVTOR_ZAYAVKI varchar(255),
    PODRAZDELENIE varchar(255),
    SUMMA_PERECHESLENIYA varchar(255),
    DATA_PEREVODA varchar(255),
    DATA_PRECHESLENIYA varchar(255),
    VID_VZAIMORACHETOV varchar(255),
    SUMMA_K_OPLATE varchar(255),
    BANK_SCHER_PLATELSHIKA varchar(255),
    DOGOVOR_PLATELSHIKA varchar(255),
    POLUCHATEL varchar(255),
    RASHCETNIYA_SCHET varchar(255),
    NOMER_RASPOR varchar(255),
    DATA_RASPOR varchar(255),
    ISH_PISMO varchar(255),
    PODRAZDELENIYA_AKIMATA varchar(255),
    RAYON varchar(255),
    OBLASTI varchar(255),
    SUMMA_DLYA_PERECHESLENIYA varchar(255),
    TEMA varchar(255),
    DOGOVOR_PORUCHENIYA varchar(255),
    OSNOVNOI_DOLG varchar(255),
    VOZNAGROZHEDNIYA varchar(255),
    PENYA varchar(255),
    UGD_PO_RAYONU varchar(255),
    DGDPO_RAOYNU varchar(255),
    PODRAZDELENIE2 varchar(255),
    NOMER_PROTOCOLA varchar(255),
    ZAEMCHIK varchar(255),
    DOGOVOR_RAMOCH varchar(255),
    OBSHAYA_SUMMA_ZALOG varchar(255),
    OBSHYA_TEK_SUMMA varchar(255),
    OBSHAYA_SUMMA_PEREOCENKI varchar(255),
    OBSHAYA_SUMMA_ITOGOVOY_PEREOCENKI varchar(255),
    OSNOVANIYA_SNYATIYA_OBESPECH varchar(255),
    SCHET_KORP varchar(255),
    BANK_ORG varchar(255),
    BIK_BANKA varchar(255),
    OBLAST_PODRAZDELENIA varchar(255),
    KVARTAL1 varchar(255),
    NAZNACHENIYA_PLATEZHA varchar(255),
    SUMMA_POSTUPLENIYA varchar(255),
    DATA_POSTUPLENIYA varchar(255),
    DOGOVOR_SUBSIDIROVANIYA varchar(255),
    DATA_DOGOVORA_SUBSIDIROVANIYA varchar(255),
    RAB_ORGAN varchar(255),
    BIN_USH varchar(255),
    KVARTAL2 varchar(255),
    SUMMA_SUBSIDIY varchar(255),
    SCHET_POLUCHATELYA varchar(255),
    BIK_POLUCHATELYA varchar(255),
    BANK_POLUCHATELYA varchar(255),
    KBK varchar(255),
    OBLAST_PODRAZDELENIA2 varchar(255),
    NOMER_PROTOKOLA_KK varchar(255),
    OSNOVANIYE varchar(255),
    CONTRAGENT varchar(255),
    BIN_CONTRAGENTA varchar(255),
    KONECHNIY_ZAEMCHIK varchar(255),
    POLNOE_NAIMENOVANIE varchar(255),
    DOGOVOR varchar(255),
    SUMMA varchar(255),
    KREDITNAYA_LINIYA varchar(255),
    ISTOCHNIK_FINANSIROVANIYA varchar(255),
    STAVKA_VOZ varchar(255),
    SROK_KREDITA varchar(255),
    BANK varchar(255),
    SUMMANA_SPEC_CHETEV_NB varchar(255),
    BIK varchar(255),
    IIK varchar(255),
    BIN varchar(255),
    KNP varchar(255),
    KOD varchar(255),
    KBE varchar(255),
    UR_ADRES varchar(255),
    NOMER_NUM varchar(255),
    ZAEMCHIK_TABLE varchar(255),
    BIN_TABLE varchar(255),
    ZALOGODATEL_TABLE varchar(255),
    NAIMENOVANIYA_ZAEMCHIKA varchar(255),
    NAIMENOVANIYA_ZALOGODATELYA varchar(255),
    DOGOVOR_SVYAZ_S_OBESPECH varchar(255),
    PREDMET_TABLE varchar(255),
    TEKUCHAYA_STOIMOST varchar(255),
    PREOCEN_STOIMOST varchar(255),
    OBESPECHENIYA_TABLE varchar(255),
    ZALOG_STOIMOST_TABLE varchar(255),
    ITOG_SUMMA_K_SPISANIYA varchar(255),
    DATA_PEREOCEN varchar(255),
    OSNOVANIYA_TABLE varchar(255),
    DOCUMENT_USLOVIY_TABLE varchar(255),
    IIN_TABLE varchar(255),
    NOMER_AND_DATA_KREDITNIY_DOGOVOR varchar(255),
    SUMMA_SUBSIDIY_DLYA_PERECHESLENIYA varchar(255),
    OTKLONENIE_PO_SUMME_SUBSIDIY varchar(255),
    IIK_ZAEMCHIKA_DLYA_PERECHESLENIYA_SUB_TABLE varchar(255),
    BANK_TABLE varchar(255),
    POYASNENIYE varchar(255),
    --
    primary key (CARD_ID)
)^
-- end THESISSUMMER_ORDER_DOCUMENT
-- begin THESISSUMMER_AAA
create table THESISSUMMER_AAA (
    ID uuid,
    VERSION integer not null,
    CREATE_TS timestamp,
    CREATED_BY varchar(50),
    UPDATE_TS timestamp,
    UPDATED_BY varchar(50),
    DELETE_TS timestamp,
    DELETED_BY varchar(50),
    --
    primary key (ID)
)^
-- end THESISSUMMER_AAA
-- begin THESISSUMMER_PROTOCOL_FILE_TEMPLATE
create table THESISSUMMER_PROTOCOL_FILE_TEMPLATE (
    ID uuid,
    VERSION integer not null,
    CREATE_TS timestamp,
    CREATED_BY varchar(50),
    UPDATE_TS timestamp,
    UPDATED_BY varchar(50),
    DELETE_TS timestamp,
    DELETED_BY varchar(50),
    --
    CODE varchar(255),
    FILE_CONTENT bytea,
    FILE_DESCRIPTOR_ID uuid,
    --
    primary key (ID)
)^
-- end THESISSUMMER_PROTOCOL_FILE_TEMPLATE
