package com.scheffel.tf_fds.aplicacao.dtos;

public class ChegadaProdutoDTO {
    private long produtoId;
    private int quantidade;

    // Construtor, getters e setters
    public ChegadaProdutoDTO(long produtoId, int quantidade) {
        this.produtoId = produtoId;
        this.quantidade = quantidade;
    }

    public long getProdutoId() {
        return produtoId;
    }

    public void setProdutoId(long produtoId) {
        this.produtoId = produtoId;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }
}