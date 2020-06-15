package com.example.demo.config;

import com.example.demo.adapters.filer.FilerAdapter;
import com.example.demo.adapters.filer.LoggingFiler;
import com.example.demo.adapters.filer.RemoteFiler;
import com.example.demo.adapters.openweathermap.LoggingWeatherData;
import com.example.demo.adapters.openweathermap.RemoteWeatherData;
import com.example.demo.adapters.openweathermap.WeatherDataAdapter;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
public class BeanConfiguration {

    @Bean
    @ConditionalOnProperty(name = "ENDPOINT")
    FilerAdapter remoteFiler(Environment environment, WebClient.Builder webclientBuilder) {
        return new RemoteFiler(environment, webclientBuilder);
    }

    @Bean
    @ConditionalOnMissingBean
    FilerAdapter loggingFiler() {
        return new LoggingFiler();
    }

    @Bean
    @ConditionalOnProperty(name = "vcap.services.openweathermap.credentials.appId")
    WeatherDataAdapter remoteWeatherData(ApiProperties apiProperties, WebClient.Builder webclientBuilder) {
        return new RemoteWeatherData(apiProperties, webclientBuilder);
    }

    @Bean
    @ConditionalOnMissingBean
    WeatherDataAdapter loggingWeatherData() {
        return new LoggingWeatherData();
    }

}
