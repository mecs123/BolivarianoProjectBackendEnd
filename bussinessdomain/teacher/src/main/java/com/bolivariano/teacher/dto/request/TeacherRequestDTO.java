package com.bolivariano.teacher.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Set;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class TeacherRequestDTO {

    @Schema(description = "Codigo asignado del profesor", example = "T001")
    @NotBlank(message = "El código del profesor no puede estar vacío")
    @Size(max = 10, message = "El código del profesor no puede superar los 10 caracteres")
    private String codTeacher;

    @Schema(description = "Nombre del profesor", example = "Juan")
    @NotBlank(message = "El nombre del profesor no puede estar vacío")
    @Size(max = 50, message = "El nombre del profesor no puede superar los 50 caracteres")
    private String nameTeacher;

    @Schema(description = "Estado del profesor", example = "true")
    private boolean estado;

    private Set<TeacherSubjecRequestDto> teacherSubjectRequestDto;

    private Set<TeacherCourseRequestDto> teacherCourseRequestDto;


}