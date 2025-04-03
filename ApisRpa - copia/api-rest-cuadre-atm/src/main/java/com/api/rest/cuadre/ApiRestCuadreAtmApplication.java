package com.api.rest.cuadre;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.scheduling.annotation.EnableAsync;

@SpringBootApplication
@EnableAsync
public class ApiRestCuadreAtmApplication extends SpringBootServletInitializer {

	public static void main(String[] args) {
		SpringApplication.run(ApiRestCuadreAtmApplication.class, args);
	}

	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
		return application.sources(ApiRestCuadreAtmApplication.class);
	}

}
