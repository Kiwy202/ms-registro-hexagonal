package com.codigo.ms_registro_hexagonal.domain.service;

import com.codigo.ms_registro_hexagonal.domain.dto.ReniecResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
@RequiredArgsConstructor
public class ReniecService {

    private final RestTemplate restTemplate;

    @Value("${reniec.url}")
    private String reniecApiUrl;

    @Value("${reniec.token}")
    private String token;

    public ReniecResponse buscarPorDni(String dni) {
        String url = reniecApiUrl + "?numero=" + dni;
        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", "Bearer " + token);

        HttpEntity<String> entity = new HttpEntity<>(headers);
        ResponseEntity<ReniecResponse> response = restTemplate.exchange(
                url, HttpMethod.GET, entity, ReniecResponse.class
        );

        return response.getBody();
    }
}