package com.bolivariano.student.dto.response;

import com.bolivariano.student.entities.Student;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class StudentResponseDTO {
    private Long id;
    private String name;
    private String courseName;  // Asumiendo que un estudiante tiene un curso asociado
    private String codeStudent;  // Nuevo campo

    public static StudentResponseDTO fromEntity(Student student) {
        StudentResponseDTO dto = new StudentResponseDTO();
        dto.setId(student.getId());
        dto.setName(student.getNameStudent());
        dto.setCodeStudent(student.getCodeStudent());
        return dto;
    }
}
