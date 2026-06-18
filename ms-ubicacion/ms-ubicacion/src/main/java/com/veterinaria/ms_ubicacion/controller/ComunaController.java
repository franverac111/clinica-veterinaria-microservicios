package com.veterinaria.ms_ubicacion.controller;

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

import com.veterinaria.ms_ubicacion.DTO.ComunaDTO;
import com.veterinaria.ms_ubicacion.model.Comuna;
import com.veterinaria.ms_ubicacion.service.ComunaService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/v1/comunas")
public class ComunaController {
    @Autowired
    private ComunaService comunaService;

    @GetMapping
    public ResponseEntity<List<ComunaDTO>> listar() {
        return new ResponseEntity<>(
                comunaService.obtenerTodos(),
                HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ComunaDTO> buscar(@PathVariable Integer id) {
        return new ResponseEntity<>(
                comunaService.buscarPorId(id),
                HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<ComunaDTO> guardar(@Valid @RequestBody ComunaDTO dto) {
        return new ResponseEntity<>(
                comunaService.guardar(dto),
                HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Comuna> actualizar(@PathVariable Integer id,@Valid @RequestBody Comuna comuna) {
        return new ResponseEntity<>(
                comunaService.actualizarComuna(id, comuna),
                HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> eliminar( @PathVariable Integer id) {
        return new ResponseEntity<>(
                comunaService.eliminar(id),
                HttpStatus.OK);
    }

}
