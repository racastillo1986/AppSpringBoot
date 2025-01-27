package com.bot.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class BotRcApplication {

    public static void main(String[] args) {

		//SpringApplication.run(BotRcApplication.class, args);
		Integer a = 200;
        Integer b = 201;

        Boolean result = a.equals(b);

        System.out.println(result);
    }

}
