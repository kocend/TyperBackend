package com.typer.typer_online.model;

public class Score {

    private Integer position;
    private String username;
    private Integer points;

    public Score() {
    }

    public Score(Integer position, String username, Integer points) {
        this.position = position;
        this.username = username;
        this.points = points;
    }

    public Integer getPosition() {
        return position;
    }

    public void setPosition(Integer position) {
        this.position = position;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Integer getPoints() {
        return points;
    }

    public void setPoints(Integer points) {
        this.points = points;
    }
}
