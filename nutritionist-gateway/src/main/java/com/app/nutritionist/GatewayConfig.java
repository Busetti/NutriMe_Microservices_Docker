package com.app.nutritionist;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.app.nutritionist.filters.JwtAuthenticationFilter;

@Configuration
public class GatewayConfig {

	@Autowired
	private JwtAuthenticationFilter filter;

	@Bean
	public RouteLocator routes(RouteLocatorBuilder builder) {
		return builder.routes()
				.route("user-service", r -> r.path("/app/v1/user/**").filters(f -> f.filter(filter)).uri("lb://user-service"))
				.route("search-service", r -> r.path("/api/v1/search/**").filters(f -> f.filter(filter)).uri("lb://search-service"))
				.route("favourites-service", r -> r.path("/api/v1/favourite/**").filters(f -> f.filter(filter)).uri("lb://favourites-service"))
				.route("meal-service", r -> r.path("/api/v1/meal/**").filters(f -> f.filter(filter)).uri("lb://meal-service"))
				.build();
	}

}