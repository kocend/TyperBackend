package com.typer.typer_online.service;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class PasswordEncoder {

    BCryptPasswordEncoder passwordEncoder;

    public PasswordEncoder(){
        passwordEncoder = new BCryptPasswordEncoder();
    }

    public String encodePassword(String password){
        return passwordEncoder.encode(password);
    }
}
