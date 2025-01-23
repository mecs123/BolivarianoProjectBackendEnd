package com.bolivariano.teacher.dto.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Builder
@JsonInclude(JsonInclude.Include.NON_EMPTY) // Excluye listas vacías y valores nulos
@NoArgsConstructor
@AllArgsConstructor
@Data
public class TeacherResponseDTO {
    private Long id;
    private String nameTeacher;
    private String codTeacher;
    private boolean estado;
    private Set<TeacherSubjectResponseDto> teacherSubjectResponseDto;
    private Set<TeacherCourseResponseDto> teacherCourseResponseDto;

}
