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
