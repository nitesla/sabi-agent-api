package com.sabi.agent.api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;

@ComponentScan(basePackages = "com.sabi")
@EntityScan(basePackages = {"com.sabi.agent.core.models"})
@SpringBootApplication
public class AgentApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(AgentApiApplication.class, args);
	}

}
