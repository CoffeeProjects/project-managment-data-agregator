package org.coffeeprojects.pmda;

import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
@EnableBatchProcessing
public class PmdaApplication {

	public static void main(String[] args) {
		SpringApplication.run(PmdaApplication.class, args);
	}

}