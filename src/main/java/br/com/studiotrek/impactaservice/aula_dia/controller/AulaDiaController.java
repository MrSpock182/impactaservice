package br.com.studiotrek.impactaservice.aula_dia.controller;

import br.com.studiotrek.impactaservice.aula_dia.model.AulaDia;
import br.com.studiotrek.impactaservice.aula_dia.regra.AulaDiaRegra;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
public class AulaDiaController {

    @RequestMapping(value = "/v2/aula-dia", method = RequestMethod.POST)
    public ResponseEntity<AulaDia> post(@RequestHeader String token) {
        AulaDia aulaDia = new AulaDia();

        try {
            aulaDia = new AulaDiaRegra(token, aulaDia).getAulaDia();
            return new ResponseEntity<>(aulaDia, HttpStatus.OK);
        } catch (IllegalAccessException ex) {
            return new ResponseEntity<>(aulaDia, HttpStatus.UNAUTHORIZED);
        } catch (Exception ex) {
            return new ResponseEntity<>(aulaDia, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Deprecated
    @RequestMapping(value = "/aula-dia", method = RequestMethod.POST)
    public ResponseEntity<AulaDia> postMethod(@RequestBody Map<String, String> json) {
        AulaDia aulaDia = new AulaDia();

        try {
            aulaDia = new AulaDiaRegra(json.get("cookie"), aulaDia).getAulaDia();
            return new ResponseEntity<>(aulaDia, HttpStatus.OK);
        } catch (IllegalAccessException ex) {
            return new ResponseEntity<>(aulaDia, HttpStatus.UNAUTHORIZED);
        } catch (Exception ex) {
            return new ResponseEntity<>(aulaDia, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
