package com.plannet.plannet;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class PlannetApplication {

	public static void main(String[] args) {
		SpringApplication.run(PlannetApplication.class, args);
	}

}
