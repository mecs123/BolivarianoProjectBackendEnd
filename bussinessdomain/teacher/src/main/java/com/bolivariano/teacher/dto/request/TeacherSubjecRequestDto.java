package com.bolivariano.teacher.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class TeacherSubjecRequestDto {
    @Schema(description = "ID de la materia asociado al profesor", example = "1")
    private long idSubject;
    @Schema(description = "Nombre de la materia asociado al profesor", example = "Ingles")
    private String nameSubject;


}