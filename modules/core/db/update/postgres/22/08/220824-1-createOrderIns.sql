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
);