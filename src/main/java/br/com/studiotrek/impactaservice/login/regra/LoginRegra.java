package br.com.studiotrek.impactaservice.login.regra;

import br.com.studiotrek.impactaservice.login.model.Login;
import br.com.studiotrek.impactaservice.request_impacta.Request;

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
        this.login.setCookin(retorno.get(Request.COOKIE));
        this.login.setResponse(retorno.get(Request.RESPONSE));
        return this.login;
    }

}
