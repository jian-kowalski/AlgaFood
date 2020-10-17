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
insert into cidade (nome, estado_id) values ('Renascença', 1);
insert into cidade (nome, estado_id) values ('Curitiba', 1);

insert into forma_pagamento (descricao) values ('Dinheiro ');  
insert into forma_pagamento (descricao) values ('Cartão');  
insert into forma_pagamento (descricao) values ('PicPay');  

insert into permissao (nome) values ('Total');  
insert into permissao (nome) values ('Media');  
insert into permissao (nome) values ('Nenhuma');  

INSERT INTO restaurante (endereco_bairo, endereco_cep, endereco_complemento, endereco_logradouro, endereco_numero, nome, taxa_frete, cozinha_id, endereco_cidade_id, data_cadastro, data_atualizacao) VALUES('Centro', '85520-568', 'apto 1005', 'Pedro Ramires de mello', '1005', 'Da outra esquina', 20.00, 1, 1, utc_timestamp, utc_timestamp);
INSERT INTO restaurante (endereco_bairo, endereco_cep, endereco_complemento, endereco_logradouro, endereco_numero, nome, taxa_frete, cozinha_id, endereco_cidade_id, data_cadastro, data_atualizacao) VALUES('Centro', '85520-568', 'apto 1005', 'Pedro Ramires de mello', '1005', 'Central', 0.00, 1, 1, utc_timestamp, utc_timestamp);
INSERT INTO restaurante (endereco_bairo, endereco_cep, endereco_complemento, endereco_logradouro, endereco_numero, nome, taxa_frete, cozinha_id, endereco_cidade_id, data_cadastro, data_atualizacao) VALUES('Centro', '85520-568', 'apto 1005', 'Pedro Ramires de mello', '1005', 'Edificio Agape', 10.1, 1, 1, utc_timestamp, utc_timestamp);

insert into restaurante_forma_pagamento(restaurante_id, forma_pagamento_id) values (1,1);
insert into restaurante_forma_pagamento(restaurante_id, forma_pagamento_id) values (1,2);
insert into restaurante_forma_pagamento(restaurante_id, forma_pagamento_id) values (1,3);






