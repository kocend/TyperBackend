package com.typer.typer_online.model;

import javax.persistence.*;
import java.sql.Date;
import java.sql.Timestamp;

@Entity
@Table(name = "Games")
public class Game {

    @Id
    private Integer game_id;
    private Integer league_id;
    private String league_name;
    private Integer season_id;
    private String home_team_name;
    private String away_team_name;
    private Integer home_score;
    private Integer away_score;
    private Integer round;
    private Timestamp event_timestamp;
    private Integer home_team_id;
    private Integer away_team_id;

    public Game(){};

    public Game(GameJSON gameJSON){
        this.game_id = gameJSON.getIdEvent();
        this.league_id = gameJSON.getIdLeague();
        this.league_name = gameJSON.getStrLeague();
        this.season_id = Integer.parseInt(gameJSON.getStrSeason());
        this.home_team_name = gameJSON.getStrHomeTeam();
        this.away_team_name = gameJSON.getStrAwayTeam();
        this.home_score = gameJSON.getIntHomeScore();
        this.away_score = gameJSON.getIntAwayScore();
        this.round = gameJSON.getIntRound();
        this.event_timestamp = Timestamp.valueOf(gameJSON.getDateEvent()+" "+gameJSON.getStrTimeLocal());
        this.home_team_id = gameJSON.getIdHomeTeam();
        this.away_team_id = gameJSON.getIdAwayTeam();
    }

    public Integer getGame_id() {
        return game_id;
    }

    public void setGame_id(Integer game_id) {
        this.game_id = game_id;
    }

    public Integer getLeague_id() {
        return league_id;
    }

    public void setLeague_id(Integer league_id) {
        this.league_id = league_id;
    }

    public String getLeague_name() {
        return league_name;
    }

    public void setLeague_name(String league_name) {
        this.league_name = league_name;
    }

    public Integer getSeason_id() {
        return season_id;
    }

    public void setSeason_id(Integer season_id) {
        this.season_id = season_id;
    }

    public String getHome_team_name() {
        return home_team_name;
    }

    public void setHome_team_name(String home_team_name) {
        this.home_team_name = home_team_name;
    }

    public String getAway_team_name() {
        return away_team_name;
    }

    public void setAway_team_name(String away_team_name) {
        this.away_team_name = away_team_name;
    }

    public Integer getHome_score() {
        return home_score;
    }

    public void setHome_score(Integer home_score) {
        this.home_score = home_score;
    }

    public Integer getAway_score() {
        return away_score;
    }

    public void setAway_score(Integer away_score) {
        this.away_score = away_score;
    }

    public Integer getRound() {
        return round;
    }

    public void setRound(Integer round) {
        this.round = round;
    }

    public Timestamp getEvent_timestamp() {
        return event_timestamp;
    }

    public void setEvent_timestamp(Timestamp event_timestamp) {
        this.event_timestamp = event_timestamp;
    }

    public Integer getHome_team_id() {
        return home_team_id;
    }

    public void setHome_team_id(Integer home_team_id) {
        this.home_team_id = home_team_id;
    }

    public Integer getAway_team_id() {
        return away_team_id;
    }

    public void setAway_team_id(Integer away_team_id) {
        this.away_team_id = away_team_id;
    }
}
