package com.veterinaria.ms_clinica.service;
import org.springframework.stereotype.Service;
import com.veterinaria.ms_clinica.DTO.VeterinarioDTO;
import com.veterinaria.ms_clinica.model.Veterinario;

@Service
public class VeterinarioValidaciones {

    public VeterinarioDTO convertirADTO(Veterinario veterinario) {
       VeterinarioDTO dto = new VeterinarioDTO();
       dto.setId(veterinario.getId());
       dto.setNombre(veterinario.getNombreVeterinario());
       dto.setTelefono(veterinario.getTelefono());
       dto.setEspecialidad(veterinario.getEspecialidad());

       if (veterinario.getConsultas() != null) {//da el total de consultas si esque hay 
        dto.setTotalConsultas(veterinario.getConsultas().size()) ;
       }
       else {
          dto.setTotalConsultas(0);//si no hay consultas muestra 0
            }
              return dto;
    }

}
