package com.veterinaria.ms_clinica.DTO;

import lombok.Data;

@Data
public class VeterinarioDTO {
    private Integer id;
    private String nombre;
    private String telefono;
    private String especialidad;
    private Integer totalConsultas;

}
