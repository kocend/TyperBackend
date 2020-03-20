package com.typer.typer_online.model;

import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;

public class Credentials {

    @NotBlank(message = "username cannot be blank")
    @Length(max = 20)
    private String username;

    @NotBlank(message = "password cannot be blank")
    @Length(min = 4, max = 60)
    private String password;

    public Credentials() {
    }

    public Credentials(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
