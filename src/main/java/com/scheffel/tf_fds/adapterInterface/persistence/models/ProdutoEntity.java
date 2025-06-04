package com.scheffel.tf_fds.adapterInterface.persistence.models;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class ProdutoEntity {
    @Id
    private Long id;

    private String descricao;
    private double precoUnitario;

    public ProdutoEntity(long id, String descricao, double precoUnitario) {
        this.id = id;
        this.descricao = descricao;
        this.precoUnitario = precoUnitario;
    }

    protected ProdutoEntity() {

    }

    public long getId() {
        return this.id;
    }

    public String getDescricao() {
        return this.descricao;
    }

    public double getPrecoUnitario() {
        return this.precoUnitario;
    }

    public void setPrecoUnitario(double precoUnitario) {
        this.precoUnitario = precoUnitario;
    }

    @Override
    public String toString() {
        return "{" +
                " codigo='" + getId() + "'" +
                ", descricao='" + getDescricao() + "'" +
                ", precoUnitario='" + getPrecoUnitario() + "'" +
                "}";
    }
}