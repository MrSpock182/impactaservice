package br.com.studiotrek.impactaservice.quadro_horario.controller;

import br.com.studiotrek.impactaservice.login.model.Login;
import br.com.studiotrek.impactaservice.quadro_horario.model.QuadroHorario;
import br.com.studiotrek.impactaservice.quadro_horario.regra.QuadroHorarioRegra;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
public class QuadroHorarioController {

    @RequestMapping(value = "/v2/quadro-horario", method = RequestMethod.POST)
    public ResponseEntity<QuadroHorario> post(@RequestHeader String token) {
        QuadroHorario quadroHorario = new QuadroHorario();
        try {
            quadroHorario = new QuadroHorarioRegra(token).parseHtml();
            return new ResponseEntity<>(quadroHorario, HttpStatus.OK);
        } catch (IllegalAccessException ex) {
            return new ResponseEntity<>(quadroHorario, HttpStatus.UNAUTHORIZED);
        } catch (Exception ex) {
            return new ResponseEntity<>(quadroHorario, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Deprecated
    @RequestMapping(value = "/quadro-horario", method = RequestMethod.POST)
    public ResponseEntity<QuadroHorario> post(@RequestBody Map<String, String> json) {
        QuadroHorario quadroHorario = new QuadroHorario();
        try {
            quadroHorario = new QuadroHorarioRegra(json.get("cookie")).parseHtml();
            return new ResponseEntity<>(quadroHorario, HttpStatus.OK);
        } catch (IllegalAccessException ex) {
            return new ResponseEntity<>(quadroHorario, HttpStatus.UNAUTHORIZED);
        } catch (Exception ex) {
            return new ResponseEntity<>(quadroHorario, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
