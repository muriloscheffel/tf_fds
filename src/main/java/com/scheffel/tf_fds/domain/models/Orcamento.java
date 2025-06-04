package com.scheffel.tf_fds.domain.models;

import java.util.Date;
import java.util.List;

public class Orcamento {
  private Long id;
  private Date data;
  private String nomeCliente;
  private List<Produto> itens;
  private String estadoEntrega;
  private String paisEntrega;
  private double valorTotalItens;
  private double impostoEstadual;
  private double impostoFederal;
  private double desconto;
  private double valorFinal;

  public Long getId() {
    return this.id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public Date getData() {
    return this.data;
  }

  public void setData(Date data) {
    this.data = data;
  }

  public String getNomeCliente() {
    return this.nomeCliente;
  }

  public void setNomeCliente(String nomeCliente) {
    this.nomeCliente = nomeCliente;
  }

  public List<Produto> getItens() {
    return this.itens;
  }

  public void setItens(List<Produto> itens) {
    this.itens = itens;
  }

  public String getEstadoEntrega() {
    return this.estadoEntrega;
  }

  public void setEstadoEntrega(String estadoEntrega) {
    this.estadoEntrega = estadoEntrega;
  }

  public String getPaisEntrega() {
    return this.paisEntrega;
  }

  public void setPaisEntrega(String paisEntrega) {
    this.paisEntrega = paisEntrega;
  }

  public double getValorTotalItens() {
    return this.valorTotalItens;
  }

  public void setValorTotalItens(double valorTotalItens) {
    this.valorTotalItens = valorTotalItens;
  }

  public double getImpostoEstadual() {
    return this.impostoEstadual;
  }

  public void setImpostoEstadual(double impostoEstadual) {
    this.impostoEstadual = impostoEstadual;
  }

  public double getImpostoFederal() {
    return this.impostoFederal;
  }

  public void setImpostoFederal(double impostoFederal) {
    this.impostoFederal = impostoFederal;
  }

  public double getDesconto() {
    return this.desconto;
  }

  public void setDesconto(double desconto) {
    this.desconto = desconto;
  }

  public double getValorFinal() {
    return this.valorFinal;
  }

  public void setValorFinal(double valorFinal) {
    this.valorFinal = valorFinal;
  }
}
