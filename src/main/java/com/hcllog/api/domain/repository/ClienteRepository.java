package com.hcllog.api.domain.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.hcllog.api.domain.model.Cliente;

@Repository
public interface ClienteRepository extends JpaRepository<Cliente, Long> {

	List<Cliente> findByNome(String nome);

	List<Cliente> findByNomeContaining(String nome);

	Optional<Cliente> findByEmail(String email);

	Optional<Cliente> findByTelefone(String telefone);

}
