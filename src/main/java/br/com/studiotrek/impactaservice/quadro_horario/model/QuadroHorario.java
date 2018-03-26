package br.com.studiotrek.impactaservice.quadro_horario.model;

import org.springframework.stereotype.Repository;

import java.io.Serializable;

@Repository
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
