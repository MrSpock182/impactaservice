package br.com.studiotrek.impactaservice.horario.model;

import java.io.Serializable;

public class HorarioDetalhado implements Serializable {

    private String disciplina;
    private String professor;
    private String sala;

    public String getDisciplina() {
        return disciplina;
    }

    public void setDisciplina(String disciplina) {
        this.disciplina = disciplina;
    }

    public String getProfessor() {
        return professor;
    }

    public void setProfessor(String professor) {
        this.professor = professor;
    }

    public String getSala() {
        return sala;
    }

    public void setSala(String sala) {
        this.sala = sala;
    }
}
