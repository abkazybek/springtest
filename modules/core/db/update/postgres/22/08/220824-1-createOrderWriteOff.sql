create table THESISSUMMER_ORDER_WRITE_OFF (
    CARD_ID uuid,
    --
    NOMER_ORDER varchar(255),
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
);