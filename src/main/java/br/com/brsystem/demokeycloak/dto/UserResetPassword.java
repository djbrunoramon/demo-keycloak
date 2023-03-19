package br.com.brsystem.demokeycloak.dto;

import javax.validation.constraints.NotBlank;

public class UserResetPassword {

    @NotBlank
    private String idUser;

    @NotBlank
    private String password;

    public String getIdUser() {
        return idUser;
    }

    public void setIdUser(String idUser) {
        this.idUser = idUser;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
