package com.whosricardo.gymbookingapi;

import com.whosricardo.gymbookingapi.config.security.JwtProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@SpringBootApplication
@EnableConfigurationProperties(JwtProperties.class)
public class GymBookingApiApplication {
    public static void main(String[] args) {
        SpringApplication.run(GymBookingApiApplication.class, args);
    }
}
