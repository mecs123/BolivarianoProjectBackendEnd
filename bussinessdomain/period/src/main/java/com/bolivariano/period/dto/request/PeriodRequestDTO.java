package com.bolivariano.period.dto.request;

import com.bolivariano.period.entities.Period;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class PeriodRequestDTO {
    private String name;  // Name of the period (e.g., "Semester 1")

    // Convert to entity
    public Period toEntity() {
        Period period = new Period();
        period.setName(this.name);
        return period;
    }
}