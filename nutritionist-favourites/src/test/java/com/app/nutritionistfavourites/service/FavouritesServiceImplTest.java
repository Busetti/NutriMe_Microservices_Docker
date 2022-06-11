package com.app.nutritionistfavourites.service;

import com.app.nutritionistfavourites.dao.FavouritesRepository;
import com.app.nutritionistfavourites.exceptions.FavouriteNotFoundException;
import com.app.nutritionistfavourites.exceptions.FavouritesAlreadyExistsException;
import com.app.nutritionistfavourites.model.NutritionistFavourite;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class FavouritesServiceImplTest {
    @Mock
    FavouritesRepository favouritesRepository;

    @InjectMocks
    FavouritesServiceImpl favouritesService;

    @Test
    public void givenValidIDThenShouldReturnEmployee() throws FavouriteNotFoundException {
        NutritionistFavourite favourite = new NutritionistFavourite(100,10023,"This is a egg","20/04/2019","Britannia",8.34,"Nisha");
        Optional<NutritionistFavourite> optionalNutritionistFavourite = Optional.of(favourite);
        when(favouritesRepository.findByFdcIdAndUsername(10023,"Nisha")).thenReturn(optionalNutritionistFavourite);
        NutritionistFavourite retreivedUsername = (NutritionistFavourite) favouritesService.getfavouritebyusername("Nisha");
        assertEquals(favourite.getId(),retreivedUsername.getId(),"should return favourite for valid id of existing username");
    }

    @Test
    public void givenInValidIDThenShouldThrowException() throws FavouriteNotFoundException {
        Optional<NutritionistFavourite> optionalNutritionistFavourite = Optional.empty();
        when(favouritesRepository.findByFdcIdAndUsername(10023,"Nisha")).thenReturn(optionalNutritionistFavourite);
        assertThrows(FavouriteNotFoundException.class,()->{favouritesService.getfavouritebyusername("Nisha");});
    }

    @Test
    public void givenNewFavouriteWhenSavedShouldReturnEmployee() throws FavouritesAlreadyExistsException {
        NutritionistFavourite favourite = new NutritionistFavourite(100,10023,"This is a egg","20/04/2019","Britannia",8.34,"Nisha");
        Optional<NutritionistFavourite> optionalNutritionistFavourite = Optional.empty();
        when(favouritesRepository.findById(100)).thenReturn(optionalNutritionistFavourite);
        NutritionistFavourite addedEmployee = favouritesService.addfavoritefood(favourite);
        verify(favouritesRepository,times(1)).findById(100);
        verify(favouritesRepository,times(1)).save(favourite);
    }

    @Test
    public void givenDuplicateFavouriteWhenSavedShouldThrowException() throws FavouritesAlreadyExistsException {
        NutritionistFavourite favourite = new NutritionistFavourite(100,10023,"This is a egg","20/04/2019","Britannia",8.34,"Nisha");
        Optional<NutritionistFavourite> optionalNutritionistFavourite = Optional.of(favourite);
        when(favouritesRepository.findById(100)).thenReturn(optionalNutritionistFavourite);
        assertThrows(FavouritesAlreadyExistsException.class,()->{favouritesService.addfavoritefood(favourite);});
        verify(favouritesRepository,times(1)).findById(100);
        verify(favouritesRepository,times(0)).save(favourite);
    }
}
