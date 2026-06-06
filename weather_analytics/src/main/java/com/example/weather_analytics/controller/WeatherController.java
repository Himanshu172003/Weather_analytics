package com.example.weather_analytics.controller;

import com.example.weather_analytics.model.WeatherData;
import com.example.weather_analytics.service.WeatherService;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/weather")
public class WeatherController {
    private final WeatherService service;

    public WeatherController(WeatherService service) {
        this.service = service;
    }

    // ✅ Manual fetch for one city (example: Delhi)
    // URL: http://localhost:8080/api/weather/fetch?city=Delhi&lat=28.6139&lon=77.2090
    @GetMapping("/fetch")
    public WeatherData fetch(@RequestParam String city,
                             @RequestParam double lat,
                             @RequestParam double lon) {
        return service.fetchAndSave(city, lat, lon);
    }

    // ✅ Fetch all records
    @GetMapping("/all")
    public List<WeatherData> all() {
        return service.fetchAll();
    }

    // ✅ Fetch by city name
    @GetMapping("/city/{city}")
    public List<WeatherData> byCity(@PathVariable String city) {
        return service.findByCity(city);
    }
}
