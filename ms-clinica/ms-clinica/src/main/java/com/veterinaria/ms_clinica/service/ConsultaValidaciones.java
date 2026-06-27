package com.veterinaria.ms_clinica.service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import com.veterinaria.ms_clinica.DTO.ConsultaDTO;
import com.veterinaria.ms_clinica.DTO.MascotaExternoDTO;
import com.veterinaria.ms_clinica.model.Consulta;

@Service
public class ConsultaValidaciones {
     @Autowired
     private WebClient.Builder webClientBuilder;


    public ConsultaDTO convertirADTO(Consulta consulta) {
      ConsultaDTO dto = new ConsultaDTO();
      dto.setId(consulta.getId());
      dto.setFecha(consulta.getFecha());
      dto.setMotivo(consulta.getMotivo());
      dto.setDiagnostico(consulta.getDiagnostico());
      dto.setNomVet(consulta.getVeterinario().getNombreVeterinario());
      dto.setEspecialidad(consulta.getVeterinario().getEspecialidad());
        try {
            MascotaExternoDTO mascotaRecuperada = webClientBuilder.build()
                .get()
                .uri("http://localhost:8082/api/v1/mascotas/" + consulta.getMascotaId())
                .retrieve()
                .bodyToMono(MascotaExternoDTO.class)
                .block();

            dto.setMascota(mascotaRecuperada);
            
        } catch (Exception e) {
            dto.setMascota(null); 
        }
        return dto;
    }

    public boolean existeMascota(Integer mascotaId) {
    try {
        webClientBuilder.build()
                .get()
                .uri("http://localhost:8082/api/v1/mascotas/" + mascotaId)
                .retrieve()
                .bodyToMono(MascotaExternoDTO.class)
                .block();
        return true;
        } 
        catch (Exception e) {
            return false;
         }
    }


}
