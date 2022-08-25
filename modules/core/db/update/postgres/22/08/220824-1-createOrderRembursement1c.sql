create table THESISSUMMER_ORDER_REMBURSEMENT1C (
    ID uuid,
    VERSION integer not null,
    CREATE_TS timestamp,
    CREATED_BY varchar(50),
    UPDATE_TS timestamp,
    UPDATED_BY varchar(50),
    DELETE_TS timestamp,
    DELETED_BY varchar(50),
    --
    NUMBER_ varchar(255),
    NAMEOFBORROWER varchar(255),
    IIN varchar(255),
    NOMER_AND_DATA_KREDITNOGOG_DOGOVORA varchar(255),
    SUMMA_SUB varchar(255),
    IIK_ZAEM varchar(255),
    BANK varchar(255),
    ORDER_REMBURSEMENT_ID uuid not null,
    --
    primary key (ID)
);