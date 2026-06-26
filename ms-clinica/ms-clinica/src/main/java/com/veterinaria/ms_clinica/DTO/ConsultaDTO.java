package com.veterinaria.ms_clinica.DTO;

import java.time.LocalDate;

import lombok.Data;

@Data
public class ConsultaDTO {
    private Integer id;
    private LocalDate fecha;
    private String motivo;
    private String diagnostico;
    private String nomVet;
    private String especialidad;
    private MascotaExternoDTO mascota;


}
