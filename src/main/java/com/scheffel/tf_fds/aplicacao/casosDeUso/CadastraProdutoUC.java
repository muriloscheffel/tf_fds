package com.scheffel.tf_fds.aplicacao.casosDeUso;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.scheffel.tf_fds.aplicacao.dtos.ProdutoDTO;
import com.scheffel.tf_fds.dominio.modelos.ProdutoModel;
import com.scheffel.tf_fds.dominio.servicos.ServicoDeEstoque;

@Component
public class CadastraProdutoUC {

    @Autowired
    private ServicoDeEstoque servicoEstoque;

    public ProdutoDTO run(ProdutoDTO produto) {
        ProdutoModel prodMod = ProdutoDTO.toModel(produto);
        servicoEstoque.cadastraProduto(prodMod);

        return produto;
    }
}
