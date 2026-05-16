package com.example.cellphones_spring;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing(dateTimeProviderRef = "auditingDateTimeProvider")
@SpringBootApplication
public class CellphonesSpringApplication {

	public static void main(String[] args) {
		SpringApplication.run(CellphonesSpringApplication.class, args);
	}

}
