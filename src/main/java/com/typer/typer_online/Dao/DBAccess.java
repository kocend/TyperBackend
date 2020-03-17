package com.typer.typer_online.Dao;

import com.typer.typer_online.model.Game;
import com.typer.typer_online.model.Tip;
import com.typer.typer_online.model.User;
import org.springframework.security.core.userdetails.UsernameNotFoundException;


public interface DBAccess {

    public void register();

    public Integer getUserIdByName(String name);

    public Integer getUserScores(Integer userID);

    public User getUserDetailsByUsername(String unsername) throws UsernameNotFoundException;

    public void addTip(Tip tip);

    public Tip getTip(Integer userID, Integer gameID);

    public void setTip(Integer userID, Integer gameID, Tip tip);

    public void addResult(Game game);

}
