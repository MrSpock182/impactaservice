package br.com.studiotrek.impactaservice.horario.model;

import br.com.studiotrek.impactaservice.quadro_horario.model.QuadroHorario;

import java.io.Serializable;
import java.util.Objects;

public class HorarioDetalhado implements Serializable {

    private String disciplina;
    private String professor;
    private String sala;

    public HorarioDetalhado(String disciplina, String professor, String sala) {
        this.disciplina = disciplina;
        this.professor = professor;
        this.sala = sala;
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
        return Objects.hash(disciplina, professor, sala);
    }
}
