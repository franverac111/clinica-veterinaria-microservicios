-- ============================================
-- TABLA ESPECIE
-- ============================================

CREATE TABLE especie (
    id INT AUTO_INCREMENT PRIMARY KEY,
    nombre_especie VARCHAR(40) NOT NULL
);

-- ============================================
-- TABLA RAZA
-- ============================================

CREATE TABLE raza (
    id INT AUTO_INCREMENT PRIMARY KEY,
    nombre_raza VARCHAR(40) NOT NULL
);

-- ============================================
-- TABLA DUENO
-- ============================================

CREATE TABLE dueno (
    id INT AUTO_INCREMENT PRIMARY KEY,
    rut VARCHAR(13) NOT NULL UNIQUE,
    nombre VARCHAR(60) NOT NULL,
    telefono VARCHAR(12) NOT NULL,
    direccion VARCHAR(80) NOT NULL,
    mail VARCHAR(80) NOT NULL
);

-- ============================================
-- TABLA MASCOTA
-- ============================================

CREATE TABLE mascota (
    id INT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(100) NOT NULL,
    edad INT NOT NULL,
    sexo VARCHAR(10) NOT NULL,

    especie_id INT NOT NULL,
    raza_id INT NULL,
    dueno_id INT NOT NULL,

    CONSTRAINT fk_mascota_especie
        FOREIGN KEY (especie_id)
        REFERENCES especie(id),

    CONSTRAINT fk_mascota_raza
        FOREIGN KEY (raza_id)
        REFERENCES raza(id),

    CONSTRAINT fk_mascota_dueno
        FOREIGN KEY (dueno_id)
        REFERENCES dueno(id)
);


