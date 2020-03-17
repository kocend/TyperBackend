package com.typer.typer_online;

import com.typer.typer_online.model.GameJSON;
import com.typer.typer_online.service.EventService;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
class TyperOnlineApplicationTests {

	@Autowired
	EventService eventService;

	@Test
	void contextLoads() {
	}

	@Test
	public void returnApropriateGameId(){

		Integer gameID = 672508;

		Integer returnedGameID = eventService.getGameById(gameID).getIdEvent();

		Assert.assertEquals(gameID,returnedGameID);
	}

	@Test
	public void returnApropriateAmountOfGames(){

		List<GameJSON> games = eventService.getNext15Games();

		Assert.assertEquals(15, games.size());
	}
}
