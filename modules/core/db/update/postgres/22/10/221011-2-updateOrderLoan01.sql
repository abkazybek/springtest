alter table THESISSUMMER_ORDER_LOAN add constraint FK_THESISSUMMER_ORDER_LOAN_ON_PROTOCOL_TEMPLATE foreign key (PROTOCOL_TEMPLATE_ID) references SYS_FILE(ID);
create index IDX_THESISSUMMER_ORDER_LOAN_ON_PROTOCOL_TEMPLATE on THESISSUMMER_ORDER_LOAN (PROTOCOL_TEMPLATE_ID);
