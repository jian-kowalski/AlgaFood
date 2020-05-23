insert into cozinha (nome) values ('Tailandesa');
insert into cozinha (nome) values ('Brasileira');
insert into cozinha (nome) values ('Argentina');
insert into cozinha (nome) values ('Americana');
insert into cozinha (nome) values ('Japonesa');


insert into restaurante (nome, taxa_frete, cozinha_id) values ('Da esquina', 1, 1);
insert into restaurante (nome, taxa_frete,cozinha_id) values ('Da outra esquina',1, 2);
insert into restaurante (nome, taxa_frete, cozinha_id) values ('Central', 1, 2);


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



