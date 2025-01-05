package com.bolivariano.grade.dto.request;

import com.bolivariano.grade.entities.Grade;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class GradeRequestDTO {
    private Double valueGrade;  // Grade value

    private Long studentId;  // ID of the student to associate the grade with

    private Long periodId;   // ID of the period for the grade

    // Convert to entity
    public Grade toEntity() {
        Grade grade = new Grade();
        grade.setValueGrade(this.valueGrade);
        // You can add logic here to set the student and period based on their IDs
        return grade;
    }
}
