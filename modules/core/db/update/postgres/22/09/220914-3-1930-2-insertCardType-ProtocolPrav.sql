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
