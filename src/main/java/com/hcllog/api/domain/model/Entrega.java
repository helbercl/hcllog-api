package com.hcllog.api.domain.model;

import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

//Resource Representation Model - Classe que modela o que deve ser retornado

@Getter
@Setter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
public class Entrega {

	@EqualsAndHashCode.Include
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	/*
	 * Mapeamento de um relacionamento entre entidades entrega(N) e cliente(1). Do
	 * lado do banco por dafult é o mapeamento do join cliente_id Pode definir o
	 * campo explicitamente por anotacao @JoinColumn
	 * Ex:@JoinColumn(name="cliente_id")
	 * 
	 * @Valid - cascateia a validacao para os objetos dentro da classe.(validar o
	 * que ta dentro)
	 * 
	 * @Transient - a marcação indica que o campo vai para o modelo de representação
	 * de recurso API (o que tem na entidade para o que vai ser apresentado na api)
	 */
	/*
	 * @Valid
	 * 
	 * @ConvertGroup(from = Default.class,to=ValidationsGroups.ClienteId.class)
	 * 
	 * @NotNull
	 */
	@ManyToOne
	private Cliente cliente;

	/*
	 * Os dados do destinario estão em outra classe mas ficarão na tabela entrega
	 */
	/*
	 * @Valid
	 * 
	 * @NotNull
	 */
	@Embedded
	private Destinario destinatario;

	private BigDecimal taxa;

	// Readonly quer dizer que o consumer nao podera manipular o campo mesmo sabendo
	// da existência dele.
	// @JsonProperty(access = Access.READ_ONLY)
	@Enumerated(EnumType.STRING)
	private StatusEntrega status;

	// @JsonProperty(access = Access.READ_ONLY)
	private OffsetDateTime dataPedido;

	// @JsonProperty(access=Access.READ_ONLY)
	private OffsetDateTime dataFinalizacao;

	@OneToMany(mappedBy = "entrega", cascade = CascadeType.ALL)
	private List<Ocorrencia> listaOcorrencias = new ArrayList<>();

	public Ocorrencia adicionarOcorrencia(String descricao) {

		Ocorrencia ocorrencia = new Ocorrencia();
		ocorrencia.setDescricao(descricao);
		ocorrencia.setDataRegistro(OffsetDateTime.now());
		ocorrencia.setEntrega(this);// a propria entrega
		this.getListaOcorrencias().add(ocorrencia);

		return ocorrencia;

	}

}
