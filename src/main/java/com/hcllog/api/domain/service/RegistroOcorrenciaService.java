package com.hcllog.api.domain.service;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import com.hcllog.api.domain.exception.NegocioException;
import com.hcllog.api.domain.model.Entrega;
import com.hcllog.api.domain.model.Ocorrencia;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@Service
public class RegistroOcorrenciaService {

	private BuscaEntregaService buscaEntregaService;

	@Transactional
	public Ocorrencia registrar(Long entregaId, String descricao) {

		Entrega entrega = buscaEntregaService.buscar(entregaId);

		if (!entrega.podeSerfinalizada()) {
			throw new NegocioException("Não é permitido registrar ocorrências com o status atual da entrega.");
		}
		return entrega.adicionarOcorrencia(descricao);

	}
}
