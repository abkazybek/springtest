-- begin update discriminator for ExtEmployee
update DF_CORRESPONDENT set TYPE = 'X' where TYPE = 'E' ^
-- end update discriminator for ExtEmployee
