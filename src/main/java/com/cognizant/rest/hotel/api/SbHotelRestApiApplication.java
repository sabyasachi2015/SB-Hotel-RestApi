package com.cognizant.rest.hotel.api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
//@ComponentScan(basePackages = {"com.cognizant.rest.hotel.api"})
//@EnableJpaRepositories("com.cognizant.rest.hotel.api")
public class SbHotelRestApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(SbHotelRestApiApplication.class, args);
	}

}
