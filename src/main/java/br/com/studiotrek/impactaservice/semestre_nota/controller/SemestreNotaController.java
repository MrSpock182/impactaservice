package br.com.studiotrek.impactaservice.semestre_nota.controller;

import br.com.studiotrek.impactaservice.semestre_nota.model.SemestreNota;
import br.com.studiotrek.impactaservice.semestre_nota.regra.SemestreNotaRegra;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
public class SemestreNotaController {

    @RequestMapping(value = "/v2/semestre-nota", method = RequestMethod.POST)
    public ResponseEntity<SemestreNota> post(@RequestHeader String token) {
        try {
            SemestreNota semestreNota = new SemestreNotaRegra(token).parseHtml();
            return new ResponseEntity<>(semestreNota, HttpStatus.OK);
        } catch (IllegalAccessException ex) {
            return new ResponseEntity<>((SemestreNota) null, HttpStatus.UNAUTHORIZED);
        } catch (Exception ex) {
            return new ResponseEntity<>((SemestreNota) null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Deprecated
    @RequestMapping(value = "/semestre-nota", method = RequestMethod.POST)
    public ResponseEntity<SemestreNota> post(@RequestBody Map<String, String> json) {
        try {
            SemestreNota semestreNota = new SemestreNotaRegra(json.get("cookie")).parseHtml();
            return new ResponseEntity<>(semestreNota, HttpStatus.OK);
        } catch (IllegalAccessException ex) {
            return new ResponseEntity<>((SemestreNota) null, HttpStatus.UNAUTHORIZED);
        } catch (Exception ex) {
            return new ResponseEntity<>((SemestreNota) null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
