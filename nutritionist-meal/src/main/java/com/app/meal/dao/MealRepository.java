package com.app.meal.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.app.meal.model.NutritionistMeal;

@Repository
public interface MealRepository extends JpaRepository<NutritionistMeal, Integer>{

	Optional<NutritionistMeal> findByFdcIdAndUsername(int fdcId, String username);

	Optional<List<NutritionistMeal>> findByUsername(String username);
}
