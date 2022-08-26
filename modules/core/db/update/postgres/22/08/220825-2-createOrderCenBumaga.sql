alter table THESISSUMMER_ORDER_CEN_BUMAGA add constraint FK_THESISSUMMER_ORDER_CEN_BUMAGA_ON_CARD foreign key (CARD_ID) references WF_CARD(ID) on delete CASCADE;
