-- begin insert docKind for ProtocolPrav
CREATE OR REPLACE FUNCTION baseInsert()
RETURNS integer
AS $$
DECLARE
    cnt integer = 0;
BEGIN
    cnt = (select count(CATEGORY_ID) from DF_DOC_KIND where CATEGORY_ID = '6a2cd0cd-6dbf-4151-a6ba-308354e7856d');
    if (cnt = 0) then
        insert into SYS_CATEGORY (ID, NAME, ENTITY_TYPE, IS_DEFAULT, CREATE_TS, CREATED_BY, VERSION, DISCRIMINATOR)
        values ('6a2cd0cd-6dbf-4151-a6ba-308354e7856d', 'Протокол Правления', 'thesissummer$ProtocolPrav', false, now(), 'system', 1, 1);

        insert into DF_DOC_KIND (CATEGORY_ID, CREATE_TS, CREATED_BY, VERSION, DOC_TYPE_ID, NUMERATOR_ID,
                                 NUMERATOR_TYPE, CATEGORY_ATTRS_PLACE, TAB_NAME, PORTAL_PUBLISH_ALLOWED, DISABLE_ADD_PROCESS_ACTORS, CREATE_ONLY_BY_TEMPLATE)
        values ('6a2cd0cd-6dbf-4151-a6ba-308354e7856d', now(), 'system', 1, 'f5eb864e-bdfb-43aa-a35d-6cd9d52c2e66', 'c981b413-6107-49f7-85fe-1fee10beac2d',
                1, 1, 'Доп. поля', false, false, false);
end if;
return 0;
END;
$$
LANGUAGE plpgsql;
^
select baseInsert();^
drop function if exists baseInsert();^
-- end insert docKind for ProtocolPrav
