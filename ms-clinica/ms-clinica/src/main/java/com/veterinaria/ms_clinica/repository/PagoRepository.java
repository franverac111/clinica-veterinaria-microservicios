package com.veterinaria.ms_clinica.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.veterinaria.ms_clinica.model.Pago;

@Repository
public interface PagoRepository extends JpaRepository<Pago, Integer>{
      @Query("SELECT SUM(p.monto) FROM Pago p WHERE p.consulta.veterinario.clinica.id = :clinicaId")
      Integer totalRecaudadoPorClinica(@Param("clinicaId") Integer clinicaId);

}
