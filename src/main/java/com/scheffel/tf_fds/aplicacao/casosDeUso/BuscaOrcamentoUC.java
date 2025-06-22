package com.scheffel.tf_fds.aplicacao.casosDeUso;

import com.scheffel.tf_fds.aplicacao.dtos.OrcamentoDTO;
import org.springframework.stereotype.Component;

import com.scheffel.tf_fds.dominio.modelos.OrcamentoModel;
import com.scheffel.tf_fds.dominio.servicos.ServicoDeVendas;

@Component
public class BuscaOrcamentoUC {

    private ServicoDeVendas servicoDeVendas;

    public BuscaOrcamentoUC(ServicoDeVendas servicoDeVendas) {
        this.servicoDeVendas = servicoDeVendas;
    }

    public OrcamentoDTO run(long idOrcamento) {
        OrcamentoModel orcModel = servicoDeVendas.buscaOrcamento(idOrcamento);

        return OrcamentoDTO.fromModel(orcModel);
    }
}
