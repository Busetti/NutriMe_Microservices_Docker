package com.app.meal.service;

import java.util.List;
import java.util.Map;

import com.app.meal.exceptions.MealNotFoundException;
import com.app.meal.exceptions.MealsAlreadyExistsException;
import com.app.meal.exceptions.UserNotFoundException;
import com.app.meal.model.MultipleFoodSearchCriteria;
import com.app.meal.model.NutritionistMeal;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;

public interface MealService {
	
	public NutritionistMeal addMealFood(NutritionistMeal meal) throws MealsAlreadyExistsException;

	public List<NutritionistMeal> getmealbyusername(String username) throws UserNotFoundException;

	void deletemeal(Integer Id) throws MealNotFoundException;

	public Map<String, Double> searchFoodDetails(MultipleFoodSearchCriteria searchInput,
			Map<String, Double> analysisData) throws JsonProcessingException, JsonMappingException ;
}
