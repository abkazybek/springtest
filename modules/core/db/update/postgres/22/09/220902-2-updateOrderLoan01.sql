alter table THESISSUMMER_ORDER_LOAN add constraint FK_THESISSUMMER_ORDER_LOAN_ON_ORDER_RECEIVER foreign key (ORDER_RECEIVER_ID) references DF_EMPLOYEE(CORRESPONDENT_ID);
create index IDX_THESISSUMMER_ORDER_LOAN_ON_ORDER_RECEIVER on THESISSUMMER_ORDER_LOAN (ORDER_RECEIVER_ID);
