package br.com.studiotrek.impactaservice.horario;

import br.com.studiotrek.impactaservice.horario.model.Horario;
import br.com.studiotrek.impactaservice.horario.model.HorarioDetalhado;
import br.com.studiotrek.impactaservice.horario.regra.HorarioRegra;
import org.junit.Test;

import java.util.*;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

public class TestHorario {

    public static final int QUANTIDADE_DE_HORARIOS_DISTINTOS = 4;
    public static final int QUANTIDADE_DE_HORARIOS_REPETIDOS_DISTINTOS = 2;

    @Test
    public void testHorario() {
        try {
            List<Horario> horarios = new ArrayList<>();
            HorarioRegra horarioRegra = new HorarioRegra("MDE2TVRVeE9EY3lNRGN5TlE9PU16Z3hOQT09",
                    "MDE2TVRVeE9EY3lNRGN5TlE9PU5UYz0=",horarios);
            horarios = horarioRegra.parseHtml("PHPSESSID=rtljmtmm3dmapvu3a4ncfevft0; path=/");

            assertNotNull(horarios);
        } catch (Exception ex) {
            assertTrue(false);
        }
    }

    @Test
    public void testListaHorarioDetalhado_comValoresRepetidos_retornaValoresUnicos() {
        Set<HorarioDetalhado> horarioDetalhados = geraListaFakeDeHorariosDuplicados();
        assertTrue(horarioDetalhados.size() == QUANTIDADE_DE_HORARIOS_REPETIDOS_DISTINTOS);
    }

    @Test
    public void testListaHorarioDetalhado_comValoresDistintos_retornaListaOriginal() {
        Set<HorarioDetalhado> horarioDetalhados = geraListaFakeDeHorariosDistintos();
        assertTrue(horarioDetalhados.size() == QUANTIDADE_DE_HORARIOS_DISTINTOS);
    }


    private Set geraListaFakeDeHorariosDuplicados() {
        Set<HorarioDetalhado> horariosDetalhados = new HashSet<>();
        horariosDetalhados.add(new HorarioDetalhado("Disciplina 1", "Professor 1", "Sala 1"));
        horariosDetalhados.add(new HorarioDetalhado("Disciplina 1", "Professor 1", "Sala 1"));
        horariosDetalhados.add(new HorarioDetalhado("Disciplina 2", "Professor 2", "Sala 2"));
        horariosDetalhados.add(new HorarioDetalhado("Disciplina 2", "Professor 2", "Sala 2"));
        return horariosDetalhados;
    }

    private Set geraListaFakeDeHorariosDistintos() {
        Set<HorarioDetalhado> horariosDetalhados = new HashSet<>();
        horariosDetalhados.add(new HorarioDetalhado("Disciplina 1", "Professor 2", "Sala 1"));
        horariosDetalhados.add(new HorarioDetalhado("Disciplina 1", "Professor 1", "Sala 1"));
        horariosDetalhados.add(new HorarioDetalhado("Disciplina 1", "Professor 2", "Sala 2"));
        horariosDetalhados.add(new HorarioDetalhado("Disciplina 2", "Professor 2", "Sala 1"));
        return horariosDetalhados;
    }

}
