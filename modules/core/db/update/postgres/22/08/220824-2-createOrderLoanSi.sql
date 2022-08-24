alter table THESISSUMMER_ORDER_LOAN_SI add constraint FK_THESISSUMMER_ORDER_LOAN_SI_ON_CARD foreign key (CARD_ID) references WF_CARD(ID) on delete CASCADE;
