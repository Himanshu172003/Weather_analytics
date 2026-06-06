package com.example.weather_analytics.service;

import com.example.weather_analytics.model.WeatherData;
import com.example.weather_analytics.repository.WeatherRepository;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

@Service
public class WeatherService {

    private final WeatherRepository repo;
    private final RestTemplate rest = new RestTemplate();

    public WeatherService(WeatherRepository repo) {
        this.repo = repo;
    }

    // 🌦️ Fetch and save live data for a single city
    public WeatherData fetchAndSave(String city, double lat, double lon) {
        try {
            String url = "https://api.open-meteo.com/v1/forecast?latitude=" + lat +
                    "&longitude=" + lon + "&current_weather=true";

            Map response = rest.getForObject(url, Map.class);
            Map current = (Map) response.get("current_weather");

            WeatherData wd = new WeatherData();
            wd.setCity(city);
            wd.setTemperature(current.get("temperature") != null ? ((Number) current.get("temperature")).doubleValue() : null);
            wd.setWindSpeed(current.get("windspeed") != null ? ((Number) current.get("windspeed")).doubleValue() : null);
            wd.setDescription(current.get("weathercode") != null ? "code:" + current.get("weathercode") : "current");
            wd.setDateTime(LocalDateTime.now());

            return repo.save(wd);
        } catch (Exception e) {
            System.err.println("⚠️ Error fetching live data for: " + city + " → " + e.getMessage());
            return null;
        }
    }

    // 🧠 Fetch missing (historical) data if app opened after many days
    public void fetchMissingData(String city, double lat, double lon) {
        try {
            LocalDate lastSavedDate = repo.findLastDateForCity(city);
            if (lastSavedDate == null) {
                lastSavedDate = LocalDate.now().minusDays(3); // default: last 3 days if no data
            }

            LocalDate today = LocalDate.now();

            if (lastSavedDate.isBefore(today)) {
                System.out.println("📅 Fetching missing data from " + lastSavedDate + " to " + today + " for " + city);

                String url = String.format(
                        "https://archive-api.open-meteo.com/v1/archive?latitude=%f&longitude=%f&start_date=%s&end_date=%s&daily=temperature_2m_max,temperature_2m_min&timezone=auto",
                        lat, lon, lastSavedDate, today
                );

                Map response = rest.getForObject(url, Map.class);
                Map daily = (Map) response.get("daily");

                List<String> dates = (List<String>) daily.get("time");
                List<Double> temps = (List<Double>) daily.get("temperature_2m_max");

                for (int i = 0; i < dates.size(); i++) {
                    WeatherData wd = new WeatherData();
                    wd.setCity(city);
                    wd.setTemperature(temps.get(i));
                    wd.setDescription("Historical data");
                    wd.setDateTime(LocalDate.parse(dates.get(i)).atStartOfDay());
                    repo.save(wd);
                }

                System.out.println("✅ Missing data for " + city + " added successfully!");
            } else {
                System.out.println("✅ No missing data for " + city);
            }

        } catch (Exception e) {
            System.err.println("⚠️ Error fetching missing data for " + city + ": " + e.getMessage());
        }
    }

    // 🕐 Auto-fetch live data every 30 minutes for 25 major cities
    @Scheduled(fixedRate = 1800000)
    public void autoFetchWeather() {
        System.out.println("🌍 Auto fetching global weather data...");

        // 🌏 ASIA
        fetchAndSave("Delhi", 28.6139, 77.2090);
        fetchAndSave("Mumbai", 19.0760, 72.8777);
        fetchAndSave("Tokyo", 35.6762, 139.6503);
        fetchAndSave("Beijing", 39.9042, 116.4074);
        fetchAndSave("Singapore", 1.3521, 103.8198);
        fetchAndSave("Bangkok", 13.7563, 100.5018);

        // 🌍 EUROPE
        fetchAndSave("London", 51.5072, -0.1276);
        fetchAndSave("Paris", 48.8566, 2.3522);
        fetchAndSave("Berlin", 52.5200, 13.4050);
        fetchAndSave("Moscow", 55.7558, 37.6173);
        fetchAndSave("Rome", 41.9028, 12.4964);

        // 🌎 NORTH AMERICA
        fetchAndSave("New York", 40.7128, -74.0060);
        fetchAndSave("Los Angeles", 34.0522, -118.2437);
        fetchAndSave("Toronto", 43.65107, -79.347015);
        fetchAndSave("Mexico City", 19.4326, -99.1332);
        fetchAndSave("Chicago", 41.8781, -87.6298);

        // 🌍 SOUTH AMERICA
        fetchAndSave("São Paulo", -23.5505, -46.6333);
        fetchAndSave("Buenos Aires", -34.6037, -58.3816);
        fetchAndSave("Lima", -12.0464, -77.0428);

        // 🌍 AFRICA
        fetchAndSave("Cairo", 30.0444, 31.2357);
        fetchAndSave("Nairobi", -1.2921, 36.8219);
        fetchAndSave("Cape Town", -33.9249, 18.4241);

        // 🌏 OCEANIA
        fetchAndSave("Sydney", -33.8688, 151.2093);
        fetchAndSave("Melbourne", -37.8136, 144.9631);
        fetchAndSave("Auckland", -36.8485, 174.7633);

        System.out.println("✅ Weather data for 25 major cities updated successfully!");
    }

    // 📋 Fetch all data
    public List<WeatherData> fetchAll() {
        return repo.findAll();
    }

    // 🔍 Find data by city name
    public List<WeatherData> findByCity(String city) {
        return repo.findByCityOrderByDateTimeDesc(city);
    }
}
