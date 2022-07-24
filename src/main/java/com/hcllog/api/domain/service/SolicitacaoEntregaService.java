package com.hcllog.api.domain.service;

import java.time.OffsetDateTime;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hcllog.api.assembler.EntregaAssembler;
import com.hcllog.api.domain.model.Cliente;
import com.hcllog.api.domain.model.Entrega;
import com.hcllog.api.domain.model.StatusEntrega;
import com.hcllog.api.domain.model.input.EntregaInput;
import com.hcllog.api.domain.repository.EntregaRepository;
import com.hcllog.api.representationmodel.outuput.EntregaModelOutuput;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@Service
public class SolicitacaoEntregaService {

	private EntregaRepository entregaRepository;
	private CatalogoClienteService catalogoClienteService;
	private EntregaAssembler entregaAssembler;

	@Transactional
	public EntregaModelOutuput solicitar(EntregaInput entregaInputModel) {

		Entrega entrega = entregaAssembler.toEntity(entregaInputModel);// converte o modelo de entrada do input em um
																		// modelo de domain(Entrega.class)

		// Seta as informações do cliente, status e data do pedido a partir dos dados
		// recebidos pelo modelo de domain(Entrega.class)
		Cliente cliente = catalogoClienteService.buscarCliente(entrega.getCliente().getId());
		entrega.setCliente(cliente);
		entrega.setStatus(StatusEntrega.PENDENTE);
		entrega.setDataPedido(OffsetDateTime.now());

		// retorna um objeto de modelo de representação(EntregaModel) a partir da
		// conversão de um modelo de domain(Entrega.class)
		return entregaAssembler.toModel(entregaRepository.save(entrega));
	}

	public List<EntregaModelOutuput> listar() {
		return entregaAssembler.toListCollectionModel(entregaRepository.findAll());
	}

	public ResponseEntity<EntregaModelOutuput> listar(Long entregaId) {
		return entregaRepository.findById(entregaId)
				.map(entrega -> ResponseEntity.ok(entregaAssembler.toModel(entrega)))
				.orElse(ResponseEntity.notFound().build());

	}

}
