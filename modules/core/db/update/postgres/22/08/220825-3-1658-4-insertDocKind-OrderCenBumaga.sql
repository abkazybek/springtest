-- begin insert docKind for OrderCenBumaga
CREATE OR REPLACE FUNCTION baseInsert()
RETURNS integer
AS $$
DECLARE
    cnt integer = 0;
BEGIN
    cnt = (select count(CATEGORY_ID) from DF_DOC_KIND where CATEGORY_ID = '94cd15fd-11f9-4c2e-b4b2-ffab658ce461');
    if (cnt = 0) then
        insert into SYS_CATEGORY (ID, NAME, ENTITY_TYPE, IS_DEFAULT, CREATE_TS, CREATED_BY, VERSION, DISCRIMINATOR)
        values ('94cd15fd-11f9-4c2e-b4b2-ffab658ce461', 'Распоряжения по ценным бумагам', 'thesissummer$OrderCenBumaga', false, now(), 'system', 1, 1);

        insert into DF_DOC_KIND (CATEGORY_ID, CREATE_TS, CREATED_BY, VERSION, DOC_TYPE_ID, NUMERATOR_ID,
                                 NUMERATOR_TYPE, CATEGORY_ATTRS_PLACE, TAB_NAME, PORTAL_PUBLISH_ALLOWED, DISABLE_ADD_PROCESS_ACTORS, CREATE_ONLY_BY_TEMPLATE)
        values ('94cd15fd-11f9-4c2e-b4b2-ffab658ce461', now(), 'system', 1, 'e2d3cd7e-60d6-42ed-960e-c5624c8e5985', '5c171be0-5d8b-4604-88c2-49a52ac00dcb',
                1, 1, 'Доп. поля', false, false, false);
end if;
return 0;
END;
$$
LANGUAGE plpgsql;
^
select baseInsert();^
drop function if exists baseInsert();^
-- end insert docKind for OrderCenBumaga
