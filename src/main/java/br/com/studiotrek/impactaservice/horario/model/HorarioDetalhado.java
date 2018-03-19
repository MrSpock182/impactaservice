package br.com.studiotrek.impactaservice.horario.model;

import java.io.Serializable;
import java.util.Objects;

public class HorarioDetalhado implements Serializable {

    private String disciplina;
    private String professor;
    private String sala;
    private String faltaB1;
    private String faltaB2;
    private String cargaHoraria;

    public HorarioDetalhado(String disciplina, String professor, String sala, String faltaB1, String faltaB2, String cargaHoraria) {
        this.disciplina = disciplina;
        this.professor = professor;
        this.sala = sala;
        this.faltaB1 = faltaB1;
        this.faltaB2 = faltaB2;
        this.cargaHoraria = cargaHoraria;
    }

    public HorarioDetalhado() {
    }

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

    public String getFaltaB1() {
        return faltaB1;
    }

    public void setFaltaB1(String faltaB1) {
        this.faltaB1 = faltaB1;
    }

    public String getFaltaB2() {
        return faltaB2;
    }

    public void setFaltaB2(String faltaB2) {
        this.faltaB2 = faltaB2;
    }

    public String getCargaHoraria() {
        return cargaHoraria;
    }

    public void setCargaHoraria(String cargaHoraria) {
        this.cargaHoraria = cargaHoraria;
    }

    public boolean equals(Object obj) {

        if (!(obj instanceof HorarioDetalhado)) {
            return false;
        }

        final HorarioDetalhado horarioDetalhado = (HorarioDetalhado) obj;
        if (!this.getDisciplina().equals(horarioDetalhado.getDisciplina())){
            return false;
        }

        if (!this.getProfessor().equals(horarioDetalhado.getProfessor())) {
            return false;
        }

        if (!this.getSala().equals(horarioDetalhado.getSala())){
            return false;
        }

        return true;
    }

    @Override
    public int hashCode() {
        return Objects.hash(disciplina, professor, sala, faltaB1, faltaB2, cargaHoraria);
    }
}
