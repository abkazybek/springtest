alter table THESISSUMMER_ORDER_NA_VOZVRAT add constraint FK_THESISSUMMER_ORDER_NA_VOZVRAT_ON_ORDER_RECEIVER foreign key (ORDER_RECEIVER_ID) references DF_EMPLOYEE(CORRESPONDENT_ID);
create index IDX_THESISSUMMER_ORDER_NA_VOZVRAT_ON_ORDER_RECEIVER on THESISSUMMER_ORDER_NA_VOZVRAT (ORDER_RECEIVER_ID);
