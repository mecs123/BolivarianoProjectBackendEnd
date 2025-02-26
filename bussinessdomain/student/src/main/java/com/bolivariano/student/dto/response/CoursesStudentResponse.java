package com.bolivariano.student.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class CoursesStudentResponse {
    private Long idCourse;
    private String nameCourse;
    private String codeCourse;

}
