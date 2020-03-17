package com.typer.typer_online.controller;


import com.typer.typer_online.model.GameJSON;
import com.typer.typer_online.service.EventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class MainController {

    @Autowired
    private EventService eventService;

    @GetMapping(value = "/games")
    public List<GameJSON> getGames(){
        return eventService.getNext15Games();
    }

}
