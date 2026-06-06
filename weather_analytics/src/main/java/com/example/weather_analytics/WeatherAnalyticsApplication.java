package com.example.weather_analytics;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling  //✅ Enables automatic background weather fetching
public class WeatherAnalyticsApplication {

    public static void main(String[] args) {
        SpringApplication.run(WeatherAnalyticsApplication.class, args);
        System.out.println("🚀 Weather Analytics Application Started Successfully!");
    }
}
