alter table THESISSUMMER_ORDER_TRANFER add constraint FK_THESISSUMMER_ORDER_TRANFER_ON_CARD foreign key (CARD_ID) references WF_CARD(ID) on delete CASCADE;
