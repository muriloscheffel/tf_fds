package com.scheffel.tf_fds.dominio.persistencia;

import java.util.List;

import com.scheffel.tf_fds.dominio.modelos.ItemDeEstoqueModel;
import com.scheffel.tf_fds.dominio.modelos.ProdutoModel;
import com.scheffel.tf_fds.persistencia.entity.ItemDeEstoque;

public interface IEstoqueRepositorio {
    List<ProdutoModel> todos();

    List<ProdutoModel> todosComEstoque();

    int quantidadeEmEstoque(long codigo);

    int baixaEstoque(long codProd, int qtdade);

    ItemDeEstoque chegadaProduto(ItemDeEstoqueModel produto);
}
