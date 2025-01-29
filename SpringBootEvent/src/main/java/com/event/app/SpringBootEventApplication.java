package com.event.app;

import com.event.app.publisher.UserPublisher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class SpringBootEventApplication {

	public static void main(String[] args) {

		SpringApplication.run(SpringBootEventApplication.class, args);
	}

	@Autowired
	private UserPublisher userPublisher;

	@Bean
	CommandLineRunner init(){
		return args -> {
			userPublisher.publishUserRegisteredEvent("anvera", "1234", (byte) 28);
		};
	}

}
