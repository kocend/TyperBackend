package com.typer.typer_online.service;

import com.typer.typer_online.Dao.DBAccess;
import com.typer.typer_online.model.Credentials;
import com.typer.typer_online.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class RegisterService {

    @Autowired
    DBAccess dbAccess;

    public boolean isUsernameFree(String username){
        return this.dbAccess.isUsernameFree(username);
    }

    public void register(Credentials credentials){
        User user = new User();
        user.setUsername(credentials.getUsername());
        user.setPassword(credentials.getPassword());
        user.setEnabled(true);
        this.dbAccess.register(user);
    }
}
