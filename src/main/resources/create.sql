DROP SCHEMA auth;
CREATE SCHEMA auth;
CREATE TABLE auth.usuarios (
	id SERIAL,
	login VARCHAR (256) NOT NULL,
	password VARCHAR (256) NOT NULL,
	PRIMARY KEY (id)
)
CREATE TABLE auth.grupos (
	id SERIAL,
	grupo VARCHAR(32) NOT NULL UNIQUE,
	PRIMARY KEY (id)
)
CREATE TABLE auth.usuarios_grupos (
	usuario_id INTEGER,
	grupo_id INTEGER,
	PRIMARY KEY (usuario_id, grupo_id),
	FOREIGN KEY (usuario_id) REFERENCES auth.usuarios (id),
	FOREIGN KEY (grupo_id) REFERENCES auth.grupos (id)
)
