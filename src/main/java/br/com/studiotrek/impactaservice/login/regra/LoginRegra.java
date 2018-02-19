package br.com.studiotrek.impactaservice.login.regra;

import br.com.studiotrek.impactaservice.login.model.Login;
import br.com.studiotrek.impactaservice.login.model.ResponseLogin;
import br.com.studiotrek.impactaservice.request_impacta.Request;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.Serializable;
import java.util.Map;

public class LoginRegra implements Serializable {

    private Login login;
    private Request request;

    public LoginRegra(Login login) {
        this.login = login;
        this.request = new Request();
    }

    public Login request(String rm, String senha) throws Exception {
        Map<String, String> retorno = this.request.login(rm, senha);
        Gson gson = new GsonBuilder().disableHtmlEscaping().create();
        ResponseLogin responseLogin = gson.fromJson(retorno.get(Request.RESPONSE), ResponseLogin.class);

        if(responseLogin.getSuccess()) {
            this.login.setCookin(retorno.get(Request.COOKIE));
            return this.login;
        } else {
            throw new IllegalAccessException("Login invalido");
        }
    }

}
