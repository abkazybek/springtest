alter table THESISSUMMER_ORDER_POGASH_OB add constraint FK_THESISSUMMER_ORDER_POGASH_OB_ON_CARD foreign key (CARD_ID) references WF_CARD(ID) on delete CASCADE;
