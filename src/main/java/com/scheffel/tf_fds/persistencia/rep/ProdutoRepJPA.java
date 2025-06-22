package com.scheffel.tf_fds.persistencia.rep;

import java.util.LinkedList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Repository;

import com.scheffel.tf_fds.dominio.modelos.ProdutoModel;
import com.scheffel.tf_fds.dominio.persistencia.IProdutoRepositorio;
import com.scheffel.tf_fds.persistencia.entity.Produto;
import com.scheffel.tf_fds.persistencia.jpa.ProdutoJPA_ItfRep;

@Repository
@Primary
public class ProdutoRepJPA implements IProdutoRepositorio {

    @Autowired
    private ProdutoJPA_ItfRep produtoRepository;

    @Override
    public List<ProdutoModel> todos() {
        List<Produto> produtos = produtoRepository.findAll();
        if (produtos.size() == 0) {
            return new LinkedList<ProdutoModel>();
        } else {
            return produtos.stream()
                    .map(prod -> Produto.toProdutoModel(prod))
                    .toList();
        }
    }

    @Override
    public ProdutoModel consultaPorId(long id) {
        Produto produto = produtoRepository.findById(id);
        System.out.println("Produto de codigo: " + id + ": " + produto);
        if (produto == null) {
            return null;
        } else {
            return Produto.toProdutoModel(produto);
        }
    }

    @Override
    public ProdutoModel cadastraProduto(ProdutoModel produto) {
        if(produto == null) {
            throw new IllegalArgumentException("Produto não pode ser nulo");
        } else {
            Produto prodEntity = Produto.fromProdutoModel(produto);
            Produto savedProd = produtoRepository.save(prodEntity);
            return Produto.toProdutoModel(savedProd);
        }
    }
}
