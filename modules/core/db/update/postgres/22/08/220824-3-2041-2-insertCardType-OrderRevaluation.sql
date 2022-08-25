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
