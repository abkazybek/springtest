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
