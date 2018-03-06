package br.com.studiotrek.impactaservice.horario.model;

import java.io.Serializable;
import java.util.List;
import java.util.Set;

public class Horario implements Serializable {

    private String dia;
    private Set<HorarioDetalhado> horarioDetalhado;

    public String getDia() {
        return dia;
    }

    public void setDia(String dia) {
        this.dia = dia;
    }

    public Set<HorarioDetalhado> getHorarioDetalhado() {
        return horarioDetalhado;
    }

    public void setHorarioDetalhado(Set<HorarioDetalhado> horarioDetalhado) {
        this.horarioDetalhado = horarioDetalhado;
    }
}
