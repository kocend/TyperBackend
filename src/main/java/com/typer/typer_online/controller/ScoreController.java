package com.typer.typer_online.controller;

import com.typer.typer_online.service.ScoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "http://localhost:4200", maxAge = 3600 )
@RestController
public class ScoreController {

    @Autowired
    ScoreService scoreService;

    @GetMapping(value = "/scores")
    public ResponseEntity<?> getAllUserScores(){
        return ResponseEntity.ok(this.scoreService.getAllUserScores());
    }

    @GetMapping(value = "/scores/all")
    public ResponseEntity<?> getAllUsersAndScores(){
        return ResponseEntity.ok(this.scoreService.getAllUsersAndScores());
    }

    @GetMapping(value = "/scores/leagues/{id}")
    public ResponseEntity<?> getAllUsersAndScoresByLeagueId(@PathVariable(value = "id") Integer leagueID){
        return ResponseEntity.ok(this.scoreService.getAllUsersAndScoresByLeagueId(leagueID));
    }

    @GetMapping(value = "/scores/teams/{id}")
    public ResponseEntity<?> getAllUsersAndScoresByTeamId(@PathVariable(value = "id") Integer teamID){
        return ResponseEntity.ok(this.scoreService.getAllUsersAndScoresByTeamId(teamID));
    }
}
