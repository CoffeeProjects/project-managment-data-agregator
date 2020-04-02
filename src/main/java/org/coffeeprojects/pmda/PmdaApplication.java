package org.coffeeprojects.pmda;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class PmdaApplication {

	public static void main(String[] args) throws InterruptedException {
		SpringApplication.run(PmdaApplication.class, args);
	}

}