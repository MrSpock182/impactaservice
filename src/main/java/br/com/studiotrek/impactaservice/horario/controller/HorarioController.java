package br.com.studiotrek.impactaservice.horario.controller;

import br.com.studiotrek.impactaservice.horario.model.Horario;
import br.com.studiotrek.impactaservice.horario.regra.HorarioRegra;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@RestController
public class HorarioController {

    @RequestMapping(value = "/horario", method = RequestMethod.POST)
    public String post(@RequestBody Map<String, String> json) {
        try {
            List<Horario> horario = new ArrayList<>();
            horario = new HorarioRegra(json.get("turmaId"), json.get("produto"), horario).parseHtml(json.get("cookie"));
            Gson gson = new GsonBuilder().disableHtmlEscaping().create();
            return gson.toJson(horario);
        } catch (Exception ex) {
            return "Error";
        }
    }

}
