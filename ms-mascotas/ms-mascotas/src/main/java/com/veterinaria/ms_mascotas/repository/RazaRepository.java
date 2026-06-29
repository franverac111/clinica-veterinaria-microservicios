package com.veterinaria.ms_mascotas.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.veterinaria.ms_mascotas.model.Raza;

@Repository
public interface RazaRepository extends JpaRepository<Raza, Integer>{
    Optional<Raza> findByNombreRaza(String nombre);

}
