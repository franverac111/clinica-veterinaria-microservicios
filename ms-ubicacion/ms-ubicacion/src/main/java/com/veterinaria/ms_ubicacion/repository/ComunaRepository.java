package com.veterinaria.ms_ubicacion.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import com.veterinaria.ms_ubicacion.model.Comuna;

public interface ComunaRepository extends JpaRepository<Comuna , Integer>{

        boolean existsByNombreComunaIgnoreCaseAndRegion_Id( String nombreComuna, Integer regionId);
}
