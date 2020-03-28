package org.coffeeprojects.pmda;

import org.coffeeprojects.pmda.service.ProjectsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class PmdaApplication {

	@Autowired
	ProjectsService projectsService;

	public static void main(String[] args) throws InterruptedException {
		SpringApplication.run(PmdaApplication.class, args);
	}

}