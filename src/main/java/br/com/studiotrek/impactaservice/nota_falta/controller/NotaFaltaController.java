package br.com.studiotrek.impactaservice.nota_falta.controller;

import br.com.studiotrek.impactaservice.nota_falta.model.NotaFalta;
import br.com.studiotrek.impactaservice.nota_falta.regra.NotaFaltaRegra;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
public class NotaFaltaController {

    @RequestMapping(value = "/nota-falta", method = RequestMethod.POST)
    public String post(@RequestBody Map<String, String> json) {
        try {
            NotaFalta notaFalta = new NotaFalta();
            notaFalta = new NotaFaltaRegra(json.get("url"), notaFalta).parseHtml(json.get("cookie"));
            Gson gson = new GsonBuilder().disableHtmlEscaping().create();
            return gson.toJson(notaFalta);
        } catch (Exception ex) {
            return "Error";
        }
    }

}
