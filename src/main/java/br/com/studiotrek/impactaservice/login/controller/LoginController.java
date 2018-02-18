package br.com.studiotrek.impactaservice.login.controller;

import br.com.studiotrek.impactaservice.login.model.Login;
import br.com.studiotrek.impactaservice.login.regra.LoginRegra;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.springframework.web.bind.annotation.*;

@RestController
public class LoginController {

    @RequestMapping(value = "/login/{rm}/{senha}", method = RequestMethod.GET)
    public String get(@RequestBody @PathVariable("rm") String rm, @PathVariable("senha") String senha) {
        try {
            Login login = new Login();
            login = new LoginRegra(login).request(rm, senha);
            Gson gson = new GsonBuilder().disableHtmlEscaping().create();
            return gson.toJson(login);
        } catch (Exception ex) {
            return "error";
        }
    }

}
