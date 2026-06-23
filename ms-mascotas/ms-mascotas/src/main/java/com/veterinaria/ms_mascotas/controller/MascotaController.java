package com.veterinaria.ms_mascotas.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.veterinaria.ms_mascotas.DTO.MascotaDTO;
import com.veterinaria.ms_mascotas.model.Mascota;
import com.veterinaria.ms_mascotas.service.MascotaService;

import io.swagger.v3.oas.annotations.parameters.RequestBody;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/v1/mascotas")
public class MascotaController {
    @Autowired
    private MascotaService mascotaService;

     @GetMapping
    public ResponseEntity<List<MascotaDTO>> listar() {
        return new ResponseEntity<>(
                mascotaService.obtenerTodos(),
                HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<MascotaDTO> buscarPorId(@PathVariable Integer id) {
        return new ResponseEntity<>(
                mascotaService.buscarPorId(id),
                HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Mascota> guardar(@Valid @RequestBody  Mascota mascota) {
        return new ResponseEntity<>(
                mascotaService.guardarMascota(mascota),
                HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Mascota> actualizar(@PathVariable Integer id,@Valid @RequestBody Mascota mascota) {
        return new ResponseEntity<>(
                mascotaService.actualizarMascota(id, mascota),
                HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> eliminar( @PathVariable Integer id) {
        return new ResponseEntity<>(
                mascotaService.eliminar(id),
                HttpStatus.OK);
    }
    

}
