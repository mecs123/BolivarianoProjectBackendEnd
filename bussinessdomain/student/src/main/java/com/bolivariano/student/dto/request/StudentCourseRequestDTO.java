package com.bolivariano.student.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class StudentCourseRequestDTO {
    private String nameStudent;  // Nombre del estudiante
    private String codeStudent;  // Código del estudiante (nuevo campo)
    private List<CoursesStudent> studentCourses;  // Ids de los cursos asociados al estudiante
}
