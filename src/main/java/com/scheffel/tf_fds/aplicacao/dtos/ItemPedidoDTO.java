package com.scheffel.tf_fds.aplicacao.dtos;

import com.scheffel.tf_fds.dominio.modelos.ItemPedidoModel;

public class ItemPedidoDTO {
    private long idProduto;
    private int qtdade;

    public ItemPedidoDTO(long idProduto, int qtdade) {
        this.idProduto = idProduto;
        this.qtdade = qtdade;
    }

    public long getIdProduto() {
        return idProduto;
    }

    public int getQtdade() {
        return qtdade;
    }

    @Override
    public String toString() {
        return "ItemPedidoDTO [idProduto=" + idProduto + ", qtdade=" + qtdade + "]";
    }

    public static ItemPedidoDTO fromModel(ItemPedidoModel model) {
        if (model == null)
            return null;
        return new ItemPedidoDTO(
                model.getProduto().getId(),
                model.getQuantidade());
    }
}
