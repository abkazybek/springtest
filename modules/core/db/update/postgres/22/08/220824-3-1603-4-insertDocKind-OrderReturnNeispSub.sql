-- begin insert docKind for OrderReturnNeispSub
CREATE OR REPLACE FUNCTION baseInsert()
RETURNS integer
AS $$
DECLARE
    cnt integer = 0;
BEGIN
    cnt = (select count(CATEGORY_ID) from DF_DOC_KIND where CATEGORY_ID = '70be4cf1-a286-4cd4-a9a2-085db6e2ddad');
    if (cnt = 0) then
        insert into SYS_CATEGORY (ID, NAME, ENTITY_TYPE, IS_DEFAULT, CREATE_TS, CREATED_BY, VERSION, DISCRIMINATOR)
        values ('70be4cf1-a286-4cd4-a9a2-085db6e2ddad', 'Распоряжения на возврат неиспользованных субсидий', 'thesissummer$OrderReturnNeispSub', false, now(), 'system', 1, 1);

        insert into DF_DOC_KIND (CATEGORY_ID, CREATE_TS, CREATED_BY, VERSION, DOC_TYPE_ID, NUMERATOR_ID,
                                 NUMERATOR_TYPE, CATEGORY_ATTRS_PLACE, TAB_NAME, PORTAL_PUBLISH_ALLOWED, DISABLE_ADD_PROCESS_ACTORS, CREATE_ONLY_BY_TEMPLATE)
        values ('70be4cf1-a286-4cd4-a9a2-085db6e2ddad', now(), 'system', 1, 'a9b20c4d-b4fe-4b53-ae98-c20cd9601a1f', '36e7173f-513e-4a08-bcba-1da0a69da074',
                1, 1, 'Доп. поля', false, false, false);
end if;
return 0;
END;
$$
LANGUAGE plpgsql;
^
select baseInsert();^
drop function if exists baseInsert();^
-- end insert docKind for OrderReturnNeispSub
