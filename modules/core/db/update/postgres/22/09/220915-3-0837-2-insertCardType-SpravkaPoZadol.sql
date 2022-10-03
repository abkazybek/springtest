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
