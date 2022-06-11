package com.app.nutritionistfavourites.service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.app.nutritionistfavourites.model.FoodFdcId;

@FeignClient(name = "search-service")
public interface FoodSearchService {

	 @GetMapping(value = "/api/v1/search/food/{fdcId}")
	 public FoodFdcId getFoodByFdcId(@PathVariable("fdcId") String fdcId);
	
}
