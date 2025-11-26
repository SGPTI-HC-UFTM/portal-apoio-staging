CREATE SCHEMA apoio;
CREATE TABLE apoio.avisos (
    id SERIAL,
    titulo VARCHAR(255),
    informacao VARCHAR(255),
    data_inicial TIMESTAMP,
    data_final TIMESTAMP,
    usuario VARCHAR(255),
    PRIMARY KEY (id)
);
