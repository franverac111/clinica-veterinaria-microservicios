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

import com.veterinaria.ms_ubicacion.DTO.RegionDTO;
import com.veterinaria.ms_ubicacion.model.Region;
import com.veterinaria.ms_ubicacion.service.RegionService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/v1/regiones")
public class RegionController {
        @Autowired
    private RegionService regionService;

    @GetMapping
    public ResponseEntity<List<RegionDTO>> listar() {
        return new ResponseEntity<>(
                regionService.obtenerTodos(),
                HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<RegionDTO> buscar( @PathVariable Integer id) {
        return new ResponseEntity<>(
                regionService.buscarPorId(id),
                HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<RegionDTO> guardar(@Valid @RequestBody RegionDTO dto) {
        return new ResponseEntity<>(
                regionService.guardar(dto),
                HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Region> actualizar(
            @PathVariable Integer id, @Valid @RequestBody Region region) {
        return new ResponseEntity<>(
                regionService.actualizarRegion(id, region),
                HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> eliminar(
            @PathVariable Integer id) {
        return new ResponseEntity<>(
                regionService.eliminar(id),
                HttpStatus.OK);
    }

}
