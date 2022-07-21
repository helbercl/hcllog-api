package com.hcllog.api.exceptionhandler;

import java.time.LocalDateTime;
import java.util.List;

import com.hcllog.api.domain.model.Campo;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Erro {
	
	private Integer cd_status;
	private LocalDateTime dataHoraErro;
	private String descricaoErro;
	private List<Campo> listaCampos;
	
}
