set foreign_key_checks = 0;

DELETE FROM algafood.cidade;
DELETE FROM algafood.cozinha;
DELETE FROM algafood.estado;
DELETE FROM algafood.forma_pagamento;
DELETE FROM algafood.grupo;
DELETE FROM algafood.grupo_permissao;
DELETE FROM algafood.permissao;
DELETE FROM algafood.produto;
DELETE FROM algafood.restaurante;
DELETE FROM algafood.restaurante_forma_pagamento;
DELETE FROM algafood.usuario;
DELETE FROM algafood.usuario_grupo;

set foreign_key_checks = 1;

alter table algafood.cidade auto_increment = 1;
alter table algafood.cozinha auto_increment = 1;
alter table algafood.estado auto_increment = 1;
alter table algafood.forma_pagamento auto_increment = 1;
alter table algafood.grupo auto_increment = 1;
alter table algafood.grupo_permissao auto_increment = 1;
alter table algafood.permissao auto_increment = 1;
alter table algafood.produto auto_increment = 1;
alter table algafood.restaurante auto_increment = 1;
alter table algafood.restaurante_forma_pagamento auto_increment = 1;
alter table algafood.usuario auto_increment = 1;
alter table algafood.usuario_grupo auto_increment = 1;

insert into cozinha (nome) values ('Tailandesa');
insert into cozinha (nome) values ('Brasileira');
insert into cozinha (nome) values ('Argentina');
insert into cozinha (nome) values ('Americana');
insert into cozinha (nome) values ('Japonesa');

insert into estado (nome) values ('Parana');
insert into estado (nome) values ('Para');
insert into estado (nome) values ('Mato Grosso');

insert into cidade (nome, estado_id) values ('Marmeleiro', 1);
insert into cidade (nome, estado_id) values ('Pato Branco', 1);
insert into cidade (nome, estado_id) values ('Vitorino', 1);
insert into cidade (nome, estado_id) values ('Renascen�a', 1);
insert into cidade (nome, estado_id) values ('Curitiba', 1);

INSERT INTO restaurante (endereco_bairro, endereco_cep, endereco_complemento, endereco_logradouro, endereco_numero, nome, taxa_frete, cozinha_id, endereco_cidade_id, data_cadastro, data_atualizacao) VALUES('Centro', '85520-568', 'apto 1005', 'Pedro Ramires de mello', '1005', 'Da outra esquina', 20.00, 1, 1, utc_timestamp, utc_timestamp);
INSERT INTO restaurante (endereco_bairro, endereco_cep, endereco_complemento, endereco_logradouro, endereco_numero, nome, taxa_frete, cozinha_id, endereco_cidade_id, data_cadastro, data_atualizacao) VALUES('Centro', '85520-568', 'apto 1005', 'Pedro Ramires de mello', '1005', 'Central', 0.00, 1, 1, utc_timestamp, utc_timestamp);
INSERT INTO restaurante (endereco_bairro, endereco_cep, endereco_complemento, endereco_logradouro, endereco_numero, nome, taxa_frete, cozinha_id, endereco_cidade_id, data_cadastro, data_atualizacao) VALUES('Centro', '85520-568', 'apto 1005', 'Pedro Ramires de mello', '1005', 'Edificio Agape', 10.1, 1, 1, utc_timestamp, utc_timestamp);
INSERT INTO restaurante (endereco_bairro, endereco_cep, endereco_complemento, endereco_logradouro, endereco_numero, nome, taxa_frete, cozinha_id, endereco_cidade_id, data_cadastro, data_atualizacao) VALUES('Centro', '85520-568', 'apto 1005', 'Pedro Ramires de mello', '1005', 'Edificio Agape', 10.1, 1, 1, utc_timestamp, utc_timestamp);
INSERT INTO restaurante (endereco_bairro, endereco_cep, endereco_complemento, endereco_logradouro, endereco_numero, nome, taxa_frete, cozinha_id, endereco_cidade_id, data_cadastro, data_atualizacao) VALUES('Centro', '85520-568', 'apto 1005', 'Pedro Ramires de mello', '1005', 'Edificio Agape', 10.1, 1, 1, utc_timestamp, utc_timestamp);
INSERT INTO restaurante (endereco_bairro, endereco_cep, endereco_complemento, endereco_logradouro, endereco_numero, nome, taxa_frete, cozinha_id, endereco_cidade_id, data_cadastro, data_atualizacao) VALUES('Centro', '85520-568', 'apto 1005', 'Pedro Ramires de mello', '1005', 'Edificio Agape', 10.1, 1, 1, utc_timestamp, utc_timestamp);
INSERT INTO restaurante (endereco_bairro, endereco_cep, endereco_complemento, endereco_logradouro, endereco_numero, nome, taxa_frete, cozinha_id, endereco_cidade_id, data_cadastro, data_atualizacao) VALUES('Centro', '85520-568', 'apto 1005', 'Pedro Ramires de mello', '1005', 'Edificio Agape', 10.1, 1, 1, utc_timestamp, utc_timestamp);
INSERT INTO restaurante (endereco_bairro, endereco_cep, endereco_complemento, endereco_logradouro, endereco_numero, nome, taxa_frete, cozinha_id, endereco_cidade_id, data_cadastro, data_atualizacao) VALUES('Centro', '85520-568', 'apto 1005', 'Pedro Ramires de mello', '1005', 'Edificio Agape', 10.1, 1, 1, utc_timestamp, utc_timestamp);
INSERT INTO restaurante (endereco_bairro, endereco_cep, endereco_complemento, endereco_logradouro, endereco_numero, nome, taxa_frete, cozinha_id, endereco_cidade_id, data_cadastro, data_atualizacao) VALUES('Centro', '85520-568', 'apto 1005', 'Pedro Ramires de mello', '1005', 'Edificio Agape', 10.1, 1, 1, utc_timestamp, utc_timestamp);
INSERT INTO restaurante (endereco_bairro, endereco_cep, endereco_complemento, endereco_logradouro, endereco_numero, nome, taxa_frete, cozinha_id, endereco_cidade_id, data_cadastro, data_atualizacao) VALUES('Centro', '85520-568', 'apto 1005', 'Pedro Ramires de mello', '1005', 'Edificio Agape', 10.1, 1, 1, utc_timestamp, utc_timestamp);
INSERT INTO restaurante (endereco_bairro, endereco_cep, endereco_complemento, endereco_logradouro, endereco_numero, nome, taxa_frete, cozinha_id, endereco_cidade_id, data_cadastro, data_atualizacao) VALUES('Centro', '85520-568', 'apto 1005', 'Pedro Ramires de mello', '1005', 'Edificio Agape', 10.1, 1, 1, utc_timestamp, utc_timestamp);
INSERT INTO restaurante (endereco_bairro, endereco_cep, endereco_complemento, endereco_logradouro, endereco_numero, nome, taxa_frete, cozinha_id, endereco_cidade_id, data_cadastro, data_atualizacao) VALUES('Centro', '85520-568', 'apto 1005', 'Pedro Ramires de mello', '1005', 'Edificio Agape', 10.1, 1, 1, utc_timestamp, utc_timestamp);
INSERT INTO restaurante (endereco_bairro, endereco_cep, endereco_complemento, endereco_logradouro, endereco_numero, nome, taxa_frete, cozinha_id, endereco_cidade_id, data_cadastro, data_atualizacao) VALUES('Centro', '85520-568', 'apto 1005', 'Pedro Ramires de mello', '1005', 'Edificio Agape', 10.1, 1, 1, utc_timestamp, utc_timestamp);
INSERT INTO restaurante (endereco_bairro, endereco_cep, endereco_complemento, endereco_logradouro, endereco_numero, nome, taxa_frete, cozinha_id, endereco_cidade_id, data_cadastro, data_atualizacao) VALUES('Centro', '85520-568', 'apto 1005', 'Pedro Ramires de mello', '1005', 'Edificio Agape', 10.1, 1, 1, utc_timestamp, utc_timestamp);
INSERT INTO restaurante (endereco_bairro, endereco_cep, endereco_complemento, endereco_logradouro, endereco_numero, nome, taxa_frete, cozinha_id, endereco_cidade_id, data_cadastro, data_atualizacao) VALUES('Centro', '85520-568', 'apto 1005', 'Pedro Ramires de mello', '1005', 'Edificio Agape', 10.1, 1, 1, utc_timestamp, utc_timestamp);
INSERT INTO restaurante (endereco_bairro, endereco_cep, endereco_complemento, endereco_logradouro, endereco_numero, nome, taxa_frete, cozinha_id, endereco_cidade_id, data_cadastro, data_atualizacao) VALUES('Centro', '85520-568', 'apto 1005', 'Pedro Ramires de mello', '1005', 'Edificio Agape', 10.1, 1, 1, utc_timestamp, utc_timestamp);

