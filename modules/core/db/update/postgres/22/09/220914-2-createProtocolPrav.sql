alter table THESISSUMMER_PROTOCOL_PRAV add constraint FK_THESISSUMMER_PROTOCOL_PRAV_ON_CARD foreign key (CARD_ID) references WF_CARD(ID) on delete CASCADE;
