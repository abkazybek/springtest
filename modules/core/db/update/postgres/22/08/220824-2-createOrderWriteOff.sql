alter table THESISSUMMER_ORDER_WRITE_OFF add constraint FK_THESISSUMMER_ORDER_WRITE_OFF_ON_CARD foreign key (CARD_ID) references WF_CARD(ID) on delete CASCADE;
