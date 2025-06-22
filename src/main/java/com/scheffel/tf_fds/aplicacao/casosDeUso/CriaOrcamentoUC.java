package com.scheffel.tf_fds.aplicacao.casosDeUso;

import org.springframework.stereotype.Component;

import com.scheffel.tf_fds.aplicacao.dtos.NovoOrcamentoDTO;
import com.scheffel.tf_fds.aplicacao.dtos.OrcamentoDTO;
import com.scheffel.tf_fds.dominio.modelos.ItemPedidoModel;
import com.scheffel.tf_fds.dominio.modelos.OrcamentoModel;
import com.scheffel.tf_fds.dominio.modelos.PedidoModel;
import com.scheffel.tf_fds.dominio.modelos.ProdutoModel;
import com.scheffel.tf_fds.dominio.servicos.ServicoDeEstoque;
import com.scheffel.tf_fds.dominio.servicos.ServicoDeVendas;

@Component
public class CriaOrcamentoUC {
    private ServicoDeVendas servicoDeVendas;
    private ServicoDeEstoque servicoDeEstoque;

    public CriaOrcamentoUC(ServicoDeVendas servicoDeVendas, ServicoDeEstoque servicoDeEstoque) {
        this.servicoDeVendas = servicoDeVendas;
        this.servicoDeEstoque = servicoDeEstoque;
    }

    public OrcamentoDTO run(NovoOrcamentoDTO novoOrcamento) {
        PedidoModel pedido = new PedidoModel(0);
        novoOrcamento.getItens().forEach(item -> {
            ProdutoModel produto = servicoDeEstoque.produtoPorCodigo(item.getIdProduto());
            ItemPedidoModel itemPedido = new ItemPedidoModel(produto, item.getQtdade());
            pedido.addItem(itemPedido);
        });
        
        OrcamentoModel orcamento = servicoDeVendas.criaOrcamento(pedido, novoOrcamento.getNomeCliente(), novoOrcamento.getEstado());
        return OrcamentoDTO.fromModel(orcamento);
    }
}