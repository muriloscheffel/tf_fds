package com.scheffel.tf_fds.dominio.servicos.imposto;

import java.util.List;
import com.scheffel.tf_fds.dominio.modelos.ItemPedidoModel;

public class CalculadoraImpostoPE implements CalculadoraImposto {
    @Override
    public double calcular(double custoItens, List<ItemPedidoModel> itens) {
        double impostoTotal = 0.0;
        for (ItemPedidoModel item : itens) {
            double custoItem = item.getProduto().getPrecoUnitario() * item.getQuantidade();
            if (item.getProduto().getDescricao().endsWith("*")) {
                impostoTotal += custoItem * 0.05; // 5% para produtos essenciais
            } else {
                impostoTotal += custoItem * 0.15; // 15% para os demais
            }
        }
        return impostoTotal;
    }
}