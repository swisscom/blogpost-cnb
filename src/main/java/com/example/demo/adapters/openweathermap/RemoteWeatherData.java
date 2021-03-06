package com.example.demo.adapters.openweathermap;

import com.example.demo.config.ApiProperties;
import com.example.demo.entities.openweathermap.OpenWeatherMapResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.server.ResponseStatusException;
import reactor.core.publisher.Mono;

@Slf4j
public class RemoteWeatherData implements WeatherDataAdapter {

    private final ApiProperties apiProperties;
    private final WebClient webClient;

    public RemoteWeatherData(ApiProperties apiProperties, WebClient.Builder webclientBuilder) {
        this.apiProperties = apiProperties;
        this.webClient = webclientBuilder.baseUrl(apiProperties.getBaseUrl()).build();
    }

    public Mono<OpenWeatherMapResponse> getWeatherData(String searchQuery) {

        return webClient.get()
                .uri(uriBuilder -> uriBuilder
                        .queryParam("q", searchQuery)
                        .queryParam("APPID", apiProperties.getAppId())
                        .queryParam("units", "metric")
                        .build())
                .exchange()
                .onErrorMap(throwable -> {
                    log.error("Querying weather-data threw an exception: ", throwable);
                    return throwable;
                })
                .flatMap(response -> {
                    log.info("Receiving data resulted in status: {} for query: {}", response.statusCode(), searchQuery);
                    if (response.statusCode().is2xxSuccessful()) {
                        return response.bodyToMono(OpenWeatherMapResponse.class);
                    } else {
                        return Mono.error(new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "The API's response was not OK: " + response.statusCode()));
                    }
                });

    }

}
