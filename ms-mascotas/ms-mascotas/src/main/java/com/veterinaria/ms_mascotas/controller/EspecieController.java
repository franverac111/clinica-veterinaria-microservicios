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
import com.veterinaria.ms_mascotas.DTO.EspecieDTO;
import com.veterinaria.ms_mascotas.model.Especie;
import com.veterinaria.ms_mascotas.service.EspecieService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/v1/especies")
public class EspecieController {
    @Autowired
    private EspecieService especieService;

    @GetMapping
    public ResponseEntity<List<EspecieDTO>> listar() {
        return new ResponseEntity<>(
                especieService.obtenerTodos(),
                HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<EspecieDTO> buscarPorId(@PathVariable Integer id) {
        return new ResponseEntity<>(
                especieService.buscarPorId(id),
                HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Especie> guardar(@Valid @RequestBody  Especie especie) {
        return new ResponseEntity<>(
                especieService.guardar(especie),
                HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Especie> actualizar(@PathVariable Integer id,@Valid @RequestBody Especie especie) {
        return new ResponseEntity<>(
                especieService.actualizarEspecie(id, especie),
                HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> eliminar( @PathVariable Integer id) {
        return new ResponseEntity<>(
                especieService.eliminar(id),
                HttpStatus.OK);
    }

    @GetMapping("/nombre/{nombre}")
    public ResponseEntity<EspecieDTO> buscarPorNombre(@PathVariable String nombre) {
        return new ResponseEntity<>(
                especieService.buscarPorNombre(nombre),
                HttpStatus.OK);
    }

}
