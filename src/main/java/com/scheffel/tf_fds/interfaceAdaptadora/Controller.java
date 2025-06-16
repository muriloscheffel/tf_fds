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
import com.scheffel.tf_fds.aplicacao.casosDeUso.CriaOrcamentoUC;
import com.scheffel.tf_fds.aplicacao.casosDeUso.EfetivaOrcamentoUC;
import com.scheffel.tf_fds.aplicacao.casosDeUso.ProdutosDisponiveisUC;
import com.scheffel.tf_fds.aplicacao.dtos.ItemPedidoDTO;
import com.scheffel.tf_fds.aplicacao.dtos.OrcamentoDTO;
import com.scheffel.tf_fds.aplicacao.dtos.ProdutoDTO;
import com.scheffel.tf_fds.dominio.modelos.ItemDeEstoqueModel;
import com.scheffel.tf_fds.dominio.modelos.OrcamentoModel;
import com.scheffel.tf_fds.persistencia.entity.ItemDeEstoque;
import com.scheffel.tf_fds.persistencia.rep.EstoqueRepJPA;

/*  Retornar o catálogo de produtos
Solicitar orçamento para um pedido (lista de itens)
Efetivar orçamento indicado se ainda for válido e houver produtos disponíveis
Informar a chegada de produtos no estoque
Retornar a quantidade disponível no estoque para todos os itens do catálogo
Retornar a quantidade disponível no estoque para uma lista de produtos informados
Retornar a lista de orçamentos efetivados em um determinado período (informar data
inicial e data final)
Retornar cada uma das consultas definidas pelo grupo
*/

@RestController
public class Controller {
    private ProdutosDisponiveisUC produtosDisponiveis;
    private CriaOrcamentoUC criaOrcamento;
    private EfetivaOrcamentoUC efetivaOrcamento;
    private BuscaOrcamentoUC buscaOrcamento;
    private EstoqueRepJPA estoqueRepJPA;

    public Controller(ProdutosDisponiveisUC produtosDisponiveis,
            CriaOrcamentoUC criaOrcamento,
            EfetivaOrcamentoUC efetivaOrcamento,
            BuscaOrcamentoUC buscaOrcamento, EstoqueRepJPA estoqueRepJPA) {
        this.produtosDisponiveis = produtosDisponiveis;
        this.criaOrcamento = criaOrcamento;
        this.efetivaOrcamento = efetivaOrcamento;
        this.buscaOrcamento = buscaOrcamento;
    }

    @GetMapping("")
    @CrossOrigin(origins = "*")
    public String welcomeMessage() {
        return ("Bem vindo as lojas ACME");
    }

    @GetMapping("produtosDisponiveis")
    @CrossOrigin(origins = "*")
    public List<ProdutoDTO> produtosDisponiveis() {
        return produtosDisponiveis.run();
    }

    @PostMapping("novoOrcamento")
    @CrossOrigin(origins = "*")
    public OrcamentoDTO novoOrcamento(@RequestBody List<ItemPedidoDTO> itens) {
        return criaOrcamento.run(itens);
    }

    @GetMapping("efetivaOrcamento/{id}")
    @CrossOrigin(origins = "*")
    public OrcamentoDTO efetivaOrcamento(@PathVariable(value = "id") long idOrcamento) {
        return efetivaOrcamento.run(idOrcamento);
    }

    @GetMapping("buscaOrcamento/{id}")
    @CrossOrigin(origins = "*")
    public OrcamentoModel buscaOrcamento(@PathVariable(value = "id") long idOrcamento) {
        return buscaOrcamento.run(idOrcamento);
    }

    @GetMapping("testes")
    @CrossOrigin(origins = "*")
    public ResponseEntity<ItemDeEstoque> testes(@RequestBody ItemDeEstoqueModel produto) {
        ItemDeEstoque item = estoqueRepJPA.chegadaProduto(produto);
        return ResponseEntity.ok(item); 
    }
}