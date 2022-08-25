-- begin insert docKind for OrderRevaluation
CREATE OR REPLACE FUNCTION baseInsert()
RETURNS integer
AS $$
DECLARE
    cnt integer = 0;
BEGIN
    cnt = (select count(CATEGORY_ID) from DF_DOC_KIND where CATEGORY_ID = '105a7f10-7fb3-400d-9509-e2f8e88bf57d');
    if (cnt = 0) then
        insert into SYS_CATEGORY (ID, NAME, ENTITY_TYPE, IS_DEFAULT, CREATE_TS, CREATED_BY, VERSION, DISCRIMINATOR)
        values ('105a7f10-7fb3-400d-9509-e2f8e88bf57d', 'Распоряжения на переоценку обеспечения', 'thesissummer$OrderRevaluation', false, now(), 'system', 1, 1);

        insert into DF_DOC_KIND (CATEGORY_ID, CREATE_TS, CREATED_BY, VERSION, DOC_TYPE_ID, NUMERATOR_ID,
                                 NUMERATOR_TYPE, CATEGORY_ATTRS_PLACE, TAB_NAME, PORTAL_PUBLISH_ALLOWED, DISABLE_ADD_PROCESS_ACTORS, CREATE_ONLY_BY_TEMPLATE)
        values ('105a7f10-7fb3-400d-9509-e2f8e88bf57d', now(), 'system', 1, 'd9912a56-8826-40ba-ad3e-dba5f0eeb350', 'b9741837-9275-4bbf-bb2c-fb57eec08592',
                1, 1, 'Доп. поля', false, false, false);
end if;
return 0;
END;
$$
LANGUAGE plpgsql;
^
select baseInsert();^
drop function if exists baseInsert();^
-- end insert docKind for OrderRevaluation
