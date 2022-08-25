create table THESISSUMMER_ORDER_POSTING (
    CARD_ID uuid,
    --
    NOMER_ORDER varchar(255),
    DATA_ORDER varchar(255),
    PODRAZDELENIE varchar(255),
    NOMER_PROTOCOLA varchar(255),
    ZAEMSHIK varchar(255),
    DOGOVOR_RAMOCH_SOGL varchar(255),
    KONECHNIY_ZAEMCHIK varchar(255),
    OBSHAYA_SUMMA varchar(255),
    --
    primary key (CARD_ID)
);