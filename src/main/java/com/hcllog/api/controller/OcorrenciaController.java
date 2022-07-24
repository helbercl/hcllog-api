package com.hcllog.api.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.hcllog.api.assembler.OcorrenciaAssembler;
import com.hcllog.api.domain.model.Entrega;
import com.hcllog.api.domain.model.input.OcorrenciaInput;
import com.hcllog.api.domain.service.BuscaEntregaService;
import com.hcllog.api.domain.service.RegistroOcorrenciaService;
import com.hcllog.api.representationmodel.outuput.OcorrenciaModelOutuput;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@RestController
@RequestMapping("/entregas/{entregaId}/ocorrencias")
public class OcorrenciaController {

	private RegistroOcorrenciaService registroOcorrenciaService;
	private OcorrenciaAssembler ocorrenciaAssembler;
	private BuscaEntregaService buscaEntregaService;
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public OcorrenciaModelOutuput registrar(@PathVariable Long entregaId,
			@Valid @RequestBody OcorrenciaInput ocorrenciaInput) {
		return ocorrenciaAssembler
				.toModel(registroOcorrenciaService.registrar(entregaId, ocorrenciaInput.getDescricao()));

	}
	
	@GetMapping	
	public List<OcorrenciaModelOutuput> listar(@PathVariable Long entregaId) {
		Entrega entrega =buscaEntregaService.buscar(entregaId);
		
		return ocorrenciaAssembler.toCollectionListModel(entrega.getListaOcorrencias());
		
	}

}
