package com.veterinaria.ms_clinica.service;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.veterinaria.ms_clinica.DTO.ConsultaDTO;
import com.veterinaria.ms_clinica.model.Consulta;
import com.veterinaria.ms_clinica.repository.ConsultaRepository;
import jakarta.transaction.Transactional;


@Service
@Transactional
public class ConsultaService {
    @Autowired
    private ConsultaRepository consultaRepository;

    @Autowired
    private ConsultaValidaciones consultaValidaciones;

    public List<ConsultaDTO> obtenerTodos() {
       return consultaRepository.findAll()
                .stream()
                .map(consultaValidaciones::convertirADTO)
                .toList();
    }

    public ConsultaDTO buscarPorId(Integer id) {
       Consulta consulta = consultaRepository.findById(id)
           .orElseThrow(() -> new RuntimeException("la consulta no existe"));
       return consultaValidaciones.convertirADTO(consulta);
    }



    public ConsultaDTO guardar(Consulta consulta) {
        if (consultaValidaciones.existeMascota(consulta.getMascotaId())==false) {
            throw new RuntimeException("La mascota indicada no existe");
         }
        Consulta guardado = consultaRepository.save(consulta);
        return consultaValidaciones.convertirADTO(guardado);
    }

    public Consulta actualizarConsulta(Integer id, Consulta con) {
        Consulta consulta = consultaRepository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException("la consulta no existe"));

            if (con.getFecha() != null) {
                consulta.setFecha(con.getFecha());
            }
            if (con.getMotivo() != null) {
                consulta.setMotivo(con.getMotivo());
            }
            if (con.getDiagnostico() != null) {
                consulta.setDiagnostico(con.getDiagnostico());
            }
            if (con.getMascotaId() != null) {
                if (consultaValidaciones.existeMascota(con.getMascotaId())==false) {
                throw new RuntimeException("La comuna indicada no existe");
                }
                consulta.setMascotaId(con.getMascotaId());
            }
            if (con.getVeterinario() != null) {
                consulta.setVeterinario(con.getVeterinario());
            }
            return consultaRepository.save(consulta);
    }

}
