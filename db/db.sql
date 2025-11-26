CREATE DATABASE portal_apoio_staging WITH OWNER portal_apoio_staging;

CREATE TABLE migrations (
    id SERIAL,
    name VARCHAR(100) UNIQUE,
    data_atualizacao TIMESTAMP NOT NULL DEFAULT NOW(),
    PRIMARY KEY (id)
);
