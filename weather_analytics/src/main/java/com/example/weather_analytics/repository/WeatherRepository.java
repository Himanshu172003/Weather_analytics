package com.example.weather_analytics.repository;

import com.example.weather_analytics.model.WeatherData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface WeatherRepository extends JpaRepository<WeatherData, Long> {

    // 🔍 Find all data for a city (latest first)
    List<WeatherData> findByCityOrderByDateTimeDesc(String city);

    // 🧠 Find last saved date for a city
    @Query("SELECT MAX(DATE(w.dateTime)) FROM WeatherData w WHERE w.city = :city")
    LocalDate findLastDateForCity(String city);
}
