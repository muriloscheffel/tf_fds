package com.scheffel.tf_fds.adapterInterface.persistence.repositories.jpa;

import org.springframework.data.jpa.repository.JpaRepository;

import com.scheffel.tf_fds.adapterInterface.persistence.models.EstoqueEntity;

public interface EstoqueJpa extends JpaRepository<EstoqueEntity, Long> {

}
