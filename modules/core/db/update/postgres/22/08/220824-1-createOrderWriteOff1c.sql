create table THESISSUMMER_ORDER_WRITE_OFF1C (
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
    NAIMENOVANIE_ZALOG varchar(255),
    DOGOVOR_SVYAZ_S_OBESPECH varchar(255),
    OBESPECHENIYE varchar(255),
    ZALOG_STOIMOST varchar(255),
    DOCUMENT_USLOVIY varchar(255),
    ORDER_WRITE_OFF_ID uuid not null,
    --
    primary key (ID)
);