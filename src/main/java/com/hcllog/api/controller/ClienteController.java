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
import com.hcllog.api.domain.service.CatalogoClienteService;

import lombok.AllArgsConstructor;

/**
 * @author helber Controller - A interface para o consumidor. Onde estarão todos
 *         metodos disponiveis para a api web
 */

@AllArgsConstructor
@RestController
@RequestMapping("/clientes")
public class ClienteController {

	private CatalogoClienteService catalogoClienteService;

	@GetMapping
	public List<Cliente> listar() {
		return catalogoClienteService.listar();
	}

	@GetMapping("/{clienteId}")
	public ResponseEntity<Cliente> buscar(@PathVariable Long clienteId) {
		return catalogoClienteService.buscar(clienteId);
	}

	/*
	 * @PostMapping -indica qual verbo do protocolo http
	 * 
	 * @ResponseStatus-retorna o status 200 created
	 * 
	 * @valid é utilizado para indicar o ponto de validação para o bean validation
	 * jakart
	 */
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public Cliente adicionarCliente(@Valid @RequestBody Cliente cliente) {
		return catalogoClienteService.salvar(cliente);

	}

	@PutMapping("/{clienteId}")
	public ResponseEntity<Cliente> atualizarCliente(@PathVariable Long clienteId, @Valid @RequestBody Cliente cliente) {

		ResponseEntity<Cliente> resposta = catalogoClienteService.atualizar(clienteId, cliente);
		return resposta;

	}

	/*
	 * Void- o corpo da resposta nao vai existir NotFound(404) NoContent(204)
	 */
	@DeleteMapping("/{clienteId}")
	public ResponseEntity<Void> deletarCliente(@PathVariable Long clienteId) {

		if (clienteId <= 0) {
			return ResponseEntity.notFound().build();// codigo 404
		} else {
			return catalogoClienteService.excluir(clienteId);// codigo 204
		}

	}
}
