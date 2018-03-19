package br.com.studiotrek.impactaservice.nota_falta.model;

import java.io.Serializable;
import java.util.List;

public class NotaFalta implements Serializable {

    private Boolean semestreAc;
    private List<Nota> notas;

    public Boolean getSemestreAc() {
        return semestreAc;
    }

    public void setSemestreAc(Boolean semestreAc) {
        this.semestreAc = semestreAc;
    }

    public List<Nota> getNotas() {
        return notas;
    }

    public void setNotas(List<Nota> notas) {
        this.notas = notas;
    }
}
