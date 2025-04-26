CREATE TABLE author (
    id_author UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    name VARCHAR(50) NOT NULL,
    surname VARCHAR(100) NOT NULL,
    pseudonym VARCHAR(150) NOT NULL,
    email VARCHAR(150) UNIQUE,
    website VARCHAR(200),
    birth_date VARCHAR(10),
    death_date VARCHAR(10),
    description TEXT,
    images_url VARCHAR(255),
    nationality VARCHAR(100) NOT NULL DEFAULT 'BRASILEIRO'
);