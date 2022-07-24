package com.hcllog.api.representationmodel.outuput;

import java.time.OffsetDateTime;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OcorrenciaModelOutuput {

	private Long id;
	private String descricao;
	private OffsetDateTime dataRegistro;
}
