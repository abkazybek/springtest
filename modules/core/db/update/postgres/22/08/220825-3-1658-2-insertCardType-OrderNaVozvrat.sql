-- begin insert cardType for OrderNaVozvrat
insert into TS_CARD_TYPE (ID, CREATE_TS, CREATED_BY, NAME, DISCRIMINATOR, FIELDS_XML)
       values ('35b682c7-5b23-46dd-906c-1d9dad6fde57', now(), 'system', 'thesissummer$OrderNaVozvrat', '3200', '<?xml version="1.0" encoding="UTF-8"?>
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
  <field name="ishPismo" inDocKind="true" required="false" visible="true" signed="false" />
  <field name="podrazdelenieAkimata" inDocKind="true" required="false" visible="true" signed="false" />
  <field name="rayon" inDocKind="true" required="false" visible="true" signed="false" />
  <field name="oblast" inDocKind="true" required="false" visible="true" signed="false" />
  <field name="summaDlyaPerech" inDocKind="true" required="false" visible="true" signed="false" />
  <field name="poluchatel" inDocKind="true" required="false" visible="true" signed="false" />
  <field name="bin" inDocKind="true" required="false" visible="true" signed="false" />
  <field name="iik" inDocKind="true" required="false" visible="true" signed="false" />
  <field name="bik" inDocKind="true" required="false" visible="true" signed="false" />
  <field name="knp" inDocKind="true" required="false" visible="true" signed="false" />
  <field name="kod" inDocKind="true" required="false" visible="true" signed="false" />
</fields>
')^
-- end insert cardType for OrderNaVozvrat
