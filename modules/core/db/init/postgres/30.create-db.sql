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
