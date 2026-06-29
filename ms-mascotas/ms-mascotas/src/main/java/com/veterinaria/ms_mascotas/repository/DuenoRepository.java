package com.veterinaria.ms_mascotas.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.veterinaria.ms_mascotas.model.Dueno;

@Repository
public interface DuenoRepository extends JpaRepository<Dueno, Integer>{
    Optional<Dueno> findByRut(String rut);
    Optional<Dueno> findByNombre(String nombre);


}
