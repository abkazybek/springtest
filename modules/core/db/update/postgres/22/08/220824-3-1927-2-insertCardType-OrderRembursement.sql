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
