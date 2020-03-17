package com.typer.typer_online.service;

import com.typer.typer_online.Dao.DBAccess;
import com.typer.typer_online.model.Credentials;
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
        //TODO
    }
}
