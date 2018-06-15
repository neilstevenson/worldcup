package com.hazelcast.demo.worldcup;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * <p>
 * Run this application with <a href="https://spring.io/projects/spring-boot">Spring Boot</a>.
 * </p>
 * <p>
 * Spring Boot will scan the classes and build most of the framework we need -- Hazelcast,
 * a CLI, etc -- so we can focus on the business logic.
 * </p>
 */
@SpringBootApplication
public class Application {

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

}
