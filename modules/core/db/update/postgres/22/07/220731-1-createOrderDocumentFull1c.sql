create table THESISSUMMER_ORDER_DOCUMENT_FULL1C (
    CARD_ID uuid,
    --
    NUMBER_ varchar(255),
    ZAEMCHIK varchar(255),
    BIN varchar(255),
    ZALOGODATEL varchar(255),
    NAIMENOVANIE_ZAEMCHIKA varchar(255),
    NAIMENOVANIE_ZALOGODATELYA varchar(255),
    DOGOVOR_SVYAZANNI_S_OBESPECHENIEM varchar(255),
    PREDMET varchar(255),
    TEKUSHAYA_STOIMOST varchar(255),
    PEREOCEN_STOIMOST varchar(255),
    OBESPECHENIE varchar(255),
    ZALOGOVAYA_STOIMOST varchar(255),
    ITOG_SUMMA_K_SPISANIYU varchar(255),
    DATAPEREOCEN varchar(255),
    OSNOVANIYE varchar(255),
    DOCUMENT_USLOVIY varchar(255),
    IIN varchar(255),
    NOMER_AND_DATA varchar(255),
    SUMMA_SUBSIDI_DLYA_PERECH varchar(255),
    OTKONENIYE_PO_SUMME_SUBSIDIY varchar(255),
    IIK_ZAEMCHIKA varchar(255),
    BANK varchar(255),
    POYASNENIE varchar(255),
    --
    primary key (CARD_ID)
);