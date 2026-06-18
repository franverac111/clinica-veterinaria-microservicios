package com.veterinaria.ms_ubicacion.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.veterinaria.ms_ubicacion.DTO.ComunaDTO;
import com.veterinaria.ms_ubicacion.model.Comuna;
import com.veterinaria.ms_ubicacion.model.Region;
import com.veterinaria.ms_ubicacion.repository.ComunaRepository;
import com.veterinaria.ms_ubicacion.repository.RegionRepository;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class ComunaService {
    @Autowired
    private ComunaRepository comunaRepository;

    @Autowired
    private RegionRepository regionRepository;

    public List<ComunaDTO> obtenerTodos() {

        return comunaRepository.findAll()
                .stream()
                .map(this::convertirADTO)
                .collect(Collectors.toList());
    }

    public ComunaDTO buscarPorId(Integer id) {
        Comuna comuna = comunaRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("comuna no encontrada"));

      return convertirADTO(comuna);
    }

    public ComunaDTO guardar(ComunaDTO dto) {
      String nombreComuna = dto.getNombreComuna().trim();
      if (comunaRepository.existsByNombreComunaIgnoreCaseAndRegion_Id(
            nombreComuna,
            dto.getRegionId())) {
        throw new RuntimeException("La comuna ya existe en esa región");
     }
      Comuna comuna = convertirAEntidad(dto);
      return convertirADTO(
            comunaRepository.save(comuna));
    }

    public Comuna actualizarComuna(Integer id, Comuna comuna) {
        Comuna com = comunaRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("la comuna no existe"));
        com.setNombreComuna(comuna.getNombreComuna());

      return comunaRepository.save(com);
    }

    public String eliminar(Integer id) {
        if(comunaRepository.existsById(id)) {
            comunaRepository.deleteById(id);
            return "Comuna eliminada exitosamente";
        }
        return "Comuna no encontrada";
    }

    private ComunaDTO convertirADTO(Comuna comuna) {
      ComunaDTO dto = new ComunaDTO();
      dto.setId(comuna.getId());
      dto.setNombreComuna(comuna.getNombreComuna());
     if (comuna.getRegion() != null) {
        dto.setRegionId(comuna.getRegion().getId());
        dto.setRegion(comuna.getRegion().getNombreRegion());
        }
      return dto;
    }

    private Comuna convertirAEntidad(ComunaDTO dto) {
        Region region = regionRepository.findById(dto.getRegionId())
        .orElseThrow(() -> new RuntimeException("Región no encontrada"));
      Comuna comuna = new Comuna();
      comuna.setId(dto.getId());
      comuna.setNombreComuna(dto.getNombreComuna());
      comuna.setRegion(region);
      return comuna;
    }
}
