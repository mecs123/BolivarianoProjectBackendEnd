package com.bolivariano.period.dto.response;

import com.bolivariano.period.entities.Period;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class PeriodResponseDTO {
    private Long id;
    private String name;  // Name of the period

    // Convert from entity
    public static PeriodResponseDTO fromEntity(Period period) {
        PeriodResponseDTO dto = new PeriodResponseDTO();
        dto.setId(period.getId());
        dto.setName(period.getName());
        return dto;
    }
}