package com.typer.typer_online.service;

import com.typer.typer_online.Dao.DBAccess;
import com.typer.typer_online.model.EventsJSON;
import com.typer.typer_online.model.GameJSON;
import com.typer.typer_online.model.Tip;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.List;

@Component
public class PointScoringService {

    @Autowired
    private WebClient.Builder webClientBuilder;

    @Autowired
    private DBAccess dbAccess;

    @Autowired
    private EventService eventService;

    @Scheduled(cron = "0 0,10,20,30,40,50 * * * *")
    private void markUsersTips(){

        List<Tip> unMarkedTips = dbAccess.getAllUnmarkedTips();

        unMarkedTips.forEach(tip -> {
            GameJSON game = eventService.getGameById(tip.getGame_id());
            if(game.getIntHomeScore() == null || game.getIntAwayScore() == null)
                return;

            Integer points = 0;

            if(tip.getHome_score() == game.getIntHomeScore())
                points++;

            if(tip.getAway_score() == game.getIntAwayScore())
                points++;

            if(tip.getHome_score() == game.getIntHomeScore()
                && tip.getAway_score() == game.getIntAwayScore())
                points++;

            if(tip.getHome_score() > tip.getAway_score()
                && game.getIntHomeScore() > game.getIntAwayScore())
                points++;

            if(tip.getHome_score() == tip.getAway_score()
                    && game.getIntHomeScore() == game.getIntAwayScore())
                points++;

            if(tip.getHome_score() < tip.getAway_score()
                    && game.getIntHomeScore() < game.getIntAwayScore())
                points++;

            tip.setUser_score(points);

            dbAccess.setTip(tip.getUser_id(),tip.getGame_id(),tip);
        });
    }
}
