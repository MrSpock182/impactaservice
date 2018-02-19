package br.com.studiotrek.impactaservice.login.model;

import java.io.Serializable;

public class ResponseLogin implements Serializable {

    private Boolean success;

    public Boolean getSuccess() {
        return success;
    }

    public void setSuccess(Boolean success) {
        this.success = success;
    }
}
