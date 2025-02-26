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
    private String codeStudent;
    private String nameStudent;
    private String email;
    private boolean estado;
    private String courseCode;
    private String courseName;  // Asumiendo que un estudiante tiene un curso asociado


    public static StudentResponseDTO fromEntity(Student student) {
        StudentResponseDTO dto = new StudentResponseDTO();

        dto.setId(student.getId());
        dto.setCodeStudent(student.getCodeStudent());
        dto.setNameStudent(student.getNameStudent());
        dto.setEmail(student.getEmailStudent());
        dto.setEstado(student.isEstadoStudent());
        dto.setCourseCode(student.getCourseCode());
        dto.setCourseName(student.getCourseName());

        return dto;
    }
}
