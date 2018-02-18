package br.com.studiotrek.impactaservice.nota_falta;

import br.com.studiotrek.impactaservice.nota_falta.model.NotaFalta;
import br.com.studiotrek.impactaservice.nota_falta.regra.NotaFaltaRegra;
import org.junit.Test;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

public class TestNotaFalta {

    @Test
    public void testNotaFalta() {
        try {
            String url = "boletim.php?codigo=MDE2TVRVeE9EY3lNRFV3TlE9PU5ERTRNakU9";

            NotaFaltaRegra notaFaltaRegra = new NotaFaltaRegra(url, new NotaFalta());
            NotaFalta notaFalta = notaFaltaRegra.parseHtml("PHPSESSID=rtljmtmm3dmapvu3a4ncfevft0; path=/");

            assertNotNull(notaFalta);
        } catch (Exception ex) {
            assertTrue(false);
        }
    }

}
