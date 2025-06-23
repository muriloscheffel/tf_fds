package com.scheffel.tf_fds.persistencia.rep;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Repository;

import com.scheffel.tf_fds.dominio.modelos.ItemDeEstoqueModel;
import com.scheffel.tf_fds.dominio.modelos.ProdutoModel;
import com.scheffel.tf_fds.dominio.persistencia.IEstoqueRepositorio;
import com.scheffel.tf_fds.persistencia.entity.ItemDeEstoque;
import com.scheffel.tf_fds.persistencia.entity.Produto;
import com.scheffel.tf_fds.persistencia.jpa.EstoqueJPA_ItfRep;
import com.scheffel.tf_fds.persistencia.jpa.ProdutoJPA_ItfRep;

import jakarta.persistence.EntityNotFoundException;

@Repository
@Primary
public class EstoqueRepJPA implements IEstoqueRepositorio {

    @Autowired
    private EstoqueJPA_ItfRep estoque;
    @Autowired
    private ProdutoJPA_ItfRep produtos;

    @Override
    public List<ProdutoModel> todos() {
        List<ItemDeEstoque> itens = estoque.findAll();
        return itens.stream()
                .map(it -> Produto.toProdutoModel(it.getProduto()))
                .toList();
    }

    @Override
    public List<ProdutoModel> todosComEstoque() {
        List<ItemDeEstoque> itens = estoque.findAll();
        return itens.stream()
                .filter(it -> it.getQuantidade() > 0)
                .map(it -> Produto.toProdutoModel(it.getProduto()))
                .toList();
    }

    @Override
    public int quantidadeEmEstoque(long codigo) {
        ItemDeEstoque item = this.findByProdId(codigo);
        System.out.println("item: " + item);
        if (item == null) {
            return -1;
        } else {
            return item.getQuantidade();
        }

    }

    @Override
    public int baixaEstoque(long codProd, int qtdade) {
        ItemDeEstoque item = this.findByProdId(codProd);
        if (item == null) {
            throw new IllegalArgumentException("Produto inexistente");
        }
        if (item.getQuantidade() < qtdade) {
            throw new IllegalArgumentException("Quantidade em estoque insuficiente");
        }
        int novaQuantidade = item.getQuantidade() - qtdade;
        item.setQuantidade(novaQuantidade);
        estoque.save(item);
        return novaQuantidade;
    }

    private ItemDeEstoque findByProdId(long cod) {
        return estoque.findAll().stream()
                .filter(it -> it.getProduto().getId() == cod)
                .findFirst()
                .orElse(null);
    }

    @Override
    public ItemDeEstoque chegadaProduto(ItemDeEstoqueModel produto) {
        Produto prod = produtos.findById(produto.getProduto().getId());
        if (prod == null) {
            throw new EntityNotFoundException("Produto com ID " + produto.getProduto().getId() + " não encontrado.");
        }
        ItemDeEstoque itemExistente = findByProdId(prod.getId());
        if (itemExistente != null) {
            itemExistente.setQuantidade(itemExistente.getQuantidade() + produto.getQuantidade());
            return estoque.save(itemExistente);
        }

        ItemDeEstoque novoItem = new ItemDeEstoque(
                produto.getId(),
                prod,
                produto.getQuantidade(),
                produto.getEstoqueMin(),
                produto.getEstoqueMax());
        return estoque.save(novoItem);
    }

    @Override
    public void entradaEstoque(long codProd, int qtdade) {
        ItemDeEstoque item = this.findByProdId(codProd);
        if (item == null) {
            throw new IllegalArgumentException("Produto com código " + codProd + " não cadastrado no estoque.");
        }
        item.setQuantidade(item.getQuantidade() + qtdade);
        estoque.save(item);
    }

    @Override
    public List<ItemDeEstoqueModel> todosItensDeEstoque() {
        return estoque.findAll()
                .stream()
                .map(item -> new ItemDeEstoqueModel(
                        item.getId(),
                        Produto.toProdutoModel(item.getProduto()),
                        item.getQuantidade(),
                        item.getEstoqueMin(),
                        item.getEstoqueMax()))
                .toList();
    }
}