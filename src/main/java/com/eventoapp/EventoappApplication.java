package com.eventoapp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@ComponentScan(basePackages = { "com.eventoapp" })
@EnableJpaRepositories(basePackages = { "com.eventoapp" }) // onde procurar as interfaces dos repositórios do JPA
@EntityScan(basePackages = { "com.eventoapp.Entity" }) // onde procurar as entidades
public class EventoappApplication {

	public static void main(String[] args) {

		SpringApplication.run(EventoappApplication.class, args);

	}
}
