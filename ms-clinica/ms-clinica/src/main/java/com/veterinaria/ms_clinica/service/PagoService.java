package com.veterinaria.ms_clinica.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.veterinaria.ms_clinica.DTO.PagoDTO;
import com.veterinaria.ms_clinica.model.Pago;
import com.veterinaria.ms_clinica.repository.PagoRepository;

@Service
@Transactional
public class PagoService {
    @Autowired
    private PagoRepository pagoRepository;

    private PagoValidaciones pagoValidaciones;
     

    public List<PagoDTO> obtenerTodos() {
       return pagoRepository.findAll()
                .stream()
                .map(pagoValidaciones::convertirADTO)
                .toList();
    }

    public PagoDTO buscarPorId(Integer id) {
       Pago pago = pagoRepository.findById(id)
           .orElseThrow(() -> new RuntimeException("el pago no existe"));
       return pagoValidaciones.convertirADTO(pago);
    }

    public String eliminar(Integer id) {
     try {
           Pago pago = pagoRepository.findById(id)
                   .orElseThrow(() -> new RuntimeException("¡Imposible eliminar! el pago con ID " + id + " no existe."));
           pagoRepository.delete(pago);
           return "el pago '" + pago.getId() + "' ha sido retirada exitosamente.";
       } catch (RuntimeException e) {
           return e.getMessage();
       }
    }

    public Pago guardarPago(Pago pago) {
    return pagoRepository.save(pago);
    }

    public Pago actualizarPago(Integer id, Pago pa) {
        Pago pago = pagoRepository.findById(id).orElseThrow(() ->
                        new RuntimeException("el pago no existe"));
        if (pa.getFecha() != null) {
            pago.setFecha(pa.getFecha());
        }
        if (pa.getMonto() != null) {
            pago.setMonto(pa.getMonto());
        }
        if (pa.getMetodoPago() != null) {
            pago.setMetodoPago(pa.getMetodoPago());
        }
        if (pa.getConsulta() != null) {
            pago.setConsulta(pa.getConsulta());
        }
        return pagoRepository.save(pago);
    }

    public Integer totalRecaudadoPorClinica(Integer clinicaId) {
    return pagoRepository.totalRecaudadoPorClinica(clinicaId);
    }


}
