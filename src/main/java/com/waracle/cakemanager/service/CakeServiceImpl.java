package com.waracle.cakemanager.service;

import com.waracle.cakemanager.entity.Cake;
import com.waracle.cakemanager.exception.CustomCakeException;
import com.waracle.cakemanager.repository.CakeRepository;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Log4j2
public class CakeServiceImpl implements CakeService {

    @Autowired
    private CakeRepository cakeRepository;

    /**
     * This method is used to insert new cake details into database.
     *
     * @param cake
     * @return list of newly inserted cakes.
     */
    @Override
    public List<Cake> saveAll(List<Cake> cake) {
        if (cake.size() == 0) {
            throw new CustomCakeException("Cake List is empty");
        }
        List<Cake> cakeList = cakeRepository.saveAll(cake);
        log.info("Successfully saved cake details into database");
        return cakeList;

    }

    /**
     * This method is used to fetch the list of cakes from database.
     *
     * @return list of cakes currently in system.
     */
    @Override
    public List<Cake> findAll() {
        return cakeRepository.findAll();
    }

}
