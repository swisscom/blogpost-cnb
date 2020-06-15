package com.example.demo.adapters.filer;

import com.example.demo.entities.WeatherData;
import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Mono;

@Slf4j
public class LoggingFiler implements FilerAdapter {

    @Override
    public Mono<?> process(WeatherData weatherData) {
        return Mono.fromRunnable(() -> log.info("Filing weatherData for location={}, temperature={}", weatherData.getLocation(), weatherData.getCelsius()));
    }

}
