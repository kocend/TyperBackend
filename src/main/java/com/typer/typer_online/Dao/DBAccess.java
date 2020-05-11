package com.typer.typer_online.Dao;

import com.typer.typer_online.model.*;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.List;


public interface DBAccess {

    public void log(Log log);

    public void register(User user);

    public Integer getUserIdByName(String name) throws UsernameNotFoundException;

    public String getUsernameById(Integer userID);

    public User getUserDetailsByUsername(String unsername) throws UsernameNotFoundException;

    public boolean isUsernameFree(String username);

    public Integer getUserScores(Integer userID);

    public void addTip(Tip tip);

    public void setTip(Integer userID, Integer gameID, Tip tip);

    public void setUserScore(Integer userID, Integer gameID, Integer score);

    public Tip getTip(Integer userID, Integer gameID);

    public List<Tip> getAllUserTipsByUserId(Integer userId);

    public List<Tip> getAllTips();

    public List<Tip> getAllUnmarkedTips();

    public void addResult(Game game);

    public Long getAllUserScoresByUserId(Integer userID);
}
