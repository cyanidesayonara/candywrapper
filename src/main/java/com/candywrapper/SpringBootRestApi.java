package com.candywrapper;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@ComponentScan
@EnableMongoRepositories
@SpringBootApplication
public class SpringBootRestApi {

	public static void main(String[] args) {
		SpringApplication.run(SpringBootRestApi.class, args);
	}

}
