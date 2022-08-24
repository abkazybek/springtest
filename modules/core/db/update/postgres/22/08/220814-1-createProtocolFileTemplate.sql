create table THESISSUMMER_PROTOCOL_FILE_TEMPLATE (
    ID uuid,
    VERSION integer not null,
    CREATE_TS timestamp,
    CREATED_BY varchar(50),
    UPDATE_TS timestamp,
    UPDATED_BY varchar(50),
    DELETE_TS timestamp,
    DELETED_BY varchar(50),
    --
    CODE varchar(255),
    FILE_CONTENT bytea,
    FILE_DESCRIPTOR_ID uuid,
    --
    primary key (ID)
);