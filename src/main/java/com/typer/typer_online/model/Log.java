package com.typer.typer_online.model;

import javax.persistence.*;

@Entity
@Table(name = "log")
public class Log {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private
    Integer id;

    private String log;

    public Log(String log){
        this.log = log;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getLog() {
        return log;
    }

    public void setLog(String log) {
        this.log = log;
    }
}
