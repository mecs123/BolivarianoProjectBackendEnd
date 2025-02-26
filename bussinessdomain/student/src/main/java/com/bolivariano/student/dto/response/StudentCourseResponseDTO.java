package com.bolivariano.student.dto.response;

import com.bolivariano.student.dto.request.CoursesStudent;
import com.bolivariano.student.entities.Student;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class StudentCourseResponseDTO {
    private Long id;
    private String nameStudent;
    private List<CoursesStudentResponse> coursesStudentResponses;

    public static StudentCourseResponseDTO fromEntity(Student student) {
        return StudentCourseResponseDTO.builder()
                .id(student.getId())
                .nameStudent(student.getNameStudent())
                .build();
    }
}
