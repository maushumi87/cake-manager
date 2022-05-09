package com.waracle.cakemanager.controller;

import com.waracle.cakemanager.entity.Cake;
import com.waracle.cakemanager.service.CakeService;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ContextConfiguration;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyList;
import static org.mockito.Mockito.when;


@AutoConfigureMockMvc
@ContextConfiguration(classes = {CakeService.class})
@WebMvcTest
public class CakeControllerTest {

    @Mock
    private CakeService cakeService;

    @InjectMocks
    private CakeController cakeController;


    @Test
    public void getCakesEmptyList() {
        when(cakeService.findAll()).thenReturn(new ArrayList<Cake>());
        ResponseEntity<List<Cake>> responseEntity = cakeController.getCakes();
        assertEquals(200, responseEntity.getStatusCodeValue());
        assertEquals(0, responseEntity.getBody().size());

    }

    @Test
    public void getCakesList() {
        List<Cake> cakeListDummy = new ArrayList<>();
        Cake cake1 = new Cake(1, "Lemon cheesecake", "A cheesecake made of lemon", "https://s3-eu-west-1.amazonaws.com/s3.mediafileserver.co.uk/carnation/WebFiles/RecipeImages/lemoncheesecake_lg.jpg");
        Cake cake2 = new Cake(2, "victoria sponge", "sponge with jam", "http://www.bbcgoodfood.com/sites/bbcgoodfood.com/files/recipe_images/recipe-image-legacy-id--1001468_10.jpg");
        cakeListDummy.add(cake1);
        cakeListDummy.add(cake2);

        when(cakeService.findAll()).thenReturn(cakeListDummy);
        ResponseEntity<List<Cake>> responseEntity = cakeController.getCakes();
        assertEquals(200, responseEntity.getStatusCodeValue());
        assertEquals(2, responseEntity.getBody().size());

    }

    @Test
    public void addCakesSuccess() {
        List<Cake> cakeListDummy = new ArrayList<>();
        Cake cake1 = new Cake(1, "Lemon cheesecake", "A cheesecake made of lemon", "https://s3-eu-west-1.amazonaws.com/s3.mediafileserver.co.uk/carnation/WebFiles/RecipeImages/lemoncheesecake_lg.jpg");
        cakeListDummy.add(cake1);

        when(cakeService.saveAll(anyList())).thenReturn(cakeListDummy);
        ResponseEntity<List<Cake>> responseEntity = cakeController.addCakes(cakeListDummy);
        assertEquals(201, responseEntity.getStatusCodeValue());
    }


}
