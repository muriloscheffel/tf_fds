package com.scheffel.tf_fds.config;

import java.util.Arrays;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.scheffel.tf_fds.persistencia.entity.ItemDeEstoque;
import com.scheffel.tf_fds.persistencia.entity.Produto;
import com.scheffel.tf_fds.persistencia.jpa.EstoqueJPA_ItfRep;
import com.scheffel.tf_fds.persistencia.jpa.ProdutoJPA_ItfRep;

@Component
public class DatabaseSeeder implements CommandLineRunner {

    private final ProdutoJPA_ItfRep produtoRepository;
    private final EstoqueJPA_ItfRep estoqueRepository;

    public DatabaseSeeder(ProdutoJPA_ItfRep produtoRepository, EstoqueJPA_ItfRep estoqueRepository) {
        this.produtoRepository = produtoRepository;
        this.estoqueRepository = estoqueRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        System.out.println("Populando o banco de dados...");

        // Criar produtos
        Produto p1 = new Produto(1L, "Teclado Mecânico RGB", 350.00);
        Produto p2 = new Produto(2L, "Mouse Gamer 16000DPI", 180.50);
        Produto p3 = new Produto(3L, "Monitor Ultrawide 29'", 1200.00);
        Produto p4 = new Produto(4L, "Arroz*", 25.00); // Produto essencial para teste de imposto PE
        Produto p5 = new Produto(5L, "Webcam 4k", 800.00);

        produtoRepository.saveAll(Arrays.asList(p1, p2, p3, p4, p5));

        // Criar itens de estoque
        ItemDeEstoque ie1 = new ItemDeEstoque(1L, p1, 50, 10, 100);
        ItemDeEstoque ie2 = new ItemDeEstoque(2L, p2, 70, 15, 120);
        ItemDeEstoque ie3 = new ItemDeEstoque(3L, p3, 20, 5, 40);
        ItemDeEstoque ie4 = new ItemDeEstoque(4L, p4, 200, 50, 500);
        ItemDeEstoque ie5 = new ItemDeEstoque(5L, p5, 0, 5, 30); // Produto sem estoque

        estoqueRepository.saveAll(Arrays.asList(ie1, ie2, ie3, ie4, ie5));

        System.out.println("Banco de dados populado com sucesso!");
    }
}