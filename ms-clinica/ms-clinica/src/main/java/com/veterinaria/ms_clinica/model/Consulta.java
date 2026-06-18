package com.veterinaria.ms_clinica.model;

import java.time.LocalDate;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Consulta {
     @Id
     @GeneratedValue(strategy = GenerationType.IDENTITY)
     private Integer id;

     @NotNull(message = "La fecha es obligatoria")
     @Column(nullable = false)
     private LocalDate fecha;

     @NotBlank(message = "El motivo es obligatorio")
     @Size(min = 5, max = 255, message = "El motivo debe tener entre 5 y 255 caracteres")
     @Column(nullable = false, length = 255)
     private String motivo;

     @NotBlank(message = "El diagnostico es obligatorio")
     @Size(min = 5, max = 500, message = "El motivo debe tener entre 5 y 500 caracteres")
     @Column(nullable = false, length = 500)
     private String diagnostico;

     @Column(name = "mascota_id", nullable = false)
     private Integer mascotaId;

     @ManyToOne
     @JoinColumn(name = "veterinario_id", nullable = false)
     private Veterinario veterinario;
     
     @OneToMany(mappedBy = "consulta")
     @ToString.Exclude
     private List<Pago> pagos;

     @OneToMany(mappedBy = "consulta")
     @ToString.Exclude
     private List<ConsultaProcedimiento> consultaProcedimientos;

}
