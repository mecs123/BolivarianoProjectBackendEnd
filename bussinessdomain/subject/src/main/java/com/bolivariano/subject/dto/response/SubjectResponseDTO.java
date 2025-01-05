package com.bolivariano.subject.dto.response;

import com.bolivariano.subject.entities.SubjectCollege;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * DTO class to represent a response with Subject details.
 */
@AllArgsConstructor
@NoArgsConstructor
@Data
public class SubjectResponseDTO {

    private Long id;
    private String nameSubject;


    /**
     * Converts the given SubjectCollege entity to a SubjectResponseDTO.
     *
     * @param subject The SubjectCollege entity.
     * @return The converted SubjectResponseDTO.
     */
    public static SubjectResponseDTO fromEntityDtoToSubject(SubjectCollege subject) {
        return new SubjectResponseDTO(
                subject.getId(),
                subject.getNameSubject()
        );
    }
}