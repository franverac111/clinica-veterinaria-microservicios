-- ==========================================
-- TABLA CLINICA
-- ==========================================

CREATE TABLE clinica (
    id INT AUTO_INCREMENT PRIMARY KEY,
    nombre_clinica VARCHAR(60) NOT NULL UNIQUE,
    direccion VARCHAR(200) NOT NULL,
    telefono VARCHAR(12) NOT NULL,
    comuna_id INT NOT NULL
);

-- ==========================================
-- TABLA VETERINARIO
-- ==========================================

CREATE TABLE veterinario (
    id INT AUTO_INCREMENT PRIMARY KEY,
    nombre_veterinario VARCHAR(100) NOT NULL,
    telefono VARCHAR(12) NOT NULL,
    especialidad VARCHAR(100) NOT NULL,
    clinica_id INT NOT NULL,

    CONSTRAINT fk_veterinario_clinica
        FOREIGN KEY (clinica_id)
        REFERENCES clinica(id)
);

-- ==========================================
-- TABLA CONSULTA
-- ==========================================

CREATE TABLE consulta (
    id INT AUTO_INCREMENT PRIMARY KEY,
    fecha DATE NOT NULL,
    motivo VARCHAR(255) NOT NULL,
    diagnostico VARCHAR(500) NOT NULL,
    mascota_id INT NOT NULL,
    veterinario_id INT NOT NULL,

    CONSTRAINT fk_consulta_veterinario
        FOREIGN KEY (veterinario_id)
        REFERENCES veterinario(id)
);

-- ==========================================
-- TABLA PROCEDIMIENTO
-- ==========================================

CREATE TABLE procedimiento (
    id INT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(100) NOT NULL,
    descripcion VARCHAR(500) NOT NULL
);

-- ==========================================
-- TABLA CONSULTA_PROCEDIMIENTO
-- ==========================================

CREATE TABLE consulta_procedimiento (
    id INT AUTO_INCREMENT PRIMARY KEY,
    consulta_id INT NOT NULL,
    procedimiento_id INT NOT NULL,

    CONSTRAINT fk_cp_consulta
        FOREIGN KEY (consulta_id)
        REFERENCES consulta(id),

    CONSTRAINT fk_cp_procedimiento
        FOREIGN KEY (procedimiento_id)
        REFERENCES procedimiento(id)
);

-- ==========================================
-- TABLA PAGO
-- ==========================================

CREATE TABLE pago (
    id INT AUTO_INCREMENT PRIMARY KEY,
    fecha DATE NOT NULL,
    monto INT NOT NULL,
    metodo_pago VARCHAR(50) NOT NULL,
    consulta_id INT NOT NULL,

    CONSTRAINT fk_pago_consulta
        FOREIGN KEY (consulta_id)
        REFERENCES consulta(id)
);