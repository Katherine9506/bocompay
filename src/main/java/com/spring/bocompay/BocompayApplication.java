package com.spring.bocompay;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@SpringBootApplication
@EnableConfigurationProperties
public class BocompayApplication {

    public static void main(String[] args) {
        SpringApplication.run(BocompayApplication.class, args);
    }
}
