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
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.veterinaria.ms_mascotas.DTO.RazaDTO;
import com.veterinaria.ms_mascotas.model.Raza;
import com.veterinaria.ms_mascotas.service.RazaService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/v1/razas")
public class RazaController {
    @Autowired
    private RazaService razaService;

         @GetMapping
    public ResponseEntity<List<RazaDTO>> listar() {
        return new ResponseEntity<>(
                razaService.obtenerTodos(),
                HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<RazaDTO> buscarPorId(@PathVariable Integer id) {
        return new ResponseEntity<>(
                razaService.buscarPorId(id),
                HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Raza> guardar(@Valid @RequestBody  Raza raza) {
        return new ResponseEntity<>(
                razaService.guardarRaza(raza),
                HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Raza> actualizar(@PathVariable Integer id,@Valid @RequestBody Raza raza) {
        return new ResponseEntity<>(
                razaService.actualizarRaza(id, raza),
                HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> eliminar( @PathVariable Integer id) {
        return new ResponseEntity<>(
                razaService.eliminar(id),
                HttpStatus.OK);
    }

}
