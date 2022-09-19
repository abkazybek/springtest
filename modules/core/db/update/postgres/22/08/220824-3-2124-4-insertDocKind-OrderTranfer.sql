-- begin insert docKind for OrderTranfer
CREATE OR REPLACE FUNCTION baseInsert()
RETURNS integer
AS $$
DECLARE
    cnt integer = 0;
BEGIN
    cnt = (select count(CATEGORY_ID) from DF_DOC_KIND where CATEGORY_ID = 'aa67347a-ff92-450a-a351-942e73c22063');
    if (cnt = 0) then
        insert into SYS_CATEGORY (ID, NAME, ENTITY_TYPE, IS_DEFAULT, CREATE_TS, CREATED_BY, VERSION, DISCRIMINATOR)
        values ('aa67347a-ff92-450a-a351-942e73c22063', 'Распоряжения на перечисление субсидий', 'thesissummer$OrderTranfer', false, now(), 'system', 1, 1);

        insert into DF_DOC_KIND (CATEGORY_ID, CREATE_TS, CREATED_BY, VERSION, DOC_TYPE_ID, NUMERATOR_ID,
                                 NUMERATOR_TYPE, CATEGORY_ATTRS_PLACE, TAB_NAME, PORTAL_PUBLISH_ALLOWED, DISABLE_ADD_PROCESS_ACTORS, CREATE_ONLY_BY_TEMPLATE)
        values ('aa67347a-ff92-450a-a351-942e73c22063', now(), 'system', 1, '3d742b3c-2df2-44ff-82e3-36cc6ff1432b', 'ee384850-58e8-49ac-85c2-29af6ef219a8',
                1, 1, 'Доп. поля', false, false, false);
end if;
return 0;
END;
$$
LANGUAGE plpgsql;
select baseInsert();
drop function if exists baseInsert();
-- end insert docKind for OrderTranfer
