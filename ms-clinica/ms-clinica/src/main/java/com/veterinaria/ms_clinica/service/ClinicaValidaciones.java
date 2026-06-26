package com.veterinaria.ms_clinica.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import com.veterinaria.ms_clinica.DTO.ClinicaDTO;
import com.veterinaria.ms_clinica.DTO.ComunaExternoDTO;
import com.veterinaria.ms_clinica.model.Clinica;

@Service
public class ClinicaValidaciones {
    @Autowired
    private WebClient.Builder webClientBuilder;

    
    public ClinicaDTO convertirADTO(Clinica clinica) {
        ClinicaDTO dto = new ClinicaDTO();
        dto.setId(clinica.getId());
        dto.setNombreClinica(clinica.getNombreClinica());
        dto.setDireccion(clinica.getDireccion());
        dto.setTelefono(clinica.getTelefono());

        try {
            ComunaExternoDTO comunaRecuperada = webClientBuilder.build()
                .get()
                .uri("http://localhost:8081/api/v1/comunas/" + clinica.getComunaId())
                .retrieve()
                .bodyToMono(ComunaExternoDTO.class)
                .block();

            dto.setComuna(comunaRecuperada);
            
        } catch (Exception e) {
            dto.setComuna(null); 
        }
        return dto;
    }

    public boolean existeComuna(Integer comunaId) {
    try {
        webClientBuilder.build()
                .get()
                .uri("http://localhost:8081/api/v1/comunas/" + comunaId)
                .retrieve()
                .bodyToMono(ComunaExternoDTO.class)
                .block();
        return true;
        } 
        catch (Exception e) {
            return false;
         }
    }




}
