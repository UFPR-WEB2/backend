package com.grupo2.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories(basePackages = {"com.grupo2.demo.repository"})
public class PiazadaApplication {

    public static void main(String[] args) {
        SpringApplication.run(PiazadaApplication.class, args);
    }

}
