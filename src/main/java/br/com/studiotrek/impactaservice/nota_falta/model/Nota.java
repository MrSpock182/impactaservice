package br.com.studiotrek.impactaservice.nota_falta.model;

import java.io.Serializable;
import java.util.HashMap;

public class Nota implements Serializable {

    private String nomeMateria;
    private HashMap falta;

    public HashMap getFalta() {
        return falta;
    }

    public void setFalta(HashMap falta) {
        this.falta = falta;
    }

    public String getNomeMateria() {
        return nomeMateria;
    }

    public void setNomeMateria(String nomeMateria) {
        this.nomeMateria = nomeMateria;
    }
}
