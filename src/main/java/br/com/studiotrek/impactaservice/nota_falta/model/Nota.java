package br.com.studiotrek.impactaservice.nota_falta.model;

import java.io.Serializable;
import java.util.HashMap;

public class Nota implements Serializable {

    private String nomeMateria;

    public HashMap getJson() {
        return json;
    }

    public void setJson(HashMap json) {
        this.json = json;
    }

    private HashMap json;

    public String getNomeMateria() {
        return nomeMateria;
    }

    public void setNomeMateria(String nomeMateria) {
        this.nomeMateria = nomeMateria;
    }
}
