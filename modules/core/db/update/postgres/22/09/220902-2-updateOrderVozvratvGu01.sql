alter table THESISSUMMER_ORDER_VOZVRATV_GU add constraint FK_THESISSUMMER_ORDER_VOZVRATV_GU_ON_ORDER_RECEIVER foreign key (ORDER_RECEIVER_ID) references DF_EMPLOYEE(CORRESPONDENT_ID);
create index IDX_THESISSUMMER_ORDER_VOZVRATV_GU_ON_ORDER_RECEIVER on THESISSUMMER_ORDER_VOZVRATV_GU (ORDER_RECEIVER_ID);