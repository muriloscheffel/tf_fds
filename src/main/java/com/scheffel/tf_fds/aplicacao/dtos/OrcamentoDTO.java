package com.scheffel.tf_fds.aplicacao.dtos;

import java.time.LocalDate;
import java.util.LinkedList;
import java.util.List;

import com.scheffel.tf_fds.dominio.modelos.ItemPedidoModel;
import com.scheffel.tf_fds.dominio.modelos.OrcamentoModel;

public class OrcamentoDTO {
    private long id;
    private String nomeCliente;
    private String estado;
    private LocalDate dataCriacao;
    private List<ItemPedidoDTO> itens;
    private double custoItens;
    private double imposto;
    private double desconto;
    private double custoConsumidor;
    private boolean efetivado;

    public OrcamentoDTO(OrcamentoModel orcamento) {
        this.id = orcamento.getId();
        this.nomeCliente = orcamento.getNomeCliente();
        this.estado = orcamento.getEstado();
        this.dataCriacao = orcamento.getDataCriacao();
        this.custoItens = orcamento.getCustoItens();
        this.imposto = orcamento.getImposto();
        this.desconto = orcamento.getDesconto();
        this.custoConsumidor = orcamento.getCustoConsumidor();
        this.efetivado = orcamento.isEfetivado();

        this.itens = new LinkedList<>();
        for (ItemPedidoModel item : orcamento.getItens()) {
            itens.add(new ItemPedidoDTO(item.getProduto().getId(), item.getQuantidade()));
        }
    }

    public String getNomeCliente() {
        return nomeCliente;
    }

    public String getEstado() {
        return estado;
    }

    public LocalDate getDataCriacao() {
        return dataCriacao;
    }

    public long getId() {
        return id;
    }

    public List<ItemPedidoDTO> getItens() {
        return itens;
    }

    public double getCustoItens() {
        return custoItens;
    }

    public double getImposto() {
        return imposto;
    }

    public double getDesconto() {
        return desconto;
    }

    public double getCustoConsumidor() {
        return custoConsumidor;
    }

    public boolean isEfetivado() {
        return efetivado;
    }

    public static OrcamentoDTO fromModel(OrcamentoModel orcamento) {
        return new OrcamentoDTO(orcamento);
    }
}