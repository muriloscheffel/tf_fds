package com.scheffel.tf_fds.dominio.servicos;

import java.util.List;

import org.springframework.stereotype.Service;

import com.scheffel.tf_fds.dominio.persistencia.IEstoqueRepositorio;
import com.scheffel.tf_fds.dominio.persistencia.IProdutoRepositorio;
import com.scheffel.tf_fds.dominio.modelos.ItemDeEstoqueModel;
import com.scheffel.tf_fds.dominio.modelos.ProdutoModel;

@Service
public class ServicoDeEstoque {
    private IEstoqueRepositorio estoque;
    private IProdutoRepositorio produtos;

    public ServicoDeEstoque(IProdutoRepositorio produtos, IEstoqueRepositorio estoque) {
        this.produtos = produtos;
        this.estoque = estoque;
    }

    public List<ProdutoModel> produtosDisponiveis() {
        return estoque.todosComEstoque();
    }

    public ProdutoModel produtoPorCodigo(long id) {
        return this.produtos.consultaPorId(id);
    }

    public int qtdadeEmEstoque(long id) {
        System.out.println("--qtEstoque: " + id);
        int qtde = estoque.quantidadeEmEstoque(id);
        System.out.println("--qtEstoque: " + id + " qtde: " + qtde);
        return qtde;
    }

    public void baixaEstoque(long id, int qtdade) {
        System.out.println("--qtEstoque: " + id);
        estoque.baixaEstoque(id, qtdade);
    }

    public boolean chegadaEstoque(ItemDeEstoqueModel item) {
        if (item == null || item.getProduto() == null) {
            throw new IllegalArgumentException("Item ou produto inválido");
        }
        if (item.getQuantidade() <= 0) {
            throw new IllegalArgumentException("Quantidade deve ser maior que zero");
        }
        return estoque.chegadaProduto(item) != null;
    }
}
