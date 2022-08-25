create table THESISSUMMER_ORDER_TRANFER (
    CARD_ID uuid,
    --
    NOMER_ORDER varchar(255),
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
);