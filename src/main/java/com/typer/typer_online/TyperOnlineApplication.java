package com.typer.typer_online;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class TyperOnlineApplication {

	public static void main(String[] args) {
		SpringApplication.run(TyperOnlineApplication.class, args);
	}

}
