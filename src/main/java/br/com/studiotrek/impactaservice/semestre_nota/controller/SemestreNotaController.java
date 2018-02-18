package br.com.studiotrek.impactaservice.semestre_nota.controller;

import br.com.studiotrek.impactaservice.semestre_nota.model.SemestreNota;
import br.com.studiotrek.impactaservice.semestre_nota.regra.SemestreNotaRegra;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.springframework.web.bind.annotation.*;

@RestController
public class SemestreNotaController {

    @RequestMapping(value = "/semestre-nota", method = RequestMethod.POST)
    public String post(@RequestBody String cookie) {
        try {
            SemestreNota semestreNota =  new SemestreNota();
            semestreNota = new SemestreNotaRegra(semestreNota).parseHtml(cookie);
            Gson gson = new GsonBuilder().disableHtmlEscaping().create();
            return gson.toJson(semestreNota);
        } catch (Exception ex) {
            return "Error";
        }
    }

}
