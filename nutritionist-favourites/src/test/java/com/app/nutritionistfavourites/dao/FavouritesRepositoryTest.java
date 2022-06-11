package com.app.nutritionistfavourites.dao;

import com.app.nutritionistfavourites.model.NutritionistFavourite;
//import com.app.nutritionistfavourites.dao.FavouritesRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@DataJpaTest
class FavouritesRepositoryTest {

    @Autowired
    FavouritesRepository favouritesRepository;


    @Test
    public void givenNewEmployeeWhenSavedThenReturnEmployee(){
        NutritionistFavourite favourite = new NutritionistFavourite(100,10023,"This is a egg","20/04/2019","Britannia",8.34,"Nisha");
        NutritionistFavourite adddedEmployee = favouritesRepository.save(favourite);
        assertEquals(favourite.getId(),adddedEmployee.getId(), "New Favourite should be saved and the same should be returned");
    }

    @Test
    public void givenInValidUsernameThenReturnEmptyOptional(){
        assertTrue(!favouritesRepository.findByUsername("Nisha").isPresent());
    }

    @Test
    public void givenValidIdThenReturnEmployeeOptional(){
        NutritionistFavourite favourite = new NutritionistFavourite(100,10023,"This is a egg","20/04/2019","Britannia",8.34,"Nisha");
        favouritesRepository.save(favourite);
        assertTrue(favouritesRepository.findById(100).isPresent());

    }
}
