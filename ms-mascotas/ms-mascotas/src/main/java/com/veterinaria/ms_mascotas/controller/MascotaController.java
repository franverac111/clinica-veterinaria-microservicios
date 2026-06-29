package com.veterinaria.ms_mascotas.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.veterinaria.ms_mascotas.DTO.MascotaDTO;
import com.veterinaria.ms_mascotas.model.Mascota;
import com.veterinaria.ms_mascotas.service.MascotaService;
import com.veterinaria.ms_mascotas.service.DuenoService;

import jakarta.validation.Valid;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

@RestController
@RequestMapping("/api/v1/mascotas")
public class MascotaController {

    @Autowired
    private MascotaService mascotaService;

    @Autowired
    private DuenoService duenoService;

    @GetMapping
    public ResponseEntity<CollectionModel<EntityModel<MascotaDTO>>> listar() {

        List<MascotaDTO> mascotas = mascotaService.obtenerTodos();

        List<EntityModel<MascotaDTO>> recursos = mascotas.stream()
                .map(m -> EntityModel.of(m,
                        linkTo(methodOn(MascotaController.class).buscarPorId(m.getId())).withSelfRel(),
                        linkTo(methodOn(MascotaController.class).listar()).withRel("todas")))
                .toList();

        return ResponseEntity.ok(
                CollectionModel.of(recursos,
                        linkTo(methodOn(MascotaController.class).listar()).withSelfRel())
        );
    }

    @GetMapping("/{id}")
    public ResponseEntity<EntityModel<MascotaDTO>> buscarPorId(@PathVariable Integer id) {

        MascotaDTO mascota = mascotaService.buscarPorId(id);

        EntityModel<MascotaDTO> recurso = EntityModel.of(mascota,
                linkTo(methodOn(MascotaController.class).buscarPorId(id)).withSelfRel(),
                linkTo(methodOn(MascotaController.class).listar()).withRel("lista"),
                linkTo(methodOn(MascotaController.class).eliminar(id)).withRel("eliminar"),
                linkTo(methodOn(MascotaController.class).actualizar(id, null)).withRel("actualizar"));

        return ResponseEntity.ok(recurso);
    }

    @PostMapping
    public ResponseEntity<MascotaDTO> guardar(@Valid @RequestBody Mascota mascota) {
        return new ResponseEntity<>(mascotaService.guardarMascota(mascota), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<MascotaDTO> actualizar(@PathVariable Integer id,@Valid @RequestBody Mascota mascota) {
        return ResponseEntity.ok(mascotaService.actualizarMascota(id, mascota));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> eliminar(@PathVariable Integer id) {
        return new ResponseEntity<>(mascotaService.eliminar(id), HttpStatus.OK);
    }

    @GetMapping("/descuento/{id}")
    public ResponseEntity<Double> calcularDescuento(@PathVariable Integer id) {
        try {
            Double descuento = duenoService.calcularDescuento(id);
            return ResponseEntity.ok(descuento);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }
}