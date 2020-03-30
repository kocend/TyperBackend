package com.typer.typer_online.Dao;

import com.typer.typer_online.model.Game;
import com.typer.typer_online.model.Score;
import com.typer.typer_online.model.Tip;
import com.typer.typer_online.model.User;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.List;


public interface DBAccess {

    public void register(User user);

    public Integer getUserIdByName(String name) throws UsernameNotFoundException;

    public String getUsernameById(Integer userID);

    public User getUserDetailsByUsername(String unsername) throws UsernameNotFoundException;

    public boolean isUsernameFree(String username);

    public Integer getUserScores(Integer userID);

    public void addTip(Tip tip);

    public Tip getTip(Integer userID, Integer gameID);

    public List<Tip> getAllUserTipsByUserId(Integer userId);

    public List<Tip> getAllTips();

    public void setTip(Integer userID, Integer gameID, Tip tip);

    public void addResult(Game game);

    public Long getAllUserScoresByUserId(Integer userID);
}
