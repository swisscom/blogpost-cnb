package com.example.demo.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Data
@Component
@ConfigurationProperties("proxy")
public class ProxyProperties {

    private String host;
    private Integer port = 8080;

}
