package com.scheffel.tf_fds.aplicacao.casosDeUso;

import java.util.List;
import org.springframework.stereotype.Component;
import com.scheffel.tf_fds.aplicacao.dtos.ItemEstoqueViewDTO;
import com.scheffel.tf_fds.dominio.servicos.ServicoDeEstoque;

@Component
public class ConsultaEstoqueUC {
    private ServicoDeEstoque servicoDeEstoque;

    public ConsultaEstoqueUC(ServicoDeEstoque servicoDeEstoque) {
        this.servicoDeEstoque = servicoDeEstoque;
    }

    public List<ItemEstoqueViewDTO> run() {
        return servicoDeEstoque.estoqueDeTodosOsProdutos();
    }

    public ItemEstoqueViewDTO run(long produtoId) {
        return servicoDeEstoque.estoquePorProduto(produtoId);
    }
}