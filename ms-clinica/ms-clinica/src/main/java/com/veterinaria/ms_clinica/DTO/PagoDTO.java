package com.veterinaria.ms_clinica.DTO;

import java.time.LocalDate;

import lombok.Data;

@Data
public class PagoDTO {
    private Integer id;
    private LocalDate fecha;
    private Integer monto;
    private String metodoPago;
    private Integer consultaId;

}
