package com.hcllog.api.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.hcllog.api.domain.model.Entrega;
import com.hcllog.api.domain.service.SolicitacaoEntregaService;
import com.hcllog.api.representationmodel.dto.EntregaModel;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@RestController
@RequestMapping("/entregas")
public class EntregaController {

	private SolicitacaoEntregaService solicitacaoEntregaService;
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public Entrega solicitar(@Valid @RequestBody Entrega entrega) {		
		return solicitacaoEntregaService.solicitar(entrega);		
	}
	
	@GetMapping
	public List<Entrega> listar(){
		return solicitacaoEntregaService.listar();
	}
	@GetMapping("/{entregaId}")
	public ResponseEntity<EntregaModel> listar(@PathVariable Long entregaId){
		return solicitacaoEntregaService.listar(entregaId);		
	}
	
	
}
