package com.gabriel.crudsimplescomaws;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@SpringBootApplication
@EnableMongoRepositories
public class CrudSimplesComAwsApplication {

	public static void main(String[] args) {
		SpringApplication.run(CrudSimplesComAwsApplication.class, args);
	}

}
