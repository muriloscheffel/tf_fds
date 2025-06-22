package com.scheffel.tf_fds.dominio.servicos.imposto;

import java.util.List;
import com.scheffel.tf_fds.dominio.modelos.ItemPedidoModel;

public class CalculadoraImpostoRS implements CalculadoraImposto {
    @Override
    public double calcular(double custoItens, List<ItemPedidoModel> itens) {
        if (custoItens > 100.0) {
            return (custoItens - 100.0) * 0.10;
        }
        return 0.0;
    }
}