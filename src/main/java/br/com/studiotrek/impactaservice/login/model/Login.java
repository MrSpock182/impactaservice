package br.com.studiotrek.impactaservice.login.model;

import java.io.Serializable;

public class Login implements Serializable {
    private String cookin;
    private String response;

    public String getCookin() {
        return cookin;
    }

    public void setCookin(String cookin) {
        this.cookin = cookin;
    }

    public String getResponse() {
        return response;
    }

    public void setResponse(String response) {
        this.response = response;
    }
}
