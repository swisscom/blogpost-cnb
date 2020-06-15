package com.example.demo.adapters.openweathermap;

import com.example.demo.entities.openweathermap.OpenWeatherMapResponse;
import reactor.core.publisher.Mono;

public interface WeatherDataAdapter {

    Mono<OpenWeatherMapResponse> getWeatherData(String searchQuery);

}
