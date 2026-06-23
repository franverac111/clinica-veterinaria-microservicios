package com.veterinaria.ms_mascotas.service;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.veterinaria.ms_mascotas.DTO.DuenoDTO;
import com.veterinaria.ms_mascotas.DTO.EspecieDTO;
import com.veterinaria.ms_mascotas.model.Dueno;
import com.veterinaria.ms_mascotas.model.Especie;
import com.veterinaria.ms_mascotas.repository.EspecieRepository;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class EspecieService {
    @Autowired 
    private EspecieRepository especieRepository;

    private EspecieDTO convertirADTO(Especie especie) {
        EspecieDTO dto = new EspecieDTO();
            dto.setId(especie.getId());
            dto.setNombre(especie.getNombreEspecie());
            if (especie.getMascotas() != null) {
            dto.setMascotas(especie.getMascotas()
                        .stream()
                        .map(m -> m.getNombre())
                        .collect(Collectors.toList()));
        }
        return dto;
    }

    private Especie convertirAEntidad(EspecieDTO dto) {
        Especie especie = new Especie();
        especie.setId(dto.getId());
        especie.setNombreEspecie(dto.getNombre());
        return especie;
    }

    public List<EspecieDTO> obtenerTodos() {
        return especieRepository.findAll()
                .stream()
                .map(this::convertirADTO)
                .collect(Collectors.toList());
    }

    public EspecieDTO buscarPorId(Integer id) {
        Especie especie = especieRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("especie no encontrada"));

    return convertirADTO(especie);
    }

    public Especie guardar(Especie especie) {
    return especieRepository.save(especie);
    }

    public Especie actualizarEspecie(Integer id, Especie especie) {
        Especie e = especieRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Especie no encontrado"));
        e.setNombreEspecie(especie.getNombreEspecie());
        return especieRepository.save(e);
    }

    public String eliminar(Integer id) {
        if (especieRepository.existsById(id)) {
            especieRepository.deleteById(id);
            return "Especie eliminado exitosamente";
        }
        return "Especie no encontrado";
    }

    public EspecieDTO buscarPorNombre(String nombre) {
    Especie especie = especieRepository.findByNombreEspecie(nombre)
            .orElseThrow(() -> new RuntimeException("especie no encontrado"));
    return convertirADTO(especie);
    }


}
