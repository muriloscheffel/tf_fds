package com.scheffel.tf_fds.aplicacao.dtos;

public record ItemEstoqueViewDTO(
    long produtoId,
    String descricao,
    int quantidadeDisponivel
) {}