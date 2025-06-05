package com.scheffel.tf_fds.dominio.persistencia;

import java.util.List;

import com.scheffel.tf_fds.dominio.modelos.ProdutoModel;

public interface IEstoqueRepositorio {
    List<ProdutoModel> todos();

    List<ProdutoModel> todosComEstoque();

    int quantidadeEmEstoque(long codigo);

    int baixaEstoque(long codProd, int qtdade);
}
