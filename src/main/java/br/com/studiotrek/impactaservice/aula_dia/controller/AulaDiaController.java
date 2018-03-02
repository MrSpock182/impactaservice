package br.com.studiotrek.impactaservice.aula_dia.controller;

import br.com.studiotrek.impactaservice.aula_dia.model.AulaDia;
import br.com.studiotrek.impactaservice.aula_dia.regra.AulaDiaRegra;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
public class AulaDiaController {

    @RequestMapping(value = "/aula-dia", method = RequestMethod.POST)
    public ResponseEntity<AulaDia> post(@RequestBody Map<String, String> json) {
        AulaDia aulaDia = new AulaDia();

        try {
            aulaDia = new AulaDiaRegra(aulaDia).getAulaDia(json.get("cookie"));
            return new ResponseEntity<>(aulaDia, HttpStatus.OK);
        } catch (IllegalAccessException ex) {
            return new ResponseEntity<>(aulaDia, HttpStatus.UNAUTHORIZED);
        } catch (Exception ex) {
            return new ResponseEntity<>(aulaDia, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
