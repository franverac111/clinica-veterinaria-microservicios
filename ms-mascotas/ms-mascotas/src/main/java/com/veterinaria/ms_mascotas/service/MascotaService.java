package com.veterinaria.ms_mascotas.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.veterinaria.ms_mascotas.DTO.MascotaDTO;
import com.veterinaria.ms_mascotas.client.UbicacionClient;
import com.veterinaria.ms_mascotas.exception.MascotaNoEncontradaException;
import com.veterinaria.ms_mascotas.model.Mascota;
import com.veterinaria.ms_mascotas.repository.MascotaRepository;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class MascotaService {

    private static final Logger logger = LoggerFactory.getLogger(MascotaService.class);

    @Autowired
    private MascotaRepository mascotaRepository;

    @Autowired
    private UbicacionClient ubicacionClient;

    private MascotaDTO convertirADTO(Mascota mascota) {

        Object ubicacion = ubicacionClient.obtenerRegiones();

        MascotaDTO dto = new MascotaDTO();
        dto.setId(mascota.getId());
        dto.setNombre(mascota.getNombre());
        dto.setEdad(mascota.getEdad());
        dto.setSexo(mascota.getSexo());
        dto.setEspecie(mascota.getEspecie().getNombreEspecie());
        dto.setRaza(mascota.getRaza().getNombreRaza());
        dto.setDueno(mascota.getDueno().getNombre());
        dto.setUbicacion(ubicacionClient.obtenerRegiones());

        return dto;
    }

    public List<MascotaDTO> obtenerTodos() {
        logger.info("Obteniendo listado de mascotas");

        return mascotaRepository.findAll()
                .stream()
                .map(this::convertirADTO)
                .collect(Collectors.toList());
    }

    public MascotaDTO buscarPorId(Integer id) {
        logger.info("Buscando mascota con id {}", id);

        Mascota mascota = mascotaRepository.findById(id)
                .orElseThrow(() -> {
                    logger.error("No existe mascota con id {}", id);
                    return new MascotaNoEncontradaException("Mascota no encontrada");
                });

        return convertirADTO(mascota);
    }

    public MascotaDTO guardarMascota(Mascota mascota) {
        logger.info("Guardando mascota {}", mascota.getNombre());

        Mascota nueva = mascotaRepository.save(mascota);

        logger.info("Mascota guardada con id {}", nueva.getId());

        return convertirADTO(nueva);
    }

    public MascotaDTO actualizarMascota(Integer id, Mascota mascota) {
        logger.info("Actualizando mascota con id {}", id);

        Mascota m = mascotaRepository.findById(id)
                .orElseThrow(() -> new MascotaNoEncontradaException("Mascota no encontrada"));

        m.setNombre(mascota.getNombre());
        m.setEdad(mascota.getEdad());
        m.setSexo(mascota.getSexo());
        m.setEspecie(mascota.getEspecie());
        m.setRaza(mascota.getRaza());
        m.setDueno(mascota.getDueno());

        Mascota actualizada = mascotaRepository.save(m);

        logger.info("Mascota {} actualizada correctamente", actualizada.getId());

        return convertirADTO(actualizada);
    }

    public String eliminar(Integer id) {
        logger.info("Intentando eliminar mascota con id {}", id);

        if (mascotaRepository.existsById(id)) {
            mascotaRepository.deleteById(id);
            logger.info("Mascota {} eliminada correctamente", id);
            return "Mascota eliminada exitosamente";
        }

        logger.warn("Se intentó eliminar una mascota inexistente: {}", id);
        return "Mascota no encontrada";
    }
}