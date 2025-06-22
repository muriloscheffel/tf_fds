package com.scheffel.tf_fds.dominio.servicos.imposto;

import java.util.List;
import com.scheffel.tf_fds.dominio.modelos.ItemPedidoModel;

public class CalculadoraImpostoSP implements CalculadoraImposto {
    @Override
    public double calcular(double custoItens, List<ItemPedidoModel> itens) {
        return custoItens * 0.12;
    }
}