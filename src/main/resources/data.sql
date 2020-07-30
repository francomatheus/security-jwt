INSERT INTO user(email, username, password, fullname) VALUES ('admin@test.com', 'admin', '$2y$12$Wv3F9M2JErRtPIOlm.iVuuPJJj7/HV9a143e.945ZII1l6COP/cIG', 'administrador');
INSERT INTO role(role) VALUES ('ROLE_ADMIN');
INSERT INTO user_roles(user_entity_id,roles_id) VALUES (1,1);