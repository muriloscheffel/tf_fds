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
        novoOrcamento.setPais("BRASIL");
        novoOrcamento.setDataCriacao(LocalDate.now());

        double custoItens = novoOrcamento.getItens().stream()
                .mapToDouble(it -> it.getProduto().getPrecoUnitario() * it.getQuantidade())
                .sum();
        novoOrcamento.setCustoItens(custoItens);

        CalculadoraImposto calculadora = calculadoraImpostoFactory.getCalculadora(estado);
        double impostoEstadual = calculadora.calcular(custoItens, novoOrcamento.getItens());
        double impostoFederal = custoItens * 0.15; // Imposto federal fixo
        novoOrcamento.setImposto(impostoEstadual + impostoFederal);

        double descontoTotal = 0.0;
        for (ItemPedidoModel item : novoOrcamento.getItens()) {
            if (item.getQuantidade() > 3) {
                descontoTotal += (item.getProduto().getPrecoUnitario() * item.getQuantidade()) * 0.05;
            }
        }
        if (novoOrcamento.getItens().size() > 10) {
            descontoTotal += custoItens * 0.10;
        }
        novoOrcamento.setDesconto(descontoTotal);

        novoOrcamento.setCustoConsumidor(custoItens + novoOrcamento.getImposto() - novoOrcamento.getDesconto());

        return this.orcamentos.cadastra(novoOrcamento);
    }

    public OrcamentoModel efetivaOrcamento(long id) {
        var orcamento = this.orcamentos.recuperaPorId(id);
        if (orcamento == null) {
            throw new IllegalArgumentException("Orçamento #" + id + " inexistente.");
        }
        if (orcamento.isEfetivado()) {
            throw new IllegalStateException("Orçamento #" + id + " já foi efetivado.");
        }

        LocalDate dataExpiracao = orcamento.getDataCriacao().plusDays(21);
        if (LocalDate.now().isAfter(dataExpiracao)) {
            throw new IllegalStateException("Orçamento #" + id + " está expirado.");
        }

        for (ItemPedidoModel itemPedido : orcamento.getItens()) {
            int qtdadeEmEstoque = estoque.quantidadeEmEstoque(itemPedido.getProduto().getId());
            if (qtdadeEmEstoque < itemPedido.getQuantidade()) {
                throw new IllegalStateException(
                        "Estoque insuficiente para o produto: " + itemPedido.getProduto().getDescricao());
            }
        }

        for (ItemPedidoModel itemPedido : orcamento.getItens()) {
            estoque.baixaEstoque(itemPedido.getProduto().getId(), itemPedido.getQuantidade());
        }

        orcamentos.marcaComoEfetivado(id);

        return orcamentos.recuperaPorId(id);
    }

    public OrcamentoModel buscaOrcamento(long idOrcamento) {
        return this.orcamentos.recuperaPorId(idOrcamento);
    }

    public List<OrcamentoModel> consultaOrcamentosEfetivados(LocalDate inicio, LocalDate fim) {
        return orcamentos.findEfetivadosPorPeriodo(inicio, fim);
    }
}