-- begin insert default numerator for OrderLoan
CREATE OR REPLACE FUNCTION baseInsert()
RETURNS integer
AS $$
DECLARE
    cnt integer = 0;
BEGIN
cnt = (select count(id) from DF_NUMERATOR where CODE = 'OrderLoanNumerator' and delete_ts is null);
if(cnt = 0) then
    INSERT INTO DF_NUMERATOR (ID, CREATE_TS, CREATED_BY, VERSION, CODE, NUMERATOR_FORMAT, SCRIPT_ENABLED,
                              PERIODICITY, NUMBER_INITIAL_VALUE, LOC_NAME)
    VALUES ('43026722-613a-4135-abcf-485c63c6f18f', now(), 'system', 1, 'OrderLoanNumerator', '[number]', FALSE, 'Y', 1,
            '{"captionWithLanguageList":[{"language":"ru","caption":"Распоряжения на выдачу займа"},{"language":"en","caption":"OrderLoan"}]}'
    );
end if;

return 0;
END;
$$
LANGUAGE plpgsql;
^

select baseInsert()^
drop function if exists baseInsert()^
-- end insert default numerator for OrderLoan
-- begin insert cardType for OrderLoan
insert into TS_CARD_TYPE (ID, CREATE_TS, CREATED_BY, NAME, DISCRIMINATOR, FIELDS_XML)
       values ('19c9ab68-9080-4264-bca7-118bc987ca08', now(), 'system', 'thesissummer$OrderLoan', '2000', '<?xml version="1.0" encoding="UTF-8"?>
<fields>
  <field name="date" inDocKind="true" required="false" visible="true" signed="false" />
  <field name="docCategory" inDocKind="true" required="false" visible="true" signed="false" />
  <field name="owner" inDocKind="true" required="false" visible="true" signed="false" />
  <field name="department" inDocKind="true" required="false" visible="true" signed="false" />
  <field name="comment" inDocKind="true" required="false" visible="true" signed="false" />
  <field name="finishDatePlan" inDocKind="true" required="false" visible="true" signed="false" />
  <field name="resolution" inDocKind="true" required="false" visible="true" signed="false" />
  <field name="number" inDocKind="true" required="false" visible="true" signed="false" />
  <field name="organization" inDocKind="true" required="false" visible="true" signed="false" />
  <field name="parentCard" inDocKind="true" required="false" visible="true" signed="false" />
  <field name="theme" inDocKind="true" required="false" visible="true" signed="false" />
  <field name="podrazdelenie" inDocKind="true" required="false" visible="true" signed="false" />
  <field name="nomerOrder" inDocKind="true" required="false" visible="true" signed="false" />
  <field name="dataOrder" inDocKind="true" required="false" visible="true" signed="false" />
  <field name="podrazdelenie2" inDocKind="true" required="false" visible="true" signed="false" />
  <field name="nomerProtocolaKK" inDocKind="true" required="false" visible="true" signed="false" />
  <field name="contragent" inDocKind="true" required="false" visible="true" signed="false" />
  <field name="fullName" inDocKind="true" required="false" visible="true" signed="false" />
  <field name="dogovor" inDocKind="true" required="false" visible="true" signed="false" />
  <field name="summa" inDocKind="true" required="false" visible="true" signed="false" />
  <field name="kreditLiniya" inDocKind="true" required="false" visible="true" signed="false" />
  <field name="istochnik" inDocKind="true" required="false" visible="true" signed="false" />
  <field name="stavkaVoz" inDocKind="true" required="false" visible="true" signed="false" />
  <field name="srokKredita" inDocKind="true" required="false" visible="true" signed="false" />
  <field name="bank" inDocKind="true" required="false" visible="true" signed="false" />
  <field name="bik" inDocKind="true" required="false" visible="true" signed="false" />
  <field name="iik" inDocKind="true" required="false" visible="true" signed="false" />
  <field name="bin" inDocKind="true" required="false" visible="true" signed="false" />
  <field name="kbe" inDocKind="true" required="false" visible="true" signed="false" />
  <field name="urAdress" inDocKind="true" required="false" visible="true" signed="false" />
</fields>
')^
-- end insert cardType for OrderLoan
-- begin update procCardType for OrderLoan
update wf_proc set card_types = regexp_replace(card_types, ',thesissummer\\$OrderLoan', '') where code in ('Endorsement', 'Resolution', 'Acquaintance', 'Registration')^
update wf_proc set updated_by='system', card_types = card_types || 'thesissummer$OrderLoan,' where code in ('Endorsement', 'Resolution', 'Acquaintance', 'Registration')^
-- end update procCardType for OrderLoan
-- begin insert docKind for OrderLoan
CREATE OR REPLACE FUNCTION baseInsert()
RETURNS integer
AS $$
DECLARE
    cnt integer = 0;
BEGIN
    cnt = (select count(CATEGORY_ID) from DF_DOC_KIND where CATEGORY_ID = '93882fd4-5a26-4759-8c50-1d35c1f03683');
    if (cnt = 0) then
        insert into SYS_CATEGORY (ID, NAME, ENTITY_TYPE, IS_DEFAULT, CREATE_TS, CREATED_BY, VERSION, DISCRIMINATOR)
        values ('93882fd4-5a26-4759-8c50-1d35c1f03683', 'Распоряжения на выдачу займа', 'thesissummer$OrderLoan', false, now(), 'system', 1, 1);

        insert into DF_DOC_KIND (CATEGORY_ID, CREATE_TS, CREATED_BY, VERSION, DOC_TYPE_ID, NUMERATOR_ID,
                                 NUMERATOR_TYPE, CATEGORY_ATTRS_PLACE, TAB_NAME, PORTAL_PUBLISH_ALLOWED, DISABLE_ADD_PROCESS_ACTORS, CREATE_ONLY_BY_TEMPLATE)
        values ('93882fd4-5a26-4759-8c50-1d35c1f03683', now(), 'system', 1, '19c9ab68-9080-4264-bca7-118bc987ca08', '43026722-613a-4135-abcf-485c63c6f18f',
                1, 1, 'Доп. поля', false, false, false);
end if;
return 0;
END;
$$
LANGUAGE plpgsql;
^
select baseInsert();^
drop function if exists baseInsert();^
-- end insert docKind for OrderLoan
-- begin insert secPermissions for OrderLoan
insert into SEC_PERMISSION (ID, CREATE_TS, CREATED_BY, VERSION, UPDATE_TS, UPDATED_BY, DELETE_TS, DELETED_BY, PERMISSION_TYPE, TARGET, VALUE_, ROLE_ID)
values (newid(), now(), 'system', 1, now(), null, null, null, 20, 'thesissummer$OrderLoan:create', 0, (select ID from SEC_ROLE where NAME = 'SimpleUser'))^
insert into SEC_PERMISSION (ID, CREATE_TS, CREATED_BY, VERSION, UPDATE_TS, UPDATED_BY, DELETE_TS, DELETED_BY, PERMISSION_TYPE, TARGET, VALUE_, ROLE_ID)
values (newid(), now(), 'system', 1, now(), null, null, null, 20, 'thesissummer$OrderLoan:delete', 0, (select ID from SEC_ROLE where NAME = 'SimpleUser'))^
insert into SEC_PERMISSION (ID, CREATE_TS, CREATED_BY, VERSION, UPDATE_TS, UPDATED_BY, DELETE_TS, DELETED_BY, PERMISSION_TYPE, TARGET, VALUE_, ROLE_ID)
values (newid(), now(), 'system', 1, now(), null, null, null, 20, 'thesissummer$OrderLoan:create', 1, (select ID from SEC_ROLE where NAME = 'Administrators'))^
insert into SEC_PERMISSION (ID, CREATE_TS, CREATED_BY, VERSION, UPDATE_TS, UPDATED_BY, DELETE_TS, DELETED_BY, PERMISSION_TYPE, TARGET, VALUE_, ROLE_ID)
values (newid(), now(), 'system', 1, now(), null, null, null, 20, 'thesissummer$OrderLoan:update', 1, (select ID from SEC_ROLE where NAME = 'Administrators'))^
insert into SEC_PERMISSION (ID, CREATE_TS, CREATED_BY, VERSION, UPDATE_TS, UPDATED_BY, DELETE_TS, DELETED_BY, PERMISSION_TYPE, TARGET, VALUE_, ROLE_ID)
values (newid(), now(), 'system', 1, now(), null, null, null, 20, 'thesissummer$OrderLoan:delete', 1, (select ID from SEC_ROLE where NAME = 'Administrators'))^
insert into SEC_PERMISSION (ID, CREATE_TS, CREATED_BY, VERSION, UPDATE_TS, UPDATED_BY, DELETE_TS, DELETED_BY, PERMISSION_TYPE, TARGET, VALUE_, ROLE_ID)
values (newid(), now(), 'system', 1, now(), null, null, null, 20, 'thesissummer$OrderLoan:create', 1, (select ID from SEC_ROLE where NAME = 'doc_initiator'))^
insert into SEC_PERMISSION (ID, CREATE_TS, CREATED_BY, VERSION, UPDATE_TS, UPDATED_BY, DELETE_TS, DELETED_BY, PERMISSION_TYPE, TARGET, VALUE_, ROLE_ID)
values (newid(), now(), 'system', 1, now(), null, null, null, 20, 'thesissummer$OrderLoan:update', 1, (select ID from SEC_ROLE where NAME = 'doc_initiator'))^
insert into SEC_PERMISSION (ID, CREATE_TS, CREATED_BY, VERSION, UPDATE_TS, UPDATED_BY, DELETE_TS, DELETED_BY, PERMISSION_TYPE, TARGET, VALUE_, ROLE_ID)
values (newid(), now(), 'system', 1, now(), null, null, null, 20, 'thesissummer$OrderLoan:delete', 1, (select ID from SEC_ROLE where NAME = 'doc_initiator'))^
-- end insert secPermissions for OrderLoan
-- begin insert default numerator for OrderIns
CREATE OR REPLACE FUNCTION baseInsert()
RETURNS integer
AS $$
DECLARE
    cnt integer = 0;
BEGIN
cnt = (select count(id) from DF_NUMERATOR where CODE = 'OrderInsNumerator' and delete_ts is null);
if(cnt = 0) then
    INSERT INTO DF_NUMERATOR (ID, CREATE_TS, CREATED_BY, VERSION, CODE, NUMERATOR_FORMAT, SCRIPT_ENABLED,
                              PERIODICITY, NUMBER_INITIAL_VALUE, LOC_NAME)
    VALUES ('33f9e4e5-fdec-4c21-af9a-40a1d9cc739a', now(), 'system', 1, 'OrderInsNumerator', '[number]', FALSE, 'Y', 1,
            '{"captionWithLanguageList":[{"language":"ru","caption":"На выплату страховой премии"},{"language":"en","caption":"OrderIns"}]}'
    );
end if;

return 0;
END;
$$
LANGUAGE plpgsql;
^

select baseInsert()^
drop function if exists baseInsert()^
-- end insert default numerator for OrderIns
-- begin insert cardType for OrderIns
insert into TS_CARD_TYPE (ID, CREATE_TS, CREATED_BY, NAME, DISCRIMINATOR, FIELDS_XML)
       values ('500e2a14-73d9-4d23-a5d6-d902488923e1', now(), 'system', 'thesissummer$OrderIns', '2100', '<?xml version="1.0" encoding="UTF-8"?>
<fields>
  <field name="date" inDocKind="true" required="false" visible="true" signed="false" />
  <field name="docCategory" inDocKind="true" required="false" visible="true" signed="false" />
  <field name="owner" inDocKind="true" required="false" visible="true" signed="false" />
  <field name="department" inDocKind="true" required="false" visible="true" signed="false" />
  <field name="comment" inDocKind="true" required="false" visible="true" signed="false" />
  <field name="finishDatePlan" inDocKind="true" required="false" visible="true" signed="false" />
  <field name="resolution" inDocKind="true" required="false" visible="true" signed="false" />
  <field name="number" inDocKind="true" required="false" visible="true" signed="false" />
  <field name="organization" inDocKind="true" required="false" visible="true" signed="false" />
  <field name="parentCard" inDocKind="true" required="false" visible="true" signed="false" />
  <field name="theme" inDocKind="true" required="false" visible="true" signed="false" />
  <field name="podrazdelenie" inDocKind="true" required="false" visible="true" signed="false" />
  <field name="reshenieOperatora" inDocKind="true" required="false" visible="true" signed="false" />
  <field name="dogovorStrah" inDocKind="true" required="false" visible="true" signed="false" />
  <field name="iikObsh" inDocKind="true" required="false" visible="true" signed="false" />
  <field name="bik" inDocKind="true" required="false" visible="true" signed="false" />
  <field name="kbe" inDocKind="true" required="false" visible="true" signed="false" />
  <field name="summaDlyaPerech" inDocKind="true" required="false" visible="true" signed="false" />
  <field name="naimenovaniePoluch" inDocKind="true" required="false" visible="true" signed="false" />
  <field name="bin" inDocKind="true" required="false" visible="true" signed="false" />
  <field name="iik" inDocKind="true" required="false" visible="true" signed="false" />
  <field name="bankPoluch" inDocKind="true" required="false" visible="true" signed="false" />
  <field name="bik" inDocKind="true" required="false" visible="true" signed="false" />
  <field name="kbe" inDocKind="true" required="false" visible="true" signed="false" />
  <field name="summaNaSpec" inDocKind="true" required="false" visible="true" signed="false" />
</fields>
')^
-- end insert cardType for OrderIns
-- begin update procCardType for OrderIns
update wf_proc set card_types = regexp_replace(card_types, ',thesissummer\\$OrderIns', '') where code in ('Endorsement', 'Resolution', 'Acquaintance', 'Registration')^
update wf_proc set updated_by='system', card_types = card_types || 'thesissummer$OrderIns,' where code in ('Endorsement', 'Resolution', 'Acquaintance', 'Registration')^
-- end update procCardType for OrderIns
-- begin insert docKind for OrderIns
CREATE OR REPLACE FUNCTION baseInsert()
RETURNS integer
AS $$
DECLARE
    cnt integer = 0;
BEGIN
    cnt = (select count(CATEGORY_ID) from DF_DOC_KIND where CATEGORY_ID = '099e5270-08f5-48e4-9776-88eace3e19bb');
    if (cnt = 0) then
        insert into SYS_CATEGORY (ID, NAME, ENTITY_TYPE, IS_DEFAULT, CREATE_TS, CREATED_BY, VERSION, DISCRIMINATOR)
        values ('099e5270-08f5-48e4-9776-88eace3e19bb', 'На выплату страховой премии', 'thesissummer$OrderIns', false, now(), 'system', 1, 1);

        insert into DF_DOC_KIND (CATEGORY_ID, CREATE_TS, CREATED_BY, VERSION, DOC_TYPE_ID, NUMERATOR_ID,
                                 NUMERATOR_TYPE, CATEGORY_ATTRS_PLACE, TAB_NAME, PORTAL_PUBLISH_ALLOWED, DISABLE_ADD_PROCESS_ACTORS, CREATE_ONLY_BY_TEMPLATE)
        values ('099e5270-08f5-48e4-9776-88eace3e19bb', now(), 'system', 1, '500e2a14-73d9-4d23-a5d6-d902488923e1', '33f9e4e5-fdec-4c21-af9a-40a1d9cc739a',
                1, 1, 'Доп. поля', false, false, false);
end if;
return 0;
END;
$$
LANGUAGE plpgsql;
^
select baseInsert();^
drop function if exists baseInsert();^
-- end insert docKind for OrderIns
-- begin insert secPermissions for OrderIns
insert into SEC_PERMISSION (ID, CREATE_TS, CREATED_BY, VERSION, UPDATE_TS, UPDATED_BY, DELETE_TS, DELETED_BY, PERMISSION_TYPE, TARGET, VALUE_, ROLE_ID)
values (newid(), now(), 'system', 1, now(), null, null, null, 20, 'thesissummer$OrderIns:create', 0, (select ID from SEC_ROLE where NAME = 'SimpleUser'))^
insert into SEC_PERMISSION (ID, CREATE_TS, CREATED_BY, VERSION, UPDATE_TS, UPDATED_BY, DELETE_TS, DELETED_BY, PERMISSION_TYPE, TARGET, VALUE_, ROLE_ID)
values (newid(), now(), 'system', 1, now(), null, null, null, 20, 'thesissummer$OrderIns:delete', 0, (select ID from SEC_ROLE where NAME = 'SimpleUser'))^
insert into SEC_PERMISSION (ID, CREATE_TS, CREATED_BY, VERSION, UPDATE_TS, UPDATED_BY, DELETE_TS, DELETED_BY, PERMISSION_TYPE, TARGET, VALUE_, ROLE_ID)
values (newid(), now(), 'system', 1, now(), null, null, null, 20, 'thesissummer$OrderIns:create', 1, (select ID from SEC_ROLE where NAME = 'Administrators'))^
insert into SEC_PERMISSION (ID, CREATE_TS, CREATED_BY, VERSION, UPDATE_TS, UPDATED_BY, DELETE_TS, DELETED_BY, PERMISSION_TYPE, TARGET, VALUE_, ROLE_ID)
values (newid(), now(), 'system', 1, now(), null, null, null, 20, 'thesissummer$OrderIns:update', 1, (select ID from SEC_ROLE where NAME = 'Administrators'))^
insert into SEC_PERMISSION (ID, CREATE_TS, CREATED_BY, VERSION, UPDATE_TS, UPDATED_BY, DELETE_TS, DELETED_BY, PERMISSION_TYPE, TARGET, VALUE_, ROLE_ID)
values (newid(), now(), 'system', 1, now(), null, null, null, 20, 'thesissummer$OrderIns:delete', 1, (select ID from SEC_ROLE where NAME = 'Administrators'))^
insert into SEC_PERMISSION (ID, CREATE_TS, CREATED_BY, VERSION, UPDATE_TS, UPDATED_BY, DELETE_TS, DELETED_BY, PERMISSION_TYPE, TARGET, VALUE_, ROLE_ID)
values (newid(), now(), 'system', 1, now(), null, null, null, 20, 'thesissummer$OrderIns:create', 1, (select ID from SEC_ROLE where NAME = 'doc_initiator'))^
insert into SEC_PERMISSION (ID, CREATE_TS, CREATED_BY, VERSION, UPDATE_TS, UPDATED_BY, DELETE_TS, DELETED_BY, PERMISSION_TYPE, TARGET, VALUE_, ROLE_ID)
values (newid(), now(), 'system', 1, now(), null, null, null, 20, 'thesissummer$OrderIns:update', 1, (select ID from SEC_ROLE where NAME = 'doc_initiator'))^
insert into SEC_PERMISSION (ID, CREATE_TS, CREATED_BY, VERSION, UPDATE_TS, UPDATED_BY, DELETE_TS, DELETED_BY, PERMISSION_TYPE, TARGET, VALUE_, ROLE_ID)
values (newid(), now(), 'system', 1, now(), null, null, null, 20, 'thesissummer$OrderIns:delete', 1, (select ID from SEC_ROLE where NAME = 'doc_initiator'))^
-- end insert secPermissions for OrderIns
-- begin update discriminator for ExtDepartment
update DF_CORRESPONDENT set TYPE = 'A' where TYPE = 'D' ^
-- end update discriminator for ExtDepartment
-- begin update discriminator for ExtEmployee
update DF_CORRESPONDENT set TYPE = 'X' where TYPE = 'E' ^
-- end update discriminator for ExtEmployee
-- begin insert default numerator for OrderTranferMoney
CREATE OR REPLACE FUNCTION baseInsert()
RETURNS integer
AS $$
DECLARE
    cnt integer = 0;
BEGIN
cnt = (select count(id) from DF_NUMERATOR where CODE = 'OrderTranferMoneyNumerator' and delete_ts is null);
if(cnt = 0) then
    INSERT INTO DF_NUMERATOR (ID, CREATE_TS, CREATED_BY, VERSION, CODE, NUMERATOR_FORMAT, SCRIPT_ENABLED,
                              PERIODICITY, NUMBER_INITIAL_VALUE, LOC_NAME)
    VALUES ('5bf56187-abfb-49e7-8540-4e878b7876ad', now(), 'system', 1, 'OrderTranferMoneyNumerator', '[number]', FALSE, 'Y', 1,
            '{"captionWithLanguageList":[{"language":"ru","caption":"На перевод денежных средств"},{"language":"en","caption":"OrderTranferMoney"}]}'
    );
end if;

return 0;
END;
$$
LANGUAGE plpgsql;
^

select baseInsert()^
drop function if exists baseInsert()^
-- end insert default numerator for OrderTranferMoney
-- begin insert cardType for OrderTranferMoney
insert into TS_CARD_TYPE (ID, CREATE_TS, CREATED_BY, NAME, DISCRIMINATOR, FIELDS_XML)
       values ('af43fccc-f8d0-42c5-a029-a3b95c6ac8d8', now(), 'system', 'thesissummer$OrderTranferMoney', '2200', '<?xml version="1.0" encoding="UTF-8"?>
<fields>
  <field name="date" inDocKind="true" required="false" visible="true" signed="false" />
  <field name="docCategory" inDocKind="true" required="false" visible="true" signed="false" />
  <field name="owner" inDocKind="true" required="false" visible="true" signed="false" />
  <field name="department" inDocKind="true" required="false" visible="true" signed="false" />
  <field name="comment" inDocKind="true" required="false" visible="true" signed="false" />
  <field name="finishDatePlan" inDocKind="true" required="false" visible="true" signed="false" />
  <field name="resolution" inDocKind="true" required="false" visible="true" signed="false" />
  <field name="number" inDocKind="true" required="false" visible="true" signed="false" />
  <field name="organization" inDocKind="true" required="false" visible="true" signed="false" />
  <field name="parentCard" inDocKind="true" required="false" visible="true" signed="false" />
  <field name="theme" inDocKind="true" required="false" visible="true" signed="false" />
  <field name="summaDlyAPerech" inDocKind="true" required="false" visible="true" signed="false" />
  <field name="dataPerevoda" inDocKind="true" required="false" visible="true" signed="false" />
  <field name="poluchatel" inDocKind="true" required="false" visible="true" signed="false" />
  <field name="raschetSchet" inDocKind="true" required="false" visible="true" signed="false" />
</fields>
')^
-- end insert cardType for OrderTranferMoney
-- begin update procCardType for OrderTranferMoney
update wf_proc set card_types = regexp_replace(card_types, ',thesissummer\\$OrderTranferMoney', '') where code in ('Endorsement', 'Resolution', 'Acquaintance', 'Registration')^
update wf_proc set updated_by='system', card_types = card_types || 'thesissummer$OrderTranferMoney,' where code in ('Endorsement', 'Resolution', 'Acquaintance', 'Registration')^
-- end update procCardType for OrderTranferMoney
-- begin insert docKind for OrderTranferMoney
CREATE OR REPLACE FUNCTION baseInsert()
RETURNS integer
AS $$
DECLARE
    cnt integer = 0;
BEGIN
    cnt = (select count(CATEGORY_ID) from DF_DOC_KIND where CATEGORY_ID = 'fc0641f7-bf21-4a7a-9155-433a944e4e84');
    if (cnt = 0) then
        insert into SYS_CATEGORY (ID, NAME, ENTITY_TYPE, IS_DEFAULT, CREATE_TS, CREATED_BY, VERSION, DISCRIMINATOR)
        values ('fc0641f7-bf21-4a7a-9155-433a944e4e84', 'На перевод денежных средств', 'thesissummer$OrderTranferMoney', false, now(), 'system', 1, 1);

        insert into DF_DOC_KIND (CATEGORY_ID, CREATE_TS, CREATED_BY, VERSION, DOC_TYPE_ID, NUMERATOR_ID,
                                 NUMERATOR_TYPE, CATEGORY_ATTRS_PLACE, TAB_NAME, PORTAL_PUBLISH_ALLOWED, DISABLE_ADD_PROCESS_ACTORS, CREATE_ONLY_BY_TEMPLATE)
        values ('fc0641f7-bf21-4a7a-9155-433a944e4e84', now(), 'system', 1, 'af43fccc-f8d0-42c5-a029-a3b95c6ac8d8', '5bf56187-abfb-49e7-8540-4e878b7876ad',
                1, 1, 'Доп. поля', false, false, false);
end if;
return 0;
END;
$$
LANGUAGE plpgsql;
^
select baseInsert();^
drop function if exists baseInsert();^
-- end insert docKind for OrderTranferMoney
-- begin insert secPermissions for OrderTranferMoney
insert into SEC_PERMISSION (ID, CREATE_TS, CREATED_BY, VERSION, UPDATE_TS, UPDATED_BY, DELETE_TS, DELETED_BY, PERMISSION_TYPE, TARGET, VALUE_, ROLE_ID)
values (newid(), now(), 'system', 1, now(), null, null, null, 20, 'thesissummer$OrderTranferMoney:create', 0, (select ID from SEC_ROLE where NAME = 'SimpleUser'))^
insert into SEC_PERMISSION (ID, CREATE_TS, CREATED_BY, VERSION, UPDATE_TS, UPDATED_BY, DELETE_TS, DELETED_BY, PERMISSION_TYPE, TARGET, VALUE_, ROLE_ID)
values (newid(), now(), 'system', 1, now(), null, null, null, 20, 'thesissummer$OrderTranferMoney:delete', 0, (select ID from SEC_ROLE where NAME = 'SimpleUser'))^
insert into SEC_PERMISSION (ID, CREATE_TS, CREATED_BY, VERSION, UPDATE_TS, UPDATED_BY, DELETE_TS, DELETED_BY, PERMISSION_TYPE, TARGET, VALUE_, ROLE_ID)
values (newid(), now(), 'system', 1, now(), null, null, null, 20, 'thesissummer$OrderTranferMoney:create', 1, (select ID from SEC_ROLE where NAME = 'Administrators'))^
insert into SEC_PERMISSION (ID, CREATE_TS, CREATED_BY, VERSION, UPDATE_TS, UPDATED_BY, DELETE_TS, DELETED_BY, PERMISSION_TYPE, TARGET, VALUE_, ROLE_ID)
values (newid(), now(), 'system', 1, now(), null, null, null, 20, 'thesissummer$OrderTranferMoney:update', 1, (select ID from SEC_ROLE where NAME = 'Administrators'))^
insert into SEC_PERMISSION (ID, CREATE_TS, CREATED_BY, VERSION, UPDATE_TS, UPDATED_BY, DELETE_TS, DELETED_BY, PERMISSION_TYPE, TARGET, VALUE_, ROLE_ID)
values (newid(), now(), 'system', 1, now(), null, null, null, 20, 'thesissummer$OrderTranferMoney:delete', 1, (select ID from SEC_ROLE where NAME = 'Administrators'))^
insert into SEC_PERMISSION (ID, CREATE_TS, CREATED_BY, VERSION, UPDATE_TS, UPDATED_BY, DELETE_TS, DELETED_BY, PERMISSION_TYPE, TARGET, VALUE_, ROLE_ID)
values (newid(), now(), 'system', 1, now(), null, null, null, 20, 'thesissummer$OrderTranferMoney:create', 1, (select ID from SEC_ROLE where NAME = 'doc_initiator'))^
insert into SEC_PERMISSION (ID, CREATE_TS, CREATED_BY, VERSION, UPDATE_TS, UPDATED_BY, DELETE_TS, DELETED_BY, PERMISSION_TYPE, TARGET, VALUE_, ROLE_ID)
values (newid(), now(), 'system', 1, now(), null, null, null, 20, 'thesissummer$OrderTranferMoney:update', 1, (select ID from SEC_ROLE where NAME = 'doc_initiator'))^
insert into SEC_PERMISSION (ID, CREATE_TS, CREATED_BY, VERSION, UPDATE_TS, UPDATED_BY, DELETE_TS, DELETED_BY, PERMISSION_TYPE, TARGET, VALUE_, ROLE_ID)
values (newid(), now(), 'system', 1, now(), null, null, null, 20, 'thesissummer$OrderTranferMoney:delete', 1, (select ID from SEC_ROLE where NAME = 'doc_initiator'))^
-- end insert secPermissions for OrderTranferMoney
-- begin insert default numerator for OrderLoanSI
CREATE OR REPLACE FUNCTION baseInsert()
RETURNS integer
AS $$
DECLARE
    cnt integer = 0;
BEGIN
cnt = (select count(id) from DF_NUMERATOR where CODE = 'OrderLoanSINumerator' and delete_ts is null);
if(cnt = 0) then
    INSERT INTO DF_NUMERATOR (ID, CREATE_TS, CREATED_BY, VERSION, CODE, NUMERATOR_FORMAT, SCRIPT_ENABLED,
                              PERIODICITY, NUMBER_INITIAL_VALUE, LOC_NAME)
    VALUES ('c3f6366b-95b1-446e-b955-9f3cbe5bc366', now(), 'system', 1, 'OrderLoanSINumerator', '[number]', FALSE, 'Y', 1,
            '{"captionWithLanguageList":[{"language":"ru","caption":"На выдачу займа (СИ)"},{"language":"en","caption":"OrderLoanSI"}]}'
    );
end if;

return 0;
END;
$$
LANGUAGE plpgsql;
^

select baseInsert()^
drop function if exists baseInsert()^
-- end insert default numerator for OrderLoanSI
-- begin insert cardType for OrderLoanSI
insert into TS_CARD_TYPE (ID, CREATE_TS, CREATED_BY, NAME, DISCRIMINATOR, FIELDS_XML)
       values ('cfdd51f1-0756-4fe4-bf02-5ae90dfe06a4', now(), 'system', 'thesissummer$OrderLoanSI', '2300', '<?xml version="1.0" encoding="UTF-8"?>
<fields>
  <field name="date" inDocKind="true" required="false" visible="true" signed="false" />
  <field name="docCategory" inDocKind="true" required="false" visible="true" signed="false" />
  <field name="owner" inDocKind="true" required="false" visible="true" signed="false" />
  <field name="department" inDocKind="true" required="false" visible="true" signed="false" />
  <field name="comment" inDocKind="true" required="false" visible="true" signed="false" />
  <field name="finishDatePlan" inDocKind="true" required="false" visible="true" signed="false" />
  <field name="resolution" inDocKind="true" required="false" visible="true" signed="false" />
  <field name="number" inDocKind="true" required="false" visible="true" signed="false" />
  <field name="organization" inDocKind="true" required="false" visible="true" signed="false" />
  <field name="parentCard" inDocKind="true" required="false" visible="true" signed="false" />
  <field name="theme" inDocKind="true" required="false" visible="true" signed="false" />
  <field name="nomerRasp" inDocKind="true" required="false" visible="true" signed="false" />
  <field name="dataRasp" inDocKind="true" required="false" visible="true" signed="false" />
  <field name="podrazdelenie" inDocKind="true" required="false" visible="true" signed="false" />
  <field name="osnovanie" inDocKind="true" required="false" visible="true" signed="false" />
  <field name="kontragent" inDocKind="true" required="false" visible="true" signed="false" />
  <field name="iin" inDocKind="true" required="false" visible="true" signed="false" />
  <field name="konechZaemchik" inDocKind="true" required="false" visible="true" signed="false" />
  <field name="dogovor" inDocKind="true" required="false" visible="true" signed="false" />
  <field name="summa" inDocKind="true" required="false" visible="true" signed="false" />
  <field name="stavkaVoz" inDocKind="true" required="false" visible="true" signed="false" />
  <field name="srokKredita" inDocKind="true" required="false" visible="true" signed="false" />
  <field name="bank" inDocKind="true" required="false" visible="true" signed="false" />
  <field name="bik" inDocKind="true" required="false" visible="true" signed="false" />
  <field name="iik" inDocKind="true" required="false" visible="true" signed="false" />
  <field name="bin" inDocKind="true" required="false" visible="true" signed="false" />
  <field name="kbe" inDocKind="true" required="false" visible="true" signed="false" />
  <field name="urAdres" inDocKind="true" required="false" visible="true" signed="false" />
</fields>
')^
-- end insert cardType for OrderLoanSI
-- begin update procCardType for OrderLoanSI
update wf_proc set card_types = regexp_replace(card_types, ',thesissummer\\$OrderLoanSI', '') where code in ('Endorsement', 'Resolution', 'Acquaintance', 'Registration')^
update wf_proc set updated_by='system', card_types = card_types || 'thesissummer$OrderLoanSI,' where code in ('Endorsement', 'Resolution', 'Acquaintance', 'Registration')^
-- end update procCardType for OrderLoanSI
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
-- begin insert secPermissions for OrderLoanSI
insert into SEC_PERMISSION (ID, CREATE_TS, CREATED_BY, VERSION, UPDATE_TS, UPDATED_BY, DELETE_TS, DELETED_BY, PERMISSION_TYPE, TARGET, VALUE_, ROLE_ID)
values (newid(), now(), 'system', 1, now(), null, null, null, 20, 'thesissummer$OrderLoanSI:create', 0, (select ID from SEC_ROLE where NAME = 'SimpleUser'))^
insert into SEC_PERMISSION (ID, CREATE_TS, CREATED_BY, VERSION, UPDATE_TS, UPDATED_BY, DELETE_TS, DELETED_BY, PERMISSION_TYPE, TARGET, VALUE_, ROLE_ID)
values (newid(), now(), 'system', 1, now(), null, null, null, 20, 'thesissummer$OrderLoanSI:delete', 0, (select ID from SEC_ROLE where NAME = 'SimpleUser'))^
insert into SEC_PERMISSION (ID, CREATE_TS, CREATED_BY, VERSION, UPDATE_TS, UPDATED_BY, DELETE_TS, DELETED_BY, PERMISSION_TYPE, TARGET, VALUE_, ROLE_ID)
values (newid(), now(), 'system', 1, now(), null, null, null, 20, 'thesissummer$OrderLoanSI:create', 1, (select ID from SEC_ROLE where NAME = 'Administrators'))^
insert into SEC_PERMISSION (ID, CREATE_TS, CREATED_BY, VERSION, UPDATE_TS, UPDATED_BY, DELETE_TS, DELETED_BY, PERMISSION_TYPE, TARGET, VALUE_, ROLE_ID)
values (newid(), now(), 'system', 1, now(), null, null, null, 20, 'thesissummer$OrderLoanSI:update', 1, (select ID from SEC_ROLE where NAME = 'Administrators'))^
insert into SEC_PERMISSION (ID, CREATE_TS, CREATED_BY, VERSION, UPDATE_TS, UPDATED_BY, DELETE_TS, DELETED_BY, PERMISSION_TYPE, TARGET, VALUE_, ROLE_ID)
values (newid(), now(), 'system', 1, now(), null, null, null, 20, 'thesissummer$OrderLoanSI:delete', 1, (select ID from SEC_ROLE where NAME = 'Administrators'))^
insert into SEC_PERMISSION (ID, CREATE_TS, CREATED_BY, VERSION, UPDATE_TS, UPDATED_BY, DELETE_TS, DELETED_BY, PERMISSION_TYPE, TARGET, VALUE_, ROLE_ID)
values (newid(), now(), 'system', 1, now(), null, null, null, 20, 'thesissummer$OrderLoanSI:create', 1, (select ID from SEC_ROLE where NAME = 'doc_initiator'))^
insert into SEC_PERMISSION (ID, CREATE_TS, CREATED_BY, VERSION, UPDATE_TS, UPDATED_BY, DELETE_TS, DELETED_BY, PERMISSION_TYPE, TARGET, VALUE_, ROLE_ID)
values (newid(), now(), 'system', 1, now(), null, null, null, 20, 'thesissummer$OrderLoanSI:update', 1, (select ID from SEC_ROLE where NAME = 'doc_initiator'))^
insert into SEC_PERMISSION (ID, CREATE_TS, CREATED_BY, VERSION, UPDATE_TS, UPDATED_BY, DELETE_TS, DELETED_BY, PERMISSION_TYPE, TARGET, VALUE_, ROLE_ID)
values (newid(), now(), 'system', 1, now(), null, null, null, 20, 'thesissummer$OrderLoanSI:delete', 1, (select ID from SEC_ROLE where NAME = 'doc_initiator'))^
-- end insert secPermissions for OrderLoanSI
-- begin insert default numerator for OrderVozvratvGu
CREATE OR REPLACE FUNCTION baseInsert()
RETURNS integer
AS $$
DECLARE
    cnt integer = 0;
BEGIN
cnt = (select count(id) from DF_NUMERATOR where CODE = 'OrderVozvratvGuNumerator' and delete_ts is null);
if(cnt = 0) then
    INSERT INTO DF_NUMERATOR (ID, CREATE_TS, CREATED_BY, VERSION, CODE, NUMERATOR_FORMAT, SCRIPT_ENABLED,
                              PERIODICITY, NUMBER_INITIAL_VALUE, LOC_NAME)
    VALUES ('99d7ad01-0b16-414c-b495-9ed625b8a2ea', now(), 'system', 1, 'OrderVozvratvGuNumerator', '[number]', FALSE, 'Y', 1,
            '{"captionWithLanguageList":[{"language":"ru","caption":"Распоряжения на возврат в ГУ (СИ)"},{"language":"en","caption":"OrderVozvratvGu"}]}'
    );
end if;

return 0;
END;
$$
LANGUAGE plpgsql;
^

select baseInsert()^
drop function if exists baseInsert()^
-- end insert default numerator for OrderVozvratvGu
-- begin insert cardType for OrderVozvratvGu
insert into TS_CARD_TYPE (ID, CREATE_TS, CREATED_BY, NAME, DISCRIMINATOR, FIELDS_XML)
       values ('67ffcb48-932e-4abe-bccc-502566cc5b11', now(), 'system', 'thesissummer$OrderVozvratvGu', '2400', '<?xml version="1.0" encoding="UTF-8"?>
<fields>
  <field name="date" inDocKind="true" required="false" visible="true" signed="false" />
  <field name="docCategory" inDocKind="true" required="false" visible="true" signed="false" />
  <field name="owner" inDocKind="true" required="false" visible="true" signed="false" />
  <field name="department" inDocKind="true" required="false" visible="true" signed="false" />
  <field name="comment" inDocKind="true" required="false" visible="true" signed="false" />
  <field name="finishDatePlan" inDocKind="true" required="false" visible="true" signed="false" />
  <field name="resolution" inDocKind="true" required="false" visible="true" signed="false" />
  <field name="number" inDocKind="true" required="false" visible="true" signed="false" />
  <field name="organization" inDocKind="true" required="false" visible="true" signed="false" />
  <field name="parentCard" inDocKind="true" required="false" visible="true" signed="false" />
  <field name="theme" inDocKind="true" required="false" visible="true" signed="false" />
  <field name="dogovorPoruch" inDocKind="true" required="false" visible="true" signed="false" />
  <field name="onsovnoiDolg" inDocKind="true" required="false" visible="true" signed="false" />
  <field name="voznagrazhdenie" inDocKind="true" required="false" visible="true" signed="false" />
  <field name="penya" inDocKind="true" required="false" visible="true" signed="false" />
  <field name="ugdRayon" inDocKind="true" required="false" visible="true" signed="false" />
  <field name="dgdOblast" inDocKind="true" required="false" visible="true" signed="false" />
  <field name="bin" inDocKind="true" required="false" visible="true" signed="false" />
  <field name="iik" inDocKind="true" required="false" visible="true" signed="false" />
  <field name="bik" inDocKind="true" required="false" visible="true" signed="false" />
  <field name="bik2" inDocKind="true" required="false" visible="true" signed="false" />
  <field name="knp" inDocKind="true" required="false" visible="true" signed="false" />
  <field name="kod" inDocKind="true" required="false" visible="true" signed="false" />
</fields>
')^
-- end insert cardType for OrderVozvratvGu
-- begin update procCardType for OrderVozvratvGu
update wf_proc set card_types = regexp_replace(card_types, ',thesissummer\\$OrderVozvratvGu', '') where code in ('Endorsement', 'Resolution', 'Acquaintance', 'Registration')^
update wf_proc set updated_by='system', card_types = card_types || 'thesissummer$OrderVozvratvGu,' where code in ('Endorsement', 'Resolution', 'Acquaintance', 'Registration')^
-- end update procCardType for OrderVozvratvGu
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
-- begin insert secPermissions for OrderVozvratvGu
insert into SEC_PERMISSION (ID, CREATE_TS, CREATED_BY, VERSION, UPDATE_TS, UPDATED_BY, DELETE_TS, DELETED_BY, PERMISSION_TYPE, TARGET, VALUE_, ROLE_ID)
values (newid(), now(), 'system', 1, now(), null, null, null, 20, 'thesissummer$OrderVozvratvGu:create', 0, (select ID from SEC_ROLE where NAME = 'SimpleUser'))^
insert into SEC_PERMISSION (ID, CREATE_TS, CREATED_BY, VERSION, UPDATE_TS, UPDATED_BY, DELETE_TS, DELETED_BY, PERMISSION_TYPE, TARGET, VALUE_, ROLE_ID)
values (newid(), now(), 'system', 1, now(), null, null, null, 20, 'thesissummer$OrderVozvratvGu:delete', 0, (select ID from SEC_ROLE where NAME = 'SimpleUser'))^
insert into SEC_PERMISSION (ID, CREATE_TS, CREATED_BY, VERSION, UPDATE_TS, UPDATED_BY, DELETE_TS, DELETED_BY, PERMISSION_TYPE, TARGET, VALUE_, ROLE_ID)
values (newid(), now(), 'system', 1, now(), null, null, null, 20, 'thesissummer$OrderVozvratvGu:create', 1, (select ID from SEC_ROLE where NAME = 'Administrators'))^
insert into SEC_PERMISSION (ID, CREATE_TS, CREATED_BY, VERSION, UPDATE_TS, UPDATED_BY, DELETE_TS, DELETED_BY, PERMISSION_TYPE, TARGET, VALUE_, ROLE_ID)
values (newid(), now(), 'system', 1, now(), null, null, null, 20, 'thesissummer$OrderVozvratvGu:update', 1, (select ID from SEC_ROLE where NAME = 'Administrators'))^
insert into SEC_PERMISSION (ID, CREATE_TS, CREATED_BY, VERSION, UPDATE_TS, UPDATED_BY, DELETE_TS, DELETED_BY, PERMISSION_TYPE, TARGET, VALUE_, ROLE_ID)
values (newid(), now(), 'system', 1, now(), null, null, null, 20, 'thesissummer$OrderVozvratvGu:delete', 1, (select ID from SEC_ROLE where NAME = 'Administrators'))^
insert into SEC_PERMISSION (ID, CREATE_TS, CREATED_BY, VERSION, UPDATE_TS, UPDATED_BY, DELETE_TS, DELETED_BY, PERMISSION_TYPE, TARGET, VALUE_, ROLE_ID)
values (newid(), now(), 'system', 1, now(), null, null, null, 20, 'thesissummer$OrderVozvratvGu:create', 1, (select ID from SEC_ROLE where NAME = 'doc_initiator'))^
insert into SEC_PERMISSION (ID, CREATE_TS, CREATED_BY, VERSION, UPDATE_TS, UPDATED_BY, DELETE_TS, DELETED_BY, PERMISSION_TYPE, TARGET, VALUE_, ROLE_ID)
values (newid(), now(), 'system', 1, now(), null, null, null, 20, 'thesissummer$OrderVozvratvGu:update', 1, (select ID from SEC_ROLE where NAME = 'doc_initiator'))^
insert into SEC_PERMISSION (ID, CREATE_TS, CREATED_BY, VERSION, UPDATE_TS, UPDATED_BY, DELETE_TS, DELETED_BY, PERMISSION_TYPE, TARGET, VALUE_, ROLE_ID)
values (newid(), now(), 'system', 1, now(), null, null, null, 20, 'thesissummer$OrderVozvratvGu:delete', 1, (select ID from SEC_ROLE where NAME = 'doc_initiator'))^
-- end insert secPermissions for OrderVozvratvGu
-- begin insert default numerator for OrderReturnNeispSub
CREATE OR REPLACE FUNCTION baseInsert()
RETURNS integer
AS $$
DECLARE
    cnt integer = 0;
BEGIN
cnt = (select count(id) from DF_NUMERATOR where CODE = 'OrderReturnNeispSubNumerator' and delete_ts is null);
if(cnt = 0) then
    INSERT INTO DF_NUMERATOR (ID, CREATE_TS, CREATED_BY, VERSION, CODE, NUMERATOR_FORMAT, SCRIPT_ENABLED,
                              PERIODICITY, NUMBER_INITIAL_VALUE, LOC_NAME)
    VALUES ('36e7173f-513e-4a08-bcba-1da0a69da074', now(), 'system', 1, 'OrderReturnNeispSubNumerator', '[number]', FALSE, 'Y', 1,
            '{"captionWithLanguageList":[{"language":"ru","caption":"Распоряжения на возврат неиспользованных субсидий"},{"language":"en","caption":"OrderReturnNeispSub"}]}'
    );
end if;

return 0;
END;
$$
LANGUAGE plpgsql;
^

select baseInsert()^
drop function if exists baseInsert()^
-- end insert default numerator for OrderReturnNeispSub
-- begin insert cardType for OrderReturnNeispSub
insert into TS_CARD_TYPE (ID, CREATE_TS, CREATED_BY, NAME, DISCRIMINATOR, FIELDS_XML)
       values ('a9b20c4d-b4fe-4b53-ae98-c20cd9601a1f', now(), 'system', 'thesissummer$OrderReturnNeispSub', '2500', '<?xml version="1.0" encoding="UTF-8"?>
<fields>
  <field name="date" inDocKind="true" required="false" visible="true" signed="false" />
  <field name="docCategory" inDocKind="true" required="false" visible="true" signed="false" />
  <field name="owner" inDocKind="true" required="false" visible="true" signed="false" />
  <field name="department" inDocKind="true" required="false" visible="true" signed="false" />
  <field name="comment" inDocKind="true" required="false" visible="true" signed="false" />
  <field name="finishDatePlan" inDocKind="true" required="false" visible="true" signed="false" />
  <field name="resolution" inDocKind="true" required="false" visible="true" signed="false" />
  <field name="number" inDocKind="true" required="false" visible="true" signed="false" />
  <field name="organization" inDocKind="true" required="false" visible="true" signed="false" />
  <field name="parentCard" inDocKind="true" required="false" visible="true" signed="false" />
  <field name="theme" inDocKind="true" required="false" visible="true" signed="false" />
  <field name="dataOrder" inDocKind="true" required="false" visible="true" signed="false" />
  <field name="podrazdelenie" inDocKind="true" required="false" visible="true" signed="false" />
  <field name="schetKorporacii" inDocKind="true" required="false" visible="true" signed="false" />
  <field name="bankOrganizacii" inDocKind="true" required="false" visible="true" signed="false" />
  <field name="bikBanka" inDocKind="true" required="false" visible="true" signed="false" />
  <field name="oblastPodrazdelenia" inDocKind="true" required="false" visible="true" signed="false" />
  <field name="kvartal1" inDocKind="true" required="false" visible="true" signed="false" />
  <field name="naznacheniyaPlatezha" inDocKind="true" required="false" visible="true" signed="false" />
  <field name="summaPostupleniya" inDocKind="true" required="false" visible="true" signed="false" />
  <field name="dataPostupleniya" inDocKind="true" required="false" visible="true" signed="false" />
  <field name="dogovorSub" inDocKind="true" required="false" visible="true" signed="false" />
  <field name="rabOrgan" inDocKind="true" required="false" visible="true" signed="false" />
  <field name="binUsh" inDocKind="true" required="false" visible="true" signed="false" />
  <field name="kvartal2" inDocKind="true" required="false" visible="true" signed="false" />
  <field name="summaSub" inDocKind="true" required="false" visible="true" signed="false" />
  <field name="schetPoluchatelya" inDocKind="true" required="false" visible="true" signed="false" />
  <field name="bikPoluch" inDocKind="true" required="false" visible="true" signed="false" />
  <field name="kbk" inDocKind="true" required="false" visible="true" signed="false" />
  <field name="oblastPodrazdel2" inDocKind="true" required="false" visible="true" signed="false" />
</fields>
')^
-- end insert cardType for OrderReturnNeispSub
-- begin update procCardType for OrderReturnNeispSub
update wf_proc set card_types = regexp_replace(card_types, ',thesissummer\\$OrderReturnNeispSub', '') where code in ('Endorsement', 'Resolution', 'Acquaintance', 'Registration')^
update wf_proc set updated_by='system', card_types = card_types || 'thesissummer$OrderReturnNeispSub,' where code in ('Endorsement', 'Resolution', 'Acquaintance', 'Registration')^
-- end update procCardType for OrderReturnNeispSub
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
-- begin insert secPermissions for OrderReturnNeispSub
insert into SEC_PERMISSION (ID, CREATE_TS, CREATED_BY, VERSION, UPDATE_TS, UPDATED_BY, DELETE_TS, DELETED_BY, PERMISSION_TYPE, TARGET, VALUE_, ROLE_ID)
values (newid(), now(), 'system', 1, now(), null, null, null, 20, 'thesissummer$OrderReturnNeispSub:create', 0, (select ID from SEC_ROLE where NAME = 'SimpleUser'))^
insert into SEC_PERMISSION (ID, CREATE_TS, CREATED_BY, VERSION, UPDATE_TS, UPDATED_BY, DELETE_TS, DELETED_BY, PERMISSION_TYPE, TARGET, VALUE_, ROLE_ID)
values (newid(), now(), 'system', 1, now(), null, null, null, 20, 'thesissummer$OrderReturnNeispSub:delete', 0, (select ID from SEC_ROLE where NAME = 'SimpleUser'))^
insert into SEC_PERMISSION (ID, CREATE_TS, CREATED_BY, VERSION, UPDATE_TS, UPDATED_BY, DELETE_TS, DELETED_BY, PERMISSION_TYPE, TARGET, VALUE_, ROLE_ID)
values (newid(), now(), 'system', 1, now(), null, null, null, 20, 'thesissummer$OrderReturnNeispSub:create', 1, (select ID from SEC_ROLE where NAME = 'Administrators'))^
insert into SEC_PERMISSION (ID, CREATE_TS, CREATED_BY, VERSION, UPDATE_TS, UPDATED_BY, DELETE_TS, DELETED_BY, PERMISSION_TYPE, TARGET, VALUE_, ROLE_ID)
values (newid(), now(), 'system', 1, now(), null, null, null, 20, 'thesissummer$OrderReturnNeispSub:update', 1, (select ID from SEC_ROLE where NAME = 'Administrators'))^
insert into SEC_PERMISSION (ID, CREATE_TS, CREATED_BY, VERSION, UPDATE_TS, UPDATED_BY, DELETE_TS, DELETED_BY, PERMISSION_TYPE, TARGET, VALUE_, ROLE_ID)
values (newid(), now(), 'system', 1, now(), null, null, null, 20, 'thesissummer$OrderReturnNeispSub:delete', 1, (select ID from SEC_ROLE where NAME = 'Administrators'))^
insert into SEC_PERMISSION (ID, CREATE_TS, CREATED_BY, VERSION, UPDATE_TS, UPDATED_BY, DELETE_TS, DELETED_BY, PERMISSION_TYPE, TARGET, VALUE_, ROLE_ID)
values (newid(), now(), 'system', 1, now(), null, null, null, 20, 'thesissummer$OrderReturnNeispSub:create', 1, (select ID from SEC_ROLE where NAME = 'doc_initiator'))^
insert into SEC_PERMISSION (ID, CREATE_TS, CREATED_BY, VERSION, UPDATE_TS, UPDATED_BY, DELETE_TS, DELETED_BY, PERMISSION_TYPE, TARGET, VALUE_, ROLE_ID)
values (newid(), now(), 'system', 1, now(), null, null, null, 20, 'thesissummer$OrderReturnNeispSub:update', 1, (select ID from SEC_ROLE where NAME = 'doc_initiator'))^
insert into SEC_PERMISSION (ID, CREATE_TS, CREATED_BY, VERSION, UPDATE_TS, UPDATED_BY, DELETE_TS, DELETED_BY, PERMISSION_TYPE, TARGET, VALUE_, ROLE_ID)
values (newid(), now(), 'system', 1, now(), null, null, null, 20, 'thesissummer$OrderReturnNeispSub:delete', 1, (select ID from SEC_ROLE where NAME = 'doc_initiator'))^
-- end insert secPermissions for OrderReturnNeispSub
-- begin insert default numerator for OrderRembursement
CREATE OR REPLACE FUNCTION baseInsert()
RETURNS integer
AS $$
DECLARE
    cnt integer = 0;
BEGIN
cnt = (select count(id) from DF_NUMERATOR where CODE = 'OrderRembursementNumerator' and delete_ts is null);
if(cnt = 0) then
    INSERT INTO DF_NUMERATOR (ID, CREATE_TS, CREATED_BY, VERSION, CODE, NUMERATOR_FORMAT, SCRIPT_ENABLED,
                              PERIODICITY, NUMBER_INITIAL_VALUE, LOC_NAME)
    VALUES ('d409e5fb-6bd3-4899-aacd-a3ef2444e466', now(), 'system', 1, 'OrderRembursementNumerator', '[number]', FALSE, 'Y', 1,
            '{"captionWithLanguageList":[{"language":"ru","caption":"На возмещение субсидий"},{"language":"en","caption":"OrderRembursement"}]}'
    );
end if;

return 0;
END;
$$
LANGUAGE plpgsql;
^

select baseInsert()^
drop function if exists baseInsert()^
-- end insert default numerator for OrderRembursement
-- begin insert cardType for OrderRembursement
insert into TS_CARD_TYPE (ID, CREATE_TS, CREATED_BY, NAME, DISCRIMINATOR, FIELDS_XML)
       values ('0ae994e0-bb42-4bf1-be44-5c01e98a1fcf', now(), 'system', 'thesissummer$OrderRembursement', '2600', '<?xml version="1.0" encoding="UTF-8"?>
<fields>
  <field name="date" inDocKind="true" required="false" visible="true" signed="false" />
  <field name="docCategory" inDocKind="true" required="false" visible="true" signed="false" />
  <field name="owner" inDocKind="true" required="false" visible="true" signed="false" />
  <field name="department" inDocKind="true" required="false" visible="true" signed="false" />
  <field name="comment" inDocKind="true" required="false" visible="true" signed="false" />
  <field name="finishDatePlan" inDocKind="true" required="false" visible="true" signed="false" />
  <field name="resolution" inDocKind="true" required="false" visible="true" signed="false" />
  <field name="number" inDocKind="true" required="false" visible="true" signed="false" />
  <field name="organization" inDocKind="true" required="false" visible="true" signed="false" />
  <field name="parentCard" inDocKind="true" required="false" visible="true" signed="false" />
  <field name="theme" inDocKind="true" required="false" visible="true" signed="false" />
  <field name="nomerOrder" inDocKind="true" required="false" visible="true" signed="false" />
  <field name="dataOrder" inDocKind="true" required="false" visible="true" signed="false" />
  <field name="podrazdelenie" inDocKind="true" required="false" visible="true" signed="false" />
  <field name="schetKorporacii" inDocKind="true" required="false" visible="true" signed="false" />
  <field name="bankOrganizacii" inDocKind="true" required="false" visible="true" signed="false" />
  <field name="bikBanka" inDocKind="true" required="false" visible="true" signed="false" />
  <field name="oblastPodrazdeleniya" inDocKind="true" required="false" visible="true" signed="false" />
  <field name="kvartal1" inDocKind="true" required="false" visible="true" signed="false" />
  <field name="dogovorSub" inDocKind="true" required="false" visible="true" signed="false" />
  <field name="dataDogovoraSub" inDocKind="true" required="false" visible="true" signed="false" />
  <field name="rabOrgan" inDocKind="true" required="false" visible="true" signed="false" />
  <field name="kvartal2" inDocKind="true" required="false" visible="true" signed="false" />
  <field name="summaSub" inDocKind="true" required="false" visible="true" signed="false" />
</fields>
')^
-- end insert cardType for OrderRembursement
-- begin update procCardType for OrderRembursement
update wf_proc set card_types = regexp_replace(card_types, ',thesissummer\\$OrderRembursement', '') where code in ('Endorsement', 'Resolution', 'Acquaintance', 'Registration')^
update wf_proc set updated_by='system', card_types = card_types || 'thesissummer$OrderRembursement,' where code in ('Endorsement', 'Resolution', 'Acquaintance', 'Registration')^
-- end update procCardType for OrderRembursement
-- begin insert docKind for OrderRembursement
CREATE OR REPLACE FUNCTION baseInsert()
RETURNS integer
AS $$
DECLARE
    cnt integer = 0;
BEGIN
    cnt = (select count(CATEGORY_ID) from DF_DOC_KIND where CATEGORY_ID = '8e4d446c-453e-4d87-be43-e5e53fe33169');
    if (cnt = 0) then
        insert into SYS_CATEGORY (ID, NAME, ENTITY_TYPE, IS_DEFAULT, CREATE_TS, CREATED_BY, VERSION, DISCRIMINATOR)
        values ('8e4d446c-453e-4d87-be43-e5e53fe33169', 'На возмещение субсидий', 'thesissummer$OrderRembursement', false, now(), 'system', 1, 1);

        insert into DF_DOC_KIND (CATEGORY_ID, CREATE_TS, CREATED_BY, VERSION, DOC_TYPE_ID, NUMERATOR_ID,
                                 NUMERATOR_TYPE, CATEGORY_ATTRS_PLACE, TAB_NAME, PORTAL_PUBLISH_ALLOWED, DISABLE_ADD_PROCESS_ACTORS, CREATE_ONLY_BY_TEMPLATE)
        values ('8e4d446c-453e-4d87-be43-e5e53fe33169', now(), 'system', 1, '0ae994e0-bb42-4bf1-be44-5c01e98a1fcf', 'd409e5fb-6bd3-4899-aacd-a3ef2444e466',
                1, 1, 'Доп. поля', false, false, false);
end if;
return 0;
END;
$$
LANGUAGE plpgsql;
^
select baseInsert();^
drop function if exists baseInsert();^
-- end insert docKind for OrderRembursement
-- begin insert secPermissions for OrderRembursement
insert into SEC_PERMISSION (ID, CREATE_TS, CREATED_BY, VERSION, UPDATE_TS, UPDATED_BY, DELETE_TS, DELETED_BY, PERMISSION_TYPE, TARGET, VALUE_, ROLE_ID)
values (newid(), now(), 'system', 1, now(), null, null, null, 20, 'thesissummer$OrderRembursement:create', 0, (select ID from SEC_ROLE where NAME = 'SimpleUser'))^
insert into SEC_PERMISSION (ID, CREATE_TS, CREATED_BY, VERSION, UPDATE_TS, UPDATED_BY, DELETE_TS, DELETED_BY, PERMISSION_TYPE, TARGET, VALUE_, ROLE_ID)
values (newid(), now(), 'system', 1, now(), null, null, null, 20, 'thesissummer$OrderRembursement:delete', 0, (select ID from SEC_ROLE where NAME = 'SimpleUser'))^
insert into SEC_PERMISSION (ID, CREATE_TS, CREATED_BY, VERSION, UPDATE_TS, UPDATED_BY, DELETE_TS, DELETED_BY, PERMISSION_TYPE, TARGET, VALUE_, ROLE_ID)
values (newid(), now(), 'system', 1, now(), null, null, null, 20, 'thesissummer$OrderRembursement:create', 1, (select ID from SEC_ROLE where NAME = 'Administrators'))^
insert into SEC_PERMISSION (ID, CREATE_TS, CREATED_BY, VERSION, UPDATE_TS, UPDATED_BY, DELETE_TS, DELETED_BY, PERMISSION_TYPE, TARGET, VALUE_, ROLE_ID)
values (newid(), now(), 'system', 1, now(), null, null, null, 20, 'thesissummer$OrderRembursement:update', 1, (select ID from SEC_ROLE where NAME = 'Administrators'))^
insert into SEC_PERMISSION (ID, CREATE_TS, CREATED_BY, VERSION, UPDATE_TS, UPDATED_BY, DELETE_TS, DELETED_BY, PERMISSION_TYPE, TARGET, VALUE_, ROLE_ID)
values (newid(), now(), 'system', 1, now(), null, null, null, 20, 'thesissummer$OrderRembursement:delete', 1, (select ID from SEC_ROLE where NAME = 'Administrators'))^
insert into SEC_PERMISSION (ID, CREATE_TS, CREATED_BY, VERSION, UPDATE_TS, UPDATED_BY, DELETE_TS, DELETED_BY, PERMISSION_TYPE, TARGET, VALUE_, ROLE_ID)
values (newid(), now(), 'system', 1, now(), null, null, null, 20, 'thesissummer$OrderRembursement:create', 1, (select ID from SEC_ROLE where NAME = 'doc_initiator'))^
insert into SEC_PERMISSION (ID, CREATE_TS, CREATED_BY, VERSION, UPDATE_TS, UPDATED_BY, DELETE_TS, DELETED_BY, PERMISSION_TYPE, TARGET, VALUE_, ROLE_ID)
values (newid(), now(), 'system', 1, now(), null, null, null, 20, 'thesissummer$OrderRembursement:update', 1, (select ID from SEC_ROLE where NAME = 'doc_initiator'))^
insert into SEC_PERMISSION (ID, CREATE_TS, CREATED_BY, VERSION, UPDATE_TS, UPDATED_BY, DELETE_TS, DELETED_BY, PERMISSION_TYPE, TARGET, VALUE_, ROLE_ID)
values (newid(), now(), 'system', 1, now(), null, null, null, 20, 'thesissummer$OrderRembursement:delete', 1, (select ID from SEC_ROLE where NAME = 'doc_initiator'))^
-- end insert secPermissions for OrderRembursement
-- begin insert default numerator for OrderPosting
CREATE OR REPLACE FUNCTION baseInsert()
RETURNS integer
AS $$
DECLARE
    cnt integer = 0;
BEGIN
cnt = (select count(id) from DF_NUMERATOR where CODE = 'OrderPostingNumerator' and delete_ts is null);
if(cnt = 0) then
    INSERT INTO DF_NUMERATOR (ID, CREATE_TS, CREATED_BY, VERSION, CODE, NUMERATOR_FORMAT, SCRIPT_ENABLED,
                              PERIODICITY, NUMBER_INITIAL_VALUE, LOC_NAME)
    VALUES ('9a7e53be-15fd-41db-a3d3-5e299016e7d9', now(), 'system', 1, 'OrderPostingNumerator', '[number]', FALSE, 'Y', 1,
            '{"captionWithLanguageList":[{"language":"ru","caption":"Распоряжения на оприход обеспечения"},{"language":"en","caption":"OrderPosting"}]}'
    );
end if;

return 0;
END;
$$
LANGUAGE plpgsql;
^

select baseInsert()^
drop function if exists baseInsert()^
-- end insert default numerator for OrderPosting
-- begin insert cardType for OrderPosting
insert into TS_CARD_TYPE (ID, CREATE_TS, CREATED_BY, NAME, DISCRIMINATOR, FIELDS_XML)
       values ('a848a6fe-f5b7-4ae9-8ed4-db6dddddc58d', now(), 'system', 'thesissummer$OrderPosting', '2700', '<?xml version="1.0" encoding="UTF-8"?>
<fields>
  <field name="date" inDocKind="true" required="false" visible="true" signed="false" />
  <field name="docCategory" inDocKind="true" required="false" visible="true" signed="false" />
  <field name="owner" inDocKind="true" required="false" visible="true" signed="false" />
  <field name="department" inDocKind="true" required="false" visible="true" signed="false" />
  <field name="comment" inDocKind="true" required="false" visible="true" signed="false" />
  <field name="finishDatePlan" inDocKind="true" required="false" visible="true" signed="false" />
  <field name="resolution" inDocKind="true" required="false" visible="true" signed="false" />
  <field name="number" inDocKind="true" required="false" visible="true" signed="false" />
  <field name="organization" inDocKind="true" required="false" visible="true" signed="false" />
  <field name="parentCard" inDocKind="true" required="false" visible="true" signed="false" />
  <field name="theme" inDocKind="true" required="false" visible="true" signed="false" />
  <field name="nomerOrder" inDocKind="true" required="false" visible="true" signed="false" />
  <field name="dataOrder" inDocKind="true" required="false" visible="true" signed="false" />
  <field name="podrazdelenie" inDocKind="true" required="false" visible="true" signed="false" />
  <field name="nomerProtocola" inDocKind="true" required="false" visible="true" signed="false" />
  <field name="zaemshik" inDocKind="true" required="false" visible="true" signed="false" />
  <field name="dogovorRamochSogl" inDocKind="true" required="false" visible="true" signed="false" />
  <field name="konechniyZaemchik" inDocKind="true" required="false" visible="true" signed="false" />
  <field name="obshayaSumma" inDocKind="true" required="false" visible="true" signed="false" />
</fields>
')^
-- end insert cardType for OrderPosting
-- begin update procCardType for OrderPosting
update wf_proc set card_types = regexp_replace(card_types, ',thesissummer\\$OrderPosting', '') where code in ('Endorsement', 'Resolution', 'Acquaintance', 'Registration')^
update wf_proc set updated_by='system', card_types = card_types || 'thesissummer$OrderPosting,' where code in ('Endorsement', 'Resolution', 'Acquaintance', 'Registration')^
-- end update procCardType for OrderPosting
-- begin insert docKind for OrderPosting
CREATE OR REPLACE FUNCTION baseInsert()
RETURNS integer
AS $$
DECLARE
    cnt integer = 0;
BEGIN
    cnt = (select count(CATEGORY_ID) from DF_DOC_KIND where CATEGORY_ID = 'b583ccc6-636a-4cb7-a649-61ceec1e140d');
    if (cnt = 0) then
        insert into SYS_CATEGORY (ID, NAME, ENTITY_TYPE, IS_DEFAULT, CREATE_TS, CREATED_BY, VERSION, DISCRIMINATOR)
        values ('b583ccc6-636a-4cb7-a649-61ceec1e140d', 'Распоряжения на оприход обеспечения', 'thesissummer$OrderPosting', false, now(), 'system', 1, 1);

        insert into DF_DOC_KIND (CATEGORY_ID, CREATE_TS, CREATED_BY, VERSION, DOC_TYPE_ID, NUMERATOR_ID,
                                 NUMERATOR_TYPE, CATEGORY_ATTRS_PLACE, TAB_NAME, PORTAL_PUBLISH_ALLOWED, DISABLE_ADD_PROCESS_ACTORS, CREATE_ONLY_BY_TEMPLATE)
        values ('b583ccc6-636a-4cb7-a649-61ceec1e140d', now(), 'system', 1, 'a848a6fe-f5b7-4ae9-8ed4-db6dddddc58d', '9a7e53be-15fd-41db-a3d3-5e299016e7d9',
                1, 1, 'Доп. поля', false, false, false);
end if;
return 0;
END;
$$
LANGUAGE plpgsql;
^
select baseInsert();^
drop function if exists baseInsert();^
-- end insert docKind for OrderPosting
-- begin insert secPermissions for OrderPosting
insert into SEC_PERMISSION (ID, CREATE_TS, CREATED_BY, VERSION, UPDATE_TS, UPDATED_BY, DELETE_TS, DELETED_BY, PERMISSION_TYPE, TARGET, VALUE_, ROLE_ID)
values (newid(), now(), 'system', 1, now(), null, null, null, 20, 'thesissummer$OrderPosting:create', 0, (select ID from SEC_ROLE where NAME = 'SimpleUser'))^
insert into SEC_PERMISSION (ID, CREATE_TS, CREATED_BY, VERSION, UPDATE_TS, UPDATED_BY, DELETE_TS, DELETED_BY, PERMISSION_TYPE, TARGET, VALUE_, ROLE_ID)
values (newid(), now(), 'system', 1, now(), null, null, null, 20, 'thesissummer$OrderPosting:delete', 0, (select ID from SEC_ROLE where NAME = 'SimpleUser'))^
insert into SEC_PERMISSION (ID, CREATE_TS, CREATED_BY, VERSION, UPDATE_TS, UPDATED_BY, DELETE_TS, DELETED_BY, PERMISSION_TYPE, TARGET, VALUE_, ROLE_ID)
values (newid(), now(), 'system', 1, now(), null, null, null, 20, 'thesissummer$OrderPosting:create', 1, (select ID from SEC_ROLE where NAME = 'Administrators'))^
insert into SEC_PERMISSION (ID, CREATE_TS, CREATED_BY, VERSION, UPDATE_TS, UPDATED_BY, DELETE_TS, DELETED_BY, PERMISSION_TYPE, TARGET, VALUE_, ROLE_ID)
values (newid(), now(), 'system', 1, now(), null, null, null, 20, 'thesissummer$OrderPosting:update', 1, (select ID from SEC_ROLE where NAME = 'Administrators'))^
insert into SEC_PERMISSION (ID, CREATE_TS, CREATED_BY, VERSION, UPDATE_TS, UPDATED_BY, DELETE_TS, DELETED_BY, PERMISSION_TYPE, TARGET, VALUE_, ROLE_ID)
values (newid(), now(), 'system', 1, now(), null, null, null, 20, 'thesissummer$OrderPosting:delete', 1, (select ID from SEC_ROLE where NAME = 'Administrators'))^
insert into SEC_PERMISSION (ID, CREATE_TS, CREATED_BY, VERSION, UPDATE_TS, UPDATED_BY, DELETE_TS, DELETED_BY, PERMISSION_TYPE, TARGET, VALUE_, ROLE_ID)
values (newid(), now(), 'system', 1, now(), null, null, null, 20, 'thesissummer$OrderPosting:create', 1, (select ID from SEC_ROLE where NAME = 'doc_initiator'))^
insert into SEC_PERMISSION (ID, CREATE_TS, CREATED_BY, VERSION, UPDATE_TS, UPDATED_BY, DELETE_TS, DELETED_BY, PERMISSION_TYPE, TARGET, VALUE_, ROLE_ID)
values (newid(), now(), 'system', 1, now(), null, null, null, 20, 'thesissummer$OrderPosting:update', 1, (select ID from SEC_ROLE where NAME = 'doc_initiator'))^
insert into SEC_PERMISSION (ID, CREATE_TS, CREATED_BY, VERSION, UPDATE_TS, UPDATED_BY, DELETE_TS, DELETED_BY, PERMISSION_TYPE, TARGET, VALUE_, ROLE_ID)
values (newid(), now(), 'system', 1, now(), null, null, null, 20, 'thesissummer$OrderPosting:delete', 1, (select ID from SEC_ROLE where NAME = 'doc_initiator'))^
-- end insert secPermissions for OrderPosting
-- begin insert default numerator for OrderRevaluation
CREATE OR REPLACE FUNCTION baseInsert()
RETURNS integer
AS $$
DECLARE
    cnt integer = 0;
BEGIN
cnt = (select count(id) from DF_NUMERATOR where CODE = 'OrderRevaluationNumerator' and delete_ts is null);
if(cnt = 0) then
    INSERT INTO DF_NUMERATOR (ID, CREATE_TS, CREATED_BY, VERSION, CODE, NUMERATOR_FORMAT, SCRIPT_ENABLED,
                              PERIODICITY, NUMBER_INITIAL_VALUE, LOC_NAME)
    VALUES ('b9741837-9275-4bbf-bb2c-fb57eec08592', now(), 'system', 1, 'OrderRevaluationNumerator', '[number]', FALSE, 'Y', 1,
            '{"captionWithLanguageList":[{"language":"ru","caption":"Распоряжения на переоценку обеспечения"},{"language":"en","caption":"OrderRevaluation"}]}'
    );
end if;

return 0;
END;
$$
LANGUAGE plpgsql;
^

select baseInsert()^
drop function if exists baseInsert()^
-- end insert default numerator for OrderRevaluation
-- begin insert cardType for OrderRevaluation
insert into TS_CARD_TYPE (ID, CREATE_TS, CREATED_BY, NAME, DISCRIMINATOR, FIELDS_XML)
       values ('d9912a56-8826-40ba-ad3e-dba5f0eeb350', now(), 'system', 'thesissummer$OrderRevaluation', '2800', '<?xml version="1.0" encoding="UTF-8"?>
<fields>
  <field name="date" inDocKind="true" required="false" visible="true" signed="false" />
  <field name="docCategory" inDocKind="true" required="false" visible="true" signed="false" />
  <field name="owner" inDocKind="true" required="false" visible="true" signed="false" />
  <field name="department" inDocKind="true" required="false" visible="true" signed="false" />
  <field name="comment" inDocKind="true" required="false" visible="true" signed="false" />
  <field name="finishDatePlan" inDocKind="true" required="false" visible="true" signed="false" />
  <field name="resolution" inDocKind="true" required="false" visible="true" signed="false" />
  <field name="number" inDocKind="true" required="false" visible="true" signed="false" />
  <field name="organization" inDocKind="true" required="false" visible="true" signed="false" />
  <field name="parentCard" inDocKind="true" required="false" visible="true" signed="false" />
  <field name="theme" inDocKind="true" required="false" visible="true" signed="false" />
  <field name="nomerOrder" inDocKind="true" required="false" visible="true" signed="false" />
  <field name="dataOrder" inDocKind="true" required="false" visible="true" signed="false" />
  <field name="podrazdelenie" inDocKind="true" required="false" visible="true" signed="false" />
  <field name="nomerProtocola" inDocKind="true" required="false" visible="true" signed="false" />
  <field name="zaemchik" inDocKind="true" required="false" visible="true" signed="false" />
  <field name="dogovorRamoch" inDocKind="true" required="false" visible="true" signed="false" />
  <field name="konechniyZaemchik" inDocKind="true" required="false" visible="true" signed="false" />
  <field name="obhayaTekSumma" inDocKind="true" required="false" visible="true" signed="false" />
  <field name="obshayaSummaPereocenki" inDocKind="true" required="false" visible="true" signed="false" />
  <field name="obshayaItogPereocenki" inDocKind="true" required="false" visible="true" signed="false" />
</fields>
')^
-- end insert cardType for OrderRevaluation
-- begin update procCardType for OrderRevaluation
update wf_proc set card_types = regexp_replace(card_types, ',thesissummer\\$OrderRevaluation', '') where code in ('Endorsement', 'Resolution', 'Acquaintance', 'Registration')^
update wf_proc set updated_by='system', card_types = card_types || 'thesissummer$OrderRevaluation,' where code in ('Endorsement', 'Resolution', 'Acquaintance', 'Registration')^
-- end update procCardType for OrderRevaluation
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
-- begin insert secPermissions for OrderRevaluation
insert into SEC_PERMISSION (ID, CREATE_TS, CREATED_BY, VERSION, UPDATE_TS, UPDATED_BY, DELETE_TS, DELETED_BY, PERMISSION_TYPE, TARGET, VALUE_, ROLE_ID)
values (newid(), now(), 'system', 1, now(), null, null, null, 20, 'thesissummer$OrderRevaluation:create', 0, (select ID from SEC_ROLE where NAME = 'SimpleUser'))^
insert into SEC_PERMISSION (ID, CREATE_TS, CREATED_BY, VERSION, UPDATE_TS, UPDATED_BY, DELETE_TS, DELETED_BY, PERMISSION_TYPE, TARGET, VALUE_, ROLE_ID)
values (newid(), now(), 'system', 1, now(), null, null, null, 20, 'thesissummer$OrderRevaluation:delete', 0, (select ID from SEC_ROLE where NAME = 'SimpleUser'))^
insert into SEC_PERMISSION (ID, CREATE_TS, CREATED_BY, VERSION, UPDATE_TS, UPDATED_BY, DELETE_TS, DELETED_BY, PERMISSION_TYPE, TARGET, VALUE_, ROLE_ID)
values (newid(), now(), 'system', 1, now(), null, null, null, 20, 'thesissummer$OrderRevaluation:create', 1, (select ID from SEC_ROLE where NAME = 'Administrators'))^
insert into SEC_PERMISSION (ID, CREATE_TS, CREATED_BY, VERSION, UPDATE_TS, UPDATED_BY, DELETE_TS, DELETED_BY, PERMISSION_TYPE, TARGET, VALUE_, ROLE_ID)
values (newid(), now(), 'system', 1, now(), null, null, null, 20, 'thesissummer$OrderRevaluation:update', 1, (select ID from SEC_ROLE where NAME = 'Administrators'))^
insert into SEC_PERMISSION (ID, CREATE_TS, CREATED_BY, VERSION, UPDATE_TS, UPDATED_BY, DELETE_TS, DELETED_BY, PERMISSION_TYPE, TARGET, VALUE_, ROLE_ID)
values (newid(), now(), 'system', 1, now(), null, null, null, 20, 'thesissummer$OrderRevaluation:delete', 1, (select ID from SEC_ROLE where NAME = 'Administrators'))^
insert into SEC_PERMISSION (ID, CREATE_TS, CREATED_BY, VERSION, UPDATE_TS, UPDATED_BY, DELETE_TS, DELETED_BY, PERMISSION_TYPE, TARGET, VALUE_, ROLE_ID)
values (newid(), now(), 'system', 1, now(), null, null, null, 20, 'thesissummer$OrderRevaluation:create', 1, (select ID from SEC_ROLE where NAME = 'doc_initiator'))^
insert into SEC_PERMISSION (ID, CREATE_TS, CREATED_BY, VERSION, UPDATE_TS, UPDATED_BY, DELETE_TS, DELETED_BY, PERMISSION_TYPE, TARGET, VALUE_, ROLE_ID)
values (newid(), now(), 'system', 1, now(), null, null, null, 20, 'thesissummer$OrderRevaluation:update', 1, (select ID from SEC_ROLE where NAME = 'doc_initiator'))^
insert into SEC_PERMISSION (ID, CREATE_TS, CREATED_BY, VERSION, UPDATE_TS, UPDATED_BY, DELETE_TS, DELETED_BY, PERMISSION_TYPE, TARGET, VALUE_, ROLE_ID)
values (newid(), now(), 'system', 1, now(), null, null, null, 20, 'thesissummer$OrderRevaluation:delete', 1, (select ID from SEC_ROLE where NAME = 'doc_initiator'))^
-- end insert secPermissions for OrderRevaluation
-- begin insert default numerator for OrderTranfer
CREATE OR REPLACE FUNCTION baseInsert()
RETURNS integer
AS $$
DECLARE
    cnt integer = 0;
BEGIN
cnt = (select count(id) from DF_NUMERATOR where CODE = 'OrderTranferNumerator' and delete_ts is null);
if(cnt = 0) then
    INSERT INTO DF_NUMERATOR (ID, CREATE_TS, CREATED_BY, VERSION, CODE, NUMERATOR_FORMAT, SCRIPT_ENABLED,
                              PERIODICITY, NUMBER_INITIAL_VALUE, LOC_NAME)
    VALUES ('ee384850-58e8-49ac-85c2-29af6ef219a8', now(), 'system', 1, 'OrderTranferNumerator', '[number]', FALSE, 'Y', 1,
            '{"captionWithLanguageList":[{"language":"ru","caption":"Распоряжения на перечисление субсидий"},{"language":"en","caption":"OrderTranfer"}]}'
    );
end if;

return 0;
END;
$$
LANGUAGE plpgsql;
^

select baseInsert()^
drop function if exists baseInsert()^
-- end insert default numerator for OrderTranfer
-- begin insert cardType for OrderTranfer
insert into TS_CARD_TYPE (ID, CREATE_TS, CREATED_BY, NAME, DISCRIMINATOR, FIELDS_XML)
       values ('3d742b3c-2df2-44ff-82e3-36cc6ff1432b', now(), 'system', 'thesissummer$OrderTranfer', '2900', '<?xml version="1.0" encoding="UTF-8"?>
<fields>
  <field name="date" inDocKind="true" required="false" visible="true" signed="false" />
  <field name="docCategory" inDocKind="true" required="false" visible="true" signed="false" />
  <field name="owner" inDocKind="true" required="false" visible="true" signed="false" />
  <field name="department" inDocKind="true" required="false" visible="true" signed="false" />
  <field name="comment" inDocKind="true" required="false" visible="true" signed="false" />
  <field name="finishDatePlan" inDocKind="true" required="false" visible="true" signed="false" />
  <field name="resolution" inDocKind="true" required="false" visible="true" signed="false" />
  <field name="number" inDocKind="true" required="false" visible="true" signed="false" />
  <field name="organization" inDocKind="true" required="false" visible="true" signed="false" />
  <field name="parentCard" inDocKind="true" required="false" visible="true" signed="false" />
  <field name="theme" inDocKind="true" required="false" visible="true" signed="false" />
  <field name="nomerOrder" inDocKind="true" required="false" visible="true" signed="false" />
  <field name="dataOrder" inDocKind="true" required="false" visible="true" signed="false" />
  <field name="podrazdelenie" inDocKind="true" required="false" visible="true" signed="false" />
  <field name="schetKorp" inDocKind="true" required="false" visible="true" signed="false" />
  <field name="bankOrg" inDocKind="true" required="false" visible="true" signed="false" />
  <field name="oblastPodr" inDocKind="true" required="false" visible="true" signed="false" />
  <field name="kvartal1" inDocKind="true" required="false" visible="true" signed="false" />
  <field name="dogovorSub" inDocKind="true" required="false" visible="true" signed="false" />
  <field name="dataDogovorSub" inDocKind="true" required="false" visible="true" signed="false" />
  <field name="rabOperator" inDocKind="true" required="false" visible="true" signed="false" />
  <field name="summaSub" inDocKind="true" required="false" visible="true" signed="false" />
  <field name="schetPol" inDocKind="true" required="false" visible="true" signed="false" />
  <field name="bankPolucha" inDocKind="true" required="false" visible="true" signed="false" />
</fields>
')^
-- end insert cardType for OrderTranfer
-- begin update procCardType for OrderTranfer
update wf_proc set card_types = regexp_replace(card_types, ',thesissummer\\$OrderTranfer', '') where code in ('Endorsement', 'Resolution', 'Acquaintance', 'Registration')^
update wf_proc set updated_by='system', card_types = card_types || 'thesissummer$OrderTranfer,' where code in ('Endorsement', 'Resolution', 'Acquaintance', 'Registration')^
-- end update procCardType for OrderTranfer
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
^
select baseInsert();^
drop function if exists baseInsert();^
-- end insert docKind for OrderTranfer
-- begin insert secPermissions for OrderTranfer
insert into SEC_PERMISSION (ID, CREATE_TS, CREATED_BY, VERSION, UPDATE_TS, UPDATED_BY, DELETE_TS, DELETED_BY, PERMISSION_TYPE, TARGET, VALUE_, ROLE_ID)
values (newid(), now(), 'system', 1, now(), null, null, null, 20, 'thesissummer$OrderTranfer:create', 0, (select ID from SEC_ROLE where NAME = 'SimpleUser'))^
insert into SEC_PERMISSION (ID, CREATE_TS, CREATED_BY, VERSION, UPDATE_TS, UPDATED_BY, DELETE_TS, DELETED_BY, PERMISSION_TYPE, TARGET, VALUE_, ROLE_ID)
values (newid(), now(), 'system', 1, now(), null, null, null, 20, 'thesissummer$OrderTranfer:delete', 0, (select ID from SEC_ROLE where NAME = 'SimpleUser'))^
insert into SEC_PERMISSION (ID, CREATE_TS, CREATED_BY, VERSION, UPDATE_TS, UPDATED_BY, DELETE_TS, DELETED_BY, PERMISSION_TYPE, TARGET, VALUE_, ROLE_ID)
values (newid(), now(), 'system', 1, now(), null, null, null, 20, 'thesissummer$OrderTranfer:create', 1, (select ID from SEC_ROLE where NAME = 'Administrators'))^
insert into SEC_PERMISSION (ID, CREATE_TS, CREATED_BY, VERSION, UPDATE_TS, UPDATED_BY, DELETE_TS, DELETED_BY, PERMISSION_TYPE, TARGET, VALUE_, ROLE_ID)
values (newid(), now(), 'system', 1, now(), null, null, null, 20, 'thesissummer$OrderTranfer:update', 1, (select ID from SEC_ROLE where NAME = 'Administrators'))^
insert into SEC_PERMISSION (ID, CREATE_TS, CREATED_BY, VERSION, UPDATE_TS, UPDATED_BY, DELETE_TS, DELETED_BY, PERMISSION_TYPE, TARGET, VALUE_, ROLE_ID)
values (newid(), now(), 'system', 1, now(), null, null, null, 20, 'thesissummer$OrderTranfer:delete', 1, (select ID from SEC_ROLE where NAME = 'Administrators'))^
insert into SEC_PERMISSION (ID, CREATE_TS, CREATED_BY, VERSION, UPDATE_TS, UPDATED_BY, DELETE_TS, DELETED_BY, PERMISSION_TYPE, TARGET, VALUE_, ROLE_ID)
values (newid(), now(), 'system', 1, now(), null, null, null, 20, 'thesissummer$OrderTranfer:create', 1, (select ID from SEC_ROLE where NAME = 'doc_initiator'))^
insert into SEC_PERMISSION (ID, CREATE_TS, CREATED_BY, VERSION, UPDATE_TS, UPDATED_BY, DELETE_TS, DELETED_BY, PERMISSION_TYPE, TARGET, VALUE_, ROLE_ID)
values (newid(), now(), 'system', 1, now(), null, null, null, 20, 'thesissummer$OrderTranfer:update', 1, (select ID from SEC_ROLE where NAME = 'doc_initiator'))^
insert into SEC_PERMISSION (ID, CREATE_TS, CREATED_BY, VERSION, UPDATE_TS, UPDATED_BY, DELETE_TS, DELETED_BY, PERMISSION_TYPE, TARGET, VALUE_, ROLE_ID)
values (newid(), now(), 'system', 1, now(), null, null, null, 20, 'thesissummer$OrderTranfer:delete', 1, (select ID from SEC_ROLE where NAME = 'doc_initiator'))^
-- end insert secPermissions for OrderTranfer
-- begin insert default numerator for OrderWriteOff
CREATE OR REPLACE FUNCTION baseInsert()
RETURNS integer
AS $$
DECLARE
    cnt integer = 0;
BEGIN
cnt = (select count(id) from DF_NUMERATOR where CODE = 'OrderWriteOffNumerator' and delete_ts is null);
if(cnt = 0) then
    INSERT INTO DF_NUMERATOR (ID, CREATE_TS, CREATED_BY, VERSION, CODE, NUMERATOR_FORMAT, SCRIPT_ENABLED,
                              PERIODICITY, NUMBER_INITIAL_VALUE, LOC_NAME)
    VALUES ('c26ce3c0-3636-4309-9321-55edff38e3b4', now(), 'system', 1, 'OrderWriteOffNumerator', '[number]', FALSE, 'Y', 1,
            '{"captionWithLanguageList":[{"language":"ru","caption":"На списание обеспечения"},{"language":"en","caption":"OrderWriteOff"}]}'
    );
end if;

return 0;
END;
$$
LANGUAGE plpgsql;
^

select baseInsert()^
drop function if exists baseInsert()^
-- end insert default numerator for OrderWriteOff
-- begin insert cardType for OrderWriteOff
insert into TS_CARD_TYPE (ID, CREATE_TS, CREATED_BY, NAME, DISCRIMINATOR, FIELDS_XML)
       values ('8b619a9d-74b7-4429-9a72-4ee47848676c', now(), 'system', 'thesissummer$OrderWriteOff', '3000', '<?xml version="1.0" encoding="UTF-8"?>
<fields>
  <field name="date" inDocKind="true" required="false" visible="true" signed="false" />
  <field name="docCategory" inDocKind="true" required="false" visible="true" signed="false" />
  <field name="owner" inDocKind="true" required="false" visible="true" signed="false" />
  <field name="department" inDocKind="true" required="false" visible="true" signed="false" />
  <field name="comment" inDocKind="true" required="false" visible="true" signed="false" />
  <field name="finishDatePlan" inDocKind="true" required="false" visible="true" signed="false" />
  <field name="resolution" inDocKind="true" required="false" visible="true" signed="false" />
  <field name="number" inDocKind="true" required="false" visible="true" signed="false" />
  <field name="organization" inDocKind="true" required="false" visible="true" signed="false" />
  <field name="parentCard" inDocKind="true" required="false" visible="true" signed="false" />
  <field name="theme" inDocKind="true" required="false" visible="true" signed="false" />
  <field name="nomerOrder" inDocKind="true" required="false" visible="true" signed="false" />
  <field name="dataOrder" inDocKind="true" required="false" visible="true" signed="false" />
  <field name="podrazdelenie" inDocKind="true" required="false" visible="true" signed="false" />
  <field name="nomerProtocola" inDocKind="true" required="false" visible="true" signed="false" />
  <field name="zaemchik" inDocKind="true" required="false" visible="true" signed="false" />
  <field name="dogovorRamoch" inDocKind="true" required="false" visible="true" signed="false" />
  <field name="konechniyZaemchik" inDocKind="true" required="false" visible="true" signed="false" />
  <field name="osnovaniyeSniyatiaObespech" inDocKind="true" required="false" visible="true" signed="false" />
  <field name="obshayaSummaZalogObespech" inDocKind="true" required="false" visible="true" signed="false" />
</fields>
')^
-- end insert cardType for OrderWriteOff
-- begin update procCardType for OrderWriteOff
update wf_proc set card_types = regexp_replace(card_types, ',thesissummer\\$OrderWriteOff', '') where code in ('Endorsement', 'Resolution', 'Acquaintance', 'Registration')^
update wf_proc set updated_by='system', card_types = card_types || 'thesissummer$OrderWriteOff,' where code in ('Endorsement', 'Resolution', 'Acquaintance', 'Registration')^
-- end update procCardType for OrderWriteOff
-- begin insert docKind for OrderWriteOff
CREATE OR REPLACE FUNCTION baseInsert()
RETURNS integer
AS $$
DECLARE
    cnt integer = 0;
BEGIN
    cnt = (select count(CATEGORY_ID) from DF_DOC_KIND where CATEGORY_ID = '47919524-e519-4286-955a-f125bb935f69');
    if (cnt = 0) then
        insert into SYS_CATEGORY (ID, NAME, ENTITY_TYPE, IS_DEFAULT, CREATE_TS, CREATED_BY, VERSION, DISCRIMINATOR)
        values ('47919524-e519-4286-955a-f125bb935f69', 'На списание обеспечения', 'thesissummer$OrderWriteOff', false, now(), 'system', 1, 1);

        insert into DF_DOC_KIND (CATEGORY_ID, CREATE_TS, CREATED_BY, VERSION, DOC_TYPE_ID, NUMERATOR_ID,
                                 NUMERATOR_TYPE, CATEGORY_ATTRS_PLACE, TAB_NAME, PORTAL_PUBLISH_ALLOWED, DISABLE_ADD_PROCESS_ACTORS, CREATE_ONLY_BY_TEMPLATE)
        values ('47919524-e519-4286-955a-f125bb935f69', now(), 'system', 1, '8b619a9d-74b7-4429-9a72-4ee47848676c', 'c26ce3c0-3636-4309-9321-55edff38e3b4',
                1, 1, 'Доп. поля', false, false, false);
end if;
return 0;
END;
$$
LANGUAGE plpgsql;
^
select baseInsert();^
drop function if exists baseInsert();^
-- end insert docKind for OrderWriteOff
-- begin insert secPermissions for OrderWriteOff
insert into SEC_PERMISSION (ID, CREATE_TS, CREATED_BY, VERSION, UPDATE_TS, UPDATED_BY, DELETE_TS, DELETED_BY, PERMISSION_TYPE, TARGET, VALUE_, ROLE_ID)
values (newid(), now(), 'system', 1, now(), null, null, null, 20, 'thesissummer$OrderWriteOff:create', 0, (select ID from SEC_ROLE where NAME = 'SimpleUser'))^
insert into SEC_PERMISSION (ID, CREATE_TS, CREATED_BY, VERSION, UPDATE_TS, UPDATED_BY, DELETE_TS, DELETED_BY, PERMISSION_TYPE, TARGET, VALUE_, ROLE_ID)
values (newid(), now(), 'system', 1, now(), null, null, null, 20, 'thesissummer$OrderWriteOff:delete', 0, (select ID from SEC_ROLE where NAME = 'SimpleUser'))^
insert into SEC_PERMISSION (ID, CREATE_TS, CREATED_BY, VERSION, UPDATE_TS, UPDATED_BY, DELETE_TS, DELETED_BY, PERMISSION_TYPE, TARGET, VALUE_, ROLE_ID)
values (newid(), now(), 'system', 1, now(), null, null, null, 20, 'thesissummer$OrderWriteOff:create', 1, (select ID from SEC_ROLE where NAME = 'Administrators'))^
insert into SEC_PERMISSION (ID, CREATE_TS, CREATED_BY, VERSION, UPDATE_TS, UPDATED_BY, DELETE_TS, DELETED_BY, PERMISSION_TYPE, TARGET, VALUE_, ROLE_ID)
values (newid(), now(), 'system', 1, now(), null, null, null, 20, 'thesissummer$OrderWriteOff:update', 1, (select ID from SEC_ROLE where NAME = 'Administrators'))^
insert into SEC_PERMISSION (ID, CREATE_TS, CREATED_BY, VERSION, UPDATE_TS, UPDATED_BY, DELETE_TS, DELETED_BY, PERMISSION_TYPE, TARGET, VALUE_, ROLE_ID)
values (newid(), now(), 'system', 1, now(), null, null, null, 20, 'thesissummer$OrderWriteOff:delete', 1, (select ID from SEC_ROLE where NAME = 'Administrators'))^
insert into SEC_PERMISSION (ID, CREATE_TS, CREATED_BY, VERSION, UPDATE_TS, UPDATED_BY, DELETE_TS, DELETED_BY, PERMISSION_TYPE, TARGET, VALUE_, ROLE_ID)
values (newid(), now(), 'system', 1, now(), null, null, null, 20, 'thesissummer$OrderWriteOff:create', 1, (select ID from SEC_ROLE where NAME = 'doc_initiator'))^
insert into SEC_PERMISSION (ID, CREATE_TS, CREATED_BY, VERSION, UPDATE_TS, UPDATED_BY, DELETE_TS, DELETED_BY, PERMISSION_TYPE, TARGET, VALUE_, ROLE_ID)
values (newid(), now(), 'system', 1, now(), null, null, null, 20, 'thesissummer$OrderWriteOff:update', 1, (select ID from SEC_ROLE where NAME = 'doc_initiator'))^
insert into SEC_PERMISSION (ID, CREATE_TS, CREATED_BY, VERSION, UPDATE_TS, UPDATED_BY, DELETE_TS, DELETED_BY, PERMISSION_TYPE, TARGET, VALUE_, ROLE_ID)
values (newid(), now(), 'system', 1, now(), null, null, null, 20, 'thesissummer$OrderWriteOff:delete', 1, (select ID from SEC_ROLE where NAME = 'doc_initiator'))^
-- end insert secPermissions for OrderWriteOff
-- begin insert default numerator for OrderPogashOb
CREATE OR REPLACE FUNCTION baseInsert()
RETURNS integer
AS $$
DECLARE
    cnt integer = 0;
BEGIN
cnt = (select count(id) from DF_NUMERATOR where CODE = 'OrderPogashObNumerator' and delete_ts is null);
if(cnt = 0) then
    INSERT INTO DF_NUMERATOR (ID, CREATE_TS, CREATED_BY, VERSION, CODE, NUMERATOR_FORMAT, SCRIPT_ENABLED,
                              PERIODICITY, NUMBER_INITIAL_VALUE, LOC_NAME)
    VALUES ('c2a954cc-7f37-408f-bdf5-3fe4df11c2f5', now(), 'system', 1, 'OrderPogashObNumerator', '[number]', FALSE, 'Y', 1,
            '{"captionWithLanguageList":[{"language":"ru","caption":"На погашение обязательств"},{"language":"en","caption":"OrderPogashOb"}]}'
    );
end if;

return 0;
END;
$$
LANGUAGE plpgsql;
^

select baseInsert()^
drop function if exists baseInsert()^
-- end insert default numerator for OrderPogashOb
-- begin insert cardType for OrderPogashOb
insert into TS_CARD_TYPE (ID, CREATE_TS, CREATED_BY, NAME, DISCRIMINATOR, FIELDS_XML)
       values ('55f17b71-f25e-4611-b8c7-380d4b74b936', now(), 'system', 'thesissummer$OrderPogashOb', '3300', '<?xml version="1.0" encoding="UTF-8"?>
<fields>
  <field name="date" inDocKind="true" required="false" visible="true" signed="false" />
  <field name="docCategory" inDocKind="true" required="false" visible="true" signed="false" />
  <field name="owner" inDocKind="true" required="false" visible="true" signed="false" />
  <field name="department" inDocKind="true" required="false" visible="true" signed="false" />
  <field name="comment" inDocKind="true" required="false" visible="true" signed="false" />
  <field name="finishDatePlan" inDocKind="true" required="false" visible="true" signed="false" />
  <field name="resolution" inDocKind="true" required="false" visible="true" signed="false" />
  <field name="number" inDocKind="true" required="false" visible="true" signed="false" />
  <field name="organization" inDocKind="true" required="false" visible="true" signed="false" />
  <field name="parentCard" inDocKind="true" required="false" visible="true" signed="false" />
  <field name="theme" inDocKind="true" required="false" visible="true" signed="false" />
  <field name="dogovor" inDocKind="true" required="false" visible="true" signed="false" />
  <field name="naznacheniye" inDocKind="true" required="false" visible="true" signed="false" />
  <field name="contragent" inDocKind="true" required="false" visible="true" signed="false" />
  <field name="dataPerech" inDocKind="true" required="false" visible="true" signed="false" />
  <field name="vidCzaimorachetov" inDocKind="true" required="false" visible="true" signed="false" />
  <field name="summaKOplate" inDocKind="true" required="false" visible="true" signed="false" />
  <field name="poluchatel" inDocKind="true" required="false" visible="true" signed="false" />
  <field name="bankovsckiySchetPlatelshika" inDocKind="true" required="false" visible="true" signed="false" />
  <field name="dogovorPlat" inDocKind="true" required="false" visible="true" signed="false" />
  <field name="bin" inDocKind="true" required="false" visible="true" signed="false" />
  <field name="bankBenefeciar" inDocKind="true" required="false" visible="true" signed="false" />
  <field name="bik" inDocKind="true" required="false" visible="true" signed="false" />
  <field name="iik" inDocKind="true" required="false" visible="true" signed="false" />
  <field name="kbe" inDocKind="true" required="false" visible="true" signed="false" />
  <field name="knp" inDocKind="true" required="false" visible="true" signed="false" />
  <field name="kbk" inDocKind="true" required="false" visible="true" signed="false" />
</fields>
')^
-- end insert cardType for OrderPogashOb
-- begin update procCardType for OrderPogashOb
update wf_proc set card_types = regexp_replace(card_types, ',thesissummer\\$OrderPogashOb', '') where code in ('Endorsement', 'Resolution', 'Acquaintance', 'Registration')^
update wf_proc set updated_by='system', card_types = card_types || 'thesissummer$OrderPogashOb,' where code in ('Endorsement', 'Resolution', 'Acquaintance', 'Registration')^
-- end update procCardType for OrderPogashOb
-- begin insert docKind for OrderPogashOb
CREATE OR REPLACE FUNCTION baseInsert()
RETURNS integer
AS $$
DECLARE
    cnt integer = 0;
BEGIN
    cnt = (select count(CATEGORY_ID) from DF_DOC_KIND where CATEGORY_ID = '5cf090fa-a293-4591-9270-be3522132989');
    if (cnt = 0) then
        insert into SYS_CATEGORY (ID, NAME, ENTITY_TYPE, IS_DEFAULT, CREATE_TS, CREATED_BY, VERSION, DISCRIMINATOR)
        values ('5cf090fa-a293-4591-9270-be3522132989', 'На погашение обязательств', 'thesissummer$OrderPogashOb', false, now(), 'system', 1, 1);

        insert into DF_DOC_KIND (CATEGORY_ID, CREATE_TS, CREATED_BY, VERSION, DOC_TYPE_ID, NUMERATOR_ID,
                                 NUMERATOR_TYPE, CATEGORY_ATTRS_PLACE, TAB_NAME, PORTAL_PUBLISH_ALLOWED, DISABLE_ADD_PROCESS_ACTORS, CREATE_ONLY_BY_TEMPLATE)
        values ('5cf090fa-a293-4591-9270-be3522132989', now(), 'system', 1, '55f17b71-f25e-4611-b8c7-380d4b74b936', 'c2a954cc-7f37-408f-bdf5-3fe4df11c2f5',
                1, 1, 'Доп. поля', false, false, false);
end if;
return 0;
END;
$$
LANGUAGE plpgsql;
^
select baseInsert();^
drop function if exists baseInsert();^
-- end insert docKind for OrderPogashOb
-- begin insert secPermissions for OrderPogashOb
insert into SEC_PERMISSION (ID, CREATE_TS, CREATED_BY, VERSION, UPDATE_TS, UPDATED_BY, DELETE_TS, DELETED_BY, PERMISSION_TYPE, TARGET, VALUE_, ROLE_ID)
values (newid(), now(), 'system', 1, now(), null, null, null, 20, 'thesissummer$OrderPogashOb:create', 0, (select ID from SEC_ROLE where NAME = 'SimpleUser'))^
insert into SEC_PERMISSION (ID, CREATE_TS, CREATED_BY, VERSION, UPDATE_TS, UPDATED_BY, DELETE_TS, DELETED_BY, PERMISSION_TYPE, TARGET, VALUE_, ROLE_ID)
values (newid(), now(), 'system', 1, now(), null, null, null, 20, 'thesissummer$OrderPogashOb:delete', 0, (select ID from SEC_ROLE where NAME = 'SimpleUser'))^
insert into SEC_PERMISSION (ID, CREATE_TS, CREATED_BY, VERSION, UPDATE_TS, UPDATED_BY, DELETE_TS, DELETED_BY, PERMISSION_TYPE, TARGET, VALUE_, ROLE_ID)
values (newid(), now(), 'system', 1, now(), null, null, null, 20, 'thesissummer$OrderPogashOb:create', 1, (select ID from SEC_ROLE where NAME = 'Administrators'))^
insert into SEC_PERMISSION (ID, CREATE_TS, CREATED_BY, VERSION, UPDATE_TS, UPDATED_BY, DELETE_TS, DELETED_BY, PERMISSION_TYPE, TARGET, VALUE_, ROLE_ID)
values (newid(), now(), 'system', 1, now(), null, null, null, 20, 'thesissummer$OrderPogashOb:update', 1, (select ID from SEC_ROLE where NAME = 'Administrators'))^
insert into SEC_PERMISSION (ID, CREATE_TS, CREATED_BY, VERSION, UPDATE_TS, UPDATED_BY, DELETE_TS, DELETED_BY, PERMISSION_TYPE, TARGET, VALUE_, ROLE_ID)
values (newid(), now(), 'system', 1, now(), null, null, null, 20, 'thesissummer$OrderPogashOb:delete', 1, (select ID from SEC_ROLE where NAME = 'Administrators'))^
insert into SEC_PERMISSION (ID, CREATE_TS, CREATED_BY, VERSION, UPDATE_TS, UPDATED_BY, DELETE_TS, DELETED_BY, PERMISSION_TYPE, TARGET, VALUE_, ROLE_ID)
values (newid(), now(), 'system', 1, now(), null, null, null, 20, 'thesissummer$OrderPogashOb:create', 1, (select ID from SEC_ROLE where NAME = 'doc_initiator'))^
insert into SEC_PERMISSION (ID, CREATE_TS, CREATED_BY, VERSION, UPDATE_TS, UPDATED_BY, DELETE_TS, DELETED_BY, PERMISSION_TYPE, TARGET, VALUE_, ROLE_ID)
values (newid(), now(), 'system', 1, now(), null, null, null, 20, 'thesissummer$OrderPogashOb:update', 1, (select ID from SEC_ROLE where NAME = 'doc_initiator'))^
insert into SEC_PERMISSION (ID, CREATE_TS, CREATED_BY, VERSION, UPDATE_TS, UPDATED_BY, DELETE_TS, DELETED_BY, PERMISSION_TYPE, TARGET, VALUE_, ROLE_ID)
values (newid(), now(), 'system', 1, now(), null, null, null, 20, 'thesissummer$OrderPogashOb:delete', 1, (select ID from SEC_ROLE where NAME = 'doc_initiator'))^
-- end insert secPermissions for OrderPogashOb
-- begin insert default numerator for OrderCenBumaga
CREATE OR REPLACE FUNCTION baseInsert()
RETURNS integer
AS $$
DECLARE
    cnt integer = 0;
BEGIN
cnt = (select count(id) from DF_NUMERATOR where CODE = 'OrderCenBumagaNumerator' and delete_ts is null);
if(cnt = 0) then
    INSERT INTO DF_NUMERATOR (ID, CREATE_TS, CREATED_BY, VERSION, CODE, NUMERATOR_FORMAT, SCRIPT_ENABLED,
                              PERIODICITY, NUMBER_INITIAL_VALUE, LOC_NAME)
    VALUES ('5c171be0-5d8b-4604-88c2-49a52ac00dcb', now(), 'system', 1, 'OrderCenBumagaNumerator', '[number]', FALSE, 'Y', 1,
            '{"captionWithLanguageList":[{"language":"ru","caption":"Распоряжения по ценным бумагам"},{"language":"en","caption":"OrderCenBumaga"}]}'
    );
end if;

return 0;
END;
$$
LANGUAGE plpgsql;
^

select baseInsert()^
drop function if exists baseInsert()^
-- end insert default numerator for OrderCenBumaga
-- begin insert cardType for OrderCenBumaga
insert into TS_CARD_TYPE (ID, CREATE_TS, CREATED_BY, NAME, DISCRIMINATOR, FIELDS_XML)
       values ('e2d3cd7e-60d6-42ed-960e-c5624c8e5985', now(), 'system', 'thesissummer$OrderCenBumaga', '3400', '<?xml version="1.0" encoding="UTF-8"?>
<fields>
  <field name="date" inDocKind="true" required="false" visible="true" signed="false" />
  <field name="docCategory" inDocKind="true" required="false" visible="true" signed="false" />
  <field name="owner" inDocKind="true" required="false" visible="true" signed="false" />
  <field name="department" inDocKind="true" required="false" visible="true" signed="false" />
  <field name="comment" inDocKind="true" required="false" visible="true" signed="false" />
  <field name="finishDatePlan" inDocKind="true" required="false" visible="true" signed="false" />
  <field name="resolution" inDocKind="true" required="false" visible="true" signed="false" />
  <field name="number" inDocKind="true" required="false" visible="true" signed="false" />
  <field name="organization" inDocKind="true" required="false" visible="true" signed="false" />
  <field name="parentCard" inDocKind="true" required="false" visible="true" signed="false" />
  <field name="theme" inDocKind="true" required="false" visible="true" signed="false" />
  <field name="nomerOrder" inDocKind="true" required="false" visible="true" signed="false" />
  <field name="dataOrder" inDocKind="true" required="false" visible="true" signed="false" />
  <field name="contragent" inDocKind="true" required="false" visible="true" signed="false" />
  <field name="emitent" inDocKind="true" required="false" visible="true" signed="false" />
  <field name="nin" inDocKind="true" required="false" visible="true" signed="false" />
  <field name="dogovor" inDocKind="true" required="false" visible="true" signed="false" />
  <field name="nomerSdelki" inDocKind="true" required="false" visible="true" signed="false" />
  <field name="vid" inDocKind="true" required="false" visible="true" signed="false" />
  <field name="cennayaBumaga" inDocKind="true" required="false" visible="true" signed="false" />
</fields>
')^
-- end insert cardType for OrderCenBumaga
-- begin update procCardType for OrderCenBumaga
update wf_proc set card_types = regexp_replace(card_types, ',thesissummer\\$OrderCenBumaga', '') where code in ('Endorsement', 'Resolution', 'Acquaintance', 'Registration')^
update wf_proc set updated_by='system', card_types = card_types || 'thesissummer$OrderCenBumaga,' where code in ('Endorsement', 'Resolution', 'Acquaintance', 'Registration')^
-- end update procCardType for OrderCenBumaga
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
-- begin insert secPermissions for OrderCenBumaga
insert into SEC_PERMISSION (ID, CREATE_TS, CREATED_BY, VERSION, UPDATE_TS, UPDATED_BY, DELETE_TS, DELETED_BY, PERMISSION_TYPE, TARGET, VALUE_, ROLE_ID)
values (newid(), now(), 'system', 1, now(), null, null, null, 20, 'thesissummer$OrderCenBumaga:create', 0, (select ID from SEC_ROLE where NAME = 'SimpleUser'))^
insert into SEC_PERMISSION (ID, CREATE_TS, CREATED_BY, VERSION, UPDATE_TS, UPDATED_BY, DELETE_TS, DELETED_BY, PERMISSION_TYPE, TARGET, VALUE_, ROLE_ID)
values (newid(), now(), 'system', 1, now(), null, null, null, 20, 'thesissummer$OrderCenBumaga:delete', 0, (select ID from SEC_ROLE where NAME = 'SimpleUser'))^
insert into SEC_PERMISSION (ID, CREATE_TS, CREATED_BY, VERSION, UPDATE_TS, UPDATED_BY, DELETE_TS, DELETED_BY, PERMISSION_TYPE, TARGET, VALUE_, ROLE_ID)
values (newid(), now(), 'system', 1, now(), null, null, null, 20, 'thesissummer$OrderCenBumaga:create', 1, (select ID from SEC_ROLE where NAME = 'Administrators'))^
insert into SEC_PERMISSION (ID, CREATE_TS, CREATED_BY, VERSION, UPDATE_TS, UPDATED_BY, DELETE_TS, DELETED_BY, PERMISSION_TYPE, TARGET, VALUE_, ROLE_ID)
values (newid(), now(), 'system', 1, now(), null, null, null, 20, 'thesissummer$OrderCenBumaga:update', 1, (select ID from SEC_ROLE where NAME = 'Administrators'))^
insert into SEC_PERMISSION (ID, CREATE_TS, CREATED_BY, VERSION, UPDATE_TS, UPDATED_BY, DELETE_TS, DELETED_BY, PERMISSION_TYPE, TARGET, VALUE_, ROLE_ID)
values (newid(), now(), 'system', 1, now(), null, null, null, 20, 'thesissummer$OrderCenBumaga:delete', 1, (select ID from SEC_ROLE where NAME = 'Administrators'))^
insert into SEC_PERMISSION (ID, CREATE_TS, CREATED_BY, VERSION, UPDATE_TS, UPDATED_BY, DELETE_TS, DELETED_BY, PERMISSION_TYPE, TARGET, VALUE_, ROLE_ID)
values (newid(), now(), 'system', 1, now(), null, null, null, 20, 'thesissummer$OrderCenBumaga:create', 1, (select ID from SEC_ROLE where NAME = 'doc_initiator'))^
insert into SEC_PERMISSION (ID, CREATE_TS, CREATED_BY, VERSION, UPDATE_TS, UPDATED_BY, DELETE_TS, DELETED_BY, PERMISSION_TYPE, TARGET, VALUE_, ROLE_ID)
values (newid(), now(), 'system', 1, now(), null, null, null, 20, 'thesissummer$OrderCenBumaga:update', 1, (select ID from SEC_ROLE where NAME = 'doc_initiator'))^
insert into SEC_PERMISSION (ID, CREATE_TS, CREATED_BY, VERSION, UPDATE_TS, UPDATED_BY, DELETE_TS, DELETED_BY, PERMISSION_TYPE, TARGET, VALUE_, ROLE_ID)
values (newid(), now(), 'system', 1, now(), null, null, null, 20, 'thesissummer$OrderCenBumaga:delete', 1, (select ID from SEC_ROLE where NAME = 'doc_initiator'))^
-- end insert secPermissions for OrderCenBumaga
-- begin insert default numerator for OrderNaVozvrat
CREATE OR REPLACE FUNCTION baseInsert()
RETURNS integer
AS $$
DECLARE
    cnt integer = 0;
BEGIN
cnt = (select count(id) from DF_NUMERATOR where CODE = 'OrderNaVozvratNumerator' and delete_ts is null);
if(cnt = 0) then
    INSERT INTO DF_NUMERATOR (ID, CREATE_TS, CREATED_BY, VERSION, CODE, NUMERATOR_FORMAT, SCRIPT_ENABLED,
                              PERIODICITY, NUMBER_INITIAL_VALUE, LOC_NAME)
    VALUES ('a9056e68-1d5c-40bb-9e90-0ba3cfdb4850', now(), 'system', 1, 'OrderNaVozvratNumerator', '[number]', FALSE, 'Y', 1,
            '{"captionWithLanguageList":[{"language":"ru","caption":"На возврат в ГУ неосв./ошибочно перечисл. средств (СИ)"},{"language":"en","caption":"OrderNaVozvrat"}]}'
    );
end if;

return 0;
END;
$$
LANGUAGE plpgsql;
^

select baseInsert()^
drop function if exists baseInsert()^
-- end insert default numerator for OrderNaVozvrat
-- begin insert cardType for OrderNaVozvrat
insert into TS_CARD_TYPE (ID, CREATE_TS, CREATED_BY, NAME, DISCRIMINATOR, FIELDS_XML)
       values ('35b682c7-5b23-46dd-906c-1d9dad6fde57', now(), 'system', 'thesissummer$OrderNaVozvrat', '3200', '<?xml version="1.0" encoding="UTF-8"?>
<fields>
  <field name="date" inDocKind="true" required="false" visible="true" signed="false" />
  <field name="docCategory" inDocKind="true" required="false" visible="true" signed="false" />
  <field name="owner" inDocKind="true" required="false" visible="true" signed="false" />
  <field name="department" inDocKind="true" required="false" visible="true" signed="false" />
  <field name="comment" inDocKind="true" required="false" visible="true" signed="false" />
  <field name="finishDatePlan" inDocKind="true" required="false" visible="true" signed="false" />
  <field name="resolution" inDocKind="true" required="false" visible="true" signed="false" />
  <field name="number" inDocKind="true" required="false" visible="true" signed="false" />
  <field name="organization" inDocKind="true" required="false" visible="true" signed="false" />
  <field name="parentCard" inDocKind="true" required="false" visible="true" signed="false" />
  <field name="theme" inDocKind="true" required="false" visible="true" signed="false" />
  <field name="nomerOrder" inDocKind="true" required="false" visible="true" signed="false" />
  <field name="dataOrder" inDocKind="true" required="false" visible="true" signed="false" />
  <field name="podrazdelenie" inDocKind="true" required="false" visible="true" signed="false" />
  <field name="ishPismo" inDocKind="true" required="false" visible="true" signed="false" />
  <field name="podrazdelenieAkimata" inDocKind="true" required="false" visible="true" signed="false" />
  <field name="rayon" inDocKind="true" required="false" visible="true" signed="false" />
  <field name="oblast" inDocKind="true" required="false" visible="true" signed="false" />
  <field name="summaDlyaPerech" inDocKind="true" required="false" visible="true" signed="false" />
  <field name="poluchatel" inDocKind="true" required="false" visible="true" signed="false" />
  <field name="bin" inDocKind="true" required="false" visible="true" signed="false" />
  <field name="iik" inDocKind="true" required="false" visible="true" signed="false" />
  <field name="bik" inDocKind="true" required="false" visible="true" signed="false" />
  <field name="knp" inDocKind="true" required="false" visible="true" signed="false" />
  <field name="kod" inDocKind="true" required="false" visible="true" signed="false" />
</fields>
')^
-- end insert cardType for OrderNaVozvrat
-- begin update procCardType for OrderNaVozvrat
update wf_proc set card_types = regexp_replace(card_types, ',thesissummer\\$OrderNaVozvrat', '') where code in ('Endorsement', 'Resolution', 'Acquaintance', 'Registration')^
update wf_proc set updated_by='system', card_types = card_types || 'thesissummer$OrderNaVozvrat,' where code in ('Endorsement', 'Resolution', 'Acquaintance', 'Registration')^
-- end update procCardType for OrderNaVozvrat
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
-- begin insert secPermissions for OrderNaVozvrat
insert into SEC_PERMISSION (ID, CREATE_TS, CREATED_BY, VERSION, UPDATE_TS, UPDATED_BY, DELETE_TS, DELETED_BY, PERMISSION_TYPE, TARGET, VALUE_, ROLE_ID)
values (newid(), now(), 'system', 1, now(), null, null, null, 20, 'thesissummer$OrderNaVozvrat:create', 0, (select ID from SEC_ROLE where NAME = 'SimpleUser'))^
insert into SEC_PERMISSION (ID, CREATE_TS, CREATED_BY, VERSION, UPDATE_TS, UPDATED_BY, DELETE_TS, DELETED_BY, PERMISSION_TYPE, TARGET, VALUE_, ROLE_ID)
values (newid(), now(), 'system', 1, now(), null, null, null, 20, 'thesissummer$OrderNaVozvrat:delete', 0, (select ID from SEC_ROLE where NAME = 'SimpleUser'))^
insert into SEC_PERMISSION (ID, CREATE_TS, CREATED_BY, VERSION, UPDATE_TS, UPDATED_BY, DELETE_TS, DELETED_BY, PERMISSION_TYPE, TARGET, VALUE_, ROLE_ID)
values (newid(), now(), 'system', 1, now(), null, null, null, 20, 'thesissummer$OrderNaVozvrat:create', 1, (select ID from SEC_ROLE where NAME = 'Administrators'))^
insert into SEC_PERMISSION (ID, CREATE_TS, CREATED_BY, VERSION, UPDATE_TS, UPDATED_BY, DELETE_TS, DELETED_BY, PERMISSION_TYPE, TARGET, VALUE_, ROLE_ID)
values (newid(), now(), 'system', 1, now(), null, null, null, 20, 'thesissummer$OrderNaVozvrat:update', 1, (select ID from SEC_ROLE where NAME = 'Administrators'))^
insert into SEC_PERMISSION (ID, CREATE_TS, CREATED_BY, VERSION, UPDATE_TS, UPDATED_BY, DELETE_TS, DELETED_BY, PERMISSION_TYPE, TARGET, VALUE_, ROLE_ID)
values (newid(), now(), 'system', 1, now(), null, null, null, 20, 'thesissummer$OrderNaVozvrat:delete', 1, (select ID from SEC_ROLE where NAME = 'Administrators'))^
insert into SEC_PERMISSION (ID, CREATE_TS, CREATED_BY, VERSION, UPDATE_TS, UPDATED_BY, DELETE_TS, DELETED_BY, PERMISSION_TYPE, TARGET, VALUE_, ROLE_ID)
values (newid(), now(), 'system', 1, now(), null, null, null, 20, 'thesissummer$OrderNaVozvrat:create', 1, (select ID from SEC_ROLE where NAME = 'doc_initiator'))^
insert into SEC_PERMISSION (ID, CREATE_TS, CREATED_BY, VERSION, UPDATE_TS, UPDATED_BY, DELETE_TS, DELETED_BY, PERMISSION_TYPE, TARGET, VALUE_, ROLE_ID)
values (newid(), now(), 'system', 1, now(), null, null, null, 20, 'thesissummer$OrderNaVozvrat:update', 1, (select ID from SEC_ROLE where NAME = 'doc_initiator'))^
insert into SEC_PERMISSION (ID, CREATE_TS, CREATED_BY, VERSION, UPDATE_TS, UPDATED_BY, DELETE_TS, DELETED_BY, PERMISSION_TYPE, TARGET, VALUE_, ROLE_ID)
values (newid(), now(), 'system', 1, now(), null, null, null, 20, 'thesissummer$OrderNaVozvrat:delete', 1, (select ID from SEC_ROLE where NAME = 'doc_initiator'))^
-- end insert secPermissions for OrderNaVozvrat
-- begin insert default numerator for OrderSubsidiy
CREATE OR REPLACE FUNCTION baseInsert()
RETURNS integer
AS $$
DECLARE
    cnt integer = 0;
BEGIN
cnt = (select count(id) from DF_NUMERATOR where CODE = 'OrderSubsidiyNumerator' and delete_ts is null);
if(cnt = 0) then
    INSERT INTO DF_NUMERATOR (ID, CREATE_TS, CREATED_BY, VERSION, CODE, NUMERATOR_FORMAT, SCRIPT_ENABLED,
                              PERIODICITY, NUMBER_INITIAL_VALUE, LOC_NAME)
    VALUES ('676382f4-a7b2-4976-b720-55460f3cb3be', now(), 'system', 1, 'OrderSubsidiyNumerator', '[number]', FALSE, 'Y', 1,
            '{"captionWithLanguageList":[{"language":"ru","caption":"По субсидированию"},{"language":"en","caption":"OrderSubsidiy"}]}'
    );
end if;

return 0;
END;
$$
LANGUAGE plpgsql;
^

select baseInsert()^
drop function if exists baseInsert()^
-- end insert default numerator for OrderSubsidiy
-- begin insert cardType for OrderSubsidiy
insert into TS_CARD_TYPE (ID, CREATE_TS, CREATED_BY, NAME, DISCRIMINATOR, FIELDS_XML)
       values ('9f329f4c-c6c8-4be7-9af3-f35ebaaf2815', now(), 'system', 'thesissummer$OrderSubsidiy', '3100', '<?xml version="1.0" encoding="UTF-8"?>
<fields>
  <field name="date" inDocKind="true" required="false" visible="true" signed="false" />
  <field name="docCategory" inDocKind="true" required="false" visible="true" signed="false" />
  <field name="owner" inDocKind="true" required="false" visible="true" signed="false" />
  <field name="department" inDocKind="true" required="false" visible="true" signed="false" />
  <field name="comment" inDocKind="true" required="false" visible="true" signed="false" />
  <field name="finishDatePlan" inDocKind="true" required="false" visible="true" signed="false" />
  <field name="resolution" inDocKind="true" required="false" visible="true" signed="false" />
  <field name="number" inDocKind="true" required="false" visible="true" signed="false" />
  <field name="organization" inDocKind="true" required="false" visible="true" signed="false" />
  <field name="parentCard" inDocKind="true" required="false" visible="true" signed="false" />
  <field name="theme" inDocKind="true" required="false" visible="true" signed="false" />
  <field name="nomerOrder" inDocKind="true" required="false" visible="true" signed="false" />
  <field name="dataOrder" inDocKind="true" required="false" visible="true" signed="false" />
</fields>
')^
-- end insert cardType for OrderSubsidiy
-- begin update procCardType for OrderSubsidiy
update wf_proc set card_types = regexp_replace(card_types, ',thesissummer\\$OrderSubsidiy', '') where code in ('Endorsement', 'Resolution', 'Acquaintance', 'Registration')^
update wf_proc set updated_by='system', card_types = card_types || 'thesissummer$OrderSubsidiy,' where code in ('Endorsement', 'Resolution', 'Acquaintance', 'Registration')^
-- end update procCardType for OrderSubsidiy
-- begin insert docKind for OrderSubsidiy
CREATE OR REPLACE FUNCTION baseInsert()
RETURNS integer
AS $$
DECLARE
    cnt integer = 0;
BEGIN
    cnt = (select count(CATEGORY_ID) from DF_DOC_KIND where CATEGORY_ID = '514c2b32-9841-4a30-a2e3-7934f91e509d');
    if (cnt = 0) then
        insert into SYS_CATEGORY (ID, NAME, ENTITY_TYPE, IS_DEFAULT, CREATE_TS, CREATED_BY, VERSION, DISCRIMINATOR)
        values ('514c2b32-9841-4a30-a2e3-7934f91e509d', 'По субсидированию', 'thesissummer$OrderSubsidiy', false, now(), 'system', 1, 1);

        insert into DF_DOC_KIND (CATEGORY_ID, CREATE_TS, CREATED_BY, VERSION, DOC_TYPE_ID, NUMERATOR_ID,
                                 NUMERATOR_TYPE, CATEGORY_ATTRS_PLACE, TAB_NAME, PORTAL_PUBLISH_ALLOWED, DISABLE_ADD_PROCESS_ACTORS, CREATE_ONLY_BY_TEMPLATE)
        values ('514c2b32-9841-4a30-a2e3-7934f91e509d', now(), 'system', 1, '9f329f4c-c6c8-4be7-9af3-f35ebaaf2815', '676382f4-a7b2-4976-b720-55460f3cb3be',
                1, 1, 'Доп. поля', false, false, false);
end if;
return 0;
END;
$$
LANGUAGE plpgsql;
^
select baseInsert();^
drop function if exists baseInsert();^
-- end insert docKind for OrderSubsidiy
-- begin insert secPermissions for OrderSubsidiy
insert into SEC_PERMISSION (ID, CREATE_TS, CREATED_BY, VERSION, UPDATE_TS, UPDATED_BY, DELETE_TS, DELETED_BY, PERMISSION_TYPE, TARGET, VALUE_, ROLE_ID)
values (newid(), now(), 'system', 1, now(), null, null, null, 20, 'thesissummer$OrderSubsidiy:create', 0, (select ID from SEC_ROLE where NAME = 'SimpleUser'))^
insert into SEC_PERMISSION (ID, CREATE_TS, CREATED_BY, VERSION, UPDATE_TS, UPDATED_BY, DELETE_TS, DELETED_BY, PERMISSION_TYPE, TARGET, VALUE_, ROLE_ID)
values (newid(), now(), 'system', 1, now(), null, null, null, 20, 'thesissummer$OrderSubsidiy:delete', 0, (select ID from SEC_ROLE where NAME = 'SimpleUser'))^
insert into SEC_PERMISSION (ID, CREATE_TS, CREATED_BY, VERSION, UPDATE_TS, UPDATED_BY, DELETE_TS, DELETED_BY, PERMISSION_TYPE, TARGET, VALUE_, ROLE_ID)
values (newid(), now(), 'system', 1, now(), null, null, null, 20, 'thesissummer$OrderSubsidiy:create', 1, (select ID from SEC_ROLE where NAME = 'Administrators'))^
insert into SEC_PERMISSION (ID, CREATE_TS, CREATED_BY, VERSION, UPDATE_TS, UPDATED_BY, DELETE_TS, DELETED_BY, PERMISSION_TYPE, TARGET, VALUE_, ROLE_ID)
values (newid(), now(), 'system', 1, now(), null, null, null, 20, 'thesissummer$OrderSubsidiy:update', 1, (select ID from SEC_ROLE where NAME = 'Administrators'))^
insert into SEC_PERMISSION (ID, CREATE_TS, CREATED_BY, VERSION, UPDATE_TS, UPDATED_BY, DELETE_TS, DELETED_BY, PERMISSION_TYPE, TARGET, VALUE_, ROLE_ID)
values (newid(), now(), 'system', 1, now(), null, null, null, 20, 'thesissummer$OrderSubsidiy:delete', 1, (select ID from SEC_ROLE where NAME = 'Administrators'))^
insert into SEC_PERMISSION (ID, CREATE_TS, CREATED_BY, VERSION, UPDATE_TS, UPDATED_BY, DELETE_TS, DELETED_BY, PERMISSION_TYPE, TARGET, VALUE_, ROLE_ID)
values (newid(), now(), 'system', 1, now(), null, null, null, 20, 'thesissummer$OrderSubsidiy:create', 1, (select ID from SEC_ROLE where NAME = 'doc_initiator'))^
insert into SEC_PERMISSION (ID, CREATE_TS, CREATED_BY, VERSION, UPDATE_TS, UPDATED_BY, DELETE_TS, DELETED_BY, PERMISSION_TYPE, TARGET, VALUE_, ROLE_ID)
values (newid(), now(), 'system', 1, now(), null, null, null, 20, 'thesissummer$OrderSubsidiy:update', 1, (select ID from SEC_ROLE where NAME = 'doc_initiator'))^
insert into SEC_PERMISSION (ID, CREATE_TS, CREATED_BY, VERSION, UPDATE_TS, UPDATED_BY, DELETE_TS, DELETED_BY, PERMISSION_TYPE, TARGET, VALUE_, ROLE_ID)
values (newid(), now(), 'system', 1, now(), null, null, null, 20, 'thesissummer$OrderSubsidiy:delete', 1, (select ID from SEC_ROLE where NAME = 'doc_initiator'))^
-- end insert secPermissions for OrderSubsidiy
-- begin insert default numerator for ProtocolKK
CREATE OR REPLACE FUNCTION baseInsert()
RETURNS integer
AS $$
DECLARE
    cnt integer = 0;
BEGIN
cnt = (select count(id) from DF_NUMERATOR where CODE = 'ProtocolKKNumerator' and delete_ts is null);
if(cnt = 0) then
    INSERT INTO DF_NUMERATOR (ID, CREATE_TS, CREATED_BY, VERSION, CODE, NUMERATOR_FORMAT, SCRIPT_ENABLED,
                              PERIODICITY, NUMBER_INITIAL_VALUE, LOC_NAME)
    VALUES ('1248ffbe-0034-4bb8-a2e3-807f61f708af', now(), 'system', 1, 'ProtocolKKNumerator', '[number]', FALSE, 'Y', 1,
            '{"captionWithLanguageList":[{"language":"ru","caption":"protocolKK"},{"language":"en","caption":"ProtocolKK"}]}'
    );
end if;

return 0;
END;
$$
LANGUAGE plpgsql;
^

select baseInsert()^
drop function if exists baseInsert()^
-- end insert default numerator for ProtocolKK
-- begin insert cardType for ProtocolKK
insert into TS_CARD_TYPE (ID, CREATE_TS, CREATED_BY, NAME, DISCRIMINATOR, FIELDS_XML)
       values ('7020728c-e89c-45c8-a759-922d3cb857ba', now(), 'system', 'thesissummer$ProtocolKK', '3500', '<?xml version="1.0" encoding="UTF-8"?>
<fields>
  <field name="date" inDocKind="true" required="false" visible="true" signed="false" />
  <field name="docCategory" inDocKind="true" required="false" visible="true" signed="false" />
  <field name="owner" inDocKind="true" required="false" visible="true" signed="false" />
  <field name="department" inDocKind="true" required="false" visible="true" signed="false" />
  <field name="comment" inDocKind="true" required="false" visible="true" signed="false" />
  <field name="finishDatePlan" inDocKind="true" required="false" visible="true" signed="false" />
  <field name="resolution" inDocKind="true" required="false" visible="true" signed="false" />
  <field name="number" inDocKind="true" required="false" visible="true" signed="false" />
  <field name="organization" inDocKind="true" required="false" visible="true" signed="false" />
  <field name="parentCard" inDocKind="true" required="false" visible="true" signed="false" />
  <field name="theme" inDocKind="true" required="false" visible="true" signed="false" />
  <field name="nomerProtocola" inDocKind="true" required="false" visible="true" signed="false" />
  <field name="dataProtocola" inDocKind="true" required="false" visible="true" signed="false" />
</fields>
')^
-- end insert cardType for ProtocolKK
-- begin update procCardType for ProtocolKK
update wf_proc set card_types = regexp_replace(card_types, ',thesissummer\\$ProtocolKK', '') where code in ('Endorsement', 'Resolution', 'Acquaintance', 'Registration')^
update wf_proc set updated_by='system', card_types = card_types || 'thesissummer$ProtocolKK,' where code in ('Endorsement', 'Resolution', 'Acquaintance', 'Registration')^
-- end update procCardType for ProtocolKK
-- begin insert docKind for ProtocolKK
CREATE OR REPLACE FUNCTION baseInsert()
RETURNS integer
AS $$
DECLARE
    cnt integer = 0;
BEGIN
    cnt = (select count(CATEGORY_ID) from DF_DOC_KIND where CATEGORY_ID = '768817c5-0f3f-4ae0-97b2-27fc0074a5d1');
    if (cnt = 0) then
        insert into SYS_CATEGORY (ID, NAME, ENTITY_TYPE, IS_DEFAULT, CREATE_TS, CREATED_BY, VERSION, DISCRIMINATOR)
        values ('768817c5-0f3f-4ae0-97b2-27fc0074a5d1', 'protocolKK', 'thesissummer$ProtocolKK', false, now(), 'system', 1, 1);

        insert into DF_DOC_KIND (CATEGORY_ID, CREATE_TS, CREATED_BY, VERSION, DOC_TYPE_ID, NUMERATOR_ID,
                                 NUMERATOR_TYPE, CATEGORY_ATTRS_PLACE, TAB_NAME, PORTAL_PUBLISH_ALLOWED, DISABLE_ADD_PROCESS_ACTORS, CREATE_ONLY_BY_TEMPLATE)
        values ('768817c5-0f3f-4ae0-97b2-27fc0074a5d1', now(), 'system', 1, '7020728c-e89c-45c8-a759-922d3cb857ba', '1248ffbe-0034-4bb8-a2e3-807f61f708af',
                1, 1, 'Доп. поля', false, false, false);
end if;
return 0;
END;
$$
LANGUAGE plpgsql;
^
select baseInsert();^
drop function if exists baseInsert();^
-- end insert docKind for ProtocolKK
-- begin insert secPermissions for ProtocolKK
insert into SEC_PERMISSION (ID, CREATE_TS, CREATED_BY, VERSION, UPDATE_TS, UPDATED_BY, DELETE_TS, DELETED_BY, PERMISSION_TYPE, TARGET, VALUE_, ROLE_ID)
values (newid(), now(), 'system', 1, now(), null, null, null, 20, 'thesissummer$ProtocolKK:create', 0, (select ID from SEC_ROLE where NAME = 'SimpleUser'))^
insert into SEC_PERMISSION (ID, CREATE_TS, CREATED_BY, VERSION, UPDATE_TS, UPDATED_BY, DELETE_TS, DELETED_BY, PERMISSION_TYPE, TARGET, VALUE_, ROLE_ID)
values (newid(), now(), 'system', 1, now(), null, null, null, 20, 'thesissummer$ProtocolKK:delete', 0, (select ID from SEC_ROLE where NAME = 'SimpleUser'))^
insert into SEC_PERMISSION (ID, CREATE_TS, CREATED_BY, VERSION, UPDATE_TS, UPDATED_BY, DELETE_TS, DELETED_BY, PERMISSION_TYPE, TARGET, VALUE_, ROLE_ID)
values (newid(), now(), 'system', 1, now(), null, null, null, 20, 'thesissummer$ProtocolKK:create', 1, (select ID from SEC_ROLE where NAME = 'Administrators'))^
insert into SEC_PERMISSION (ID, CREATE_TS, CREATED_BY, VERSION, UPDATE_TS, UPDATED_BY, DELETE_TS, DELETED_BY, PERMISSION_TYPE, TARGET, VALUE_, ROLE_ID)
values (newid(), now(), 'system', 1, now(), null, null, null, 20, 'thesissummer$ProtocolKK:update', 1, (select ID from SEC_ROLE where NAME = 'Administrators'))^
insert into SEC_PERMISSION (ID, CREATE_TS, CREATED_BY, VERSION, UPDATE_TS, UPDATED_BY, DELETE_TS, DELETED_BY, PERMISSION_TYPE, TARGET, VALUE_, ROLE_ID)
values (newid(), now(), 'system', 1, now(), null, null, null, 20, 'thesissummer$ProtocolKK:delete', 1, (select ID from SEC_ROLE where NAME = 'Administrators'))^
insert into SEC_PERMISSION (ID, CREATE_TS, CREATED_BY, VERSION, UPDATE_TS, UPDATED_BY, DELETE_TS, DELETED_BY, PERMISSION_TYPE, TARGET, VALUE_, ROLE_ID)
values (newid(), now(), 'system', 1, now(), null, null, null, 20, 'thesissummer$ProtocolKK:create', 1, (select ID from SEC_ROLE where NAME = 'doc_initiator'))^
insert into SEC_PERMISSION (ID, CREATE_TS, CREATED_BY, VERSION, UPDATE_TS, UPDATED_BY, DELETE_TS, DELETED_BY, PERMISSION_TYPE, TARGET, VALUE_, ROLE_ID)
values (newid(), now(), 'system', 1, now(), null, null, null, 20, 'thesissummer$ProtocolKK:update', 1, (select ID from SEC_ROLE where NAME = 'doc_initiator'))^
insert into SEC_PERMISSION (ID, CREATE_TS, CREATED_BY, VERSION, UPDATE_TS, UPDATED_BY, DELETE_TS, DELETED_BY, PERMISSION_TYPE, TARGET, VALUE_, ROLE_ID)
values (newid(), now(), 'system', 1, now(), null, null, null, 20, 'thesissummer$ProtocolKK:delete', 1, (select ID from SEC_ROLE where NAME = 'doc_initiator'))^
-- end insert secPermissions for ProtocolKK
-- begin insert default numerator for ProtocolPrav
CREATE OR REPLACE FUNCTION baseInsert()
RETURNS integer
AS $$
DECLARE
    cnt integer = 0;
BEGIN
cnt = (select count(id) from DF_NUMERATOR where CODE = 'ProtocolPravNumerator' and delete_ts is null);
if(cnt = 0) then
    INSERT INTO DF_NUMERATOR (ID, CREATE_TS, CREATED_BY, VERSION, CODE, NUMERATOR_FORMAT, SCRIPT_ENABLED,
                              PERIODICITY, NUMBER_INITIAL_VALUE, LOC_NAME)
    VALUES ('c981b413-6107-49f7-85fe-1fee10beac2d', now(), 'system', 1, 'ProtocolPravNumerator', '[number]', FALSE, 'Y', 1,
            '{"captionWithLanguageList":[{"language":"ru","caption":"Протокол Правления"},{"language":"en","caption":"protocolPrav"}]}'
    );
end if;

return 0;
END;
$$
LANGUAGE plpgsql;
^

select baseInsert()^
drop function if exists baseInsert()^
-- end insert default numerator for ProtocolPrav
-- begin insert cardType for ProtocolPrav
insert into TS_CARD_TYPE (ID, CREATE_TS, CREATED_BY, NAME, DISCRIMINATOR, FIELDS_XML)
       values ('f5eb864e-bdfb-43aa-a35d-6cd9d52c2e66', now(), 'system', 'thesissummer$ProtocolPrav', '3600', '<?xml version="1.0" encoding="UTF-8"?>
<fields>
  <field name="date" inDocKind="true" required="false" visible="true" signed="false" />
  <field name="docCategory" inDocKind="true" required="false" visible="true" signed="false" />
  <field name="owner" inDocKind="true" required="false" visible="true" signed="false" />
  <field name="department" inDocKind="true" required="false" visible="true" signed="false" />
  <field name="comment" inDocKind="true" required="false" visible="true" signed="false" />
  <field name="finishDatePlan" inDocKind="true" required="false" visible="true" signed="false" />
  <field name="resolution" inDocKind="true" required="false" visible="true" signed="false" />
  <field name="number" inDocKind="true" required="false" visible="true" signed="false" />
  <field name="organization" inDocKind="true" required="false" visible="true" signed="false" />
  <field name="parentCard" inDocKind="true" required="false" visible="true" signed="false" />
  <field name="theme" inDocKind="true" required="false" visible="true" signed="false" />
  <field name="addressees" inDocKind="false" required="false" visible="false" signed="false" />
  <field name="officeExecutor" inDocKind="false" required="false" visible="false" signed="false" />
  <field name="employeeExecutor" inDocKind="false" required="false" visible="false" signed="false" />
  <field name="officeSignedBy" inDocKind="false" required="false" visible="false" signed="false" />
  <field name="receivingMethod" inDocKind="false" required="false" visible="false" signed="false" />
  <field name="responseToDoc" inDocKind="false" required="false" visible="false" signed="false" />
  <field name="sender" inDocKind="false" required="false" visible="false" signed="false" />
  <field name="postTrackingNumber" inDocKind="true" required="false" visible="true" signed="false" />
  <field name="nomerProtocola" inDocKind="true" required="false" visible="true" signed="false" />
  <field name="dataProtocola" inDocKind="true" required="false" visible="true" signed="false" />
</fields>
')^
-- end insert cardType for ProtocolPrav
-- begin update procCardType for ProtocolPrav
update wf_proc set card_types = regexp_replace(card_types, ',thesissummer\\$ProtocolPrav', '') where code in ('Endorsement', 'Resolution', 'Acquaintance', 'Registration')^
update wf_proc set updated_by='system', card_types = card_types || 'thesissummer$ProtocolPrav,' where code in ('Endorsement', 'Resolution', 'Acquaintance', 'Registration')^
-- end update procCardType for ProtocolPrav
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
-- begin insert secPermissions for ProtocolPrav
insert into SEC_PERMISSION (ID, CREATE_TS, CREATED_BY, VERSION, UPDATE_TS, UPDATED_BY, DELETE_TS, DELETED_BY, PERMISSION_TYPE, TARGET, VALUE_, ROLE_ID)
values (newid(), now(), 'system', 1, now(), null, null, null, 20, 'thesissummer$ProtocolPrav:create', 0, (select ID from SEC_ROLE where NAME = 'SimpleUser'))^
insert into SEC_PERMISSION (ID, CREATE_TS, CREATED_BY, VERSION, UPDATE_TS, UPDATED_BY, DELETE_TS, DELETED_BY, PERMISSION_TYPE, TARGET, VALUE_, ROLE_ID)
values (newid(), now(), 'system', 1, now(), null, null, null, 20, 'thesissummer$ProtocolPrav:delete', 0, (select ID from SEC_ROLE where NAME = 'SimpleUser'))^
insert into SEC_PERMISSION (ID, CREATE_TS, CREATED_BY, VERSION, UPDATE_TS, UPDATED_BY, DELETE_TS, DELETED_BY, PERMISSION_TYPE, TARGET, VALUE_, ROLE_ID)
values (newid(), now(), 'system', 1, now(), null, null, null, 20, 'thesissummer$ProtocolPrav:create', 1, (select ID from SEC_ROLE where NAME = 'Administrators'))^
insert into SEC_PERMISSION (ID, CREATE_TS, CREATED_BY, VERSION, UPDATE_TS, UPDATED_BY, DELETE_TS, DELETED_BY, PERMISSION_TYPE, TARGET, VALUE_, ROLE_ID)
values (newid(), now(), 'system', 1, now(), null, null, null, 20, 'thesissummer$ProtocolPrav:update', 1, (select ID from SEC_ROLE where NAME = 'Administrators'))^
insert into SEC_PERMISSION (ID, CREATE_TS, CREATED_BY, VERSION, UPDATE_TS, UPDATED_BY, DELETE_TS, DELETED_BY, PERMISSION_TYPE, TARGET, VALUE_, ROLE_ID)
values (newid(), now(), 'system', 1, now(), null, null, null, 20, 'thesissummer$ProtocolPrav:delete', 1, (select ID from SEC_ROLE where NAME = 'Administrators'))^
insert into SEC_PERMISSION (ID, CREATE_TS, CREATED_BY, VERSION, UPDATE_TS, UPDATED_BY, DELETE_TS, DELETED_BY, PERMISSION_TYPE, TARGET, VALUE_, ROLE_ID)
values (newid(), now(), 'system', 1, now(), null, null, null, 20, 'thesissummer$ProtocolPrav:create', 1, (select ID from SEC_ROLE where NAME = 'doc_initiator'))^
insert into SEC_PERMISSION (ID, CREATE_TS, CREATED_BY, VERSION, UPDATE_TS, UPDATED_BY, DELETE_TS, DELETED_BY, PERMISSION_TYPE, TARGET, VALUE_, ROLE_ID)
values (newid(), now(), 'system', 1, now(), null, null, null, 20, 'thesissummer$ProtocolPrav:update', 1, (select ID from SEC_ROLE where NAME = 'doc_initiator'))^
insert into SEC_PERMISSION (ID, CREATE_TS, CREATED_BY, VERSION, UPDATE_TS, UPDATED_BY, DELETE_TS, DELETED_BY, PERMISSION_TYPE, TARGET, VALUE_, ROLE_ID)
values (newid(), now(), 'system', 1, now(), null, null, null, 20, 'thesissummer$ProtocolPrav:delete', 1, (select ID from SEC_ROLE where NAME = 'doc_initiator'))^
-- end insert secPermissions for ProtocolPrav
-- begin insert default numerator for SpravkaPoZadol
CREATE OR REPLACE FUNCTION baseInsert()
RETURNS integer
AS $$
DECLARE
    cnt integer = 0;
BEGIN
cnt = (select count(id) from DF_NUMERATOR where CODE = 'SpravkaPoZadolNumerator' and delete_ts is null);
if(cnt = 0) then
    INSERT INTO DF_NUMERATOR (ID, CREATE_TS, CREATED_BY, VERSION, CODE, NUMERATOR_FORMAT, SCRIPT_ENABLED,
                              PERIODICITY, NUMBER_INITIAL_VALUE, LOC_NAME)
    VALUES ('e537e92a-c45b-405b-a5fe-d9b9187846a5', now(), 'system', 1, 'SpravkaPoZadolNumerator', '[number]', FALSE, 'Y', 1,
            '{"captionWithLanguageList":[{"language":"ru","caption":"Справка по задолженности"},{"language":"en","caption":"SpravkaPoZadol"}]}'
    );
end if;

return 0;
END;
$$
LANGUAGE plpgsql;
^

select baseInsert()^
drop function if exists baseInsert()^
-- end insert default numerator for SpravkaPoZadol
-- begin insert cardType for SpravkaPoZadol
insert into TS_CARD_TYPE (ID, CREATE_TS, CREATED_BY, NAME, DISCRIMINATOR, FIELDS_XML)
       values ('2431cbc2-bbd2-4cda-9971-ef4d09577292', now(), 'system', 'thesissummer$SpravkaPoZadol', '3800', '<?xml version="1.0" encoding="UTF-8"?>
<fields>
  <field name="date" inDocKind="true" required="false" visible="true" signed="false" />
  <field name="docCategory" inDocKind="true" required="false" visible="true" signed="false" />
  <field name="owner" inDocKind="true" required="false" visible="true" signed="false" />
  <field name="department" inDocKind="true" required="false" visible="true" signed="false" />
  <field name="comment" inDocKind="true" required="false" visible="true" signed="false" />
  <field name="finishDatePlan" inDocKind="true" required="false" visible="true" signed="false" />
  <field name="resolution" inDocKind="true" required="false" visible="true" signed="false" />
  <field name="number" inDocKind="true" required="false" visible="true" signed="false" />
  <field name="organization" inDocKind="true" required="false" visible="true" signed="false" />
  <field name="parentCard" inDocKind="true" required="false" visible="true" signed="false" />
  <field name="theme" inDocKind="true" required="false" visible="true" signed="false" />
  <field name="addressees" inDocKind="false" required="false" visible="false" signed="false" />
  <field name="officeExecutor" inDocKind="false" required="false" visible="false" signed="false" />
  <field name="employeeExecutor" inDocKind="false" required="false" visible="false" signed="false" />
  <field name="officeSignedBy" inDocKind="false" required="false" visible="false" signed="false" />
  <field name="receivingMethod" inDocKind="false" required="false" visible="false" signed="false" />
  <field name="responseToDoc" inDocKind="false" required="false" visible="false" signed="false" />
  <field name="sender" inDocKind="false" required="false" visible="false" signed="false" />
  <field name="postTrackingNumber" inDocKind="true" required="false" visible="true" signed="false" />
  <field name="nomerOrder" inDocKind="true" required="false" visible="true" signed="false" />
  <field name="dataOrder" inDocKind="true" required="false" visible="true" signed="false" />
</fields>
')^
-- end insert cardType for SpravkaPoZadol
-- begin update procCardType for SpravkaPoZadol
update wf_proc set card_types = regexp_replace(card_types, ',thesissummer\\$SpravkaPoZadol', '') where code in ('Endorsement', 'Resolution', 'Acquaintance', 'Registration')^
update wf_proc set updated_by='system', card_types = card_types || 'thesissummer$SpravkaPoZadol,' where code in ('Endorsement', 'Resolution', 'Acquaintance', 'Registration')^
-- end update procCardType for SpravkaPoZadol
-- begin insert docKind for SpravkaPoZadol
CREATE OR REPLACE FUNCTION baseInsert()
RETURNS integer
AS $$
DECLARE
    cnt integer = 0;
BEGIN
    cnt = (select count(CATEGORY_ID) from DF_DOC_KIND where CATEGORY_ID = 'b437e348-9011-4769-9447-10388950d05b');
    if (cnt = 0) then
        insert into SYS_CATEGORY (ID, NAME, ENTITY_TYPE, IS_DEFAULT, CREATE_TS, CREATED_BY, VERSION, DISCRIMINATOR)
        values ('b437e348-9011-4769-9447-10388950d05b', 'Справка по задолженности', 'thesissummer$SpravkaPoZadol', false, now(), 'system', 1, 1);

        insert into DF_DOC_KIND (CATEGORY_ID, CREATE_TS, CREATED_BY, VERSION, DOC_TYPE_ID, NUMERATOR_ID,
                                 NUMERATOR_TYPE, CATEGORY_ATTRS_PLACE, TAB_NAME, PORTAL_PUBLISH_ALLOWED, DISABLE_ADD_PROCESS_ACTORS, CREATE_ONLY_BY_TEMPLATE)
        values ('b437e348-9011-4769-9447-10388950d05b', now(), 'system', 1, '2431cbc2-bbd2-4cda-9971-ef4d09577292', 'e537e92a-c45b-405b-a5fe-d9b9187846a5',
                1, 1, 'Доп. поля', false, false, false);
end if;
return 0;
END;
$$
LANGUAGE plpgsql;
^
select baseInsert();^
drop function if exists baseInsert();^
-- end insert docKind for SpravkaPoZadol
-- begin insert secPermissions for SpravkaPoZadol
insert into SEC_PERMISSION (ID, CREATE_TS, CREATED_BY, VERSION, UPDATE_TS, UPDATED_BY, DELETE_TS, DELETED_BY, PERMISSION_TYPE, TARGET, VALUE_, ROLE_ID)
values (newid(), now(), 'system', 1, now(), null, null, null, 20, 'thesissummer$SpravkaPoZadol:create', 0, (select ID from SEC_ROLE where NAME = 'SimpleUser'))^
insert into SEC_PERMISSION (ID, CREATE_TS, CREATED_BY, VERSION, UPDATE_TS, UPDATED_BY, DELETE_TS, DELETED_BY, PERMISSION_TYPE, TARGET, VALUE_, ROLE_ID)
values (newid(), now(), 'system', 1, now(), null, null, null, 20, 'thesissummer$SpravkaPoZadol:delete', 0, (select ID from SEC_ROLE where NAME = 'SimpleUser'))^
insert into SEC_PERMISSION (ID, CREATE_TS, CREATED_BY, VERSION, UPDATE_TS, UPDATED_BY, DELETE_TS, DELETED_BY, PERMISSION_TYPE, TARGET, VALUE_, ROLE_ID)
values (newid(), now(), 'system', 1, now(), null, null, null, 20, 'thesissummer$SpravkaPoZadol:create', 1, (select ID from SEC_ROLE where NAME = 'Administrators'))^
insert into SEC_PERMISSION (ID, CREATE_TS, CREATED_BY, VERSION, UPDATE_TS, UPDATED_BY, DELETE_TS, DELETED_BY, PERMISSION_TYPE, TARGET, VALUE_, ROLE_ID)
values (newid(), now(), 'system', 1, now(), null, null, null, 20, 'thesissummer$SpravkaPoZadol:update', 1, (select ID from SEC_ROLE where NAME = 'Administrators'))^
insert into SEC_PERMISSION (ID, CREATE_TS, CREATED_BY, VERSION, UPDATE_TS, UPDATED_BY, DELETE_TS, DELETED_BY, PERMISSION_TYPE, TARGET, VALUE_, ROLE_ID)
values (newid(), now(), 'system', 1, now(), null, null, null, 20, 'thesissummer$SpravkaPoZadol:delete', 1, (select ID from SEC_ROLE where NAME = 'Administrators'))^
insert into SEC_PERMISSION (ID, CREATE_TS, CREATED_BY, VERSION, UPDATE_TS, UPDATED_BY, DELETE_TS, DELETED_BY, PERMISSION_TYPE, TARGET, VALUE_, ROLE_ID)
values (newid(), now(), 'system', 1, now(), null, null, null, 20, 'thesissummer$SpravkaPoZadol:create', 1, (select ID from SEC_ROLE where NAME = 'doc_initiator'))^
insert into SEC_PERMISSION (ID, CREATE_TS, CREATED_BY, VERSION, UPDATE_TS, UPDATED_BY, DELETE_TS, DELETED_BY, PERMISSION_TYPE, TARGET, VALUE_, ROLE_ID)
values (newid(), now(), 'system', 1, now(), null, null, null, 20, 'thesissummer$SpravkaPoZadol:update', 1, (select ID from SEC_ROLE where NAME = 'doc_initiator'))^
insert into SEC_PERMISSION (ID, CREATE_TS, CREATED_BY, VERSION, UPDATE_TS, UPDATED_BY, DELETE_TS, DELETED_BY, PERMISSION_TYPE, TARGET, VALUE_, ROLE_ID)
values (newid(), now(), 'system', 1, now(), null, null, null, 20, 'thesissummer$SpravkaPoZadol:delete', 1, (select ID from SEC_ROLE where NAME = 'doc_initiator'))^
-- end insert secPermissions for SpravkaPoZadol
-- begin insert default numerator for ZadanieNaPlatezh
CREATE OR REPLACE FUNCTION baseInsert()
RETURNS integer
AS $$
DECLARE
    cnt integer = 0;
BEGIN
cnt = (select count(id) from DF_NUMERATOR where CODE = 'ZadanieNaPlatezhNumerator' and delete_ts is null);
if(cnt = 0) then
    INSERT INTO DF_NUMERATOR (ID, CREATE_TS, CREATED_BY, VERSION, CODE, NUMERATOR_FORMAT, SCRIPT_ENABLED,
                              PERIODICITY, NUMBER_INITIAL_VALUE, LOC_NAME)
    VALUES ('224f2605-e7f2-491a-9abd-2830095e06b3', now(), 'system', 1, 'ZadanieNaPlatezhNumerator', '[number]', FALSE, 'Y', 1,
            '{"captionWithLanguageList":[{"language":"ru","caption":"Задание на платеж"},{"language":"en","caption":"ZadanieNaPlatezh"}]}'
    );
end if;

return 0;
END;
$$
LANGUAGE plpgsql;
^

select baseInsert()^
drop function if exists baseInsert()^
-- end insert default numerator for ZadanieNaPlatezh
-- begin insert cardType for ZadanieNaPlatezh
insert into TS_CARD_TYPE (ID, CREATE_TS, CREATED_BY, NAME, DISCRIMINATOR, FIELDS_XML)
       values ('b5934f83-ea3a-4137-accb-4bdc2976995a', now(), 'system', 'thesissummer$ZadanieNaPlatezh', '3700', '<?xml version="1.0" encoding="UTF-8"?>
<fields>
  <field name="date" inDocKind="true" required="false" visible="true" signed="false" />
  <field name="docCategory" inDocKind="true" required="false" visible="true" signed="false" />
  <field name="owner" inDocKind="true" required="false" visible="true" signed="false" />
  <field name="department" inDocKind="true" required="false" visible="true" signed="false" />
  <field name="comment" inDocKind="true" required="false" visible="true" signed="false" />
  <field name="finishDatePlan" inDocKind="true" required="false" visible="true" signed="false" />
  <field name="resolution" inDocKind="true" required="false" visible="true" signed="false" />
  <field name="number" inDocKind="true" required="false" visible="true" signed="false" />
  <field name="organization" inDocKind="true" required="false" visible="true" signed="false" />
  <field name="parentCard" inDocKind="true" required="false" visible="true" signed="false" />
  <field name="theme" inDocKind="true" required="false" visible="true" signed="false" />
  <field name="addressees" inDocKind="false" required="false" visible="false" signed="false" />
  <field name="officeExecutor" inDocKind="false" required="false" visible="false" signed="false" />
  <field name="employeeExecutor" inDocKind="false" required="false" visible="false" signed="false" />
  <field name="officeSignedBy" inDocKind="false" required="false" visible="false" signed="false" />
  <field name="receivingMethod" inDocKind="false" required="false" visible="false" signed="false" />
  <field name="responseToDoc" inDocKind="false" required="false" visible="false" signed="false" />
  <field name="sender" inDocKind="false" required="false" visible="false" signed="false" />
  <field name="postTrackingNumber" inDocKind="true" required="false" visible="true" signed="false" />
  <field name="naimenovaniePol" inDocKind="true" required="false" visible="true" signed="false" />
  <field name="summaTenge" inDocKind="true" required="false" visible="true" signed="false" />
  <field name="summaDollar" inDocKind="true" required="false" visible="true" signed="false" />
  <field name="nomerAndDataDogovora" inDocKind="true" required="false" visible="true" signed="false" />
  <field name="schetNaOplatu" inDocKind="true" required="false" visible="true" signed="false" />
  <field name="nomerStati" inDocKind="true" required="false" visible="true" signed="false" />
</fields>
')^
-- end insert cardType for ZadanieNaPlatezh
-- begin update procCardType for ZadanieNaPlatezh
update wf_proc set card_types = regexp_replace(card_types, ',thesissummer\\$ZadanieNaPlatezh', '') where code in ('Endorsement', 'Resolution', 'Acquaintance', 'Registration')^
update wf_proc set updated_by='system', card_types = card_types || 'thesissummer$ZadanieNaPlatezh,' where code in ('Endorsement', 'Resolution', 'Acquaintance', 'Registration')^
-- end update procCardType for ZadanieNaPlatezh
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
-- begin insert secPermissions for ZadanieNaPlatezh
insert into SEC_PERMISSION (ID, CREATE_TS, CREATED_BY, VERSION, UPDATE_TS, UPDATED_BY, DELETE_TS, DELETED_BY, PERMISSION_TYPE, TARGET, VALUE_, ROLE_ID)
values (newid(), now(), 'system', 1, now(), null, null, null, 20, 'thesissummer$ZadanieNaPlatezh:create', 0, (select ID from SEC_ROLE where NAME = 'SimpleUser'))^
insert into SEC_PERMISSION (ID, CREATE_TS, CREATED_BY, VERSION, UPDATE_TS, UPDATED_BY, DELETE_TS, DELETED_BY, PERMISSION_TYPE, TARGET, VALUE_, ROLE_ID)
values (newid(), now(), 'system', 1, now(), null, null, null, 20, 'thesissummer$ZadanieNaPlatezh:delete', 0, (select ID from SEC_ROLE where NAME = 'SimpleUser'))^
insert into SEC_PERMISSION (ID, CREATE_TS, CREATED_BY, VERSION, UPDATE_TS, UPDATED_BY, DELETE_TS, DELETED_BY, PERMISSION_TYPE, TARGET, VALUE_, ROLE_ID)
values (newid(), now(), 'system', 1, now(), null, null, null, 20, 'thesissummer$ZadanieNaPlatezh:create', 1, (select ID from SEC_ROLE where NAME = 'Administrators'))^
insert into SEC_PERMISSION (ID, CREATE_TS, CREATED_BY, VERSION, UPDATE_TS, UPDATED_BY, DELETE_TS, DELETED_BY, PERMISSION_TYPE, TARGET, VALUE_, ROLE_ID)
values (newid(), now(), 'system', 1, now(), null, null, null, 20, 'thesissummer$ZadanieNaPlatezh:update', 1, (select ID from SEC_ROLE where NAME = 'Administrators'))^
insert into SEC_PERMISSION (ID, CREATE_TS, CREATED_BY, VERSION, UPDATE_TS, UPDATED_BY, DELETE_TS, DELETED_BY, PERMISSION_TYPE, TARGET, VALUE_, ROLE_ID)
values (newid(), now(), 'system', 1, now(), null, null, null, 20, 'thesissummer$ZadanieNaPlatezh:delete', 1, (select ID from SEC_ROLE where NAME = 'Administrators'))^
insert into SEC_PERMISSION (ID, CREATE_TS, CREATED_BY, VERSION, UPDATE_TS, UPDATED_BY, DELETE_TS, DELETED_BY, PERMISSION_TYPE, TARGET, VALUE_, ROLE_ID)
values (newid(), now(), 'system', 1, now(), null, null, null, 20, 'thesissummer$ZadanieNaPlatezh:create', 1, (select ID from SEC_ROLE where NAME = 'doc_initiator'))^
insert into SEC_PERMISSION (ID, CREATE_TS, CREATED_BY, VERSION, UPDATE_TS, UPDATED_BY, DELETE_TS, DELETED_BY, PERMISSION_TYPE, TARGET, VALUE_, ROLE_ID)
values (newid(), now(), 'system', 1, now(), null, null, null, 20, 'thesissummer$ZadanieNaPlatezh:update', 1, (select ID from SEC_ROLE where NAME = 'doc_initiator'))^
insert into SEC_PERMISSION (ID, CREATE_TS, CREATED_BY, VERSION, UPDATE_TS, UPDATED_BY, DELETE_TS, DELETED_BY, PERMISSION_TYPE, TARGET, VALUE_, ROLE_ID)
values (newid(), now(), 'system', 1, now(), null, null, null, 20, 'thesissummer$ZadanieNaPlatezh:delete', 1, (select ID from SEC_ROLE where NAME = 'doc_initiator'))^
-- end insert secPermissions for ZadanieNaPlatezh
