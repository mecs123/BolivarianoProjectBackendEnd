-- Crear la tabla 'subject' si no existe
CREATE TABLE IF NOT EXISTS subject (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    nameSubject VARCHAR(255) NOT NULL
);

-- Insertar datos en la tabla 'subject' con materias básicas
INSERT INTO subject (nameSubject) VALUES ('Matemáticas');
INSERT INTO subject (nameSubject) VALUES ('Español');
INSERT INTO subject (nameSubject) VALUES ('Ciencias Naturales');
INSERT INTO subject (nameSubject) VALUES ('Ciencias Sociales');
INSERT INTO subject (nameSubject) VALUES ('Inglés');
INSERT INTO subject (nameSubject) VALUES ('Ética');
INSERT INTO subject (nameSubject) VALUES ('Educación Física');
INSERT INTO subject (nameSubject) VALUES ('Arte');
INSERT INTO subject (nameSubject) VALUES ('Tecnología e Informática');
