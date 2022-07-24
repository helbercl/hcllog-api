package com.hcllog.api.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.hcllog.api.domain.model.input.EntregaInput;
import com.hcllog.api.domain.service.FinalizacaoEntregaService;
import com.hcllog.api.domain.service.SolicitacaoEntregaService;
import com.hcllog.api.representationmodel.outuput.EntregaModelOutuput;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@RestController
@RequestMapping("/entregas")
public class EntregaController {

	private SolicitacaoEntregaService solicitacaoEntregaService;
	private FinalizacaoEntregaService finalizacaoEntregaService;

	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public EntregaModelOutuput solicitar(@Valid @RequestBody EntregaInput entrega) {
		return solicitacaoEntregaService.solicitar(entrega);
	}

	@GetMapping
	public List<EntregaModelOutuput> listar() {
		return solicitacaoEntregaService.listar();
	}

	@GetMapping("/{entregaId}")
	public ResponseEntity<EntregaModelOutuput> listar(@PathVariable Long entregaId) {
		return solicitacaoEntregaService.listar(entregaId);
	}

	@PutMapping("/{entregaId}/finalizacao")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void finalizar(@PathVariable Long entregaId) {
		finalizacaoEntregaService.finalizar(entregaId);
	}

}
