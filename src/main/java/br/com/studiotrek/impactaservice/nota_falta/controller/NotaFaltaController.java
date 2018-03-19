package br.com.studiotrek.impactaservice.nota_falta.controller;

import br.com.studiotrek.impactaservice.nota_falta.model.NotaFalta;
import br.com.studiotrek.impactaservice.nota_falta.regra.NotaFaltaRegra;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
public class NotaFaltaController {

    @RequestMapping(value = "/nota-falta", method = RequestMethod.POST)
    public ResponseEntity<NotaFalta> post(@RequestHeader String token, @RequestBody Map<String, String> json) {
        NotaFalta notaFalta = new NotaFalta();

        try {
            NotaFaltaRegra notaFaltaRegra = new NotaFaltaRegra(token, json.get("url"), notaFalta);
            notaFalta = notaFaltaRegra.parseHtml();
            notaFalta.setSemestreAc(notaFaltaRegra.isSemestreAc());
            return new ResponseEntity<>(notaFalta, HttpStatus.OK);
        } catch (IllegalAccessException ex) {
            return new ResponseEntity<>(notaFalta, HttpStatus.UNAUTHORIZED);
        } catch (Exception ex) {
            return new ResponseEntity<>(notaFalta, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
