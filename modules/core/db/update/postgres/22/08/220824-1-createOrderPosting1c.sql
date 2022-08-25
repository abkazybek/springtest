create table THESISSUMMER_ORDER_POSTING1C (
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
    NAMEOFPLEDGER varchar(255),
    DOGOVORWITHCOLLATERAL varchar(255),
    OBESPECHENIYE varchar(255),
    ZALOG_STOIMOST varchar(255),
    DOCUMENT_USL varchar(255),
    ORDER_POSTING_ID uuid not null,
    --
    primary key (ID)
);