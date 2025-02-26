package com.bolivariano.student.dto.request;

import com.bolivariano.student.entities.Student;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;


@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class StudentRequestDTO {

    @NotBlank
    private String nameStudent;  // Nombre del estudiante
    private String codeStudent;  // Código del estudiante (nuevo campo)
    private String email;  // Correo del estudiante
    @NotNull
    private boolean estado;  // Estado del estudiante
    private String courseName;  // Nombre del curso asociado al estudiante
    @NotBlank
    private String courseCode;  // Código del curso asociado al estudiante

    public Student toEntity() {
        return Student.builder()
                .nameStudent(nameStudent)
                .codeStudent(codeStudent)
                .emailStudent(email)
                .estadoStudent(estado)
                .courseName(courseName)
                .courseCode(courseCode)
                .build();
    }
}
