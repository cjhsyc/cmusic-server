package com.example;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class CmusicServerApplication {

    public static void main(String[] args) {
        SpringApplication.run(CmusicServerApplication.class, args);
        System.out.println("http://localhost:8888");
    }

}
