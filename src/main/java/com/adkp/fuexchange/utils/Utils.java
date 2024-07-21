package com.adkp.fuexchange.utils;

import org.springframework.http.*;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.concurrent.CompletableFuture;

@Component
public class Utils {

    private final RestTemplate restTemplate;

    public Utils(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @Async
    public void navigationDataAsyncForAnotherMethod(String apiUrl, Object data, HttpMethod httpMethod) {
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(List.of(MediaType.APPLICATION_JSON));
        HttpEntity<Object> dataSend = new HttpEntity<>(data, headers);

        String response = restTemplate
                .exchange(
                        apiUrl,
                        httpMethod,
                        dataSend,
                        String.class)
                .getBody();

        CompletableFuture.completedFuture(response);
    }
}
