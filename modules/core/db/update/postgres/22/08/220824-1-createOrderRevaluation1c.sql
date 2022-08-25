create table THESISSUMMER_ORDER_REVALUATION1C (
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
    ZALOGODATEL varchar(255),
    DOGOVORSWITHCOLLATERAL varchar(255),
    PREDMET varchar(255),
    TEKUSHAYA_STOIMOST varchar(255),
    PEREOCENENNYA_STOIMOST varchar(255),
    ITOGOVYA_SUMMA varchar(255),
    DATA_PEREOCENKI varchar(255),
    OSNOVANIYE varchar(255),
    ORDER_REVALUATION_ID uuid not null,
    --
    primary key (ID)
);