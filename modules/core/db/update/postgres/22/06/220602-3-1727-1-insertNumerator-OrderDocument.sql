-- begin insert default numerator for OrderDocument
CREATE OR REPLACE FUNCTION baseInsert()
RETURNS integer
AS $$
DECLARE
    cnt integer = 0;
BEGIN
cnt = (select count(id) from DF_NUMERATOR where CODE = 'OrderDocumentNumerator' and delete_ts is null);
if(cnt = 0) then
    INSERT INTO DF_NUMERATOR (ID, CREATE_TS, CREATED_BY, VERSION, CODE, NUMERATOR_FORMAT, SCRIPT_ENABLED,
                              PERIODICITY, NUMBER_INITIAL_VALUE, LOC_NAME)
    VALUES ('58ec8f9e-c4af-4805-a960-c210de2927fe', now(), 'system', 1, 'OrderDocumentNumerator', '[number]', FALSE, 'Y', 1,
            '{"captionWithLanguageList":[{"language":"ru","caption":"Распоряжение"},{"language":"en","caption":"Order Document"}]}'
    );
end if;

return 0;
END;
$$
LANGUAGE plpgsql;
^

select baseInsert()^
drop function if exists baseInsert()^
-- end insert default numerator for OrderDocument
