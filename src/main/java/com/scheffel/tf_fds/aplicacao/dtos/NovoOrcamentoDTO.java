package com.scheffel.tf_fds.aplicacao.dtos;

import java.util.List;

public class NovoOrcamentoDTO {
    private String nomeCliente;
    private String estado;
    private List<ItemPedidoDTO> itens;

    public NovoOrcamentoDTO(String nomeCliente, String estado, List<ItemPedidoDTO> itens) {
        this.nomeCliente = nomeCliente;
        this.estado = estado;
        this.itens = itens;
    }

    public String getNomeCliente() {
        return nomeCliente;
    }

    public String getEstado() {
        return estado;
    }

    public List<ItemPedidoDTO> getItens() {
        return itens;
    }
}