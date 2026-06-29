package com.veterinaria.ms_clinica.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import com.github.javafaker.Faker;
import com.veterinaria.ms_clinica.DTO.ConsultaDTO;
import com.veterinaria.ms_clinica.model.Consulta;
import com.veterinaria.ms_clinica.repository.ConsultaRepository;

@ExtendWith(MockitoExtension.class)
public class ConsultaServiceTest {
    @Mock
	private ConsultaRepository consultaRepository;

	@Mock
	private ConsultaValidaciones consultaValidaciones;

	@InjectMocks
	private ConsultaService consultaService;

	private Faker faker = new Faker();

	@BeforeEach 
	void setup() {
	}

    @Test
    void testBuscarPorIdExitoso() {
    Integer idSimulado = 1;
    Consulta consulta = new Consulta();
    consulta.setId(idSimulado);
    consulta.setFecha(LocalDate.now());
    consulta.setMotivo(faker.medical().diseaseName());
    consulta.setDiagnostico(faker.lorem().sentence());
    ConsultaDTO dto = new ConsultaDTO();
    dto.setId(idSimulado);
    dto.setFecha(consulta.getFecha());
    dto.setMotivo(consulta.getMotivo());
    dto.setDiagnostico(consulta.getDiagnostico());

    when(consultaRepository.findById(idSimulado))
            .thenReturn(Optional.of(consulta));

    when(consultaValidaciones.convertirADTO(consulta))
            .thenReturn(dto);

    ConsultaDTO resultado = consultaService.buscarPorId(idSimulado);

    assertNotNull(resultado);
    assertEquals(consulta.getMotivo(), resultado.getMotivo());

    verify(consultaRepository, times(1)).findById(idSimulado);
    }

    @Test
void testObtenerTodos() {

    Consulta c1 = new Consulta();
    c1.setId(1);
    c1.setFecha(LocalDate.now());
    c1.setMotivo(faker.medical().diseaseName());

    Consulta c2 = new Consulta();
    c2.setId(2);
    c2.setFecha(LocalDate.now());
    c2.setMotivo(faker.medical().diseaseName());

    ConsultaDTO dto1 = new ConsultaDTO();
    dto1.setId(1);
    dto1.setMotivo(c1.getMotivo());

    ConsultaDTO dto2 = new ConsultaDTO();
    dto2.setId(2);
    dto2.setMotivo(c2.getMotivo());

    when(consultaRepository.findAll())
            .thenReturn(List.of(c1, c2));

    when(consultaValidaciones.convertirADTO(c1))
            .thenReturn(dto1);

    when(consultaValidaciones.convertirADTO(c2))
            .thenReturn(dto2);

    List<ConsultaDTO> resultado = consultaService.obtenerTodos();

    assertEquals(2, resultado.size());

    verify(consultaRepository, times(1)).findAll();
    }


}
