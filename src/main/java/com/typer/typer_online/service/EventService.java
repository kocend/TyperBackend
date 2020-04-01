package com.typer.typer_online.service;

import com.typer.typer_online.Dao.DBAccess;
import com.typer.typer_online.model.EventsJSON;
import com.typer.typer_online.model.Game;
import com.typer.typer_online.model.GameJSON;
import com.typer.typer_online.model.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.List;


@Component
public class EventService {

    @Autowired
    private WebClient.Builder webClientBuilder;

    @Autowired
    private DBAccess dbAccess;

    //@Scheduled(fixedRate = 20000)
    private void getRecentScores(){

        EventsJSON games = webClientBuilder
                .build()
                .get()
                .uri("https://www.thesportsdb.com/api/v1/json/1/eventspastleague.php?id=4422")
                .accept(MediaType.APPLICATION_JSON)
                .retrieve()
                .bodyToMono(EventsJSON.class)
                .block();

        for (GameJSON gameJSON:games.getEvents()) {
            Game game = new Game(gameJSON);
            dbAccess.addResult(game);
            System.out.println("added new record!");
        }

    }

    public GameJSON getGameById(int gameID){
        return webClientBuilder
                .build()
                .get()
                .uri("https://www.thesportsdb.com/api/v1/json/1/lookupevent.php?id="+gameID)
                .accept(MediaType.APPLICATION_JSON)
                .retrieve()
                .bodyToMono(EventsJSON.class)
                .block()
                .getEvents()
                .get(0);
    }

    public Result getResultByGameId(int gameID){
        GameJSON gameJSON = getGameById(gameID);
        return new Result(gameJSON.getIntHomeScore(), gameJSON.getIntAwayScore());
    }

    public List<GameJSON> getNext15Games(){
        return webClientBuilder
                .build()
                .get()
                .uri("https://www.thesportsdb.com/api/v1/json/1/eventsnextleague.php?id=4422")
                .accept(MediaType.APPLICATION_JSON)
                .retrieve()
                .bodyToMono(EventsJSON.class)
                .block()
                .getEvents();
    }
}
