Use aulas;
CREATE TABLE campus (
    id INT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(255) NOT NULL
);
CREATE TABLE ubicacion (
    id INT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(255) NOT NULL,
    campus_id INT,
    FOREIGN KEY (campus_id) REFERENCES campus(id)
);

-- Insertar el campus "Obregon Nainari"
INSERT INTO campus (nombre) VALUES ('Obregon Nainari');

-- Obtener el ID del campus "Obregon Nainari" insertado
SET @campusId := LAST_INSERT_ID();

-- Insertar el campus "Centro"
INSERT INTO campus (nombre) VALUES ('Centro');

-- Obtener el ID del campus "Centro" insertado
SET @campusId := LAST_INSERT_ID();

-- Insertar las ubicaciones para el campus "Obregon Nainari"
INSERT INTO ubicacion (nombre, campus_id) VALUES
('AV0200 Aula Integracion Proyeccion', 1),  -- El número 1 representa el ID del campus "Obregon Nainari"
('AV0300 Aula Integracion Proyeccion', 1),
('AV0400 Aula Integracion Proyeccion', 1),
('AV0500 Aula Integracion Proyeccion', 1),
('AV0600 Aula Integracion Proyeccion', 1),
('AV0700 Aula Integracion Proyeccion', 1),
('AV0800 Aula Integracion Proyeccion', 1),
('AV0900 Aula Audiovisual', 1),
('AV1000 Aula Clase', 1),
('AV1100 Aula Clase', 1),
('AV1200 Aula Clase', 1),
('AV1300 Aula Clase', 1),
('AV1400 Aula Clase', 1),
('AV1500 Aula Clase', 1),
('AV1600 Aula Clase', 1),
('AV1700 Diseño Grafico', 1),
('AV1800 Aulas y Cubiculos', 1),
('AV3000 Cultural', 1),
('AV4000 Cultural', 1),
('DV0300 Dibujo', 1),
('EV0100 Idiomas', 1),
('IV1000 Alberca', 1),
('IV2000 Cancha de tenis', 1),
('IV3000 Pista Atletica', 1),
('IV4000 Estadio de Beisbol', 1),
('IV5000 Campo de Futbol', 1),
('IV6000 Cancha Futbol Rapido', 1),
('IV7000 Polideportivo', 1),
('IV8000 Cancha de Baloncesto AL', 1),
('IVT100 Tutorias', 1),
('LV0100 Laboratorio Necropsias', 1),
('LV0200 Lab. Bacteriologia', 1),
('LV0500 Lab. Quimica Organica', 1),
('LV0700 Lab. de Alimentos', 1),
('LV0800 Lab. Hidraulica Canales', 1),
('LV0900 Lab.Ing. Quimica', 1),
('LV1000 Aula Interactiva Computo', 1),
('LV1100 Lab. Comunicaciones', 1),
('LV1200 Lab. Ing. de Metodos', 1),
('LV1300 Lab. Alimentos y Bebidas', 1); -- Última ubicación para "Obregon Nainari"

-- Insertar las ubicaciones para el campus "Centro"
INSERT INTO ubicacion (nombre, campus_id) VALUES
('A-0122 Aula Integral Proyeccion', 2),  -- El número 2 representa el ID del campus "Centro"
('A-0123 Aula Integral Proyeccion', 2),
('A-0124 Aula Integral Proyeccion', 2),
('A-0126 Aula Integral Proyeccion', 2),
('A-0127 Aula Integral Proyeccion', 2),
('A-0129 Aula Integral Proyeccion', 2),
('A-0131 Aula Integral Proyeccion', 2),
('A-0132 Aula Integral Proyeccion', 2),
('A-0133 Aula Clase', 2),
('A-0134 Aula Clase', 2),
('A-0137 Aula Clase', 2),
('A-0138 Aula Clase', 2),
('A-0139 Aula Clase', 2),
('A-0221 Aula Clase', 2),
('A-0222 Aula Clase', 2),
('A-0223 Aula Clase', 2),
('A-0224 Aula Clase', 2),
('A-0232 Lab. Intervencion Psicol', 2),
('A-0233 Aula Clase', 2),
('A-0234 Aula Clase', 2),
('A-0235 Aula Clase', 2),
('A-0236 Aula Clase', 2),
('A-0237 Aula Clase', 2),
('A-0321 Aula Clase', 2),
('A-0322 Aula Clase', 2),
('A-0323 Aula Clase', 2),
('A-0324 Aula Clase', 2),
('A-0331 Aula Clase', 2),
('A-0332 Aula Clase', 2),
('A-0333 Aula Clase', 2),
('A-0334 Aula Clase', 2),
('A-0411 Aula Interactiva Computo', 2),
('A-0412 Aula Interactiva Computo', 2),
('A-0421 Aula Clase', 2),
('A-0431 Aula Clase', 2),
('A-0432 Aula Clase', 2),
('A-0433 Aula Clase', 2),
('A-0811 Posgrado', 2),
('A-0812 Posgrado', 2),
('A-0813 Posgrado', 2),
('A-0814 Posgrado', 2),
('AG0121 Aula Integral Proyeccion', 2),
('AV0721 Aula Integral Proyeccion', 2),
('CITIEC Aula 111 Sotano', 2),
('CRAP Aula 1012', 2),
('CRAP Aula 1022', 2),
('Trabajo en Campo', 2),
('Galeria Itson Sala 4', 2),
('IVC-BASVOL-Cancha Centro', 2),
('Laboratorio de CIIBA', 2),
('Laboratorio de Ecodesarollo', 2),
('LV-0500 Laboratorio de sueldos', 2),
('LV1300 Lab. Alimentos y Bebidas', 2); -- Última ubicación para "Centro"