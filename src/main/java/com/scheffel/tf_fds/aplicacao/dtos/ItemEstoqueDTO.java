package com.scheffel.tf_fds.aplicacao.dtos;

import com.scheffel.tf_fds.dominio.modelos.ItemDeEstoqueModel;

public record ItemEstoqueDTO(long id,
  ProdutoDTO produto,
  int quantidade,
  int estoqueMin,
  int estoqueMax
){
  public static ItemEstoqueDTO fromModel(ItemDeEstoqueModel model) {
    if (model == null) return null;
    return new ItemEstoqueDTO(
        model.getId(),
        ProdutoDTO.fromModel(model.getProduto()),
        model.getQuantidade(),
        model.getEstoqueMin(),
        model.getEstoqueMax()
    );
}

public static ItemDeEstoqueModel toModel(ItemEstoqueDTO item) {
    item.produto();
    return new ItemDeEstoqueModel(
        item.id(),
        ProdutoDTO.toModel(item.produto()),
        item.quantidade(),
        item.estoqueMin(),
        item.estoqueMax()
    );
}
}
