package com.veterinaria.ms_clinica.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.veterinaria.ms_clinica.model.ConsultaProcedimiento;

@Repository
public interface ConsultaProcedimientoRepository extends JpaRepository<ConsultaProcedimiento, Integer>{

}
