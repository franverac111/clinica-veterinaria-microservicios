package com.veterinaria.ms_mascotas.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.veterinaria.ms_mascotas.model.Mascota;

@Repository
public interface MascotaRepository extends JpaRepository<Mascota, Integer>{

}
