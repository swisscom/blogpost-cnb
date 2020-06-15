package com.example.demo.adapters.openweathermap;

import com.example.demo.entities.openweathermap.OpenWeatherMapResponse;
import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Mono;

@Slf4j
public class LoggingWeatherData implements WeatherDataAdapter {

    @Override
    public Mono<OpenWeatherMapResponse> getWeatherData(String searchQuery) {
        return Mono.fromRunnable(() -> log.warn("No API key specified in property 'vcap.services.openweathermap.credentials.appId', cannot query pre-configured API with searchQuery={}.", searchQuery));
    }

}
