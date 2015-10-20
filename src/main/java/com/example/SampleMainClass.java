package com.example;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.SecurityAutoConfiguration;
import org.springframework.boot.autoconfigure.thymeleaf.ThymeleafAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan("com.example")
@SpringBootApplication(exclude = {ThymeleafAutoConfiguration.class})
public class SampleMainClass {

    public static void main(String[] args) {
        SpringApplication.run(SampleMainClass.class, args);
    }
}
