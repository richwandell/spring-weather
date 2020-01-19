package com.wandell.springweather;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Scope;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
@RestController
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @Bean
    @Scope("singleton")
    public RestTemplate getRestTemplate() {
        return new RestTemplate();
    }

    @Bean
    @Scope("singleton")
    public ObjectMapper getObjectMapper() {
        return new ObjectMapper();
    }
}