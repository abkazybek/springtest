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
);