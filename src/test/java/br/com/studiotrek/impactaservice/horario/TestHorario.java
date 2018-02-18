package br.com.studiotrek.impactaservice.horario;

import br.com.studiotrek.impactaservice.horario.model.Horario;
import br.com.studiotrek.impactaservice.horario.regra.HorarioRegra;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

public class TestHorario {

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

}
