CREATE TABLE region (
    id INT AUTO_INCREMENT PRIMARY KEY,
    nombre_region VARCHAR(40) NOT NULL UNIQUE
);

CREATE TABLE comuna (
    id INT AUTO_INCREMENT PRIMARY KEY,
    nombre_comuna VARCHAR(60) NOT NULL,
    region_id INT NOT NULL,
    CONSTRAINT fk_comuna_region
        FOREIGN KEY (region_id)
        REFERENCES region(id)
);

INSERT INTO region(nombre_region)
VALUES ('Metropolitana'),('Valparaiso');

INSERT INTO comuna(nombre_comuna, region_id)
VALUES
('Maipu', 1),
('Providencia', 1),
('viña del mar', 2),
('San antonio', 2);
