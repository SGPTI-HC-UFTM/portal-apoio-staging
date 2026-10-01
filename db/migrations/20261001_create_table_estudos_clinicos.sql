CREATE TABLE pesquisa.status_estudo_clinico (
    id SERIAL,
    descricao VARCHAR (100) NOT NULL,
    PRIMARY KEY (id)
);

CREATE TABLE pesquisa.estudos_clinicos (
    id SERIAL,
    titulo VARCHAR (300) NOT NULL,
    pesquisador_id INTEGER NOT NULL,
    status_id INTEGER NOT NULL,
    PRIMARY KEY (id),
    FOREIGN KEY (pesquisador_id) REFERENCES pesquisa.pesquisadores (id),
    FOREIGN KEY (status_id) REFERENCES pesquisa.status_estudo_clinico (id)
);
