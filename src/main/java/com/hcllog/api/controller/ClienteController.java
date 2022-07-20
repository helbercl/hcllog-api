/**
 * 
 */
package com.hcllog.api.controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hcllog.api.domain.model.Cliente;

/**
 * @author helber Controller -
 */

@RestController
public class ClienteController {

	@GetMapping("/clientes")
	public List<Cliente> listar() {
		List<Cliente> listaCliente = new ArrayList<>();
		
		Cliente cliente = new Cliente();
		cliente.setId(1L);
		cliente.setNome("Helber");
		cliente.setTelefone("(79)998129-9265");
		cliente.setEmail("helber@infox.com.br");
		
		Cliente cliente2 = new Cliente();
		cliente2.setId(2L);
		cliente2.setNome("Maria");
		cliente2.setTelefone("(79)998125-6765");
		cliente2.setEmail("tercya@infox.com.br");		
		
		return Arrays.asList(cliente,cliente2);
	}
	
	@PostMapping
	public String inserirClientes(String nome, String telefone, String email) {
		
		Boolean sucesso=false;
		String mensagem=null;
		try {
			sucesso=true;
		} catch (Exception e) {
			sucesso=false;
			mensagem="Erro :" + e.getMessage();
		}
		
		return mensagem;
		
	}
}
