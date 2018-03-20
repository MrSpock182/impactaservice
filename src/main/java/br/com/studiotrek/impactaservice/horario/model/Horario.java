package br.com.studiotrek.impactaservice.horario.model;

import java.io.Serializable;
import java.util.Collections;
import java.util.List;
import java.util.Set;

public class Horario implements Serializable {

    private String dia;
    private List<HorarioDetalhado> horarioDetalhado;

    public String getDia() {
        return dia;
    }

    public void setDia(String dia) {
        this.dia = dia;
    }

    public List<HorarioDetalhado> getHorarioDetalhado() {
        return horarioDetalhado;
    }

    public void setHorarioDetalhado(List<HorarioDetalhado> horarioDetalhado) {
        this.horarioDetalhado = horarioDetalhado;
    }
}
