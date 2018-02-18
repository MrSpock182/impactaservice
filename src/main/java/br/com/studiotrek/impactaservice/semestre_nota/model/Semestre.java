package br.com.studiotrek.impactaservice.semestre_nota.model;

import java.io.Serializable;

public class Semestre implements Serializable {

    private String faculdade;
    private String anoSemestre;
    private String curso;
    private String horario;
    private String semestre;
    private String status;
    private String urlBoletim;

    public String getFaculdade() {
        return faculdade;
    }

    public void setFaculdade(String faculdade) {
        this.faculdade = faculdade;
    }

    public String getAnoSemestre() {
        return anoSemestre;
    }

    public void setAnoSemestre(String anoSemestre) {
        this.anoSemestre = anoSemestre;
    }

    public String getCurso() {
        return curso;
    }

    public void setCurso(String curso) {
        this.curso = curso;
    }

    public String getHorario() {
        return horario;
    }

    public void setHorario(String horario) {
        this.horario = horario;
    }

    public String getSemestre() {
        return semestre;
    }

    public void setSemestre(String semestre) {
        this.semestre = semestre;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getUrlBoletim() {
        return urlBoletim;
    }

    public void setUrlBoletim(String urlBoletim) {
        this.urlBoletim = urlBoletim;
    }
}
