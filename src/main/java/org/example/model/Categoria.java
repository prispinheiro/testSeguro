package org.example.model;

import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class Categoria {
    private String descricao;
    private double IOF;
    private double PIS;
    private double COFINS;

    public final String getDescricao() {
        return descricao;
    }

    public final double getIOF() {
        return IOF;
    }

    public final double getPIS() {
        return PIS;
    }

    public final double getCOFINS() {
        return COFINS;
    }

    public void setDescricao(final String descricao) {
        this.descricao = descricao;
    }

    public void setIOF(final double IOF) {
        this.IOF = IOF;
    }

    public void setPIS(final double PIS) {
        this.PIS = PIS;
    }

    public void setCOFINS(final double COFINS) {
        this.COFINS = COFINS;
    }
}
