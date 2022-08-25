create table THESISSUMMER_ORDER_REVALUATION (
    CARD_ID uuid,
    --
    NOMER_ORDER varchar(255),
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
);