CREATE EXTENSION IF NOT EXISTS "pgcrypto";

CREATE TABLE publisher (
       id_publisher UUID PRIMARY KEY DEFAULT gen_random_uuid(),
       name VARCHAR(150) NOT NULL,
       foundation_date VARCHAR(10),
       description TEXT,
       email VARCHAR(150),
       country_headquarter VARCHAR(100) DEFAULT 'BRASIL'
);