insert into forma_pagamento (descricao) values ('Dinheiro ');  
insert into forma_pagamento (descricao) values ('Cart�o');  
insert into forma_pagamento (descricao) values ('PicPay');  

insert into restaurante_forma_pagamento(restaurante_id, forma_pagamento_id) values (1,1);
insert into restaurante_forma_pagamento(restaurante_id, forma_pagamento_id) values (1,2);
insert into restaurante_forma_pagamento(restaurante_id, forma_pagamento_id) values (1,3);

insert into permissao (nome, descricao) values ('Total', 'Pode alterar qualquer coisa');  
insert into permissao (nome, descricao) values ('Media',  'Pode alterar algumas coisas');  
insert into permissao (nome, descricao) values ('Nenhuma', 'n�o pode alterar qualquer coisa');  

insert into produto (nome, descricao, preco, ativo, restaurante_id) values ('Porco com molho agridoce', 'Deliciosa carne su�na ao molho especial', 78.90, 1, 1);
insert into produto (nome, descricao, preco, ativo, restaurante_id) values ('Camar�o tailand�s', '16 camar�es grandes ao molho picante', 110, 1, 1);
insert into produto (nome, descricao, preco, ativo, restaurante_id) values ('Salada picante com carne grelhada', 'Salada de folhas com cortes finos de carne bovina grelhada e nosso molho especial de pimenta vermelha', 87.20, 1, 2);
insert into produto (nome, descricao, preco, ativo, restaurante_id) values ('Garlic Naan', 'P�o tradicional indiano com cobertura de alho', 21, 1, 3);
insert into produto (nome, descricao, preco, ativo, restaurante_id) values ('Murg Curry', 'Cubos de frango preparados com molho curry e especiarias', 43, 1, 3);
insert into produto (nome, descricao, preco, ativo, restaurante_id) values ('Bife Ancho', 'Corte macio e suculento, com dois dedos de espessura, retirado da parte dianteira do contrafil�', 79, 1, 4);
insert into produto (nome, descricao, preco, ativo, restaurante_id) values ('T-Bone', 'Corte muito saboroso, com um osso em formato de T, sendo de um lado o contrafil� e do outro o fil� mignon', 89, 1, 4);
insert into produto (nome, descricao, preco, ativo, restaurante_id) values ('Sandu�che X-Tudo', 'Sandub�o com muito queijo, hamburger bovino, bacon, ovo, salada e maionese', 19, 1, 5);
insert into produto (nome, descricao, preco, ativo, restaurante_id) values ('Espetinho de Cupim', 'Acompanha farinha, mandioca e vinagrete', 8, 1, 6);





