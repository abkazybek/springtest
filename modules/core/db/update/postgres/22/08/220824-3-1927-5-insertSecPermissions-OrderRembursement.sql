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
