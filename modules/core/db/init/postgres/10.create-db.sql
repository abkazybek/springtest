-- begin DF_DEPARTMENT
alter table DF_DEPARTMENT add column PODRAZDELENIE varchar(50) ^
alter table DF_DEPARTMENT add column DESCRIBE_ varchar(255) ^
-- end DF_DEPARTMENT
-- begin THESISSUMMER_ORDER_LOAN_SI
create table THESISSUMMER_ORDER_LOAN_SI (
    CARD_ID uuid,
    --
    NOMER_RASP varchar(255),
    ORDER_RECEIVER_ID uuid,
    ISTOCHNIK varchar(255),
    DATA_RASP varchar(255),
    PODRAZDELENIE varchar(255),
    OSNOVANIE varchar(255),
    KONTRAGENT varchar(255),
    IIN varchar(255),
    KONECH_ZAEMCHIK varchar(255),
    DOGOVOR varchar(255),
    SUMMA varchar(255),
    STAVKA_VOZ varchar(255),
    SROK_KREDITA varchar(255),
    BANK varchar(255),
    BIK varchar(255),
    IIK varchar(255),
    BIN varchar(255),
    KBE varchar(255),
    UR_ADRES varchar(255),
    --
    primary key (CARD_ID)
)^
-- end THESISSUMMER_ORDER_LOAN_SI
-- begin THESISSUMMER_ORDER_VOZVRATV_GU
create table THESISSUMMER_ORDER_VOZVRATV_GU (
    CARD_ID uuid,
    --
    DOGOVOR_PORUCH varchar(255),
    ORDER_RECEIVER_ID uuid,
    DATA_ORDER varchar(255),
    NOMER_ORDER varchar(255),
    ONSOVNOI_DOLG varchar(255),
    VOZNAGRAZHDENIE varchar(255),
    PENYA varchar(255),
    UGD_RAYON varchar(255),
    DGD_OBLAST varchar(255),
    BIN varchar(255),
    IIK varchar(255),
    BIK varchar(255),
    BIK2 varchar(255),
    KNP varchar(255),
    KOD varchar(255),
    --
    primary key (CARD_ID)
)^
-- end THESISSUMMER_ORDER_VOZVRATV_GU
-- begin THESISSUMMER_ORDER_RETURN_NEISP_SUB1C
create table THESISSUMMER_ORDER_RETURN_NEISP_SUB1C (
    ID uuid,
    VERSION integer not null,
    CREATE_TS timestamp,
    CREATED_BY varchar(50),
    UPDATE_TS timestamp,
    UPDATED_BY varchar(50),
    DELETE_TS timestamp,
    DELETED_BY varchar(50),
    --
    NOMER varchar(255),
    NAIMENOVANIE_ZAEMCHIKA varchar(255),
    NOMER_DATA_KREDITNOGO_DOGOVORA varchar(255),
    SUMMA_SUBSIDIY_OTKLONENIYA varchar(255),
    DOGOVOR_SUBSIDIROVANIYA varchar(255),
    POYASNENIE varchar(255),
    ORDER_RETURN_NEISP_SUB_ID uuid not null,
    --
    primary key (ID)
)^
-- end THESISSUMMER_ORDER_RETURN_NEISP_SUB1C
-- begin THESISSUMMER_ORDER_REMBURSEMENT1C
create table THESISSUMMER_ORDER_REMBURSEMENT1C (
    ID uuid,
    VERSION integer not null,
    CREATE_TS timestamp,
    CREATED_BY varchar(50),
    UPDATE_TS timestamp,
    UPDATED_BY varchar(50),
    DELETE_TS timestamp,
    DELETED_BY varchar(50),
    --
    NUMBER_ varchar(255),
    NAMEOFBORROWER varchar(255),
    IIN varchar(255),
    NOMER_AND_DATA_KREDITNOGOG_DOGOVORA varchar(255),
    SUMMA_SUB varchar(255),
    IIK_ZAEM varchar(255),
    BANK varchar(255),
    ORDER_REMBURSEMENT_ID uuid not null,
    --
    primary key (ID)
)^
-- end THESISSUMMER_ORDER_REMBURSEMENT1C
-- begin THESISSUMMER_ORDER_REMBURSEMENT
create table THESISSUMMER_ORDER_REMBURSEMENT (
    CARD_ID uuid,
    --
    NOMER_ORDER varchar(255),
    ORDER_RECEIVER_ID uuid,
    DATA_ORDER varchar(255),
    PODRAZDELENIE varchar(255),
    SCHET_KORPORACII varchar(255),
    BANK_ORGANIZACII varchar(255),
    BIK_BANKA varchar(255),
    OBLAST_PODRAZDELENIYA varchar(255),
    KVARTAL1 varchar(255),
    DOGOVOR_SUB varchar(255),
    DATA_DOGOVORA_SUB varchar(255),
    RAB_ORGAN varchar(255),
    KVARTAL2 varchar(255),
    SUMMA_SUB varchar(255),
    --
    primary key (CARD_ID)
)^
-- end THESISSUMMER_ORDER_REMBURSEMENT
-- begin THESISSUMMER_ORDER_POSTING1C
create table THESISSUMMER_ORDER_POSTING1C (
    ID uuid,
    VERSION integer not null,
    CREATE_TS timestamp,
    CREATED_BY varchar(50),
    UPDATE_TS timestamp,
    UPDATED_BY varchar(50),
    DELETE_TS timestamp,
    DELETED_BY varchar(50),
    --
    NUMBER_ varchar(255),
    NAMEOFPLEDGER varchar(255),
    DOGOVORWITHCOLLATERAL varchar(255),
    OBESPECHENIYE varchar(255),
    ZALOG_STOIMOST varchar(255),
    DOCUMENT_USL varchar(255),
    ORDER_POSTING_ID uuid not null,
    --
    primary key (ID)
)^
-- end THESISSUMMER_ORDER_POSTING1C
-- begin THESISSUMMER_ORDER_POSTING
create table THESISSUMMER_ORDER_POSTING (
    CARD_ID uuid,
    --
    NOMER_ORDER varchar(255),
    ORDER_RECEIVER_ID uuid,
    DATA_ORDER varchar(255),
    PODRAZDELENIE varchar(255),
    NOMER_PROTOCOLA varchar(255),
    ZAEMSHIK varchar(255),
    DOGOVOR_RAMOCH_SOGL varchar(255),
    KONECHNIY_ZAEMCHIK varchar(255),
    OBSHAYA_SUMMA varchar(255),
    --
    primary key (CARD_ID)
)^
-- end THESISSUMMER_ORDER_POSTING
-- begin THESISSUMMER_ORDER_REVALUATION1C
create table THESISSUMMER_ORDER_REVALUATION1C (
    ID uuid,
    VERSION integer not null,
    CREATE_TS timestamp,
    CREATED_BY varchar(50),
    UPDATE_TS timestamp,
    UPDATED_BY varchar(50),
    DELETE_TS timestamp,
    DELETED_BY varchar(50),
    --
    NUMBER_ varchar(255),
    ZALOGODATEL varchar(255),
    DOGOVORSWITHCOLLATERAL varchar(255),
    PREDMET varchar(255),
    TEKUSHAYA_STOIMOST varchar(255),
    PEREOCENENNYA_STOIMOST varchar(255),
    ITOGOVYA_SUMMA varchar(255),
    DATA_PEREOCENKI varchar(255),
    OSNOVANIYE varchar(255),
    ORDER_REVALUATION_ID uuid not null,
    --
    primary key (ID)
)^
-- end THESISSUMMER_ORDER_REVALUATION1C
-- begin THESISSUMMER_ORDER_REVALUATION
create table THESISSUMMER_ORDER_REVALUATION (
    CARD_ID uuid,
    --
    NOMER_ORDER varchar(255),
    ORDER_RECEIVER_ID uuid,
    DATA_ORDER varchar(255),
    PODRAZDELENIE varchar(255),
    NOMER_PROTOCOLA varchar(255),
    ZAEMCHIK varchar(255),
    DOGOVOR_RAMOCH varchar(255),
    KONECHNIY_ZAEMCHIK varchar(255),
    OBHAYA_TEK_SUMMA varchar(255),
    OBSHAYA_SUMMA_PEREOCENKI varchar(255),
    OBSHAYA_ITOG_PEREOCENKI varchar(255),
    --
    primary key (CARD_ID)
)^
-- end THESISSUMMER_ORDER_REVALUATION
-- begin THESISSUMMER_ORDER_TRANFER1C
create table THESISSUMMER_ORDER_TRANFER1C (
    ID uuid,
    VERSION integer not null,
    CREATE_TS timestamp,
    CREATED_BY varchar(50),
    UPDATE_TS timestamp,
    UPDATED_BY varchar(50),
    DELETE_TS timestamp,
    DELETED_BY varchar(50),
    --
    NUMBER_ varchar(255),
    ZAEMCHIK varchar(255),
    IIN varchar(255),
    NOMERAND_DATA_KREDITNOGO_DOGOVORA varchar(255),
    SUMMA_SUBSIDII_KREDITNOGO_DOGOVORA varchar(255),
    ORDER_TRANFER_ID uuid not null,
    --
    primary key (ID)
)^
-- end THESISSUMMER_ORDER_TRANFER1C
-- begin THESISSUMMER_ORDER_WRITE_OFF1C
create table THESISSUMMER_ORDER_WRITE_OFF1C (
    ID uuid,
    VERSION integer not null,
    CREATE_TS timestamp,
    CREATED_BY varchar(50),
    UPDATE_TS timestamp,
    UPDATED_BY varchar(50),
    DELETE_TS timestamp,
    DELETED_BY varchar(50),
    --
    NUMBER_ varchar(255),
    NAIMENOVANIE_ZALOG varchar(255),
    DOGOVOR_SVYAZ_S_OBESPECH varchar(255),
    OBESPECHENIYE varchar(255),
    ZALOG_STOIMOST varchar(255),
    DOCUMENT_USLOVIY varchar(255),
    ORDER_WRITE_OFF_ID uuid not null,
    --
    primary key (ID)
)^
-- end THESISSUMMER_ORDER_WRITE_OFF1C
-- begin THESISSUMMER_ORDER_WRITE_OFF
create table THESISSUMMER_ORDER_WRITE_OFF (
    CARD_ID uuid,
    --
    NOMER_ORDER varchar(255),
    ORDER_RECEIVER_ID uuid,
    DATA_ORDER varchar(255),
    PODRAZDELENIE varchar(255),
    NOMER_PROTOCOLA varchar(255),
    ZAEMCHIK varchar(255),
    DOGOVOR_RAMOCH varchar(255),
    KONECHNIY_ZAEMCHIK varchar(255),
    OSNOVANIYE_SNIYATIA_OBESPECH varchar(255),
    OBSHAYA_SUMMA_ZALOG_OBESPECH varchar(255),
    --
    primary key (CARD_ID)
)^
-- end THESISSUMMER_ORDER_WRITE_OFF
-- begin THESISSUMMER_ORDER_SUBSIDIY
create table THESISSUMMER_ORDER_SUBSIDIY (
    CARD_ID uuid,
    --
    NOMER_ORDER varchar(255),
    ORDER_RECEIVER_ID uuid,
    DATA_ORDER varchar(255),
    --
    primary key (CARD_ID)
)^
-- end THESISSUMMER_ORDER_SUBSIDIY
-- begin THESISSUMMER_ORDER_INS
create table THESISSUMMER_ORDER_INS (
    CARD_ID uuid,
    --
    PODRAZDELENIE varchar(255),
    ORDER_RECEIVER_ID uuid,
    DATA_ORDER varchar(255),
    NOMER_ORDER varchar(255),
    RESHENIE_OPERATORA varchar(255),
    DOGOVOR_STRAH varchar(255),
    IIK_OBSH varchar(255),
    SUMMA_DLYA_PERECH varchar(255),
    NAIMENOVANIE_POLUCH varchar(255),
    BIN varchar(255),
    IIK varchar(255),
    BIK varchar(255),
    KBE varchar(255),
    BANK_POLUCH varchar(255),
    BIK_POLUCHATEL varchar(255),
    KBE_POLUCHATEL varchar(255),
    SUMMA_NA_SPEC varchar(255),
    --
    primary key (CARD_ID)
)^
-- end THESISSUMMER_ORDER_INS
-- begin THESISSUMMER_ORDER_NA_VOZVRAT
create table THESISSUMMER_ORDER_NA_VOZVRAT (
    CARD_ID uuid,
    --
    NOMER_ORDER varchar(255),
    ORDER_RECEIVER_ID uuid,
    DATA_ORDER varchar(255),
    PODRAZDELENIE varchar(255),
    ISH_PISMO varchar(255),
    PODRAZDELENIE_AKIMATA varchar(255),
    RAYON varchar(255),
    OBLAST varchar(255),
    BASE_DOUBT varchar(255),
    SUMMA_DLYA_PERECH varchar(255),
    POLUCHATEL varchar(255),
    BIN varchar(255),
    IIK varchar(255),
    BIK varchar(255),
    KNP varchar(255),
    KOD varchar(255),
    --
    primary key (CARD_ID)
)^
-- end THESISSUMMER_ORDER_NA_VOZVRAT
-- begin THESISSUMMER_ORDER_CEN_BUMAGA
create table THESISSUMMER_ORDER_CEN_BUMAGA (
    CARD_ID uuid,
    --
    NOMER_ORDER varchar(255),
    ORDER_RECEIVER_ID uuid,
    DATA_ORDER varchar(255),
    CONTRAGENT varchar(255),
    EMITENT varchar(255),
    NIN varchar(255),
    DOGOVOR varchar(255),
    NOMER_SDELKI varchar(255),
    VID varchar(255),
    CENNAYA_BUMAGA varchar(255),
    BASE_NACH varchar(255),
    DOHODNOST_REPO varchar(255),
    DATA_OPEN varchar(255),
    KOLVO_OBLIG varchar(255),
    SUMMA_SDELKI varchar(255),
    VOZNAGRAZHDENIE varchar(255),
    DATA_CLOSE varchar(255),
    --
    primary key (CARD_ID)
)^
-- end THESISSUMMER_ORDER_CEN_BUMAGA
-- begin THESISSUMMER_ORDER_RETURN_NEISP_SUB
create table THESISSUMMER_ORDER_RETURN_NEISP_SUB (
    CARD_ID uuid,
    --
    DATA_ORDER varchar(255),
    ORDER_RECEIVER_ID uuid,
    NOMER_ORDER varchar(255),
    PODRAZDELENIE varchar(255),
    SCHET_KORPORACII varchar(255),
    BANK_ORGANIZACII varchar(255),
    BIK_BANKA varchar(255),
    OBLAST_PODRAZDELENIA varchar(255),
    KVARTAL1 varchar(255),
    NAZNACHENIYA_PLATEZHA varchar(255),
    SUMMA_POSTUPLENIYA varchar(255),
    DATA_POSTUPLENIYA varchar(255),
    DOGOVOR_SUB varchar(255),
    RAB_ORGAN varchar(255),
    BIN_USH varchar(255),
    USH varchar(255),
    KVARTAL2 varchar(255),
    SUMMA_SUB varchar(255),
    SCHET_POLUCHATELYA varchar(255),
    BIK_POLUCH varchar(255),
    KBK varchar(255),
    OBLAST_PODRAZDEL2 varchar(255),
    --
    primary key (CARD_ID)
)^
-- end THESISSUMMER_ORDER_RETURN_NEISP_SUB
-- begin THESISSUMMER_ORDER_POGASH_OB
create table THESISSUMMER_ORDER_POGASH_OB (
    CARD_ID uuid,
    --
    DOGOVOR varchar(255),
    ORDER_RECEIVER_ID uuid,
    NAZNACHENIYE varchar(255),
    CONTRAGENT varchar(255),
    DATA_PERECH varchar(255),
    VID_CZAIMORACHETOV varchar(255),
    SUMMA_K_OPLATE varchar(255),
    POLUCHATEL varchar(255),
    NOMER_ORDER varchar(255),
    DATA_ORDER varchar(255),
    BANKOVSCKIY_SCHET_PLATELSHIKA varchar(255),
    DOGOVOR_PLAT varchar(255),
    BIN varchar(255),
    BANK_BENEFECIAR varchar(255),
    BIK varchar(255),
    IIK varchar(255),
    KBE varchar(255),
    KNP varchar(255),
    KBK varchar(255),
    --
    primary key (CARD_ID)
)^
-- end THESISSUMMER_ORDER_POGASH_OB
-- begin THESISSUMMER_ORDER_LOAN
create table THESISSUMMER_ORDER_LOAN (
    CARD_ID uuid,
    --
    PODRAZDELENIE varchar(255),
    ORDER_RECEIVER_ID uuid,
    NOMER_ORDER varchar(255),
    DATA_ORDER varchar(255),
    PODRAZDELENIE2 varchar(255),
    NOMER_PROTOCOLA_KK varchar(255),
    CONTRAGENT varchar(255),
    FULL_NAME varchar(255),
    DOGOVOR varchar(255),
    SUMMA varchar(255),
    KREDIT_LINIYA varchar(255),
    ISTOCHNIK varchar(255),
    STAVKA_VOZ varchar(255),
    SROK_KREDITA varchar(255),
    BANK varchar(255),
    BIK varchar(255),
    IIK varchar(255),
    BIN varchar(255),
    KBE varchar(255),
    UR_ADRESS varchar(255),
    --
    primary key (CARD_ID)
)^
-- end THESISSUMMER_ORDER_LOAN
-- begin THESISSUMMER_ORDER_TRANFER_MONEY
create table THESISSUMMER_ORDER_TRANFER_MONEY (
    CARD_ID uuid,
    --
    SUMMA_DLY_A_PERECH varchar(255),
    ORDER_RECEIVER_ID uuid,
    DATA_PEREVODA varchar(255),
    POLUCHATEL varchar(255),
    RASCHET_SCHET varchar(255),
    NOMER_ORDER varchar(255),
    DATA_ORDER varchar(255),
    RAS_CHET_ZACH varchar(255),
    --
    primary key (CARD_ID)
)^
-- end THESISSUMMER_ORDER_TRANFER_MONEY
-- begin THESISSUMMER_TYPE_PROTOCOL_KK
create table THESISSUMMER_TYPE_PROTOCOL_KK (
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
    NAME varchar(255),
    --
    primary key (ID)
)^
-- end THESISSUMMER_TYPE_PROTOCOL_KK
-- begin THESISSUMMER_CITY
create table THESISSUMMER_CITY (
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
    NAME varchar(255),
    --
    primary key (ID)
)^
-- end THESISSUMMER_CITY
-- begin THESISSUMMER_PROTOCOL_KK
create table THESISSUMMER_PROTOCOL_KK (
    CARD_ID uuid,
    --
    NOMER_PROTOCOLA varchar(255),
    ORDER_RECEIVER_ID uuid,
    JURIST_ID uuid,
    TYPE_PROTOCOL_ID uuid,
    CITY_ID uuid,
    DATA_PROTOCOLA varchar(255),
    --
    primary key (CARD_ID)
)^
-- end THESISSUMMER_PROTOCOL_KK
-- begin THESISSUMMER_ORDER_TRANFER
create table THESISSUMMER_ORDER_TRANFER (
    CARD_ID uuid,
    --
    NOMER_ORDER varchar(255),
    ORDER_RECEIVER_ID uuid,
    DATA_ORDER varchar(255),
    PODRAZDELENIE varchar(255),
    SCHET_KORP varchar(255),
    BANK_ORG varchar(255),
    OBLAST_PODR varchar(255),
    KVARTAL1 varchar(255),
    DOGOVOR_SUB varchar(255),
    DATA_DOGOVOR_SUB varchar(255),
    RAB_OPERATOR varchar(255),
    SUMMA_SUB varchar(255),
    SCHET_POL varchar(255),
    BANK_POLUCHA varchar(255),
    --
    primary key (CARD_ID)
)^
-- end THESISSUMMER_ORDER_TRANFER
-- begin THESISSUMMER_PROTOCOL_PRAV
create table THESISSUMMER_PROTOCOL_PRAV (
    CARD_ID uuid,
    --
    NOMER_PROTOCOLA varchar(255),
    DATA_PROTOCOLA varchar(255),
    --
    primary key (CARD_ID)
)^
-- end THESISSUMMER_PROTOCOL_PRAV

-- begin THESISSUMMER_ZADANIE_NA_PLATEZH
create table THESISSUMMER_ZADANIE_NA_PLATEZH (
    CARD_ID uuid,
    --
    NAIMENOVANIE_POL varchar(255),
    SUMMA_TENGE varchar(255),
    SUMMA_DOLLAR varchar(255),
    NOMER_AND_DATA_DOGOVORA varchar(255),
    ORDER_RECEIVER_ID uuid,
    NAZNACH_PLATESZ varchar(255),
    SCHET_NA_OPLATU varchar(255),
    NOMER_STATI varchar(255),
    --
    primary key (CARD_ID)
)^
-- end THESISSUMMER_ZADANIE_NA_PLATEZH
-- begin THESISSUMMER_SPRAVKA_PO_ZADOL
create table THESISSUMMER_SPRAVKA_PO_ZADOL (
    CARD_ID uuid,
    --
    NOMER_ORDER varchar(255),
    DATA_ORDER varchar(255),
    POLUCHATEL_ID uuid,
    --
    primary key (CARD_ID)
)^
-- end THESISSUMMER_SPRAVKA_PO_ZADOL
