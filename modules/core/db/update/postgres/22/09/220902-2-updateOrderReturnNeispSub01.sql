alter table THESISSUMMER_ORDER_RETURN_NEISP_SUB add constraint FK_THESISSUMMER_ORDER_RETURN_NEISP_SUB_ON_ORDER_RECEIVER foreign key (ORDER_RECEIVER_ID) references DF_EMPLOYEE(CORRESPONDENT_ID);
create index IDX_THESISSUMMER_ORDER_RETURN_NEISP_SUB_ON_ORDER_RECEIVER on THESISSUMMER_ORDER_RETURN_NEISP_SUB (ORDER_RECEIVER_ID);