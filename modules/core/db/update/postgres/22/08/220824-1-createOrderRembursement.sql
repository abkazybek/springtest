create table THESISSUMMER_ORDER_REMBURSEMENT (
    CARD_ID uuid,
    --
    NOMER_ORDER varchar(255),
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
);