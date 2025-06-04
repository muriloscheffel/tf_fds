package com.scheffel.tf_fds.domain.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.scheffel.tf_fds.adapterInterface.persistence.models.EstoqueEntity;
import com.scheffel.tf_fds.adapterInterface.persistence.models.ProdutoEntity;
import com.scheffel.tf_fds.adapterInterface.persistence.repositories.EstoqueRepository;
import com.scheffel.tf_fds.adapterInterface.persistence.repositories.ProdutoRepository;
import com.scheffel.tf_fds.domain.models.Estoque;
import com.scheffel.tf_fds.domain.models.Produto;

@Service
public class EstoqueService {
    private EstoqueRepository estoqueRepository;
    private ProdutoRepository produtosRepository;

    public EstoqueService(ProdutoRepository produtosRepository, EstoqueRepository estoqueRepository) {
        this.estoqueRepository = estoqueRepository;
        this.produtosRepository = produtosRepository;
    }

    public List<ProdutoEntity> produtosDisponiveis() {
        return estoqueRepository.findAll()
                .stream()
                .filter(item -> item.getQuantidade() > 0)
                .map(EstoqueEntity::getProduto)
                .toList();
    }

    public ProdutoEntity produtoPorCodigo(long id) {
        return this.produtosRepository.findById(id).orElse(null);
    }

    public int qtdadeEmEstoque(long id) {
        EstoqueEntity item = estoqueRepository.findById(id).orElse(null);
        if (item != null) {
            return item.getQuantidade();
        }
        return 0;
    }

    public void baixaEstoque(long id, int qtdade) {
        EstoqueEntity item = estoqueRepository.findById(id).orElse(null);
        if (item != null) {
            item.setQuantidade(item.getQuantidade() - qtdade);
            estoqueRepository.save(item);
        }
    }

    public List<EstoqueEntity> itensEmEstoque() {
        return estoqueRepository.findAll();
    }

    public void entradaEstoque(long id, int qtdade) {
        // TODO: Implementar
    }
}
