package com.waracle.cakemanager;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.waracle.cakemanager.entity.Cake;
import com.waracle.cakemanager.service.CakeService;
import lombok.extern.log4j.Log4j2;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.filter.CommonsRequestLoggingFilter;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.List;

@SpringBootApplication
@Log4j2
public class CakeManagerApplication {

    public static void main(String[] args) {
        SpringApplication.run(CakeManagerApplication.class, args);
    }

    /**
     *  read json file and write to db
     * @param cakeService
     * @return
     */
    @Bean
    CommandLineRunner runner(CakeService cakeService) {
        return args -> {
            ObjectMapper mapper = new ObjectMapper();
            TypeReference<List<Cake>> typeReference = new TypeReference<List<Cake>>() {
            };
            URL url = new URL("https://gist.githubusercontent.com/hart88/198f29ec5114a3ec3460/raw/8dd19a88f9b8d24c23d9960f3300d0c917a4f07c/cake.json");
            InputStream inputStream = url.openStream();
            try {
                List<Cake> cake = mapper.readValue(inputStream, typeReference);
                cakeService.saveAll(cake);
            } catch (IOException e) {
                log.info("Unable to save users: " + e.getMessage());
            }
        };
    }

    @Bean
    public CommonsRequestLoggingFilter requestLoggingFilter() {
        CommonsRequestLoggingFilter loggingFilter = new CommonsRequestLoggingFilter();
        loggingFilter.setIncludeClientInfo(true);
        loggingFilter.setIncludeQueryString(true);
        return loggingFilter;
    }


}