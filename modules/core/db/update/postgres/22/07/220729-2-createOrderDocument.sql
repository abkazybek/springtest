alter table THESISSUMMER_ORDER_DOCUMENT add constraint FK_THESISSUMMER_ORDER_DOCUMENT_ON_CARD foreign key (CARD_ID) references WF_CARD(ID) on delete CASCADE;
