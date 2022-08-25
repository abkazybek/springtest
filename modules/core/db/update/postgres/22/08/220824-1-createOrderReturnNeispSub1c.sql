create table THESISSUMMER_ORDER_RETURN_NEISP_SUB1C (
    ID uuid,
    VERSION integer not null,
    CREATE_TS timestamp,
    CREATED_BY varchar(50),
    UPDATE_TS timestamp,
    UPDATED_BY varchar(50),
    DELETE_TS timestamp,
    DELETED_BY varchar(50),
    --
    NOMER varchar(255),
    NAIMENOVANIE_ZAEMCHIKA varchar(255),
    NOMER_DATA_KREDITNOGO_DOGOVORA varchar(255),
    SUMMA_SUBSIDIY_OTKLONENIYA varchar(255),
    DOGOVOR_SUBSIDIROVANIYA varchar(255),
    POYASNENIE varchar(255),
    ORDER_RETURN_NEISP_SUB_ID uuid not null,
    --
    primary key (ID)
);