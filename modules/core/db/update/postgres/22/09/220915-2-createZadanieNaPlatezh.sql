alter table THESISSUMMER_ZADANIE_NA_PLATEZH add constraint FK_THESISSUMMER_ZADANIE_NA_PLATEZH_ON_CARD foreign key (CARD_ID) references WF_CARD(ID) on delete CASCADE;