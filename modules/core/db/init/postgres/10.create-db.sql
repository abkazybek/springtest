-- begin DF_DEPARTMENT
alter table DF_DEPARTMENT add column PODRAZDELENIE varchar(50) ^
alter table DF_DEPARTMENT add column DESCRIBE_ varchar(255) ^
-- end DF_DEPARTMENT
-- begin THESISSUMMER_ORDER_LOAN
create table THESISSUMMER_ORDER_LOAN (
    CARD_ID uuid,
    --
    PODRAZDELENIE varchar(255),
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
-- begin THESISSUMMER_ORDER_INS
create table THESISSUMMER_ORDER_INS (
    CARD_ID uuid,
    --
    PODRAZDELENIE varchar(255),
    RESHENIE_OPERATORA varchar(255),
    DOGOVOR_STRAH varchar(255),
    IIK_OBSH varchar(255),
    SUMMA_DLYA_PERECH varchar(255),
    NAIMENOVANIE_POLUCH varchar(255),
    BIN varchar(255),
    IIK varchar(255),
    BANK_POLUCH varchar(255),
    BIK varchar(255),
    KBE varchar(255),
    SUMMA_NA_SPEC varchar(255),
    --
    primary key (CARD_ID)
)^
-- end THESISSUMMER_ORDER_INS
-- begin THESISSUMMER_ORDER_TRANFER_MONEY
create table THESISSUMMER_ORDER_TRANFER_MONEY (
    CARD_ID uuid,
    --
    SUMMA_DLY_A_PERECH varchar(255),
    DATA_PEREVODA varchar(255),
    POLUCHATEL varchar(255),
    RASCHET_SCHET varchar(255),
    --
    primary key (CARD_ID)
)^
-- end THESISSUMMER_ORDER_TRANFER_MONEY
-- begin THESISSUMMER_ORDER_LOAN_SI
create table THESISSUMMER_ORDER_LOAN_SI (
    CARD_ID uuid,
    --
    NOMER_RASP varchar(255),
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
