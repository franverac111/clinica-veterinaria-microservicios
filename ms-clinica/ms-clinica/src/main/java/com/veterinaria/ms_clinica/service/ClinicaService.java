package com.veterinaria.ms_clinica.service;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.veterinaria.ms_clinica.DTO.ClinicaDTO;
import com.veterinaria.ms_clinica.model.Clinica;
import com.veterinaria.ms_clinica.repository.ClinicaRepository;

@Service
@Transactional
public class ClinicaService {
    @Autowired
    private ClinicaRepository clinicaRepository;
    
    @Autowired
    private ClinicaValidaciones clinicaValidaciones;
    

    public List<ClinicaDTO> obtenerTodos() {
       return clinicaRepository.findAll()
                .stream()
                .map(clinicaValidaciones::convertirADTO)
                .toList();
    }


    public ClinicaDTO buscarPorId(Integer id) {
       Clinica clinica = clinicaRepository.findById(id)
           .orElseThrow(() -> new RuntimeException("clinica no encontrada"));
        return clinicaValidaciones.convertirADTO(clinica);
    }

    

    public ClinicaDTO guardar(Clinica clinica) {
        if (clinicaValidaciones.existeComuna(clinica.getComunaId())==false) {
            throw new RuntimeException("La comuna indicada no existe");
         }
        Clinica guardado = clinicaRepository.save(clinica);
        return clinicaValidaciones.convertirADTO(guardado);
    }

    public Clinica actualizarClinica(Integer id, Clinica cli) {

    Clinica clinica = clinicaRepository.findById(id).orElseThrow(() ->
                    new RuntimeException("la clinica no existe"));
        if (cli.getNombreClinica() != null) {
                clinica.setNombreClinica(cli.getNombreClinica());
            }
        if (cli.getDireccion() != null) {
                clinica.setDireccion(cli.getDireccion());
            }
        if (cli.getTelefono() != null) {
                clinica.setTelefono(cli.getTelefono());
            }
        if (cli.getComunaId() != null) {
            if (clinicaValidaciones.existeComuna(cli.getComunaId())==false) {
              throw new RuntimeException("La comuna indicada no existe");
             }
             clinica.setComunaId(cli.getComunaId());
            }
        return clinicaRepository.save(clinica);
    }

}
