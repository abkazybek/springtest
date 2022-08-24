-- alter table THESISSUMMER_ORDER_DOCUMENT1C add column ORDER_DOCUMENT_ID uuid ^
-- update THESISSUMMER_ORDER_DOCUMENT1C set ORDER_DOCUMENT_ID = <default_value> ;
-- alter table THESISSUMMER_ORDER_DOCUMENT1C alter column ORDER_DOCUMENT_ID set not null ;
alter table THESISSUMMER_ORDER_DOCUMENT1C add column ORDER_DOCUMENT_ID uuid not null ;
