package com.scheffel.tf_fds.persistencia.jpa;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.scheffel.tf_fds.persistencia.entity.ItemDeEstoque;

public interface EstoqueJPA_ItfRep extends CrudRepository<ItemDeEstoque,Long>{
    @SuppressWarnings("null")
    List<ItemDeEstoque> findAll();
    ItemDeEstoque findById(long id);
}
