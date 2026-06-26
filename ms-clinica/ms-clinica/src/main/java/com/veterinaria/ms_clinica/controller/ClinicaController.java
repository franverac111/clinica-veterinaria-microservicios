package com.veterinaria.ms_clinica.controller;

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

import com.veterinaria.ms_clinica.DTO.ClinicaDTO;
import com.veterinaria.ms_clinica.model.Clinica;
import com.veterinaria.ms_clinica.service.ClinicaService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/v1/clinicas")
public class ClinicaController {
    @Autowired
    private ClinicaService clinicaService;

    @GetMapping
    public ResponseEntity<List<ClinicaDTO>> todasLasClinicas() {
       List<ClinicaDTO> clinicas = clinicaService.obtenerTodos();
       if (clinicas.isEmpty()) {
           return new ResponseEntity<>(HttpStatus.NO_CONTENT);
       }
       return new ResponseEntity<>(clinicas, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ClinicaDTO> buscarPorId(@PathVariable Integer id) {
       try {
           ClinicaDTO clinica = clinicaService.buscarPorId(id);
           return new ResponseEntity<>(clinica, HttpStatus.OK);
       } catch (RuntimeException e) {
           return ResponseEntity.notFound().build();
       }
    }

    @PostMapping
    public ResponseEntity<?> agregarClinica(@Valid @RequestBody Clinica clinica) {
       try {
           ClinicaDTO guardado = clinicaService.guardar(clinica);
           return new ResponseEntity<>(guardado, HttpStatus.CREATED);
       } catch (Exception e) {
           return new ResponseEntity<>(e.getMessage(),HttpStatus.BAD_REQUEST);
       }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Clinica> modificarClinica(@PathVariable Integer id,@Valid @RequestBody Clinica clinica){
       try{
            Clinica newClinica = clinicaService.actualizarClinica( id, clinica);
           return new ResponseEntity<>(newClinica, HttpStatus.OK);
       }catch (RuntimeException e) {
           return new ResponseEntity<>(HttpStatus.NOT_FOUND);
       }
    }


}
