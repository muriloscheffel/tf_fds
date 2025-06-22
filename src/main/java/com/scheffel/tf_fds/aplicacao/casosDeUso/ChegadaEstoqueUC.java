package com.scheffel.tf_fds.aplicacao.casosDeUso;

import org.springframework.stereotype.Component;
import com.scheffel.tf_fds.aplicacao.dtos.ChegadaProdutoDTO;
import com.scheffel.tf_fds.dominio.servicos.ServicoDeEstoque;

@Component
public class ChegadaEstoqueUC {
  
  private ServicoDeEstoque servicoDeEstoque;

  public ChegadaEstoqueUC(ServicoDeEstoque servicoDeEstoque){
    this.servicoDeEstoque = servicoDeEstoque;
  }

  public void run(ChegadaProdutoDTO chegada) {
    servicoDeEstoque.registrarEntradaEstoque(chegada.getProdutoId(), chegada.getQuantidade());
  }
}