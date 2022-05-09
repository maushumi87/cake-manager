package com.waracle.cakemanager.service;

import com.waracle.cakemanager.entity.Cake;

import java.util.List;


public interface CakeService {

    public List<Cake> saveAll(List<Cake> cake);

    public List<Cake> findAll();
}
