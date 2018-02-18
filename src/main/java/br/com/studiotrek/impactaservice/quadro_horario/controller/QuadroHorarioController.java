package br.com.studiotrek.impactaservice.quadro_horario.controller;

import br.com.studiotrek.impactaservice.quadro_horario.model.QuadroHorario;
import br.com.studiotrek.impactaservice.quadro_horario.regra.QuadroHorarioRegra;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.springframework.web.bind.annotation.*;


@RestController
public class QuadroHorarioController {

    @RequestMapping(value = "/quadro-horario", method = RequestMethod.POST)
    public String post(@RequestBody String cookie) {
        try {
            QuadroHorario quadroHorario = new QuadroHorario();
            quadroHorario = new QuadroHorarioRegra(quadroHorario).parseHtml(cookie);
            Gson gson = new GsonBuilder().disableHtmlEscaping().create();
            return gson.toJson(quadroHorario);
        } catch (Exception ex) {
            return "Error";
        }
    }

}
