package com.veterinaria.ms_clinica.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.veterinaria.ms_clinica.model.Veterinario;

@Repository
public interface VeterinarioRepository extends JpaRepository<Veterinario, Integer>{
    List<Veterinario> findByEspecialidad(String especialidad);

}
