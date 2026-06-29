package com.veterinaria.ms_mascotas.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.veterinaria.ms_mascotas.DTO.MascotaDTO;
import com.veterinaria.ms_mascotas.client.UbicacionClient;
import com.veterinaria.ms_mascotas.model.Mascota;
import com.veterinaria.ms_mascotas.repository.MascotaRepository;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class MascotaService {

    @Autowired
    private MascotaRepository mascotaRepository;

    @Autowired
    private UbicacionClient ubicacionClient;

    private MascotaDTO convertirADTO(Mascota mascota) {

        System.out.println(" LLAMANDO A ms-ubicacion...");
        Object ubicacion = ubicacionClient.obtenerRegiones();
        System.out.println(" RESPUESTA UBICACION: " + ubicacion);
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
                .orElseThrow(() -> new RuntimeException("mascota no encontrada"));

        m.setNombre(mascota.getNombre());
        m.setEdad(mascota.getEdad());
        m.setSexo(mascota.getSexo());

        return mascotaRepository.save(m);
    }

    public String eliminar(Integer id) {

        if (mascotaRepository.existsById(id)) {
            mascotaRepository.deleteById(id);
            return "Mascota eliminada exitosamente";
        }

        return "Mascota no encontrada";
    }
}