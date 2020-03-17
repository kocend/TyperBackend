package com.typer.typer_online.model;

import java.util.List;

public class EventsJSON {

    private List<GameJSON> events;

    public List<GameJSON> getEvents() {
        return events;
    }

    public void setEvents(List<GameJSON> events) {
        this.events = events;
    }
}
