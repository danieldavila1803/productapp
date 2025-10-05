package com.example.productapp.service;

import lombok.Data;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.Map;

@Service
public class ExternalExchangeService {
    private final WebClient client;

    public ExternalExchangeService(WebClient client) {
        this.client = client;
    }

    @Data
    public class ExchangeResponse {
        private Map<String, Double> rates;
    }

    public Mono<Double> getUsdToPen() {
        return client.get()
                .uri("/latest?base=USD&symbols=PEN")
                .retrieve()
                .bodyToMono(ExchangeResponse.class)
                .map(response -> {
                    if (response.getRates() != null && response.getRates().containsKey("PEN")) {
                        return response.getRates().get("PEN");
                    }
                    return 3.46;
                })
                .onErrorReturn(3.46);
    }
}