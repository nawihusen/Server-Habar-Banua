package com.habarbanua;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class HabarbanuaApplication {

	public static void main(String[] args) {
		SpringApplication.run(HabarbanuaApplication.class, args);
	}

}
