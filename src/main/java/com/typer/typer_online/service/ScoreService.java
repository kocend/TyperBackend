package com.typer.typer_online.service;

import com.typer.typer_online.Dao.DBAccess;
import com.typer.typer_online.model.Game;
import com.typer.typer_online.model.GameJSON;
import com.typer.typer_online.model.Score;
import com.typer.typer_online.model.Tip;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.function.Predicate;
import java.util.stream.Collectors;

@Component
public class ScoreService {

    @Autowired
    DBAccess dbAccess;
    
    @Autowired
    EventService eventService;

    public Long getAllUserScores(){
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        Integer userId = this.dbAccess.getUserIdByName(auth.getName());

        return this.dbAccess.getAllUserScoresByUserId(userId);
    }

    public List<Score> getAllUsersAndScores(){
        Predicate<Game> predicate = (Game game) -> { return true; };
        return getScoresSatisfying(predicate);
    }

    public List<Score> getAllUsersAndScoresByLeagueId(Integer leagueID){
        Predicate<Game> predicate = (Game game) -> { return game.getLeague_id().equals(leagueID); };
        return getScoresSatisfying(predicate);
    }

    public List<Score> getAllUsersAndScoresByTeamId(Integer teamID){
        Predicate<Game> predicate = (Game game) -> { return game.getHome_team_id().equals(teamID)||game.getAway_team_id().equals(teamID); };
        return getScoresSatisfying(predicate);
    }

    private List<Score> getScoresSatisfying(Predicate<Game> predicate){
        List<Tip> tips = this.dbAccess.getAllTips();
        List<Game> games = new LinkedList<>();

        for (Tip t : tips) {
            games.add(new Game(this.eventService.getGameById(t.getGame_id())));
        }

        games = games.stream()
                .filter(game -> predicate.test(game)).collect(Collectors.toList());

        List<Game> finalGames = games;

        tips = tips.stream()
                .filter(tip -> {
                    if(finalGames.stream().anyMatch(game -> game.getGame_id().equals(tip.getGame_id())))
                        return true;
                    else
                        return false;
                }).collect(Collectors.toList());

        Map<Integer, Integer> userScores = tips.stream()
                .collect(Collectors.groupingBy(Tip::getUser_id, Collectors.summingInt(Tip::getUser_score)));

        List<Score> scores = new LinkedList<>();

        for (Map.Entry<Integer, Integer> entry : userScores.entrySet()) {
            String username = this.dbAccess.getUsernameById(entry.getKey());
            scores.add(new Score(0, username, entry.getValue()));
        }

        scores.sort((a, b) -> {
            if(a.getPoints() > b.getPoints())
                return -1;
            else
                return 1;
        });

        for(int i = 0; i < scores.size(); i++)
            scores.get(i).setPosition(i+1);

        return scores;
    }
}
