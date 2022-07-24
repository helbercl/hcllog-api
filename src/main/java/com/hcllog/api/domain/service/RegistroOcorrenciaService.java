package com.hcllog.api.domain.service;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import com.hcllog.api.assembler.OcorrenciaAssembler;
import com.hcllog.api.domain.model.Entrega;
import com.hcllog.api.domain.model.Ocorrencia;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@Service
public class RegistroOcorrenciaService {

	private BuscaEntregaService buscaEntregaService;
	private OcorrenciaAssembler ocorrenciaAssembler;

	@Transactional
	public Ocorrencia registrar(Long entregaId, String descricao) {

		Entrega entrega = buscaEntregaService.buscar(entregaId);
		return entrega.adicionarOcorrencia(descricao);

	}
}
