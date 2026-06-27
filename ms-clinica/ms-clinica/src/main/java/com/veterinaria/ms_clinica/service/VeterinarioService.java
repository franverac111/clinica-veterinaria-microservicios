package com.veterinaria.ms_clinica.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.veterinaria.ms_clinica.DTO.VeterinarioDTO;
import com.veterinaria.ms_clinica.model.Consulta;
import com.veterinaria.ms_clinica.model.Veterinario;
import com.veterinaria.ms_clinica.repository.VeterinarioRepository;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class VeterinarioService {
    @Autowired
    private VeterinarioRepository veterinarioRepository;
    
    @Autowired
    private VeterinarioValidaciones veterinarioValidaciones;


    public List<VeterinarioDTO> obtenerTodos() {
       return veterinarioRepository.findAll()
                .stream()
                .map(veterinarioValidaciones::convertirADTO)
                .toList();
    }

    public VeterinarioDTO buscarPorId(Integer id) {
       Veterinario veterinario = veterinarioRepository.findById(id)
          .orElseThrow(() -> new RuntimeException("veterinario no encontrado"));
       return veterinarioValidaciones.convertirADTO(veterinario);
    }

    
    public String eliminar(Integer id) {
     try {
           Veterinario veterinario = veterinarioRepository.findById(id)
                   .orElseThrow(() -> new RuntimeException("¡Imposible eliminar! el veterinario con ID " + id + " no existe."));
           veterinarioRepository.delete(veterinario);
           return "el veterinario '" + veterinario.getNombreVeterinario() + "' ha sido retirada exitosamente.";
       } catch (RuntimeException e) {
           return e.getMessage();
       }
    }

    public VeterinarioDTO guardarVeterinario(Veterinario veterinario) {
       Veterinario guardado = veterinarioRepository.save(veterinario);
        return veterinarioValidaciones.convertirADTO(guardado);
    }

    public Veterinario actualizarVeterinario(Integer id,Veterinario vet){
       Veterinario veterinario = veterinarioRepository.findById(id).orElseThrow(() -> new RuntimeException("el veterinario no existe"));
       if(vet.getNombreVeterinario() != null){
           veterinario.setNombreVeterinario(vet.getNombreVeterinario());
       }
       if(vet.getTelefono() != null){
           veterinario.setTelefono(vet.getTelefono());
       }
       if(vet.getEspecialidad() != null){
           veterinario.setEspecialidad(vet.getEspecialidad());
       }
       if(vet.getClinica() != null){
          veterinario.setClinica(vet.getClinica());
       }
       return veterinarioRepository.save(veterinario);
    }
    
    //metodo para buscar por especialidad
    public List<VeterinarioDTO> buscarPorEspecialidad(String especialidad) {
            List<Veterinario> veterinarios = veterinarioRepository.findByEspecialidad(especialidad);
            List<VeterinarioDTO> listaDTO = new ArrayList<>();
         for (Veterinario veterinario : veterinarios) {
                listaDTO.add(veterinarioValidaciones.convertirADTO(veterinario));
            }
        return listaDTO;
    }


}
