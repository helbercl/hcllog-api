package com.hcllog.api.domain.service;

import java.time.LocalDateTime;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hcllog.api.domain.model.Cliente;
import com.hcllog.api.domain.model.Entrega;
import com.hcllog.api.domain.model.StatusEntrega;
import com.hcllog.api.domain.repository.EntregaRepository;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@Service
public class SolicitacaoEntregaService {
	
	
	private EntregaRepository entregaRepository;
	private CatalogoClienteService catalogoClienteService;
	
	@Transactional
	public Entrega solicitar(Entrega entrega) {
		
		Cliente cliente= catalogoClienteService.buscarCliente(entrega.getCliente().getId());		
		entrega.setCliente(cliente);		
		entrega.setStatus(StatusEntrega.PENDENTE);
		entrega.setDataPedido(LocalDateTime.now());
		
		return entregaRepository.save(entrega);		
	}

}
