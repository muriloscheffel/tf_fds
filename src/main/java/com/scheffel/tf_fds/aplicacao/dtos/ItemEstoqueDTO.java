package com.scheffel.tf_fds.aplicacao.dtos;

public record ItemEstoqueDTO(long id,
  ProdutoDTO produto,
  int quantidade,
  int estoqueMin,
  int estoqueMax
){}
