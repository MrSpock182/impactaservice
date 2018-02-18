package br.com.studiotrek.impactaservice.quadro_horario.model;

import java.io.Serializable;

public class QuadroHorario implements Serializable {
    private String turmaId;
    private String produto;

    public String getTurmaId() {
        return turmaId;
    }

    public void setTurmaId(String turmaId) {
        this.turmaId = turmaId;
    }

    public String getProduto() {
        return produto;
    }

    public void setProduto(String produto) {
        this.produto = produto;
    }
}
