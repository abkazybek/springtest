alter table THESISSUMMER_ORDER_RETURN_NEISP_SUB add constraint FK_THESISSUMMER_ORDER_RETURN_NEISP_SUB_ON_CARD foreign key (CARD_ID) references WF_CARD(ID) on delete CASCADE;