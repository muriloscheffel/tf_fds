package com.scheffel.tf_fds.dominio.persistencia;

import java.time.LocalDate;
import java.util.List;

import com.scheffel.tf_fds.dominio.modelos.OrcamentoModel;

public interface IOrcamentoRepositorio {
    List<OrcamentoModel> todos();

    OrcamentoModel cadastra(OrcamentoModel orcamento);

    OrcamentoModel recuperaPorId(long id);

    void marcaComoEfetivado(long id);

    List<OrcamentoModel> findEfetivadosPorPeriodo(LocalDate inicio, LocalDate fim);
}