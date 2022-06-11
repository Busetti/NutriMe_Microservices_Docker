package com.app.nutritionist;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@SpringBootApplication
@EnableEurekaServer
public class NutritionistDiscoveryApplication {

	public static void main(String[] args) {
		SpringApplication.run(NutritionistDiscoveryApplication.class, args);
	}

}
