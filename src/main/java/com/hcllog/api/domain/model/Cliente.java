package com.hcllog.api.domain.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.hcllog.api.domain.validationsgroups.ValidationsGroups;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
//Resource Representation Model - Classe que modela o que deve ser retornado

@EqualsAndHashCode(onlyExplicitlyIncluded = true)//implementação dos metodos hashcode e equals explicitando o campo
@Getter
@Setter
@Entity
public class Cliente {

	@NotNull(groups = ValidationsGroups.ClienteId.class)
	@EqualsAndHashCode.Include //campo id sera usado para criar o equals e hashcode
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)//incremente e private key
	private Long id;
	
	
	@NotBlank //jakart bean validation impede que seja nulo e vazia
	@Size(max = 60) //condizente com o tamanho da coluna do banco de dados
	private String nome;
	
	@NotBlank
	@Email
	@Size(max = 255)
	private String email;
	
	@NotBlank
	@Size(max =20)
	private String telefone;
	

}
