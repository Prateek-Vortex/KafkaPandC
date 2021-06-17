package com.prateek.addaKafka.addaKafka;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@ComponentScan("com.prateek.addaKafka.addaKafka.*")
public class AddaKafkaApplication {

	public static void main(String[] args) {
		SpringApplication.run(AddaKafkaApplication.class, args);
	}

}
