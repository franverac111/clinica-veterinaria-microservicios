package com.veterinaria.ms_mascotas;

import com.veterinaria.ms_mascotas.DTO.RegionDTO;
import com.veterinaria.ms_mascotas.client.UbicacionClient;
import com.veterinaria.ms_mascotas.exception.MascotaNoEncontradaException;
import com.veterinaria.ms_mascotas.model.Dueno;
import com.veterinaria.ms_mascotas.model.Especie;
import com.veterinaria.ms_mascotas.model.Mascota;
import com.veterinaria.ms_mascotas.model.Raza;
import com.veterinaria.ms_mascotas.repository.MascotaRepository;
import com.veterinaria.ms_mascotas.service.MascotaService;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class MascotaServiceTest {

    @Mock
    private MascotaRepository mascotaRepository;

    @Mock
    private UbicacionClient ubicacionClient;

    @InjectMocks
    private MascotaService mascotaService;

    @Test
    void buscarPorId_ok() {

        Mascota mascota = new Mascota();
        mascota.setId(1);
        mascota.setNombre("Firulais");
        mascota.setEdad(3);
        mascota.setSexo("M");

        Especie especie = new Especie();
        especie.setNombreEspecie("Perro");
        mascota.setEspecie(especie);

        Raza raza = new Raza();
        raza.setNombreRaza("Labrador");
        mascota.setRaza(raza);

        Dueno dueno = new Dueno();
        dueno.setNombre("Juan");
        mascota.setDueno(dueno);

        when(mascotaRepository.findById(1))
                .thenReturn(Optional.of(mascota));

        RegionDTO region = new RegionDTO();

        when(ubicacionClient.obtenerRegiones())
                .thenReturn(List.of(region));

        var resultado = mascotaService.buscarPorId(1);

        assertNotNull(resultado);
        assertEquals("Firulais", resultado.getNombre());
    }

    @Test
    void buscarPorId_noExiste() {

        when(mascotaRepository.findById(99))
                .thenReturn(Optional.empty());

        assertThrows(MascotaNoEncontradaException.class, () -> {
            mascotaService.buscarPorId(99);
        });
    }
}