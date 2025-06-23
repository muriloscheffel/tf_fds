package com.scheffel.tf_fds.persistencia.entity;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import com.scheffel.tf_fds.dominio.modelos.ItemPedidoModel;
import com.scheffel.tf_fds.dominio.modelos.OrcamentoModel;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

@Entity
public class Orcamento {
    @Id
    @GeneratedValue
    private long id;

    private String nomeCliente;
    private String estado;
    private String pais;
    private LocalDate dataCriacao;

    @OneToMany(cascade = CascadeType.PERSIST)
    private List<ItemPedido> itens = new ArrayList<>();
    private double custoItens;
    private double imposto;
    private double desconto;
    private double custoConsumidor;
    private boolean efetivado;

    public Orcamento() {
    }

    // Getters e Setters para os novos campos
    public String getNomeCliente() {
        return nomeCliente;
    }

    public void setNomeCliente(String nomeCliente) {
        this.nomeCliente = nomeCliente;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getPais() {
        return pais;
    }

    public void setPais(String pais) {
        this.pais = pais;
    }

    public LocalDate getDataCriacao() {
        return dataCriacao;
    }

    public void setDataCriacao(LocalDate dataCriacao) {
        this.dataCriacao = dataCriacao;
    }

    // Métodos existentes...
    public void addItensPedido(ItemPedido item) {
        itens.add(item);
    }

    public List<ItemPedido> getItens() {
        return itens;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public double getCustoItens() {
        return custoItens;
    }

    public void setCustoItens(double custoItens) {
        this.custoItens = custoItens;
    }

    public double getImposto() {
        return imposto;
    }

    public void setImposto(double imposto) {
        this.imposto = imposto;
    }

    public double getDesconto() {
        return desconto;
    }

    public void setDesconto(double desconto) {
        this.desconto = desconto;
    }

    public double getCustoConsumidor() {
        return custoConsumidor;
    }

    public void setCustoConsumidor(double custoConsumidor) {
        this.custoConsumidor = custoConsumidor;
    }

    public boolean isEfetivado() {
        return efetivado;
    }

    public void efetiva() {
        efetivado = true;
    }

    public static Orcamento fromOrcamentoModel(OrcamentoModel oModel) {
        Orcamento orc = new Orcamento();
        orc.setId(oModel.getId());
        orc.setNomeCliente(oModel.getNomeCliente());
        orc.setEstado(oModel.getEstado());
        orc.setPais(oModel.getPais());
        orc.setDataCriacao(oModel.getDataCriacao());
        orc.setCustoConsumidor(oModel.getCustoConsumidor());
        orc.setCustoItens(oModel.getCustoItens());
        orc.setImposto(oModel.getImposto());
        orc.setDesconto(oModel.getDesconto());
        if (oModel.isEfetivado())
            orc.efetiva();
        for (ItemPedidoModel itm : oModel.getItens()) {
            orc.addItensPedido(ItemPedido.fromItemPedidoModel(itm));
        }
        return orc;
    }

    public static OrcamentoModel toOrcamentoModel(Orcamento orc) {
        if (orc == null)
            return null;
        OrcamentoModel oModel = new OrcamentoModel();
        oModel.setId(orc.getId());
        oModel.setNomeCliente(orc.getNomeCliente());
        oModel.setEstado(orc.getEstado());
        oModel.setPais(orc.getPais());
        oModel.setDataCriacao(orc.getDataCriacao());
        oModel.setCustoConsumidor(orc.getCustoConsumidor());
        oModel.setCustoItens(orc.getCustoItens());
        oModel.setImposto(orc.getImposto());
        oModel.setDesconto(orc.getDesconto());
        if (orc.isEfetivado())
            oModel.efetiva();
        for (ItemPedido it : orc.getItens())
            oModel.addItensPedido(ItemPedido.toItemPedidoModel(it));
        return oModel;
    }
}