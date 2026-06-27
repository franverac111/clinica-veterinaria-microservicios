package com.veterinaria.ms_clinica.service;
import org.springframework.stereotype.Service;
import com.veterinaria.ms_clinica.DTO.PagoDTO;
import com.veterinaria.ms_clinica.model.Pago;

@Service
public class PagoValidaciones {

        public PagoDTO convertirADTO(Pago pago) {
        PagoDTO dto = new PagoDTO();
        dto.setId(pago.getId());
        dto.setFecha(pago.getFecha());
        dto.setMonto(pago.getMonto());
        dto.setMetodoPago(pago.getMetodoPago());
        if (pago.getConsulta() != null) {
            dto.setConsultaId(pago.getConsulta().getId());
        }
        return dto;
    }


}
