-- begin insert docKind for OrderNaVozvrat
CREATE OR REPLACE FUNCTION baseInsert()
RETURNS integer
AS $$
DECLARE
    cnt integer = 0;
BEGIN
    cnt = (select count(CATEGORY_ID) from DF_DOC_KIND where CATEGORY_ID = 'd461194e-9a7c-44b4-b09f-0010094a8d62');
    if (cnt = 0) then
        insert into SYS_CATEGORY (ID, NAME, ENTITY_TYPE, IS_DEFAULT, CREATE_TS, CREATED_BY, VERSION, DISCRIMINATOR)
        values ('d461194e-9a7c-44b4-b09f-0010094a8d62', 'На возврат в ГУ неосв./ошибочно перечисл. средств (СИ)', 'thesissummer$OrderNaVozvrat', false, now(), 'system', 1, 1);

        insert into DF_DOC_KIND (CATEGORY_ID, CREATE_TS, CREATED_BY, VERSION, DOC_TYPE_ID, NUMERATOR_ID,
                                 NUMERATOR_TYPE, CATEGORY_ATTRS_PLACE, TAB_NAME, PORTAL_PUBLISH_ALLOWED, DISABLE_ADD_PROCESS_ACTORS, CREATE_ONLY_BY_TEMPLATE)
        values ('d461194e-9a7c-44b4-b09f-0010094a8d62', now(), 'system', 1, '35b682c7-5b23-46dd-906c-1d9dad6fde57', 'a9056e68-1d5c-40bb-9e90-0ba3cfdb4850',
                1, 1, 'Доп. поля', false, false, false);
end if;
return 0;
END;
$$
LANGUAGE plpgsql;
^
select baseInsert();^
drop function if exists baseInsert();^
-- end insert docKind for OrderNaVozvrat
