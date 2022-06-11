package com.app.nutritionistfavourites.controller;

import com.app.nutritionistfavourites.exceptions.FavouriteNotFoundException;
import com.app.nutritionistfavourites.exceptions.FavouritesAlreadyExistsException;
import com.app.nutritionistfavourites.model.NutritionistFavourite;
import com.app.nutritionistfavourites.service.FavouritesService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(MockitoExtension.class)
class FavouritesControllerTest {

    @Mock
    private FavouritesService favouritesService;

    @InjectMocks
    private FavouritesController favouritesController;

    @Autowired
    private MockMvc mockMvc;

    @BeforeEach
    public void setUp(){
        mockMvc = MockMvcBuilders.standaloneSetup(favouritesController).build();
    }
    @Test
    public void givenNewFavouriteWhenPostThenReturnFavouriteJSON() throws Exception, FavouritesAlreadyExistsException {
        NutritionistFavourite favourite = new NutritionistFavourite(100,10023,"This is a egg","20/04/2019","Britannia",8.34,"Nisha");
        when(favouritesService.addfavoritefood(any())).thenReturn(favourite);
        mockMvc.perform(post("/api/v1/addfavouritefood")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"id\":100,\"fdcId\":\"10023\",\"description\":\"This is a egg\",\"publishedDate\":\"20/04/2019\",\"brandOwner\":\"Britannia\",\"score\":\"8.34\",\"username\":\"Nisha\"}"))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").value(100));
    }

    @Test
    public void givenDuplicateFavouriteWhenPostThenReturnErrorMessage() throws Exception, FavouritesAlreadyExistsException {
        when(favouritesService.addfavoritefood(any())).thenThrow(FavouritesAlreadyExistsException.class);
        mockMvc.perform(post("/api/v1/addfavouritefood")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"id\":100,\"fdcId\":\"10023\",\"description\":\"This is a egg\",\"publishedDate\":\"20/04/2019\",\"brandOwner\":\"Britannia\",\"score\":\"8.34\",\"username\":\"Nisha\"}"))
                .andExpect(status().isConflict())
                .andExpect(content().string("This food is already added to favourite list"));
    }

//    @Test
//    public void givenValidFoodIDWhenGETThenReturnFavourite() throws Exception {
//       NutritionistFavourite favourite = new NutritionistFavourite(100,10023,"This is a egg","20/04/2019","Britannia",8.34,"Nisha");
//        when(favouritesService.deletefavoritefood(anyInt())).thenReturn(favourite);
//        mockMvc.perform(get("/api/v1/deletefavouritefood/10023"))
//                .andExpect(status().isOk())
//                .andExpect(jsonPath("$.fdcId").value(10023));
//    }

    @Test
    public void givenInValidUsernameWhenGETThenReturnErrorMessage() throws Exception {

        when(favouritesService.getfavouritebyusername(anyString())).thenThrow(FavouriteNotFoundException.class);
        String username = "Nisha";
        mockMvc.perform(get("/api/v1/getfavouritebyusername/Nisha"))
                .andExpect(status().isNotFound())
                .andExpect(content().string("User Notfound "+ username));
    }
}
