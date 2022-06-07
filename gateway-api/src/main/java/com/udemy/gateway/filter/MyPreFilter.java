package com.udemy.gateway.filter;

import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

@Component
@Slf4j
public class MyPreFilter implements GlobalFilter {

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        log.info("My first pre filter is executed...");
        String requestPath = exchange.getRequest().getPath().toString();
        log.info("Request path is " + requestPath);
        log.info("Headers");
        HttpHeaders headers = exchange.getRequest().getHeaders();
        headers.forEach((key, value) -> log.info(key + ": " + key));
        return chain.filter(exchange);
    }
}
