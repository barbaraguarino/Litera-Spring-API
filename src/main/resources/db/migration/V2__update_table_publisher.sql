-- Adição de novas colunas
ALTER TABLE publisher ADD COLUMN website VARCHAR(200);
ALTER TABLE publisher ADD COLUMN tax_id VARCHAR(20);
ALTER TABLE publisher ADD COLUMN telephone VARCHAR(15);

-- Remoção de colunas
ALTER TABLE publisher DROP COLUMN country_headquarter;

-- Criação dos constraints unique
ALTER TABLE publisher ADD CONSTRAINT unique_taxId UNIQUE (tax_id);
ALTER TABLE publisher ADD CONSTRAINT unique_website UNIQUE (website);
ALTER TABLE publisher ADD CONSTRAINT unique_telephone UNIQUE (telephone);
ALTER TABLE publisher ADD CONSTRAINT unique_email UNIQUE (email);
