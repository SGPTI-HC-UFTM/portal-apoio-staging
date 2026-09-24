DELETE FROM auth.usuarios_grupos;
DELETE FROM auth.usuarios;
DELETE FROM auth.grupos;
DELETE FROM pesquisa.bolsas_produtividade_cnpq;
DELETE FROM pesquisa.cbos;
DELETE FROM pesquisa.tipos_projeto;
DELETE FROM pesquisa.status_projeto;
DELETE FROM pesquisa.fontes_financiadoras;

INSERT INTO auth.grupos (grupo) VALUES ('ADMIN'), ('PESQUISA_ADMIN');
INSERT INTO auth.usuarios (login, password) VALUES ('admin', 'jGl25bVBBBW96Qi9Te4V37Fnqchz/Eu4qB9vKrRIqRg=');
INSERT INTO auth.usuarios_grupos (usuario_id, grupo_id) SELECT u.id, g.id FROM auth.grupos g CROSS JOIN auth.usuarios u WHERE g.grupo IN ('ADMIN', 'PESQUISA_ADMIN') AND u.login = 'admin';

INSERT INTO pesquisa.bolsas_produtividade_cnpq (descricao) VALUES ('BOLSA 1');
INSERT INTO pesquisa.bolsas_produtividade_cnpq (descricao) VALUES ('BOLSA 2');

INSERT INTO pesquisa.cbos (descricao) VALUES ('ANALISTA DE TI');

INSERT INTO pesquisa.tipos_projeto (descricao) VALUES ('Pesquisa'), ('Extensão'), ('Inovação Tecnológica');

INSERT INTO pesquisa.status_projeto (descricao) VALUES ('Em planejamento'), ('Em execução'), ('Suspenso temporariamente'), ('Concluído');

INSERT INTO pesquisa.fontes_financiadoras (descricao, ativa) VALUES ('CAPES', true), ('CNPQ', true), ('FAPEMIG', true);

