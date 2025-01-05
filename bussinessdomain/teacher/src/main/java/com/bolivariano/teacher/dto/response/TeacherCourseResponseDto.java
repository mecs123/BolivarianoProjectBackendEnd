package com.bolivariano.teacher.dto.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * DTO para la respuesta de información sobre el curso del profesor.
 * Este objeto es utilizado para enviar los detalles de un curso asociado a un profesor en la respuesta.
 *
 * @author AsusTUF
 */
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
@NoArgsConstructor
@AllArgsConstructor
@Data
@Schema(description = "DTO para la respuesta de información sobre el curso del profesor")
public class TeacherCourseResponseDto {

    @Schema(description = "ID del curso asociado al profesor", example = "1")
    private long idCourse;

    @Schema(description = "Nombre del curso", example = "Mathematics")
    private String nameCourse;

}
