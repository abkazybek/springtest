create table THESISSUMMER_ORDER_NA_VOZVRAT (
    CARD_ID uuid,
    --
    NOMER_ORDER varchar(255),
    DATA_ORDER varchar(255),
    PODRAZDELENIE varchar(255),
    ISH_PISMO varchar(255),
    PODRAZDELENIE_AKIMATA varchar(255),
    RAYON varchar(255),
    OBLAST varchar(255),
    SUMMA_DLYA_PERECH varchar(255),
    POLUCHATEL varchar(255),
    BIN varchar(255),
    IIK varchar(255),
    BIK varchar(255),
    KNP varchar(255),
    KOD varchar(255),
    --
    primary key (CARD_ID)
);