alter table THESISSUMMER_ORDER_NA_VOZVRAT add constraint FK_THESISSUMMER_ORDER_NA_VOZVRAT_ON_CARD foreign key (CARD_ID) references WF_CARD(ID) on delete CASCADE;
