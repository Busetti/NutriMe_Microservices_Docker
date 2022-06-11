package com.app.nutritionistfavourites.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.app.nutritionistfavourites.model.NutritionistFavourite;

@Repository
public interface FavouritesRepository extends JpaRepository<NutritionistFavourite, Integer>{

	Optional<NutritionistFavourite> findByFdcIdAndUsername(int fdcId, String username);

	Optional<List<NutritionistFavourite>> findByUsername(String username);
}
