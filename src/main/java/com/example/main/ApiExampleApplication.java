package com.example.main;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableScheduling
@SpringBootApplication
public class ApiExampleApplication {

	public static void main(String[] args) {
		SpringApplication.run(ApiExampleApplication.class, args);
	}

}
