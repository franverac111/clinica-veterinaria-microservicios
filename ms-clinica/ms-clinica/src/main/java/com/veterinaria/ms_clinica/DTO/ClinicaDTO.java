package com.veterinaria.ms_clinica.DTO;

import lombok.Data;

@Data
public class ClinicaDTO {
    private Integer id;
    private String nombreClinica;
    private String direccion;
    private String telefono;
    private ComunaExternoDTO comuna;
}
