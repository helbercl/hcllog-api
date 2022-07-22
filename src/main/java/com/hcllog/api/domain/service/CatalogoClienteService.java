package com.hcllog.api.domain.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;

import com.hcllog.api.domain.exception.NegocioException;
import com.hcllog.api.domain.model.Cliente;
import com.hcllog.api.domain.repository.ClienteRepository;

@Service
public class CatalogoClienteService {

	@Autowired
	private ClienteRepository clienteRepository;
	private Boolean nomeEmUso, telefoneEmUso, emailEmUso;
	private String mensagem;

	// O metodo deve ser executado dentro de uma transação(ou executa tudo ou nada)
	@Transactional
	public Cliente salvar(Cliente cliente) {

		camposEmUso(cliente);

		if (!"".equalsIgnoreCase(mensagem)) {
			throw new NegocioException(mensagem);
		}

		return clienteRepository.save(cliente);
	}

	@Transactional
	public ResponseEntity<Void> excluir(Long clienteId) {

		if (!clienteRepository.existsById(clienteId)) {
			return ResponseEntity.notFound().build();// codigo 404
		} else {
			clienteRepository.deleteById(clienteId);
			return ResponseEntity.noContent().build();// codigo 204
		}

	}

	@Transactional
	public ResponseEntity<Cliente> atualizar(Long clienteId, Cliente cliente) {

		Boolean atualizar;

		if (!clienteRepository.existsById(clienteId)) {
			atualizar = false;
		} else {
			cliente.setId(clienteId);
			cliente = clienteRepository.save(cliente);
			atualizar = true;
		}

		return atualizar == true ? ResponseEntity.ok(cliente) : ResponseEntity.notFound().build();

	}

	public List<Cliente> listar() {
		return clienteRepository.findAll();
	}

	public ResponseEntity<Cliente> buscar(@PathVariable Long clienteId) {
		return clienteRepository.findById(clienteId)
				.map(ResponseEntity::ok)
				.orElse(ResponseEntity.notFound().build());
	}
	
	public Cliente buscarCliente(Long clienteId){		
		return clienteRepository.findById(clienteId)
				.orElseThrow(() ->  new NegocioException("Cliente não encontrado."));		
	}

	private String camposEmUso(Cliente cliente) {

		mensagem = "";

		nomeEmUso = clienteRepository.findByNomeContaining(cliente.getNome()).stream()
				.anyMatch(nomeExistente -> !nomeExistente.equals(cliente));

		emailEmUso = clienteRepository.findByEmail(cliente.getEmail()).stream()
				.anyMatch(emailExistente -> !emailExistente.equals(cliente));

		telefoneEmUso = clienteRepository.findByTelefone(cliente.getTelefone()).stream()
				.anyMatch(telefoneExistente -> !telefoneExistente.equals(cliente));

		if (nomeEmUso && emailEmUso && telefoneEmUso) {
			mensagem = "Já existe um cliente usando o nome, email e/ou telefone informados";
		} else if (nomeEmUso) {
			mensagem = "Já existe um cliente usando nome informado";
		} else if (telefoneEmUso) {
			mensagem = "Já existe um cliente usando o telefone informado";
		} else if (emailEmUso) {
			mensagem = "Já existe um cliente usando o email informado";
		}

		return mensagem;
	}
}
