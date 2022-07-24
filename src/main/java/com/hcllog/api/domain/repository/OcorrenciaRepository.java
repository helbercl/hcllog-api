package com.hcllog.api.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.hcllog.api.domain.model.Ocorrencia;

public interface OcorrenciaRepository extends JpaRepository<Ocorrencia, Long> {

}
