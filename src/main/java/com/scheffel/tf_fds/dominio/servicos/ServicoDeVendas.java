package com.scheffel.tf_fds.dominio.servicos;

import java.time.LocalDate;
import java.util.List;

import org.springframework.stereotype.Service;

import com.scheffel.tf_fds.dominio.modelos.ItemPedidoModel;
import com.scheffel.tf_fds.dominio.modelos.OrcamentoModel;
import com.scheffel.tf_fds.dominio.modelos.PedidoModel;
import com.scheffel.tf_fds.dominio.modelos.ProdutoModel;
import com.scheffel.tf_fds.dominio.persistencia.IEstoqueRepositorio;
import com.scheffel.tf_fds.dominio.persistencia.IOrcamentoRepositorio;
import com.scheffel.tf_fds.dominio.servicos.imposto.CalculadoraImposto;
import com.scheffel.tf_fds.dominio.servicos.imposto.CalculadoraImpostoFactory;

@Service
public class ServicoDeVendas {
    private IOrcamentoRepositorio orcamentos;
    private IEstoqueRepositorio estoque;
    private CalculadoraImpostoFactory calculadoraImpostoFactory;

    public ServicoDeVendas(IOrcamentoRepositorio orcamentos, IEstoqueRepositorio estoque,
            CalculadoraImpostoFactory calculadoraImpostoFactory) {
        this.orcamentos = orcamentos;
        this.estoque = estoque;
        this.calculadoraImpostoFactory = calculadoraImpostoFactory;
    }

    public List<ProdutoModel> produtosDisponiveis() {
        return estoque.todosComEstoque();
    }

    public OrcamentoModel recuperaOrcamentoPorId(long id) {
        return this.orcamentos.recuperaPorId(id);
    }

    public OrcamentoModel criaOrcamento(PedidoModel pedido, String nomeCliente, String estado) {
        var novoOrcamento = new OrcamentoModel();
        novoOrcamento.addItensPedido(pedido);
        novoOrcamento.setNomeCliente(nomeCliente);
        novoOrcamento.setEstado(estado);
        novoOrcamento.setPais("BRASIL"); // Fixo por enquanto
        novoOrcamento.setDataCriacao(LocalDate.now());

        double custoItens = novoOrcamento.getItens().stream()
                .mapToDouble(it -> it.getProduto().getPrecoUnitario() * it.getQuantidade())
                .sum();
        novoOrcamento.setCustoItens(custoItens);

        // Cálculo de Imposto com Strategy
        CalculadoraImposto calculadora = calculadoraImpostoFactory.getCalculadora(estado);
        double impostoEstadual = calculadora.calcular(custoItens, novoOrcamento.getItens());
        double impostoFederal = custoItens * 0.15; // Imposto federal fixo
        novoOrcamento.setImposto(impostoEstadual + impostoFederal);

        // Lógica de desconto (a ser implementada no próximo passo)
        if (novoOrcamento.getItens().size() > 5) {
            novoOrcamento.setDesconto(custoItens * 0.05);
        } else {
            novoOrcamento.setDesconto(0.0);
        }

        novoOrcamento.setCustoConsumidor(custoItens + novoOrcamento.getImposto() - novoOrcamento.getDesconto());
        return this.orcamentos.cadastra(novoOrcamento);
    }

    public OrcamentoModel efetivaOrcamento(long id) {
        // Recupera o orçamento
        var orcamento = this.orcamentos.recuperaPorId(id);
        if (orcamento == null || orcamento.isEfetivado()) {
            throw new IllegalArgumentException("Orçamento inexistente ou já efetivado");
        }

        // Adicionar validação de data (próximo passo)

        var ok = true;
        // Verifica se tem quantidade em estoque para todos os itens
        for (ItemPedidoModel itemPedido : orcamento.getItens()) {
            int qtdade = estoque.quantidadeEmEstoque(itemPedido.getProduto().getId());
            if (qtdade < itemPedido.getQuantidade()) {
                ok = false;
                break;
            }
        }
        // Se tem quantidade para todos os itens, da baixa no estoque para todos
        if (ok) {
            for (ItemPedidoModel itemPedido : orcamento.getItens()) {
                estoque.baixaEstoque(itemPedido.getProduto().getId(), itemPedido.getQuantidade());
            }
            // Marca o orcamento como efetivado
            orcamentos.marcaComoEfetivado(id);
        }
        // Retorna o orçamento marcado como efetivado ou não conforme disponibilidade do
        // estoque
        return orcamentos.recuperaPorId(id);
    }

    public OrcamentoModel buscaOrcamento(long idOrcamento) {
        return this.orcamentos.recuperaPorId(idOrcamento);
    }
}