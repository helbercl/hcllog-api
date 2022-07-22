package com.hcllog.api.domain.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.hcllog.api.domain.model.Entrega;
import com.hcllog.api.domain.model.StatusEntrega;

@Repository
public interface EntregaRepository extends JpaRepository<Entrega, Long>{	

}
