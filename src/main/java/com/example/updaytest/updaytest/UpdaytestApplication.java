package com.example.updaytest.updaytest;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class UpdaytestApplication {

	public static void main(String[] args) {
		SpringApplication.run(UpdaytestApplication.class, args);
	}

}
