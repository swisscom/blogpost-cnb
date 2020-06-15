package com.example.demo.config;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.boot.web.reactive.function.client.WebClientCustomizer;
import org.springframework.http.client.reactive.ReactorClientHttpConnector;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.netty.http.client.HttpClient;
import reactor.netty.tcp.ProxyProvider;

@Slf4j
@Component
@RequiredArgsConstructor
public class WebClientProxyConfig implements WebClientCustomizer {

    private final ProxyProperties proxyProperties;

    @Override
    public void customize(WebClient.Builder webClientBuilder) {
        if (StringUtils.isNotBlank(proxyProperties.getHost())) {
            log.info("Proxy settings detected, using host={}, port={}", proxyProperties.getHost(), proxyProperties.getPort());
            HttpClient httpClient = HttpClient.create()
                    .tcpConfiguration(tcpClient -> tcpClient
                            .proxy(proxy -> proxy
                                    .type(ProxyProvider.Proxy.HTTP)
                                    .host(proxyProperties.getHost())
                                    .port(proxyProperties.getPort())));

            webClientBuilder.clientConnector(new ReactorClientHttpConnector(httpClient));
        }
    }
}
