package ru.intodayer.altarixrestapitask;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;


@SpringBootApplication
@EnableJpaAuditing
public class AltarixRestApiTaskApplication {

	public static void main(String[] args) {
		SpringApplication.run(AltarixRestApiTaskApplication.class, args);
	}
}
