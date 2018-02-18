package br.com.studiotrek.impactaservice.nota_falta.model;

import java.io.Serializable;
import java.util.List;

public class NotaFalta implements Serializable {

    private List<Nota> notas;

    public List<Nota> getNotas() {
        return notas;
    }

    public void setNotas(List<Nota> notas) {
        this.notas = notas;
    }
}
