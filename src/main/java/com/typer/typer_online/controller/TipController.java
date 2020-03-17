package com.typer.typer_online.controller;


import com.typer.typer_online.model.Tip;
import com.typer.typer_online.service.TipService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "http://localhost:4200", maxAge = 3600)
@RestController
public class TipController {

    @Autowired
    private TipService tipService;

    @PostMapping(value = "/tips", consumes = "application/json")
    public void addTip(@RequestBody Tip tip){
        System.out.println(tip.toString());
        this.tipService.addTip(tip);
    }

    @GetMapping(value = "/tips/{id}")
    public Tip getTipByGameId(@PathVariable("id") Integer gameId){
        return this.tipService.getTipByGameId(gameId);
    }

    @PutMapping(value = "/tips/{id}", consumes = "application/json")
    public void setTip(@PathVariable("id") Integer gameId, @RequestBody Tip tip){
        this.tipService.setTip(gameId, tip);
    }

}
