package com.hcllog.api.representationmodel.outuput;

import java.math.BigDecimal;
import java.time.OffsetDateTime;

import com.hcllog.api.domain.model.StatusEntrega;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EntregaModelOutuput {

	private Long id;
	private ClienteResumoModelOutuput cliente;
	private DestinatarioModelOutuput destinatario;
	private BigDecimal taxa;
	private StatusEntrega status;
	private OffsetDateTime dataPedido;
	private OffsetDateTime dataFinalização;

}
