alter table THESISSUMMER_ORDER_INS add constraint FK_THESISSUMMER_ORDER_INS_ON_CARD foreign key (CARD_ID) references WF_CARD(ID) on delete CASCADE;
