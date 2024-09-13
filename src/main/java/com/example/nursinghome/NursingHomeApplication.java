/* (C)2024 */
package com.example.nursinghome;

import org.springframework.boot.Banner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class NursingHomeApplication {

    public static void main(String[] args) {
        SpringApplication application = new SpringApplication(NursingHomeApplication.class);
        application.setBannerMode(Banner.Mode.OFF);
        application.run(args);
    }
}
