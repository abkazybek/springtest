alter table THESISSUMMER_ORDER_TRANFER_MONEY add constraint FK_THESISSUMMER_ORDER_TRANFER_MONEY_ON_CARD foreign key (CARD_ID) references WF_CARD(ID) on delete CASCADE;
