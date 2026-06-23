-- ============================================
-- DATOS DE PRUEBA ESPECIES
-- ============================================

INSERT INTO especie (nombre_especie) VALUES
('Perro'),
('Gato'),
('Ave'),
('Conejo'),
('Hamster');

-- ============================================
-- DATOS DE PRUEBA RAZAS
-- ============================================

INSERT INTO raza (nombre_raza) VALUES
('Labrador'),
('Pastor Aleman'),
('Golden Retriever'),
('Poodle'),
('Bulldog Frances'),
('Siames'),
('Persa'),
('Maine Coon');

-- ============================================
-- DATOS DE PRUEBA DUENOS
-- ============================================

INSERT INTO dueno (rut, nombre, telefono, direccion, mail) VALUES
('12345678-9', 'Juan Perez', '912345678', 'Maipu 123', 'juan@gmail.com'),
('98765432-1', 'Maria Soto', '987654321', 'Padre Hurtado 456', 'maria@gmail.com'),
('11222333-4', 'Carlos Rojas', '998887766', 'Santiago Centro 789', 'carlos@gmail.com');

-- ============================================
-- DATOS DE PRUEBA MASCOTAS
-- ============================================

INSERT INTO mascota
(nombre, edad, sexo, especie_id, raza_id, dueno_id)
VALUES
('Firulais', 5, 'Macho', 1, 1, 1),
('Luna', 3, 'Hembra', 2, 6, 2),
('Max', 2, 'Macho', 1, 3, 1),
('Michi', 4, 'Hembra', 2, 7, 3);