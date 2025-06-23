package com.scheffel.tf_fds.interfaceAdaptadora;

import java.time.LocalDate;
import java.util.List;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.scheffel.tf_fds.aplicacao.casosDeUso.BuscaOrcamentoUC;
import com.scheffel.tf_fds.aplicacao.casosDeUso.ChegadaEstoqueUC;
import com.scheffel.tf_fds.aplicacao.casosDeUso.ConsultaEstoqueUC;
import com.scheffel.tf_fds.aplicacao.casosDeUso.ConsultaOrcamentosEfetivadosUC;
import com.scheffel.tf_fds.aplicacao.casosDeUso.CriaOrcamentoUC;
import com.scheffel.tf_fds.aplicacao.casosDeUso.EfetivaOrcamentoUC;
import com.scheffel.tf_fds.aplicacao.casosDeUso.ProdutosDisponiveisUC;
import com.scheffel.tf_fds.aplicacao.dtos.ChegadaProdutoDTO;
import com.scheffel.tf_fds.aplicacao.dtos.ItemEstoqueViewDTO;
import com.scheffel.tf_fds.aplicacao.dtos.NovoOrcamentoDTO;
import com.scheffel.tf_fds.aplicacao.dtos.OrcamentoDTO;

@RestController
@CrossOrigin(origins = "*")
public class Controller {
    private ProdutosDisponiveisUC produtosDisponiveis;
    private CriaOrcamentoUC criaOrcamento;
    private EfetivaOrcamentoUC efetivaOrcamento;
    private BuscaOrcamentoUC buscaOrcamento;
    private ChegadaEstoqueUC chegadaEstoque;
    private ConsultaEstoqueUC consultaEstoque;
    private ConsultaOrcamentosEfetivadosUC consultaOrcamentosEfetivados;

    public Controller(ProdutosDisponiveisUC produtosDisponiveis,
            CriaOrcamentoUC criaOrcamento,
            EfetivaOrcamentoUC efetivaOrcamento,
            BuscaOrcamentoUC buscaOrcamento,
            ChegadaEstoqueUC chegadaEstoque,
            ConsultaEstoqueUC consultaEstoque,
            ConsultaOrcamentosEfetivadosUC consultaOrcamentosEfetivados) {
        this.produtosDisponiveis = produtosDisponiveis;
        this.criaOrcamento = criaOrcamento;
        this.efetivaOrcamento = efetivaOrcamento;
        this.buscaOrcamento = buscaOrcamento;
        this.chegadaEstoque = chegadaEstoque;
        this.consultaEstoque = consultaEstoque;
        this.consultaOrcamentosEfetivados = consultaOrcamentosEfetivados;
    }

    @PostMapping("/novoOrcamento")
    public ResponseEntity<OrcamentoDTO> novoOrcamento(@RequestBody NovoOrcamentoDTO novoOrcamento) {
        try {
            OrcamentoDTO orcamento = criaOrcamento.run(novoOrcamento);
            return ResponseEntity.ok(orcamento);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().header("Error-Message", e.getMessage()).build();
        }
    }

    @PostMapping("/efetivaOrcamento/{id}")
    public ResponseEntity efetivaOrcamento(@PathVariable(value = "id") long idOrcamento) {
        try {
            OrcamentoDTO orcamento = efetivaOrcamento.run(idOrcamento);
            return ResponseEntity.ok(orcamento);
        } catch (IllegalArgumentException | IllegalStateException e) {
            return ResponseEntity.badRequest().header("Error-Message", e.getMessage()).build();
        }
    }

    @GetMapping("/buscaOrcamento/{id}")
    public OrcamentoDTO buscaOrcamento(@PathVariable(value = "id") long idOrcamento) {
        return buscaOrcamento.run(idOrcamento);
    }

    @PostMapping("/estoque/chegada")
    public ResponseEntity chegadaDeProdutos(@RequestBody ChegadaProdutoDTO chegada) {
        try {
            chegadaEstoque.run(chegada);
            return ResponseEntity.ok().build();
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @GetMapping("/estoque")
    public List<ItemEstoqueViewDTO> consultaEstoqueCompleto() {
        return consultaEstoque.run();
    }

    @GetMapping("/estoque/produto/{id}")
    public ItemEstoqueViewDTO consultaEstoquePorProduto(@PathVariable(value = "id") long idProduto) {
        return consultaEstoque.run(idProduto);
    }

    @GetMapping("/orcamentos/efetivados")
    public List<OrcamentoDTO> consultaOrcamentosEfetivados(
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate inicio,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate fim) {
        return consultaOrcamentosEfetivados.run(inicio, fim);
    }

}