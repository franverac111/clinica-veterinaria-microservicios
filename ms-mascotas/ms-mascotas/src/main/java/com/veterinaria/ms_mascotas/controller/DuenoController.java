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

import com.veterinaria.ms_mascotas.DTO.DuenoDTO;
import com.veterinaria.ms_mascotas.model.Dueno;
import com.veterinaria.ms_mascotas.service.DuenoService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/v1/duenos")
public class DuenoController {
    @Autowired
    private DuenoService duenoService;

    @GetMapping
    public ResponseEntity<List<DuenoDTO>> listar() {
        return new ResponseEntity<>(
                duenoService.obtenerTodos(),
                HttpStatus.OK);
    }

    @GetMapping("/id/{id}")
    public ResponseEntity<DuenoDTO> buscarPorId(@PathVariable Integer id) {
        return new ResponseEntity<>(
                duenoService.buscarPorId(id),
                HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<DuenoDTO> guardar(@Valid @RequestBody DuenoDTO dto) {
        return new ResponseEntity<>(
                duenoService.guardar(dto),
                HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Dueno> actualizar(@PathVariable Integer id,@Valid @RequestBody Dueno dueno) {
        return new ResponseEntity<>(
                duenoService.actualizarDueno(id, dueno),
                HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> eliminar( @PathVariable Integer id) {
        return new ResponseEntity<>(
                duenoService.eliminar(id),
                HttpStatus.OK);
    }

    @GetMapping("/nombre/{nombre}")
    public ResponseEntity<DuenoDTO> buscarPorNombre(@PathVariable String nombre) {
        return new ResponseEntity<>(
                duenoService.buscarPorNombre(nombre),
                HttpStatus.OK);
    }

    @GetMapping("/rut/{rut}")
    public ResponseEntity<DuenoDTO> buscarPorRut(@PathVariable String rut) {
        return new ResponseEntity<>(
                duenoService.buscarPorRut(rut),
                HttpStatus.OK);
    }

    @GetMapping("/{id}/descuento")
    public ResponseEntity<String> mostrarDescuento(@PathVariable Integer id) {
        return new ResponseEntity<>(
            duenoService.mostrarDescuento(id),
            HttpStatus.OK);
    }


}
