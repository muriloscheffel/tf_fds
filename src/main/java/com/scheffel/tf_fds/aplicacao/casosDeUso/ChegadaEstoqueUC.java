package com.scheffel.tf_fds.aplicacao.casosDeUso;

import org.springframework.beans.factory.annotation.Autowired;

import com.scheffel.tf_fds.dominio.modelos.ItemDeEstoqueModel;
import com.scheffel.tf_fds.dominio.servicos.ServicoDeEstoque;

public class ChegadaEstoqueUC {
  @Autowired
  private ServicoDeEstoque servicoDeEstoque;

  public boolean run(ItemDeEstoqueModel item) {
    
  }
}
