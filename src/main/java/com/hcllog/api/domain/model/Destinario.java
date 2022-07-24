package com.hcllog.api.domain.model;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.validation.constraints.NotBlank;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Embeddable
public class Destinario {

	@NotBlank
	@Column(name = "destinatario_nome")
	private String nome;

	@NotBlank
	@Column(name = "destinatario_logradouro")
	private String logradouro;

	@NotBlank
	@Column(name = "destinatario_numero")
	private String numero;

	@NotBlank
	@Column(name = "destinatario_complemento")
	@NotBlank
	private String complemento;

	@NotBlank
	@Column(name = "destinatario_bairro")
	private String bairro;

}
