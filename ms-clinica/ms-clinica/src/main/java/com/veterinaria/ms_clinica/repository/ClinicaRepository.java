package com.veterinaria.ms_clinica.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.veterinaria.ms_clinica.model.Clinica;

@Repository
public interface ClinicaRepository  extends JpaRepository<Clinica, Integer>{

}
