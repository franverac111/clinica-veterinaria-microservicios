package com.veterinaria.ms_clinica.DTO;

import java.time.LocalDate;

import lombok.Data;

@Data
public class ConsultaDTO {
    private Integer id;
    private LocalDate fecha;
    private String motivo;
    private String diagnostico;
    private Integer mascotaId;
    private String nombreMascota;
    private String sexo;
    private Integer edad;
    private String raza;
    private String nombreVet;
    private String especialidadVet;

}
