package com.veterinaria.ms_mascotas.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.veterinaria.ms_mascotas.DTO.RazaDTO;
import com.veterinaria.ms_mascotas.model.Raza;
import com.veterinaria.ms_mascotas.repository.RazaRepository;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class RazaService {
    @Autowired
    private RazaRepository razaRepository;

    private RazaDTO convertirADTO(Raza raza) {
        RazaDTO dto = new RazaDTO();
            dto.setId(raza.getId());
            dto.setNombre(raza.getNombreRaza());
            if (raza.getMascotas() != null) {
            dto.setMascotas(raza.getMascotas()
                        .stream()
                        .map(m -> m.getNombre())
                        .collect(Collectors.toList()));
        }
        return dto;
    }

    public List<RazaDTO> obtenerTodos() {
        return razaRepository.findAll()
                .stream()
                .map(this::convertirADTO)
                .collect(Collectors.toList());
    }

    public RazaDTO buscarPorId(Integer id) {
        Raza raza = razaRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("raza no encontrado"));

    return convertirADTO(raza);
    }

    public Raza guardarRaza(Raza raza) {
    return razaRepository.save(raza);
    }

    public Raza actualizarRaza(Integer id, Raza raza) {
        Raza r = razaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("raza no encontrado"));
        r.setNombreRaza(raza.getNombreRaza());
        return razaRepository.save(r);
    }

    public String eliminar(Integer id) {
        if (razaRepository.existsById(id)) {
            razaRepository.deleteById(id);
            return "raza eliminada exitosamente";
        }
        return "raza no encontrado";
    }


}
