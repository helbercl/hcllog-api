CREATE TABLE entrega (
  id bigint NOT NULL AUTO_INCREMENT,
  cliente_id bigint NOT NULL,
  taxa decimal(10,2) NOT NULL,
  status varchar(20) NOT NULL,
  data_pedido datetime NOT NULL,
  data_finalizacao datetime ,
  destinatario_nome varchar(60) NOT NULL,
  destinatario_logradouro varchar(255) NOT NULL,
  destinatario_numero varchar(30) NOT NULL,
  destinatario_complemento varchar(60) NOT NULL,
  destinatario_bairro varchar(30) NOT NULL,
  PRIMARY KEY (id)
);

alter table entrega add constraint fk_entrega_cliente_id
foreign key(cliente_id) references cliente(id);