package com.webapi.knudon;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class KnudonApplication {

    public static void main(String[] args) {
        System.setProperty("debug", "true");
        SpringApplication.run(KnudonApplication.class, args);
    }
}
