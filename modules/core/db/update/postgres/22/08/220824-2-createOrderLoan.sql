alter table THESISSUMMER_ORDER_LOAN add constraint FK_THESISSUMMER_ORDER_LOAN_ON_CARD foreign key (CARD_ID) references WF_CARD(ID) on delete CASCADE;
