-- CLINICAS

INSERT INTO clinica
(nombre_clinica, direccion, telefono, comuna_id)
VALUES
('Vet Center Maipu', 'Av. Pajaritos 1234', '912345678', 1),
('Animal Care Padre Hurtado', 'Camino Melipilla 456', '923456789', 2);

-- VETERINARIOS

INSERT INTO veterinario
(nombre_veterinario, telefono, especialidad, clinica_id)
VALUES
('Juan Soto', '934567890', 'Medicina General', 1),
('Maria Perez', '945678901', 'Cirugia', 1),
('Carlos Diaz', '956789012', 'Dermatologia', 2);

-- PROCEDIMIENTOS

INSERT INTO procedimiento
(nombre, descripcion)
VALUES
('Vacunacion', 'Aplicacion de vacunas'),
('Desparasitacion', 'Control de parasitos'),
('Esterilizacion', 'Procedimiento quirurgico de esterilizacion');

-- CONSULTAS

INSERT INTO consulta
(fecha, motivo, diagnostico, mascota_id, veterinario_id)
VALUES
('2026-06-20', 'Control anual', 'Mascota sana', 1, 1),
('2026-06-21', 'Fiebre', 'Infeccion leve', 2, 2),
('2026-06-22', 'Problema de piel', 'Dermatitis', 3, 3);

-- PAGOS

INSERT INTO pago
(fecha, monto, metodo_pago, consulta_id)
VALUES
('2026-06-20', 25000, 'Debito', 1),
('2026-06-21', 35000, 'Credito', 2),
('2026-06-22', 28000, 'Efectivo', 3);

-- CONSULTA_PROCEDIMIENTO

INSERT INTO consulta_procedimiento
(consulta_id, procedimiento_id)
VALUES
(1,1),
(2,2),
(3,3);