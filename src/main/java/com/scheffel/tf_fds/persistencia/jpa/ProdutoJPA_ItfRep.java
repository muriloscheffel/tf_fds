package com.scheffel.tf_fds.persistencia.jpa;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.scheffel.tf_fds.persistencia.entity.Produto;

public interface ProdutoJPA_ItfRep extends CrudRepository<Produto, Long>{
    @SuppressWarnings("null")
    List<Produto> findAll();
    Produto findById(long id);
}
