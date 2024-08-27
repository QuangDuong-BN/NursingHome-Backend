package com.example.nursinghome;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class NursingHomeApplication {

    public static void main(String[] args) {
        SpringApplication.run(NursingHomeApplication.class, args);
    }
}
