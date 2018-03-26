package br.com.studiotrek.impactaservice.login.regra;

import br.com.studiotrek.impactaservice.login.model.Login;
import br.com.studiotrek.impactaservice.login.model.ResponseLogin;
import br.com.studiotrek.impactaservice.request_impacta.Request;
import br.com.studiotrek.impactaservice.util.Inject;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.io.Serializable;
import java.util.Map;

@Repository
public class LoginRegra implements Serializable {

    @Autowired
    private Login login;

    @Autowired
    private Request request;

    public LoginRegra() {

    }

    public Login request(String rm, String senha) throws Exception {
        this.login = Inject.getContext().getBean(Login.class);
        this.request = Inject.getContext().getBean(Request.class);

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
