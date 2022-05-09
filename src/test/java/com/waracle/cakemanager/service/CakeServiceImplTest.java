package com.waracle.cakemanager.service;

import com.waracle.cakemanager.entity.Cake;
import com.waracle.cakemanager.exception.CustomCakeException;
import com.waracle.cakemanager.repository.CakeRepository;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.ContextConfiguration;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyList;
import static org.mockito.Mockito.when;

@AutoConfigureMockMvc
@ContextConfiguration(classes = {CakeRepository.class})
@WebMvcTest

public class CakeServiceImplTest {

    @Mock
    private CakeRepository cakeRepository;

    @InjectMocks
    private CakeServiceImpl cakeServiceImpl;

    @Test
    public void findAllNull() {
        when(cakeRepository.findAll()).thenReturn(null);
        List<Cake> cakeList = cakeServiceImpl.findAll();
        assertNull(cakeList);
    }

    @Test
    public void findAllEmptyList() {
        when(cakeRepository.findAll()).thenReturn(new ArrayList<Cake>());
        List<Cake> cakeList = cakeServiceImpl.findAll();
        assertEquals(0, cakeList.size());

    }

    @Test
    public void findAllTableMissing() {
        when(cakeRepository.findAll()).thenThrow(new RuntimeException());
        assertThrows(RuntimeException.class, () -> cakeServiceImpl.findAll());
    }

    @Test
    public void findAllList() {
        List<Cake> cakeListDummy = new ArrayList<>();
        Cake cake1 = new Cake(1, "Lemon cheesecake", "A cheesecake made of lemon", "https://s3-eu-west-1.amazonaws.com/s3.mediafileserver.co.uk/carnation/WebFiles/RecipeImages/lemoncheesecake_lg.jpg");
        Cake cake2 = new Cake(2, "victoria sponge", "sponge with jam", "http://www.bbcgoodfood.com/sites/bbcgoodfood.com/files/recipe_images/recipe-image-legacy-id--1001468_10.jpg");
        cakeListDummy.add(cake1);
        cakeListDummy.add(cake2);

        when(cakeRepository.findAll()).thenReturn(cakeListDummy);
        List<Cake> cakeList = cakeServiceImpl.findAll();
        assertEquals(2, cakeList.size());
    }

    @Test
    public void saveAllSuccess() {
        List<Cake> cakeListDummy = new ArrayList<>();
        Cake cake1 = new Cake(1, "Lemon cheesecake", "A cheesecake made of lemon", "https://s3-eu-west-1.amazonaws.com/s3.mediafileserver.co.uk/carnation/WebFiles/RecipeImages/lemoncheesecake_lg.jpg");
        cakeListDummy.add(cake1);

        when(cakeRepository.saveAll(anyList())).thenReturn(cakeListDummy);
        List<Cake> cakeList = cakeServiceImpl.saveAll(cakeListDummy);
        assertEquals(cakeListDummy.get(0).getCakeId(), cakeList.get(0).getCakeId());

    }

    @Test
    public void saveAllEmptyList() {
        List<Cake> cakeListDummy = new ArrayList<>();
        assertThrows(CustomCakeException.class, () -> cakeServiceImpl.saveAll(cakeListDummy));
    }


}
