create table THESISSUMMER_ORDER_TRANFER1C (
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
    ZAEMCHIK varchar(255),
    IIN varchar(255),
    NOMERAND_DATA_KREDITNOGO_DOGOVORA varchar(255),
    SUMMA_SUBSIDII_KREDITNOGO_DOGOVORA varchar(255),
    ORDER_TRANFER_ID uuid not null,
    --
    primary key (ID)
);