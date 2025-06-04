package com.scheffel.tf_fds.adapterInterface.persistence.models;

import jakarta.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
public class OrcamentoEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) //PK
    private Long id;

    @Temporal(TemporalType.DATE)
    private Date data;

    private String nomeCliente;

    private String estadoEntrega;
    private String paisEntrega;
    private double valorTotalItens;
    private double impostoEstadual;
    private double impostoFederal;
    private double desconto;
    private double valorFinal;
    private String status;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getData() {
        return data;
    }

    public void setData(Date data) {
        this.data = data;
    }

    public String getNomeCliente() {
        return nomeCliente;
    }

    public void setNomeCliente(String nomeCliente) {
        this.nomeCliente = nomeCliente;
    }

    public String getEstadoEntrega() {
        return estadoEntrega;
    }

    public void setEstadoEntrega(String estadoEntrega) {
        this.estadoEntrega = estadoEntrega;
    }

    public String getPaisEntrega() {
        return paisEntrega;
    }

    public void setPaisEntrega(String paisEntrega) {
        this.paisEntrega = paisEntrega;
    }

    public double getValorTotalItens() {
        return valorTotalItens;
    }

    public void setValorTotalItens(double valorTotalItens) {
        this.valorTotalItens = valorTotalItens;
    }

    public double getImpostoEstadual() {
        return impostoEstadual;
    }

    public void setImpostoEstadual(double impostoEstadual) {
        this.impostoEstadual = impostoEstadual;
    }

    public double getImpostoFederal() {
        return impostoFederal;
    }

    public void setImpostoFederal(double impostoFederal) {
        this.impostoFederal = impostoFederal;
    }

    public double getDesconto() {
        return desconto;
    }

    public void setDesconto(double desconto) {
        this.desconto = desconto;
    }

    public double getValorFinal() {
        return valorFinal;
    }

    public void setValorFinal(double valorFinal) {
        this.valorFinal = valorFinal;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}