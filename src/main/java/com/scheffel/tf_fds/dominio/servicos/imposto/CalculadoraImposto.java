package com.scheffel.tf_fds.dominio.servicos.imposto;

import java.util.List;
import com.scheffel.tf_fds.dominio.modelos.ItemPedidoModel;

public interface CalculadoraImposto {
    double calcular(double custoItens, List<ItemPedidoModel> itens);
}