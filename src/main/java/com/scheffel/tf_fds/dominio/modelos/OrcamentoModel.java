package com.scheffel.tf_fds.dominio.modelos;

import java.time.LocalDate;
import java.util.LinkedList;
import java.util.List;

public class OrcamentoModel {
    private long id;
    private String nomeCliente;
    private String estado;
    private String pais;
    private LocalDate dataCriacao;
    private List<ItemPedidoModel> itens = new LinkedList<>();
    private double custoItens;
    private double imposto;
    private double desconto;
    private double custoConsumidor;
    private boolean efetivado;

    public OrcamentoModel(long id, String nomeCliente, String estado, String pais, LocalDate dataCriacao) {
        this.id = id;
        this.nomeCliente = nomeCliente;
        this.estado = estado;
        this.pais = pais;
        this.dataCriacao = dataCriacao;
        this.itens = new LinkedList<>();
        this.efetivado = false;
    }

    public OrcamentoModel() {
        this.itens = new LinkedList<>();
        this.efetivado = false;
    }

    public String getNomeCliente() {
        return nomeCliente;
    }

    public void setNomeCliente(String nomeCliente) {
        this.nomeCliente = nomeCliente;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getPais() {
        return pais;
    }

    public void setPais(String pais) {
        this.pais = pais;
    }

    public LocalDate getDataCriacao() {
        return dataCriacao;
    }

    public void setDataCriacao(LocalDate dataCriacao) {
        this.dataCriacao = dataCriacao;
    }

    public void addItensPedido(ItemPedidoModel item) {
        itens.add(item);
    }

    public void addItensPedido(PedidoModel pedido) {
        for (ItemPedidoModel itemPedido : pedido.getItens()) {
            itens.add(itemPedido);
        }
    }

    public List<ItemPedidoModel> getItens() {
        return itens;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public double getCustoItens() {
        return custoItens;
    }

    public void setCustoItens(double custoItens) {
        this.custoItens = custoItens;
    }

    public double getImposto() {
        return imposto;
    }

    public void setImposto(double imposto) {
        this.imposto = imposto;
    }

    public double getDesconto() {
        return desconto;
    }

    public void setDesconto(double desconto) {
        this.desconto = desconto;
    }

    public double getCustoConsumidor() {
        return custoConsumidor;
    }

    public void setCustoConsumidor(double custoConsumidor) {
        this.custoConsumidor = custoConsumidor;
    }

    public boolean isEfetivado() {
        return efetivado;
    }

    public void efetiva() {
        efetivado = true;
    }
}