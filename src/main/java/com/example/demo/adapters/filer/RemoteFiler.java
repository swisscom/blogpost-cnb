package com.example.demo.adapters.filer;

import com.example.demo.entities.WeatherData;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.env.Environment;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Slf4j
public class RemoteFiler implements FilerAdapter {

    private final WebClient webClient;

    public RemoteFiler(Environment environment, WebClient.Builder webclientBuilder) {
        String baseUrl = environment.getRequiredProperty("ENDPOINT");
        log.info("Set target to endpoint={}", baseUrl);
        this.webClient = webclientBuilder.baseUrl(baseUrl).build();
    }

    @Override
    public Mono<?> process(WeatherData weatherData) {
        return webClient.post()
                .uri("api/weather-data")
                .bodyValue(weatherData)
                .exchange()
                .onErrorMap(throwable -> {
                    log.error("Sending data to consumer threw an exception: ", throwable);
                    return throwable;
                })
                .map(response -> {
                    log.info("Sending data resulted in status: {} for location: {}", response.statusCode(), weatherData.getLocation());
                    return response;
                });
    }
}
