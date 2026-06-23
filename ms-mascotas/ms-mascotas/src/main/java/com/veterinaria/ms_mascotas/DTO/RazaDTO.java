package com.veterinaria.ms_mascotas.DTO;
import java.util.List;
import lombok.Data;

@Data
public class RazaDTO {
    private Integer id;
    private String nombre;
    private List<String> mascotas;

}
