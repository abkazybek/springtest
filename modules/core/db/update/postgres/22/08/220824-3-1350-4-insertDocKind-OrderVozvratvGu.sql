-- begin insert docKind for OrderVozvratvGu
CREATE OR REPLACE FUNCTION baseInsert()
RETURNS integer
AS $$
DECLARE
    cnt integer = 0;
BEGIN
    cnt = (select count(CATEGORY_ID) from DF_DOC_KIND where CATEGORY_ID = 'fcac82b0-79df-4e73-bcf7-f5a49653f00c');
    if (cnt = 0) then
        insert into SYS_CATEGORY (ID, NAME, ENTITY_TYPE, IS_DEFAULT, CREATE_TS, CREATED_BY, VERSION, DISCRIMINATOR)
        values ('fcac82b0-79df-4e73-bcf7-f5a49653f00c', 'Распоряжения на возврат в ГУ (СИ)', 'thesissummer$OrderVozvratvGu', false, now(), 'system', 1, 1);

        insert into DF_DOC_KIND (CATEGORY_ID, CREATE_TS, CREATED_BY, VERSION, DOC_TYPE_ID, NUMERATOR_ID,
                                 NUMERATOR_TYPE, CATEGORY_ATTRS_PLACE, TAB_NAME, PORTAL_PUBLISH_ALLOWED, DISABLE_ADD_PROCESS_ACTORS, CREATE_ONLY_BY_TEMPLATE)
        values ('fcac82b0-79df-4e73-bcf7-f5a49653f00c', now(), 'system', 1, '67ffcb48-932e-4abe-bccc-502566cc5b11', '99d7ad01-0b16-414c-b495-9ed625b8a2ea',
                1, 1, 'Доп. поля', false, false, false);
end if;
return 0;
END;
$$
LANGUAGE plpgsql;
^
select baseInsert();^
drop function if exists baseInsert();^
-- end insert docKind for OrderVozvratvGu
