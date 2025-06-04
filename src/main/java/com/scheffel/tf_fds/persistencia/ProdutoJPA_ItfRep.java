package com.scheffel.tf_fds.persistencia;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

public interface ProdutoJPA_ItfRep extends CrudRepository<Produto, Long>{
    @SuppressWarnings("null")
    List<Produto> findAll();
    Produto findById(long id);
}
