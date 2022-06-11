package com.app.meal.controller;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.app.meal.model.MultipleFoodSearchCriteria;

@FeignClient(name = "search-service", url = "${client.post.baseUrl}")
public interface SearchServiceFeignClient {
	
	@PostMapping("/foods")
	public ResponseEntity<?> searchFoodByIds(@RequestBody(required = false) MultipleFoodSearchCriteria searchInput) ;

}
