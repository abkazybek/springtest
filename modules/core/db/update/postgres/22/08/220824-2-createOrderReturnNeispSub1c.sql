alter table THESISSUMMER_ORDER_RETURN_NEISP_SUB1C add constraint FK_THESISSUMMER_ORDERETUNEISSUB1_ON_ORDER_RETURN_NEISP_SUB foreign key (ORDER_RETURN_NEISP_SUB_ID) references THESISSUMMER_ORDER_RETURN_NEISP_SUB(CARD_ID);
create index IDX_THESISSUMMER_ORDERETUNEISSUB1_ON_ORDER_RETURN_NEISP_SUB on THESISSUMMER_ORDER_RETURN_NEISP_SUB1C (ORDER_RETURN_NEISP_SUB_ID);