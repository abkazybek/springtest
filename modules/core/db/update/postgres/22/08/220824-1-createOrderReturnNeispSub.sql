create table THESISSUMMER_ORDER_RETURN_NEISP_SUB (
    CARD_ID uuid,
    --
    DATA_ORDER varchar(255),
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
    KVARTAL2 varchar(255),
    SUMMA_SUB varchar(255),
    SCHET_POLUCHATELYA varchar(255),
    BIK_POLUCH varchar(255),
    KBK varchar(255),
    OBLAST_PODRAZDEL2 varchar(255),
    --
    primary key (CARD_ID)
);