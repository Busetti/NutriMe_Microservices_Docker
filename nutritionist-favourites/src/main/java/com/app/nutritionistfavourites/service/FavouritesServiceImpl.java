package com.app.nutritionistfavourites.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.nutritionistfavourites.dao.FavouritesRepository;
import com.app.nutritionistfavourites.exceptions.FavouriteNotFoundException;
import com.app.nutritionistfavourites.exceptions.FavouritesAlreadyExistsException;
import com.app.nutritionistfavourites.exceptions.UserNotFoundException;
import com.app.nutritionistfavourites.model.NutritionistFavourite;

@Service
public class FavouritesServiceImpl implements FavouritesService {
	private FavouritesRepository favouritesRepository;

	@Autowired
	public FavouritesServiceImpl(FavouritesRepository favouritesRepository) {
		this.favouritesRepository = favouritesRepository;
	}

	@Override
	public NutritionistFavourite addfavoritefood(NutritionistFavourite favourite)
			throws FavouritesAlreadyExistsException {

		Optional<NutritionistFavourite> optionalNutritionistFavourite = favouritesRepository
				.findByFdcIdAndUsername(favourite.getFdcId(), favourite.getUsername());

		if (!optionalNutritionistFavourite.isPresent()) {
			return favouritesRepository.save(favourite);
		} else {
			throw new FavouritesAlreadyExistsException("This food is already added to favourite list");
		}
	}

	@Override
	public List<NutritionistFavourite> getfavouritebyusername(String username) throws UserNotFoundException {
		return favouritesRepository.findByUsername(username).orElseThrow(() -> new UserNotFoundException("User Notfound "+ username));
	}

	@Override
	public void deletefavoritefood(Integer Id) throws FavouriteNotFoundException {
		if (favouritesRepository.existsById(Id)) {
			favouritesRepository.deleteById(Id);
		} else {
			throw new FavouriteNotFoundException("Favourite Not found with the given ID");
		}
	}

}
