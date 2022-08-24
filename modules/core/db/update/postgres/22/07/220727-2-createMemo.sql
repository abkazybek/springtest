alter table THESISSUMMER_MEMO add constraint FK_THESISSUMMER_MEMO_ON_CARD foreign key (CARD_ID) references WF_CARD(ID) on delete CASCADE;
