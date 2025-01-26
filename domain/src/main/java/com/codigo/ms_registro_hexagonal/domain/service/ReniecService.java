package com.codigo.ms_registro_hexagonal.domain.service;

import com.codigo.ms_registro_hexagonal.domain.dto.ReniecResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class ReniecService {

    private final RestTemplate restTemplate;
    private final String reniecUrl;
    private final String token;

    public ReniecService(RestTemplate restTemplate,
                         @Value("${reniec.url}") String reniecUrl,
                         @Value("${reniec.token}") String token) {
        this.restTemplate = restTemplate;
        this.reniecUrl = reniecUrl;
        this.token = token;
    }

    public ReniecResponse buscarPorDni(String numeroDni) {
        // Construir la URL
        String url = reniecUrl + "?numero=" + numeroDni;

        // Configurar los headers
        org.springframework.http.HttpHeaders headers = new org.springframework.http.HttpHeaders();
        headers.set("Authorization", token);

        org.springframework.http.HttpEntity<String> entity = new org.springframework.http.HttpEntity<>(headers);

        // Realizar la llamada al API
        return restTemplate.exchange(
                url,
                org.springframework.http.HttpMethod.GET,
                entity,
                ReniecResponse.class
        ).getBody();
    }
}