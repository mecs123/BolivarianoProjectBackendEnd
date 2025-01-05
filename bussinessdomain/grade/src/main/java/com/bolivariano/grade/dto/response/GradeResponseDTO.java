package com.bolivariano.grade.dto.response;

import com.bolivariano.grade.entities.Grade;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class GradeResponseDTO {
    private Long id;
    private Double valueGrade;  // Grade value
    private Long studentId;  // ID of the student
    private Long periodId;   // ID of the period

    // Convert from entity
    public static GradeResponseDTO fromEntity(Grade grade) {
        GradeResponseDTO dto = new GradeResponseDTO();
        dto.setId(grade.getId());
        dto.setValueGrade(grade.getValueGrade());
        return dto;
    }
}