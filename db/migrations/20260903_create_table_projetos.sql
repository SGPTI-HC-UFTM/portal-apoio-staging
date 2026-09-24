CREATE TABLE pesquisa.tipos_projeto (
    id SERIAL,
    descricao VARCHAR (200) NOT NULL UNIQUE,
    PRIMARY KEY (id)
);

INSERT INTO pesquisa.tipos_projeto (descricao) VALUES
('Pesquisa'), ('Extensão'), ('Inovação Tecnológica');

CREATE TABLE pesquisa.projetos (
    id SERIAL,
    titulo VARCHAR (200) NOT NULL,
    financiamento BOOLEAN,
    tipo_projeto_id INTEGER,
    status_projeto_id INTEGER,
    fonte_financiadora_id INTEGER,
    PRIMARY KEY (id),
    FOREIGN KEY (tipo_projeto_id) REFERENCES pesquisa.tipos_projeto (id),
    FOREIGN KEY (status_projeto_id) REFERENCES pesquisa.status_projeto (id),
    FOREIGN KEY (fonte_financiadora_id) REFERENCES pesquisa.fontes_financiadoras (id)
);
