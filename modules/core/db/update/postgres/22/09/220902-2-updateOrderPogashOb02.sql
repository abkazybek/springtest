alter table THESISSUMMER_ORDER_POGASH_OB add constraint FK_THESISSUMMER_ORDER_POGASH_OB_ON_ORDER_RECEIVER foreign key (ORDER_RECEIVER_ID) references DF_EMPLOYEE(CORRESPONDENT_ID);
create index IDX_THESISSUMMER_ORDER_POGASH_OB_ON_ORDER_RECEIVER on THESISSUMMER_ORDER_POGASH_OB (ORDER_RECEIVER_ID);
