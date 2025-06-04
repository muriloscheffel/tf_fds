package com.scheffel.tf_fds.adapterInterface.persistence.repositories.jpa;

import org.springframework.data.jpa.repository.JpaRepository;
import com.scheffel.tf_fds.adapterInterface.persistence.models.OrcamentoEntity;

public interface OrcamentoJpa extends JpaRepository<OrcamentoEntity, Long> {

}