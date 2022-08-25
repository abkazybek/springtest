alter table THESISSUMMER_ORDER_POSTING add constraint FK_THESISSUMMER_ORDER_POSTING_ON_CARD foreign key (CARD_ID) references WF_CARD(ID) on delete CASCADE;
