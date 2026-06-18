package com.veterinaria.ms_ubicacion.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.veterinaria.ms_ubicacion.model.Region;

@Repository
public interface RegionRepository extends JpaRepository<Region, Integer>{

    boolean existsByNombreRegionIgnoreCase(String nombreRegion);
    
}
