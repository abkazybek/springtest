alter table THESISSUMMER_ORDER_POSTING1C add constraint FK_THESISSUMMER_ORDER_POSTING1C_ON_ORDER_POSTING foreign key (ORDER_POSTING_ID) references THESISSUMMER_ORDER_POSTING(CARD_ID);
create index IDX_THESISSUMMER_ORDER_POSTING1C_ON_ORDER_POSTING on THESISSUMMER_ORDER_POSTING1C (ORDER_POSTING_ID);
