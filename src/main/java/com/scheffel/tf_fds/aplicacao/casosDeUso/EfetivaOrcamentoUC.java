package com.scheffel.tf_fds.aplicacao.casosDeUso;

import org.springframework.stereotype.Component;

import com.scheffel.tf_fds.aplicacao.dtos.OrcamentoDTO;
import com.scheffel.tf_fds.dominio.modelos.OrcamentoModel;
import com.scheffel.tf_fds.dominio.servicos.ServicoDeVendas;

@Component
public class EfetivaOrcamentoUC {
    private ServicoDeVendas servicoDeVendas;

    public EfetivaOrcamentoUC(ServicoDeVendas servicoDeVendas) {
        this.servicoDeVendas = servicoDeVendas;
    }

    public OrcamentoDTO run(long idOrcamento) {
        OrcamentoModel orcamento = servicoDeVendas.efetivaOrcamento(idOrcamento);

        return OrcamentoDTO.fromModel(orcamento);
    }
}