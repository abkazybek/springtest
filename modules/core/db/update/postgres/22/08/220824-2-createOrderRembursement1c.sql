alter table THESISSUMMER_ORDER_REMBURSEMENT1C add constraint FK_THESISSUMMER_ORDER_REMBURSEMENT1C_ON_ORDER_REMBURSEMENT foreign key (ORDER_REMBURSEMENT_ID) references THESISSUMMER_ORDER_REMBURSEMENT(CARD_ID);
create index IDX_THESISSUMMER_ORDER_REMBURSEMENT1C_ON_ORDER_REMBURSEMENT on THESISSUMMER_ORDER_REMBURSEMENT1C (ORDER_REMBURSEMENT_ID);