package com.sabi.agent.api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.transaction.annotation.EnableTransactionManagement;


@ComponentScan(basePackages = "com.sabi")
@EntityScan(basePackages = {"com.sabi.agent.core.models"})
@EnableTransactionManagement
@EnableScheduling
@SpringBootApplication
public class AgentApiApplication {



	public static void main(String[] args) {

		SpringApplication.run(AgentApiApplication.class, args);



	}




}
