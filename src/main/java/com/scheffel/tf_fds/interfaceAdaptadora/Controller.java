package com.scheffel.tf_fds.interfaceAdaptadora;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.scheffel.tf_fds.aplicacao.casosDeUso.BuscaOrcamentoUC;
import com.scheffel.tf_fds.aplicacao.casosDeUso.CadastraProdutoUC;
import com.scheffel.tf_fds.aplicacao.casosDeUso.ChegadaEstoqueUC;
import com.scheffel.tf_fds.aplicacao.casosDeUso.CriaOrcamentoUC;
import com.scheffel.tf_fds.aplicacao.casosDeUso.EfetivaOrcamentoUC;
import com.scheffel.tf_fds.aplicacao.casosDeUso.ProdutosDisponiveisUC;
import com.scheffel.tf_fds.aplicacao.dtos.ItemEstoqueDTO;
import com.scheffel.tf_fds.aplicacao.dtos.OrcamentoDTO;
import com.scheffel.tf_fds.aplicacao.dtos.ProdutoDTO;

@RestController
@CrossOrigin(origins = "*")
public class Controller {
    private ProdutosDisponiveisUC produtosDisponiveis;
    private CriaOrcamentoUC criaOrcamento;
    private EfetivaOrcamentoUC efetivaOrcamento;
    private BuscaOrcamentoUC buscaOrcamento;
    private CadastraProdutoUC cadastraProduto;
    private ChegadaEstoqueUC chegadaEstoque;

    public Controller(ProdutosDisponiveisUC produtosDisponiveis,
            CriaOrcamentoUC criaOrcamento,
            EfetivaOrcamentoUC efetivaOrcamento,
            BuscaOrcamentoUC buscaOrcamento,
            CadastraProdutoUC cadastraProduto,
            ChegadaEstoqueUC chegadaEstoque) {
        this.produtosDisponiveis = produtosDisponiveis;
        this.criaOrcamento = criaOrcamento;
        this.efetivaOrcamento = efetivaOrcamento;
        this.buscaOrcamento = buscaOrcamento;
        this.cadastraProduto = cadastraProduto;
        this.chegadaEstoque = chegadaEstoque;
    }

    @GetMapping("")
    public String welcomeMessage() {
        return ("Bem vindo as lojas ACME");
    }

    @GetMapping("/produtosDisponiveis")
    public List<ProdutoDTO> produtosDisponiveis() {
        return produtosDisponiveis.run();
    }

    @PostMapping("/novoOrcamento")
    public ResponseEntity<OrcamentoDTO> novoOrcamento(@RequestBody OrcamentoDTO novoOrcamento) {
        try {
            OrcamentoDTO orcamento = criaOrcamento.run(novoOrcamento);
            return ResponseEntity.ok(orcamento);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().header("Error-Message", e.getMessage()).build();
        }
    }

    @PostMapping("/efetivaOrcamento/{id}")
    public OrcamentoDTO efetivaOrcamento(@PathVariable(value = "id") long idOrcamento) {
        return efetivaOrcamento.run(idOrcamento);
    }

    @GetMapping("/buscaOrcamento/{id}")
    public OrcamentoDTO buscaOrcamento(@PathVariable(value = "id") long idOrcamento) {
        return buscaOrcamento.run(idOrcamento);
    }

    @PostMapping("/cadastraProduto")
    public ResponseEntity<ProdutoDTO> cadastraProduto(@RequestBody ProdutoDTO produto) {
        try {
            ProdutoDTO produtoCadastrado = cadastraProduto.run(produto);
            return ResponseEntity.ok(produtoCadastrado);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().header("Error-Message", e.getMessage()).build();
        }
    }

    @PostMapping("/chegadaNoEstoque")
    public ResponseEntity chegadaNoEstoque(@RequestBody ItemEstoqueDTO produto) {
        try {
            chegadaEstoque.run(produto);
            return ResponseEntity.ok().build();
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().header("Error-Message", e.getMessage()).build();
        }
    }

}