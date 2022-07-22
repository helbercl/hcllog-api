package com.hcllog.api.domain.model;

import javax.persistence.Column;
import javax.persistence.Embeddable;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Embeddable
public class Destinario {
	
	@Column(name = "destinatario_nome")
	private String nome;
	@Column(name = "destinatario_logradouro")
	private String logradouro;
	@Column(name = "destinatario_numero")
	private String numero;
	@Column(name = "destinatario_complemento")
	private String complemento;
	@Column(name = "destinatario_bairro")
	private String bairro;
	
}
