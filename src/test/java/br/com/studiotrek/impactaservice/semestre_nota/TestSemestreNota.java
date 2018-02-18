package br.com.studiotrek.impactaservice.semestre_nota;

import br.com.studiotrek.impactaservice.semestre_nota.model.SemestreNota;
import br.com.studiotrek.impactaservice.semestre_nota.regra.SemestreNotaRegra;
import org.junit.Test;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

public class TestSemestreNota {

    @Test
    public void testSemestreNota() {
        try {
            SemestreNota semestreNota = new SemestreNota();
            SemestreNotaRegra semestreNotaRegra = new SemestreNotaRegra(semestreNota);
            semestreNota = semestreNotaRegra.parseHtml("PHPSESSID=rtljmtmm3dmapvu3a4ncfevft0; path=/");

            assertNotNull(semestreNota);
        } catch (Exception ex) {
            assertTrue(false);
        }
    }

}
