/**
 * 
 */
package com.hcllog.api.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.hcllog.api.domain.model.Cliente;
import com.hcllog.api.domain.repository.ClienteRepository;

import lombok.AllArgsConstructor;

/**
 * @author helber 
 * Controller - A interface para o consumidor. Onde estarão todos  metodos disponiveis para a api web
 */

@AllArgsConstructor
@RestController
@RequestMapping("/clientes")
public class ClienteController {

	private ClienteRepository clienteRepository;
	
	@GetMapping
	public List<Cliente> listar() {
		return clienteRepository.findAll();
	}

	@GetMapping("/{clienteId}")
	public ResponseEntity<Cliente> buscar(@PathVariable Long clienteId) {
		/*
		 * Optional<Cliente> cliente = clienteRepository.findById(clienteId);
		 * 
		 * if (cliente.isPresent()) { return ResponseEntity.ok(cliente.get()); } return
		 * ResponseEntity.notFound().build(); }
		 */

		return clienteRepository.findById(clienteId)
				//.map(cliente -> ResponseEntity.ok(cliente))
				.map(ResponseEntity::ok)
				.orElse(ResponseEntity.notFound().build());
	}

	@PostMapping //indica qual verbo do protocolo http
	@ResponseStatus(HttpStatus.CREATED)//retorna o status 200 created
	public Cliente adicionarCliente(@Valid @RequestBody Cliente cliente) {
		//@valid é utilizado para indicar o ponto de validação para o bean validation jakart
		return clienteRepository.save(cliente);

	}

	@PutMapping("/{clienteId}")
	public ResponseEntity<Cliente> atualizarCliente(@Valid @PathVariable Long clienteId, @RequestBody Cliente cliente) {
		
		if (!clienteRepository.existsById(clienteId)) {
			return ResponseEntity.notFound().build();
		}
		cliente.setId(clienteId);
		cliente = clienteRepository.save(cliente);
		return ResponseEntity.ok(cliente);

	}
	
	@DeleteMapping("/{clienteId}")
	//Void- o corpo da resposta nao vai existir
	public ResponseEntity<Void> deletarCliente(@PathVariable Long clienteId){
		if (!clienteRepository.existsById(clienteId)) {
			return ResponseEntity.notFound().build();//codigo 404
		}
		clienteRepository.deleteById(clienteId);
		return ResponseEntity.noContent().build();//codigo 204
		
	}
}
