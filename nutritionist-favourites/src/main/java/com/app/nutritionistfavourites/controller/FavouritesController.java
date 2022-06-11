package com.app.nutritionistfavourites.controller;

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

import com.app.nutritionistfavourites.model.FoodFdcId;
import com.app.nutritionistfavourites.exceptions.FavouriteNotFoundException;
import com.app.nutritionistfavourites.exceptions.FavouritesAlreadyExistsException;
import com.app.nutritionistfavourites.exceptions.UserNotFoundException;
import com.app.nutritionistfavourites.model.NutritionistFavourite;
import com.app.nutritionistfavourites.service.FavouritesService;
import com.app.nutritionistfavourites.service.FoodSearchService;

@RestController
@RequestMapping(value = "/api/v1/favourite")
@CrossOrigin
public class FavouritesController {
	
	private FavouritesService favouritesService;
	
	private FoodSearchService foodSearchService;

	@Autowired
	public FavouritesController(FavouritesService favouritesService, FoodSearchService foodSearchService) {
		this.favouritesService = favouritesService;
		this.foodSearchService = foodSearchService;
	}

	@PostMapping(value = "/addfavouritefood")
	public ResponseEntity<?> addfavoritefood(@RequestBody NutritionistFavourite nutritionistFavourite) {
		try {
			return new ResponseEntity<>(favouritesService.addfavoritefood(nutritionistFavourite), HttpStatus.CREATED);
		} catch (FavouritesAlreadyExistsException e) {
			return new ResponseEntity<>("This food is already added to favourite", HttpStatus.CONFLICT);
		}

	}

	@DeleteMapping("/deletefavouritefood/{Id}")
	public ResponseEntity<?> deletefavoritefood(@PathVariable Integer Id) throws FavouriteNotFoundException {
		favouritesService.deletefavoritefood(Id);
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}

	@GetMapping(value = "/getfavouritebyusername/{username}")
	public ResponseEntity<?> getfavouritebyusername(@PathVariable String username) {
		try {
			return new ResponseEntity<>(favouritesService.getfavouritebyusername(username), HttpStatus.OK);
		} catch (UserNotFoundException e) {
			return new ResponseEntity<>("User Not found", HttpStatus.NOT_FOUND);
		}

	}
	
	@GetMapping("/searchfood/{fdcId}")
	public ResponseEntity<?> searchFood(@PathVariable("fdcId") String fdcId) {
		FoodFdcId responseEntity = null;
		try {
			responseEntity = foodSearchService.getFoodByFdcId(fdcId);
		} catch (Exception e) {
			e.printStackTrace();
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<>(responseEntity, HttpStatus.OK);
	}

}
