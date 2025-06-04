package com.scheffel.tf_fds.domain.models;

public class Estoque {
  private Produto produto;
  private int quantidade;
  private int quantMax;
  private int quantMin;

  public Produto getProduto() {
    return produto;
  }

  public void setProduto(Produto produto) {
    this.produto = produto;
  }

  public int getQuantidade() {
    return quantidade;
  }

  public void setQuantidade(int quantidade) {
    this.quantidade = quantidade;
  }

  public int getQuantMax() {
    return quantMax;
  }

  public void setQuantMax(int quantMax) {
    this.quantMax = quantMax;
  }

  public int getQuantMin() {
    return quantMin;
  }

  public void setQuantMin(int quantMin) {
    this.quantMin = quantMin;
  }

  public Estoque(Produto produto, int quantidade, int quantMax, int quantMin) {
    this.produto = produto;
    this.quantidade = quantidade;
    this.quantMax = quantMax;
    this.quantMin = quantMin;
  }

  public Estoque() {
  }
}
