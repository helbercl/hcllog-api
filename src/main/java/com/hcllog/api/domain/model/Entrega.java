package com.hcllog.api.domain.model;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
public class Entrega {
	
	@EqualsAndHashCode.Include
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	/* Mapeamento de um relacionamento entre entidades entrega(N) e cliente(1).
	 * Do lado do banco por dafult é o mapeamento do join cliente_id
	 * Pode definir o campo explicitamente por anotacao @JoinColumn
	 * Ex:@JoinColumn(name="cliente_id")
	 * */
	@ManyToOne 	
	private Cliente cliente;
	
	/*
	 * Os dados do destinario estão em outra classe mas ficarão na tabela entrega
	 * */
	@Embedded
	private Destinario destinatario;
	
	
	private BigDecimal taxa;
	
	//Readonly quer dizer que o consumer nao podera manipular o campo mesmo sabendo da existência dele.
	@JsonProperty(access = Access.READ_ONLY)
	@Enumerated(EnumType.STRING)
	private StatusEntrega status;
	
	@JsonProperty(access = Access.READ_ONLY)
	private LocalDateTime dataPedido;
	
	@JsonProperty(access=Access.READ_ONLY)
	private LocalDateTime dataFinalizacao;
	
	
}
