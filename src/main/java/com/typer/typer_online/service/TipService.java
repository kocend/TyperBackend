package com.typer.typer_online.service;

import com.typer.typer_online.Dao.DBAccess;
import com.typer.typer_online.model.Tip;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class TipService {

    @Autowired
    private DBAccess dbAccess;

    public void addTip(Tip tip){
        //Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        //System.out.println("\n\n\n\n\n\n\n\n\n\n\n\n name: "+auth.getName()+"\n\n\n\n\n\n\n\n\n\n\n\n");
        //Integer userId = this.dbAccess.getUserIdByName(auth.getName());
        //TODO get userID
        tip.setUser_id(1);
        System.out.println(tip.toString());
        this.dbAccess.addTip(tip);
    }

    public Tip getTipByGameId(Integer gameID){
        //TODO get userID
        Tip tip = this.dbAccess.getTip(1,gameID);
        if(tip != null)
            return tip;
        else
            return new Tip();
    }

    public void setTip(Integer gameId, Tip tip){
        //TODO get userID
        this.dbAccess.setTip(1, gameId, tip);
    }

}
