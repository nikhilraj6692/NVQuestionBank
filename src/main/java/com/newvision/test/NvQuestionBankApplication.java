package com.newvision.test;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class })
@EnableMongoRepositories(basePackages = {"com.newvision.test.infra"})
public class NvQuestionBankApplication {

	public static void main(String[] args) {
		SpringApplication.run(NvQuestionBankApplication.class, args);
	}

}
