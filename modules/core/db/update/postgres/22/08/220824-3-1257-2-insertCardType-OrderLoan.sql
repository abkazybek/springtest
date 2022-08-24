-- begin insert cardType for OrderLoan
insert into TS_CARD_TYPE (ID, CREATE_TS, CREATED_BY, NAME, DISCRIMINATOR, FIELDS_XML)
       values ('19c9ab68-9080-4264-bca7-118bc987ca08', now(), 'system', 'thesissummer$OrderLoan', '2000', '<?xml version="1.0" encoding="UTF-8"?>
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
  <field name="nomerOrder" inDocKind="true" required="false" visible="true" signed="false" />
  <field name="dataOrder" inDocKind="true" required="false" visible="true" signed="false" />
  <field name="podrazdelenie2" inDocKind="true" required="false" visible="true" signed="false" />
  <field name="nomerProtocolaKK" inDocKind="true" required="false" visible="true" signed="false" />
  <field name="contragent" inDocKind="true" required="false" visible="true" signed="false" />
  <field name="fullName" inDocKind="true" required="false" visible="true" signed="false" />
  <field name="dogovor" inDocKind="true" required="false" visible="true" signed="false" />
  <field name="summa" inDocKind="true" required="false" visible="true" signed="false" />
  <field name="kreditLiniya" inDocKind="true" required="false" visible="true" signed="false" />
  <field name="istochnik" inDocKind="true" required="false" visible="true" signed="false" />
  <field name="stavkaVoz" inDocKind="true" required="false" visible="true" signed="false" />
  <field name="srokKredita" inDocKind="true" required="false" visible="true" signed="false" />
  <field name="bank" inDocKind="true" required="false" visible="true" signed="false" />
  <field name="bik" inDocKind="true" required="false" visible="true" signed="false" />
  <field name="iik" inDocKind="true" required="false" visible="true" signed="false" />
  <field name="bin" inDocKind="true" required="false" visible="true" signed="false" />
  <field name="kbe" inDocKind="true" required="false" visible="true" signed="false" />
  <field name="urAdress" inDocKind="true" required="false" visible="true" signed="false" />
</fields>
')^
-- end insert cardType for OrderLoan
