-- begin update discriminator for ExtDepartment
update DF_CORRESPONDENT set TYPE = 'A' where TYPE = 'D' ^
-- end update discriminator for ExtDepartment
