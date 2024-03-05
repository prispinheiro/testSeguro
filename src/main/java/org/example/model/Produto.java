package org.example.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class Produto {
    @Id
    private String id;
    private String nome;
    private String categoria;
    private double precoBase;
    private double precoTarifado;

    public Produto() {
    }

    public Produto(String id, String nome, String categoria, double precoBase, double precoTarifado) {
        super();
        this.id = id;
        this.nome = nome;
        this.categoria = categoria;
        this.precoBase = precoBase;
        this.precoTarifado = precoTarifado;
    }

    public final String getId() {
        return id;
    }

    public void setId(final String id) {
        this.id = id;
    }

    public final String getNome() {
        return nome;
    }

    public void setNome(final String nome) {
        this.nome = nome;
    }

    public final String getCategoria() {
        return categoria;
    }

    public void setCategoria(final String categoria) {
        this.categoria = categoria;
    }

    public final double getPrecoBase() {
        return precoBase;
    }

    public void setPrecoBase(final double precoBase) {
        this.precoBase = precoBase;
    }

    public final double getPrecoTarifado() {
        return precoTarifado;
    }

    public void setPrecoTarifado(final double precoTarifado) {
        this.precoTarifado = precoTarifado;
    }

    @Override
    public String toString() {
        return "Produto{" +
                "id='" + id + '\''+
                ", nome='" + nome + '\'' +
                ", categoria='" + categoria + '\'' +
                ", precoBase=" + precoBase +
                ", precoTarifado=" + precoTarifado +
                '}';
    }
}
