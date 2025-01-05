-- Crear la tabla 'course' si no existe
CREATE TABLE IF NOT EXISTS course (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    nameCourse VARCHAR(255) NOT NULL
);

-- Insertar datos en la tabla 'course' (cursos de Primero a Once)
INSERT INTO course (nameCourse) VALUES ('Primero');
INSERT INTO course (nameCourse) VALUES ('Segundo');
INSERT INTO course (nameCourse) VALUES ('Tercero');
INSERT INTO course (nameCourse) VALUES ('Cuarto');
INSERT INTO course (nameCourse) VALUES ('Quinto');
INSERT INTO course (nameCourse) VALUES ('Sexto');
INSERT INTO course (nameCourse) VALUES ('Séptimo');
INSERT INTO course (nameCourse) VALUES ('Octavo');
INSERT INTO course (nameCourse) VALUES ('Noveno');
INSERT INTO course (nameCourse) VALUES ('Décimo');
INSERT INTO course (nameCourse) VALUES ('Once');
