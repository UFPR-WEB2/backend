package com.grupo2.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = {"com.grupo2.demo", "com.drupo2.demo.controller"})	
public class PiazadaApplication {

    public static void main(String[] args) {
        SpringApplication.run(PiazadaApplication.class, args);
    }

}
