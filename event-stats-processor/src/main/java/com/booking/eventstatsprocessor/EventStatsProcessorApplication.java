package com.booking.eventstatsprocessor;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ConfigurationPropertiesScan
@ComponentScan
public class EventStatsProcessorApplication {

	public static void main(String[] args) {

		SpringApplication.run(EventStatsProcessorApplication.class, args);
	}

}
