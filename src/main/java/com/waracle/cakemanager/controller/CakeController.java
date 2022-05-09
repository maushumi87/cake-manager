package com.waracle.cakemanager.controller;

import com.waracle.cakemanager.entity.Cake;
import com.waracle.cakemanager.service.CakeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author Maushumi Baruah
 * This is a rest controller class used to get list of cakes and add new cakes.
 */
@RestController
@RequestMapping({"/", "/cakes"})
public class CakeController {

    @Autowired
    private CakeService cakeService;

    /**
     * This method fetches all cakes currently in system.
     *
     * @return list of all cakes.
     */
    @GetMapping
    public ResponseEntity<List<Cake>> getCakes() {
        return new ResponseEntity(cakeService.findAll(), HttpStatus.OK);

    }

    /**
     * This method saves tke list of cakes in the system.
     *
     * @param cake
     * @return list of cakes newly added.
     */
    @PostMapping
    public ResponseEntity<List<Cake>> addCakes(@RequestBody List<Cake> cake) {
        List<Cake> cakeList = cakeService.saveAll(cake);
        return new ResponseEntity(cakeList, HttpStatus.CREATED);
    }

}
