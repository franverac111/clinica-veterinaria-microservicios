package com.veterinaria.ms_clinica;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.github.javafaker.Faker;
import com.veterinaria.ms_clinica.DTO.ClinicaDTO;
import com.veterinaria.ms_clinica.model.Clinica;
import com.veterinaria.ms_clinica.repository.ClinicaRepository;
import com.veterinaria.ms_clinica.service.ClinicaService;
import com.veterinaria.ms_clinica.service.ClinicaValidaciones;

@ExtendWith(MockitoExtension.class)
class MsClinicaApplicationTests {
	@Mock
	private ClinicaRepository clinicaRepository;

	@Mock
	private ClinicaValidaciones clinicaValidaciones;

	@InjectMocks
	private ClinicaService clinicaService;

	private Faker faker = new Faker();

	@BeforeEach 
	void setup() {
	}

	@Test
	void testBuscarPorId_Exitoso() {
    Integer idSimulado = 1;
    Clinica clinica = new Clinica();
    clinica.setId(idSimulado);
    clinica.setNombreClinica(faker.company().name());
    clinica.setDireccion(faker.address().streetAddress());
    clinica.setTelefono("987654321");
    ClinicaDTO dto = new ClinicaDTO();
    dto.setId(idSimulado);
    dto.setNombreClinica(clinica.getNombreClinica());
    dto.setDireccion(clinica.getDireccion());
    dto.setTelefono(clinica.getTelefono());

    when(clinicaRepository.findById(idSimulado))
            .thenReturn(Optional.of(clinica));

    when(clinicaValidaciones.convertirADTO(clinica))
            .thenReturn(dto);

    ClinicaDTO resultado = clinicaService.buscarPorId(idSimulado);

    assertNotNull(resultado);
    assertEquals(clinica.getNombreClinica(), resultado.getNombreClinica());

    verify(clinicaRepository, times(1)).findById(idSimulado);
	}

	@Test
	void testObtenerTodos() {
    Clinica c1 = new Clinica();
    c1.setId(1);
    c1.setNombreClinica(faker.company().name());
    Clinica c2 = new Clinica();
    c2.setId(2);
    c2.setNombreClinica(faker.company().name());
    ClinicaDTO dto1 = new ClinicaDTO();
    dto1.setId(1);
    dto1.setNombreClinica(c1.getNombreClinica());
    ClinicaDTO dto2 = new ClinicaDTO();
    dto2.setId(2);
    dto2.setNombreClinica(c2.getNombreClinica());
    when(clinicaRepository.findAll())
            .thenReturn(List.of(c1, c2));

    when(clinicaValidaciones.convertirADTO(c1))
            .thenReturn(dto1);

    when(clinicaValidaciones.convertirADTO(c2))
            .thenReturn(dto2);

    List<ClinicaDTO> resultado = clinicaService.obtenerTodos();

    assertEquals(2, resultado.size());

    verify(clinicaRepository, times(1)).findAll();
	}



}
