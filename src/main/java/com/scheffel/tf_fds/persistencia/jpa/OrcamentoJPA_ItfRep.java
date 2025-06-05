package com.scheffel.tf_fds.persistencia.jpa;

import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import com.scheffel.tf_fds.persistencia.entity.Orcamento;

public interface OrcamentoJPA_ItfRep extends CrudRepository<Orcamento,Long>{
    @SuppressWarnings("null")
    List<Orcamento> findAll();
    Optional<Orcamento> findById(long id);
}