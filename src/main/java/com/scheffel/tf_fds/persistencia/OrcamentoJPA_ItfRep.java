package com.scheffel.tf_fds.persistencia;

import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

public interface OrcamentoJPA_ItfRep extends CrudRepository<Orcamento,Long>{
    @SuppressWarnings("null")
    List<Orcamento> findAll();
    Optional<Orcamento> findById(long id);
}