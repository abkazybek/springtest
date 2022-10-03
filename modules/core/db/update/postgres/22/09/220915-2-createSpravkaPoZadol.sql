alter table THESISSUMMER_SPRAVKA_PO_ZADOL add constraint FK_THESISSUMMER_SPRAVKA_PO_ZADOL_ON_CARD foreign key (CARD_ID) references WF_CARD(ID) on delete CASCADE;
