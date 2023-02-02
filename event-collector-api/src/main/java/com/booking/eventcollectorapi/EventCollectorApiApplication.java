package com.booking.eventcollectorapi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;

@SpringBootApplication
@ConfigurationPropertiesScan
public class EventCollectorApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(EventCollectorApiApplication.class, args);
	}

}
