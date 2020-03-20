package com.typer.typer_online.service;

import com.typer.typer_online.Dao.DBAccess;
import com.typer.typer_online.model.Tip;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

@Component
public class TipService {

    @Autowired
    private DBAccess dbAccess;

    public void addTip(Tip tip){
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        Integer userId = this.dbAccess.getUserIdByName(auth.getName());

        tip.setUser_id(userId);
        this.dbAccess.addTip(tip);
    }

    public Tip getTipByGameId(Integer gameID){
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        Integer userId = this.dbAccess.getUserIdByName(auth.getName());

        Tip tip = this.dbAccess.getTip(userId,gameID);
        if(tip != null)
            return tip;
        else
            return new Tip();
    }

    public void setTip(Integer gameId, Tip tip){
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        Integer userId = this.dbAccess.getUserIdByName(auth.getName());

        this.dbAccess.setTip(userId, gameId, tip);
    }

}
