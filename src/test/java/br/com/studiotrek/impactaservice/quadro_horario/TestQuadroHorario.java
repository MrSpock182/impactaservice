package br.com.studiotrek.impactaservice.quadro_horario;

import br.com.studiotrek.impactaservice.quadro_horario.model.QuadroHorario;
import br.com.studiotrek.impactaservice.quadro_horario.regra.QuadroHorarioRegra;
import org.junit.Test;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

public class TestQuadroHorario {

    @Test
    public void testQuadroHorario() {
        try {
            QuadroHorario quadroHorario = new QuadroHorario();
            QuadroHorarioRegra quadroHorarioRegra = new QuadroHorarioRegra("PHPSESSID=btisi130mrjkvqoscs3n44qsi4; path=/",
                    quadroHorario);
            quadroHorario = quadroHorarioRegra.parseHtml();

            assertNotNull(quadroHorario);
        } catch (Exception ex) {
            assertTrue(false);
        }
    }

}
