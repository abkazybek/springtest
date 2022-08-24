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
