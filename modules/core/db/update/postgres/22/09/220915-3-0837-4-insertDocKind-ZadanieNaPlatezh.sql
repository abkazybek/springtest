-- begin insert docKind for ZadanieNaPlatezh
CREATE OR REPLACE FUNCTION baseInsert()
RETURNS integer
AS $$
DECLARE
    cnt integer = 0;
BEGIN
    cnt = (select count(CATEGORY_ID) from DF_DOC_KIND where CATEGORY_ID = '208bb8fa-a602-4703-b28b-ac8b94e47fbd');
    if (cnt = 0) then
        insert into SYS_CATEGORY (ID, NAME, ENTITY_TYPE, IS_DEFAULT, CREATE_TS, CREATED_BY, VERSION, DISCRIMINATOR)
        values ('208bb8fa-a602-4703-b28b-ac8b94e47fbd', 'Задание на платеж', 'thesissummer$ZadanieNaPlatezh', false, now(), 'system', 1, 1);

        insert into DF_DOC_KIND (CATEGORY_ID, CREATE_TS, CREATED_BY, VERSION, DOC_TYPE_ID, NUMERATOR_ID,
                                 NUMERATOR_TYPE, CATEGORY_ATTRS_PLACE, TAB_NAME, PORTAL_PUBLISH_ALLOWED, DISABLE_ADD_PROCESS_ACTORS, CREATE_ONLY_BY_TEMPLATE)
        values ('208bb8fa-a602-4703-b28b-ac8b94e47fbd', now(), 'system', 1, 'b5934f83-ea3a-4137-accb-4bdc2976995a', '224f2605-e7f2-491a-9abd-2830095e06b3',
                1, 1, 'Доп. поля', false, false, false);
end if;
return 0;
END;
$$
LANGUAGE plpgsql;
^
select baseInsert();^
drop function if exists baseInsert();^
-- end insert docKind for ZadanieNaPlatezh
