package com.typer.typer_online.service;

import com.typer.typer_online.Dao.DBAccess;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.ArrayList;

@Component
public class MyUserDetailService implements UserDetailsService {

    @Autowired
    DBAccess dbAccess;

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        com.typer.typer_online.model.User dbUser = this.dbAccess.getUserDetailsByUsername(s);
        return new User(dbUser.getUsername(),dbUser.getPassword(), new ArrayList<>());
    }
}
