package com.scheffel.tf_fds.aplicacao.casosDeUso;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.scheffel.tf_fds.dominio.modelos.OrcamentoModel;
import com.scheffel.tf_fds.dominio.servicos.ServicoDeVendas;

@Component
public class BuscaOrcamentoUC {
        
    private ServicoDeVendas servicoDeVendas;
        
    public BuscaOrcamentoUC(ServicoDeVendas servicoDeVendas){
            this.servicoDeVendas = servicoDeVendas;
        }
    
    public OrcamentoModel run(long idOrcamento){
        return servicoDeVendas.buscaOrcamento(idOrcamento);
        }
    }
