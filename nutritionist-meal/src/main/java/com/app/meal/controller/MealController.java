package com.app.meal.controller;

import java.security.InvalidParameterException;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.app.meal.exceptions.MealNotFoundException;
import com.app.meal.exceptions.MealsAlreadyExistsException;
import com.app.meal.exceptions.UserNotFoundException;
import com.app.meal.model.MultipleFoodSearchCriteria;
import com.app.meal.model.NutritionistMeal;
import com.app.meal.service.MealService;

@RestController
@RequestMapping(value = "/api/v1/meal")
@CrossOrigin
public class MealController {

	private MealService mealService;


	@Autowired
	public MealController(MealService favouritesService) {
		this.mealService = favouritesService;
	}

	@PostMapping(value = "/addmeal")
	public ResponseEntity<?> addfavoritefood(@RequestBody NutritionistMeal nutritionistFavourite) {
		try {
			return new ResponseEntity<>(mealService.addMealFood(nutritionistFavourite), HttpStatus.CREATED);
		} catch (MealsAlreadyExistsException e) {
			return new ResponseEntity<>("This food is already added to Meal", HttpStatus.CONFLICT);
		}

	}

	@DeleteMapping("/deletemeal/{Id}")
	public ResponseEntity<?> deletefavoritefood(@PathVariable Integer Id) throws MealNotFoundException {
		mealService.deletemeal(Id);
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}

	@GetMapping(value = "/mealsbyusername/{username}")
	public ResponseEntity<?> getfavouritebyusername(@PathVariable String username) {
		try {
			return new ResponseEntity<>(mealService.getmealbyusername(username), HttpStatus.OK);
		} catch (UserNotFoundException e) {
			return new ResponseEntity<>("User Not found", HttpStatus.NOT_FOUND);
		}

	}

	@PostMapping("/analyze")
	public ResponseEntity<?> analyze(@RequestBody(required = false) MultipleFoodSearchCriteria searchInput) {
		Map<String, Double> analysisData = null;
		try {
			if (searchInput.getFormat().toLowerCase().equals("abridged")) {
				analysisData = mealService.searchFoodDetails(searchInput, analysisData);
			} else {
				throw new InvalidParameterException("Format field must be " + "abridged");
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<>(analysisData, HttpStatus.OK);
	}



}
