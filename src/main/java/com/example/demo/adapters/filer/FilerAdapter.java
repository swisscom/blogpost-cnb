package com.example.demo.adapters.filer;

import com.example.demo.entities.WeatherData;
import reactor.core.publisher.Mono;

public interface FilerAdapter {

    Mono<?> process(WeatherData weatherData);

}
