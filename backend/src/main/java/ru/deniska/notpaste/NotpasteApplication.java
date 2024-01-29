package ru.deniska.notpaste;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;


@SpringBootApplication
@EnableScheduling
public class NotpasteApplication {

	public static void main(String[] args) {
		SpringApplication.run(NotpasteApplication.class, args);
	}

}
