package com.typer.typer_online.Dao;

import com.typer.typer_online.model.Game;
import com.typer.typer_online.model.Tip;
import com.typer.typer_online.model.User;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

@Transactional
@Component
public class DBAccessImpl implements DBAccess {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public void register(User user) {
        this.entityManager.persist(user);
    }

    @Override
    public Integer getUserIdByName(String name) throws UsernameNotFoundException {
        Query query = this.entityManager.createQuery("FROM User u WHERE u.username=:name");
        query.setParameter("name",name);
        List<User> users = query.getResultList();

        if(users.size()!= 0)
            return users.get(0).getId();
        else
            throw new UsernameNotFoundException("No user found");

    }

    @Override
    public String getUsernameById(Integer userID) {
        Query query = this.entityManager.createQuery("FROM User u WHERE u.id = :id");
        query.setParameter("id",userID);
        List<User> users = query.getResultList();
        return users.get(0).getUsername();
    }

    @Override
    public User getUserDetailsByUsername(String username) throws UsernameNotFoundException {

        Query query = this.entityManager.createQuery("FROM User u WHERE u.username=:name");
        query.setParameter("name",username);
        List<User> users = query.getResultList();

        if(users.size()!= 0)
            return users.get(0);
        else
            throw new UsernameNotFoundException("No user found");
    }

    @Override
    public boolean isUsernameFree(String username) {
        Query query = this.entityManager.createQuery("FROM User u WHERE u.username=:name");
        query.setParameter("name",username);
        List<User> users = query.getResultList();


        if(users.size() > 0)
            return false;
        else
            return true;
    }


    @Override
    public Integer getUserScores(Integer userID) {
        return null;
    }

    @Override
    public void addTip(Tip tip) {
        this.entityManager.persist(tip);
    }

    @Override
    public Tip getTip(Integer userID, Integer gameID) {
        Query query = this.entityManager.createQuery("FROM Tip t WHERE t.user_id=:userID AND t.game_id=:gameID");
        query.setParameter("userID", userID);
        query.setParameter("gameID", gameID);
        List<Tip> tips = query.getResultList();

        if(tips.size() != 0)
            return tips.get(0);
        else
            return null;
    }

    @Override
    public void setTip(Integer userID, Integer gameID, Tip tip) {
        Tip persistedTip = this.getTip(userID,gameID);
        if(persistedTip != null) {
            persistedTip.setHome_score(tip.getHome_score());
            persistedTip.setAway_score(tip.getAway_score());
            this.entityManager.flush();
        }
    }

    @Override
    public List<Tip> getAllUserTipsByUserId(Integer userId) {
        Query query = this.entityManager.createQuery("FROM Tip t WHERE t.user_id=:id");
        query.setParameter("id", userId);
        List<Tip> userTips = query.getResultList();

        return userTips;
    }

    @Override
    public List<Tip> getAllTips() {
        Query query = this.entityManager.createQuery("FROM Tip");
        List<Tip> tips = query.getResultList();

        return tips;
    }

    @Override
    public List<Tip> getAllUnmarkedTips(){
        Query query = this.entityManager.createQuery("FROM Tip t WHERE t.user_score = NULL");
        List<Tip> unMarkedTips = query.getResultList();
        return unMarkedTips;
    }

    @Override
    public void addResult(Game game) {
        if(this.entityManager.find(Game.class,game.getGame_id()) == null)
            this.entityManager.persist(game);
    }

    @Override
    public Long getAllUserScoresByUserId(Integer userID){
        Query query = this.entityManager.createQuery("SELECT SUM(t.user_score) FROM Tip t WHERE t.user_id=:id");
        query.setParameter("id",userID);
        List<Long> scores = query.getResultList();
        return scores.get(0);
    }
}
