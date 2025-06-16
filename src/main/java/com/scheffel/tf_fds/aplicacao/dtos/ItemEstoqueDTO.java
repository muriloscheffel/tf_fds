package com.scheffel.tf_fds.aplicacao.dtos;

public record ItemEstoqueDTO(long id,
  private ProdutoDTO produto,
  private int quantidade,
  private int estoqueMin,
  private int estoqueMax
){}
