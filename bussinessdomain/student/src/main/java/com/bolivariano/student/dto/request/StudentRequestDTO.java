package com.bolivariano.student.dto.request;

import com.bolivariano.student.entities.Student;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class StudentRequestDTO {
    private String nameStudent;  // Nombre del estudiante
    private String codeStudent;  // Código del estudiante (nuevo campo)
    private Long courseId;  // Id del curso asociado al estudiante

    // Método de conversión a entidad Student
    public Student toEntity() {
        Student student = new Student();
        student.setNameStudent(this.nameStudent);
        student.setCodeStudent(this.codeStudent);  // Asignando el código del estudiante
        // Si se requiere asociar el curso, puedes agregar la lógica aquí para encontrar el curso por ID
        return student;
    }
}
