-- begin insert cardType for OrderVozvratvGu
insert into TS_CARD_TYPE (ID, CREATE_TS, CREATED_BY, NAME, DISCRIMINATOR, FIELDS_XML)
       values ('67ffcb48-932e-4abe-bccc-502566cc5b11', now(), 'system', 'thesissummer$OrderVozvratvGu', '2400', '<?xml version="1.0" encoding="UTF-8"?>
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
  <field name="dogovorPoruch" inDocKind="true" required="false" visible="true" signed="false" />
  <field name="onsovnoiDolg" inDocKind="true" required="false" visible="true" signed="false" />
  <field name="voznagrazhdenie" inDocKind="true" required="false" visible="true" signed="false" />
  <field name="penya" inDocKind="true" required="false" visible="true" signed="false" />
  <field name="ugdRayon" inDocKind="true" required="false" visible="true" signed="false" />
  <field name="dgdOblast" inDocKind="true" required="false" visible="true" signed="false" />
  <field name="bin" inDocKind="true" required="false" visible="true" signed="false" />
  <field name="iik" inDocKind="true" required="false" visible="true" signed="false" />
  <field name="bik" inDocKind="true" required="false" visible="true" signed="false" />
  <field name="bik2" inDocKind="true" required="false" visible="true" signed="false" />
  <field name="knp" inDocKind="true" required="false" visible="true" signed="false" />
  <field name="kod" inDocKind="true" required="false" visible="true" signed="false" />
</fields>
')^
-- end insert cardType for OrderVozvratvGu
