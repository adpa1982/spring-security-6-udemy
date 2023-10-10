INSERT INTO customers (email, pwd) values ('account@debuggeandoieas.com', 'to_be_encoded');
INSERT INTO customers (email, pwd) values ('cards@debuggeandoieas.com', 'to_be_encoded');
INSERT INTO customers (email, pwd) values ('loans@debuggeandoieas.com', 'to_be_encoded');
INSERT INTO customers (email, pwd) values ('balance@debuggeandoieas.com', 'to_be_encoded');


INSERT INTO roles(id, role_name, description, id_customer) values (1, 'VIEW_ACCOUNT', 'cant view account endpoint', 2);
INSERT INTO roles(id, role_name, description, id_customer) values (2, 'VIEW_CARDS', 'cant view cards endpoint', 3);
INSERT INTO roles(id, role_name, description, id_customer) values (3, 'VIEW_LOANS', 'cant view loans endpoint', 4);
INSERT INTO roles(id, role_name, description, id_customer) values (4, 'VIEW_BALANCE', 'cant view balance endpoint', 5);


INSERT INTO roles(id, role_name, description, id_customer) values (1, 'ROLE_ADMIN', 'cant view account endpoint',2);
INSERT INTO roles(id, role_name, description, id_customer) values (2, 'ROLE_ADMIN', 'cant view cards endpoint', 3);
INSERT INTO roles(id, role_name, description, id_customer) values (3, 'ROLE_USER', 'cant view loans endpoint', 4);
INSERT INTO roles(id, role_name, description, id_customer) values (4, 'ROLE_USER', 'cant view balance endpoint', 5);