package com.veterinaria.ms_mascotas.DTO;

import lombok.Data;

@Data
public class MascotaDTO {
    private Integer id;
    private String nombre;
    private Integer edad;
    private String sexo;
    private String especie;
    private String raza;
    private String dueno;

}
