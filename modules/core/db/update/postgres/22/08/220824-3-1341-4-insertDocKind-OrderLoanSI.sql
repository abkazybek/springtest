-- begin insert docKind for OrderLoanSI
CREATE OR REPLACE FUNCTION baseInsert()
RETURNS integer
AS $$
DECLARE
    cnt integer = 0;
BEGIN
    cnt = (select count(CATEGORY_ID) from DF_DOC_KIND where CATEGORY_ID = '67eeac25-4e4f-456f-8238-764d3420d57a');
    if (cnt = 0) then
        insert into SYS_CATEGORY (ID, NAME, ENTITY_TYPE, IS_DEFAULT, CREATE_TS, CREATED_BY, VERSION, DISCRIMINATOR)
        values ('67eeac25-4e4f-456f-8238-764d3420d57a', 'На выдачу займа (СИ)', 'thesissummer$OrderLoanSI', false, now(), 'system', 1, 1);

        insert into DF_DOC_KIND (CATEGORY_ID, CREATE_TS, CREATED_BY, VERSION, DOC_TYPE_ID, NUMERATOR_ID,
                                 NUMERATOR_TYPE, CATEGORY_ATTRS_PLACE, TAB_NAME, PORTAL_PUBLISH_ALLOWED, DISABLE_ADD_PROCESS_ACTORS, CREATE_ONLY_BY_TEMPLATE)
        values ('67eeac25-4e4f-456f-8238-764d3420d57a', now(), 'system', 1, 'cfdd51f1-0756-4fe4-bf02-5ae90dfe06a4', 'c3f6366b-95b1-446e-b955-9f3cbe5bc366',
                1, 1, 'Доп. поля', false, false, false);
end if;
return 0;
END;
$$
LANGUAGE plpgsql;
^
select baseInsert();^
drop function if exists baseInsert();^
-- end insert docKind for OrderLoanSI
