package com.hcllog.api.exceptionhandler;

import java.time.OffsetDateTime;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.hcllog.api.domain.model.Campo;

import lombok.Getter;
import lombok.Setter;

@JsonInclude(Include.NON_NULL)
@Getter
@Setter
public class Erro {

	private Integer cd_status;
	private OffsetDateTime dataHoraErro;
	private String descricaoErro;
	private List<Campo> listaCampos;

}
