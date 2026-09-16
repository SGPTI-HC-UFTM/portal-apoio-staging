CREATE TABLE pesquisa.projetos_pesquisadores (
    id SERIAL,
    projeto_id INTEGER NOT NULL,
    pesquisador_id INTEGER NOT NULL,
    funcao VARCHAR (100) NOT NULL,
    PRIMARY KEY (id),
    FOREIGN KEY (projeto_id) REFERENCES pesquisa.projetos (id),
    FOREIGN KEY (pesquisador_id) REFERENCES pesquisa.pesquisadores (id)
);
