package com.veterinaria.ms_mascotas.service;

import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.veterinaria.ms_mascotas.DTO.MascotaDTO;
import com.veterinaria.ms_mascotas.model.Mascota;
import com.veterinaria.ms_mascotas.repository.MascotaRepository;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class MascotaService {
    @Autowired
    private MascotaRepository mascotaRepository;

    private MascotaDTO convertirADTO(Mascota mascota) {
       MascotaDTO dto = new MascotaDTO();
            dto.setId(mascota.getId());
            dto.setNombre(mascota.getNombre());
            dto.setEdad(mascota.getEdad());
            dto.setSexo(mascota.getSexo());
            dto.setEspecie(mascota.getEspecie().getNombreEspecie());
            dto.setRaza(mascota.getRaza().getNombreRaza());
            dto.setDueno(mascota.getDueno().getNombre());
        return dto;
    }

    public List<MascotaDTO> obtenerTodos() {
        return mascotaRepository.findAll()
                .stream()
                .map(this::convertirADTO)
                .collect(Collectors.toList());
    }

    
    public MascotaDTO buscarPorId(Integer id) {
        Mascota mascota = mascotaRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("mascota no encontrada"));
    return convertirADTO(mascota);
    }


    public Mascota guardarMascota(Mascota mascota) {
    return mascotaRepository.save(mascota);
    }

    
    public Mascota actualizarMascota(Integer id, Mascota mascota) {
        Mascota m = mascotaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("mascota no encontrado"));
        m.setNombre(mascota.getNombre());
        m.setEdad(mascota.getEdad());
        m.setSexo(mascota.getSexo());
        return mascotaRepository.save(m);
    }

    public String eliminar(Integer id) {
        if (mascotaRepository.existsById(id)) {
            mascotaRepository.deleteById(id);
            return "Mascota ah sido dada de alta exitosamente";
        }
        return "Mascota no encontrado";
    }



}
