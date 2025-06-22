package com.scheffel.tf_fds.aplicacao.casosDeUso;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import com.scheffel.tf_fds.aplicacao.dtos.OrcamentoDTO;
import com.scheffel.tf_fds.dominio.modelos.OrcamentoModel;
import com.scheffel.tf_fds.dominio.servicos.ServicoDeVendas;

@Component
public class ConsultaOrcamentosEfetivadosUC {
    private ServicoDeVendas servicoDeVendas;

    public ConsultaOrcamentosEfetivadosUC(ServicoDeVendas servicoDeVendas) {
        this.servicoDeVendas = servicoDeVendas;
    }

    public List<OrcamentoDTO> run(LocalDate dataInicio, LocalDate dataFim) {
        List<OrcamentoModel> orcamentos = servicoDeVendas.consultaOrcamentosEfetivados(dataInicio, dataFim);
        return orcamentos.stream()
                         .map(OrcamentoDTO::fromModel)
                         .collect(Collectors.toList());
    }
}