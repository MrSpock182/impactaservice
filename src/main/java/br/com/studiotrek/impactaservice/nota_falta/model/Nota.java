package br.com.studiotrek.impactaservice.nota_falta.model;

import java.io.Serializable;

public class Nota implements Serializable {

    private String nomeMateria;
    private String json;

    public String getNomeMateria() {
        return nomeMateria;
    }

    public void setNomeMateria(String nomeMateria) {
        this.nomeMateria = nomeMateria;
    }

    public String getJson() {
        return json;
    }

    public void setJson(String json) {
        this.json = json;
    }
}
