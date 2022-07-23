package com.hcllog.api.domain.service;

import java.time.OffsetDateTime;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hcllog.api.domain.model.Cliente;
import com.hcllog.api.domain.model.Entrega;
import com.hcllog.api.domain.model.StatusEntrega;
import com.hcllog.api.domain.repository.EntregaRepository;
import com.hcllog.api.representationmodel.dto.DestinatarioModel;
import com.hcllog.api.representationmodel.dto.EntregaModel;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@Service
public class SolicitacaoEntregaService {

	private EntregaRepository entregaRepository;
	private CatalogoClienteService catalogoClienteService;

	@Transactional
	public Entrega solicitar(Entrega entrega) {

		Cliente cliente = catalogoClienteService.buscarCliente(entrega.getCliente().getId());
		entrega.setCliente(cliente);
		entrega.setStatus(StatusEntrega.PENDENTE);
		entrega.setDataPedido(OffsetDateTime.now());

		return entregaRepository.save(entrega);
	}

	public List<Entrega> listar() {
		return entregaRepository.findAll();
	}

	public ResponseEntity<EntregaModel> listar(Long entregaId) {
		return entregaRepository.findById(entregaId).map(entrega -> {
			
			EntregaModel entregaModel = new EntregaModel();
			entregaModel.setId(entrega.getId());
			entregaModel.setNomeCliente(entrega.getCliente().getNome());
			
			entregaModel.setDestinatario(new DestinatarioModel());
			entregaModel.getDestinatario().setNome(entrega.getDestinatario().getNome());
			entregaModel.getDestinatario().setLogradouro(entrega.getDestinatario().getLogradouro());
			entregaModel.getDestinatario().setNumero(entrega.getDestinatario().getNumero());
			entregaModel.getDestinatario().setComplemento(entrega.getDestinatario().getComplemento());
			entregaModel.getDestinatario().setBairro(entrega.getDestinatario().getBairro());
			
			entregaModel.setStatus(entrega.getStatus());
			entregaModel.setTaxa(entrega.getTaxa());
			entregaModel.setDataPedido(entrega.getDataPedido());
			entregaModel.setDataFinalização(entrega.getDataFinalizacao());
			
			return ResponseEntity.ok(entregaModel);
		}).orElse(ResponseEntity.notFound().build());
	}

}
