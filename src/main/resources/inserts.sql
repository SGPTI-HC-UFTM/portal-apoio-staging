DELETE FROM auth.usuarios_grupos;
DELETE FROM auth.usuarios;
DELETE FROM auth.grupos;

INSERT INTO auth.grupos (grupo) VALUES ('ADMIN'), ('PESQUISA_ADMIN');
INSERT INTO auth.usuarios (login, password) VALUES ('admin', 'jGl25bVBBBW96Qi9Te4V37Fnqchz/Eu4qB9vKrRIqRg=');
INSERT INTO auth.usuarios_grupos (usuario_id, grupo_id) SELECT u.id, g.id FROM auth.grupos g CROSS JOIN auth.usuarios u WHERE g.grupo IN ('ADMIN', 'PESQUISA_ADMIN') AND u.login = 'admin';
