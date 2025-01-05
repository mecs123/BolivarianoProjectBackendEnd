package com.bolivariano.teacher.entities;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

import java.util.HashSet;
import java.util.Set;

import lombok.*;

/**
 * Representa un Profesor (Teacher) con su información básica y las relaciones
 * con los asignaturas (TeacherSubject) y cursos (TeacherCourse).
 *
 * Esta entidad es utilizada para almacenar y gestionar los datos de los profesores
 * en la base de datos.
 *
 * @author AsusTUF
 */
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Schema(description = "Entidad que representa a un profesor y sus asignaturas y cursos")
@Table(name = "TEACHER") // Asegúrate de que el nombre sea correcto
public class Teacher {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Schema(description = "ID único del profesor", example = "1")
    private Long id;

    @Schema(description = "Código único del profesor", example = "T001")
    private String codTeacher;

    @Schema(description = "Nombre completo del profesor", example = "John Doe")
    private String nameTeacher;

    @Schema(description = "Estado del profesor", example = "true")
    private boolean estado;


    @ToString.Exclude
    @JsonManagedReference
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "teacher", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<TeacherSubject> listSubjectByTeacher;

    @ToString.Exclude
    @JsonManagedReference
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "teacher", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<TeacherCourse> teacherCourseList = new HashSet<>();


}