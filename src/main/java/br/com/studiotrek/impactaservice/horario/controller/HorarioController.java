package br.com.studiotrek.impactaservice.horario.controller;

import br.com.studiotrek.impactaservice.horario.model.Horario;
import br.com.studiotrek.impactaservice.horario.regra.HorarioRegra;
import br.com.studiotrek.impactaservice.login.model.Login;
import br.com.studiotrek.impactaservice.quadro_horario.model.QuadroHorario;
import br.com.studiotrek.impactaservice.quadro_horario.regra.QuadroHorarioRegra;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@RestController
public class HorarioController {

    @RequestMapping(value = "/horario", method = RequestMethod.POST)
    public ResponseEntity<List<Horario>> post(@RequestBody Map<String, String> json) {
        List<Horario> horario = new ArrayList<>();
        try {
            QuadroHorario quadroHorario = new QuadroHorario();
            QuadroHorarioRegra quadroHorarioRegra = new QuadroHorarioRegra(quadroHorario);
            quadroHorario = quadroHorarioRegra.parseHtml(json.get("cookie"));
            horario = new HorarioRegra(quadroHorario.getTurmaId(), quadroHorario.getProduto(), horario).parseHtml(json.get("cookie"));
            return new ResponseEntity<>(horario, HttpStatus.OK);
        } catch (IllegalAccessException ex) {
            return new ResponseEntity<>(horario, HttpStatus.UNAUTHORIZED);
        } catch (Exception ex) {
            return new ResponseEntity<>(horario, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
