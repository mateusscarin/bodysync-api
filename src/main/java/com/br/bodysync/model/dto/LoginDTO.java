package com.br.bodysync.model.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class LoginDTO {

    @NotNull(message = "The email address must be provided!")
    @NotBlank(message = "The email must not be blank!")
    @Email
    private String email;

    @NotNull(message = "The password must be entered!")
    @NotBlank(message = "The password must not be blank!")
    private String password;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

}
