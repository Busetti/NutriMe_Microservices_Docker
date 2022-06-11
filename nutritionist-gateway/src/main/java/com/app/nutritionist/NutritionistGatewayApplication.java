package com.app.nutritionist;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class NutritionistGatewayApplication {

	public static void main(String[] args) {
		SpringApplication.run(NutritionistGatewayApplication.class, args);
	}

}
