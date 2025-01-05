package com.bolivariano.teacher.dto.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * DTO para la respuesta de información sobre las asignaturas del profesor.
 * Este objeto es utilizado para enviar los detalles de las asignaturas asociadas a un profesor en la respuesta.
 *
 * @author AsusTUF
 */
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
@NoArgsConstructor
@AllArgsConstructor
@Data
@Schema(description = "DTO para la respuesta de información sobre las asignaturas del profesor")
public class TeacherSubjectResponseDto {

    @Schema(description = "ID de la asignatura asociada al profesor", example = "10101")
    private long idSubject;

    @Schema(description = "Nombre de la asignatura", example = "Physics")
    private String nameSubject;
}
