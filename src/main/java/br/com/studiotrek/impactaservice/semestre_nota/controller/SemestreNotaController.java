package br.com.studiotrek.impactaservice.semestre_nota.controller;

import br.com.studiotrek.impactaservice.semestre_nota.model.SemestreNota;
import br.com.studiotrek.impactaservice.semestre_nota.regra.SemestreNotaRegra;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
public class SemestreNotaController {

    @RequestMapping(value = "/semestre-nota", method = RequestMethod.POST)
    public ResponseEntity<SemestreNota> post(@RequestBody Map<String, String> json) {
        SemestreNota semestreNota =  new SemestreNota();
        try {
            semestreNota = new SemestreNotaRegra(semestreNota).parseHtml(json.get("cookie"));
            return new ResponseEntity<>(semestreNota, HttpStatus.OK);
        } catch (IllegalAccessException ex) {
            return new ResponseEntity<>(semestreNota, HttpStatus.UNAUTHORIZED);
        } catch (Exception ex) {
            return new ResponseEntity<>(semestreNota, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}