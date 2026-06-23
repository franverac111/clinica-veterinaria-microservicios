package com.veterinaria.ms_mascotas.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.veterinaria.ms_mascotas.model.Especie;

@Repository 
public interface EspecieRepository extends JpaRepository<Especie, Integer>{
    Optional<Especie> findByNombreEspecie(String nombre);

}
