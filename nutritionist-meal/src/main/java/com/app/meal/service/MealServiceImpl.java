package com.app.meal.service;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.app.meal.controller.SearchServiceFeignClient;
import com.app.meal.dao.MealRepository;
import com.app.meal.exceptions.MealNotFoundException;
import com.app.meal.exceptions.MealsAlreadyExistsException;
import com.app.meal.exceptions.UserNotFoundException;
import com.app.meal.model.AbridgedNutrient;
import com.app.meal.model.FoodFdcId;
import com.app.meal.model.MultipleFoodSearchCriteria;
import com.app.meal.model.NutritionistMeal;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;

@Service
public class MealServiceImpl implements MealService {
	private MealRepository favouritesRepository;
	
	private SearchServiceFeignClient postFeignClient;

	@Autowired
	public MealServiceImpl(MealRepository favouritesRepository, SearchServiceFeignClient postFeignClient) {
		this.favouritesRepository = favouritesRepository;
		this.postFeignClient = postFeignClient;
	}

	@Override
	public NutritionistMeal addMealFood(NutritionistMeal meal)
			throws MealsAlreadyExistsException {

		Optional<NutritionistMeal> optionalNutritionistFavourite = favouritesRepository
				.findByFdcIdAndUsername(meal.getFdcId(), meal.getUsername());

		if (!optionalNutritionistFavourite.isPresent()) {
			return favouritesRepository.save(meal);
		} else {
			throw new MealsAlreadyExistsException("This food is already added to favourite list");
		}
	}

	@Override
	public List<NutritionistMeal> getmealbyusername(String username) throws UserNotFoundException {
		return favouritesRepository.findByUsername(username).orElseThrow(() -> new UserNotFoundException("User Notfound "+ username));
	}

	@Override
	public void deletemeal(Integer Id) throws MealNotFoundException {
		if (favouritesRepository.existsById(Id)) {
			favouritesRepository.deleteById(Id);
		} else {
			throw new MealNotFoundException("Favourite Not found with the given ID");
		}
	}
	
	@Override
	public Map<String, Double> searchFoodDetails(MultipleFoodSearchCriteria searchInput,
			Map<String, Double> analysisData) throws JsonProcessingException, JsonMappingException {
		ResponseEntity<?> entity;
		Object resBody;
		entity = postFeignClient.searchFoodByIds(searchInput);

		if (entity.getStatusCode().is2xxSuccessful()) {
			resBody = entity.getBody();
			ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
			String json = ow.writeValueAsString(resBody);

			ObjectMapper objectMapper = new ObjectMapper();
			FoodFdcId[] responseEntity = objectMapper.readValue(json, FoodFdcId[].class);
			analysisData = Arrays.asList(responseEntity).stream().flatMap(s -> s.getFoodNutrients().stream())
					.collect(Collectors.toList()).stream()
					.collect(Collectors.groupingBy(AbridgedNutrient::nutrientKeyString,
							Collectors.summingDouble(AbridgedNutrient::getAmount)));
		}
		return analysisData;
	}

}
