package com.veterinaria.ms_mascotas.client;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import com.veterinaria.ms_mascotas.DTO.RegionDTO;

@Service
public class UbicacionClient {

    private final WebClient webClient;

    public UbicacionClient(WebClient.Builder builder) {
        this.webClient = builder.baseUrl("http://ms-ubicacion").build();
    }

    public List<RegionDTO> obtenerRegiones() {
        return webClient.get()
                .uri("/api/v1/regiones")
                .retrieve()
                .bodyToFlux(RegionDTO.class)
                .collectList()
                .block();
    }
}