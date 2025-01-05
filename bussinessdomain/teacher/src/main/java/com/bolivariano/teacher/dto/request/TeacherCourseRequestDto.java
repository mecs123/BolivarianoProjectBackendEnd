package com.bolivariano.teacher.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * DTO para la solicitud de información sobre el curso del profesor.
 * Este objeto es utilizado para recibir los detalles de un curso asociado a un profesor.
 *
 * @author AsusTUF
 */
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
@Schema(description = "DTO para la solicitud de información sobre el curso del profesor")
public class TeacherCourseRequestDto {

    @Schema(description = "ID del curso asociado al profesor", example = "1")
    private long idCourse;
    @Schema(description = "Nombre del curso", example = "Once")
    private String nameCourse;
}
