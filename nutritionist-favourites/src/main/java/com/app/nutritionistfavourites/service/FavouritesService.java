package com.app.nutritionistfavourites.service;

import java.util.List;

import com.app.nutritionistfavourites.exceptions.FavouriteNotFoundException;
import com.app.nutritionistfavourites.exceptions.FavouritesAlreadyExistsException;
import com.app.nutritionistfavourites.exceptions.UserNotFoundException;
import com.app.nutritionistfavourites.model.NutritionistFavourite;

public interface FavouritesService {
    public NutritionistFavourite addfavoritefood(NutritionistFavourite favourite) throws FavouritesAlreadyExistsException;

    public List<NutritionistFavourite> getfavouritebyusername(String username) throws UserNotFoundException;
    void deletefavoritefood(Integer Id) throws FavouriteNotFoundException;
}
