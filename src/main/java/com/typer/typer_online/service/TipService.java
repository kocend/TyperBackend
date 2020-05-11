package com.typer.typer_online.service;

import com.typer.typer_online.Dao.DBAccess;
import com.typer.typer_online.model.GameJSON;
import com.typer.typer_online.model.Log;
import com.typer.typer_online.model.Tip;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import java.util.List;


@Component
public class TipService {

    @Autowired
    private DBAccess dbAccess;

    @Autowired
    private EventService eventService;

    public void addTip(Tip tip){
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        Integer userId = this.dbAccess.getUserIdByName(auth.getName());

        tip.setUser_id(userId);
        //tip.setUser_score(0);
        this.dbAccess.addTip(tip);

        GameJSON gameJSON = eventService.getGameById(tip.getGame_id());
        Log log = new Log("user id: " + userId + " "
                    + gameJSON.getIdEvent() + " "
                    + gameJSON.getStrEvent() + " "
                    + gameJSON.getDateEvent());
        dbAccess.log(log);
    }

    public List<Tip> getAllUserTips(){
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        Integer userId = this.dbAccess.getUserIdByName(auth.getName());

        return this.dbAccess.getAllUserTipsByUserId(userId);
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

        //tip.setUser_score(0);
        this.dbAccess.setTip(userId, gameId, tip);
    }

}
