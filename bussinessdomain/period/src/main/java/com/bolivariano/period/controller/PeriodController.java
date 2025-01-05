package com.bolivariano.period.controller;

import com.bolivariano.period.dto.request.PeriodRequestDTO;
import com.bolivariano.period.dto.response.PeriodResponseDTO;
import com.bolivariano.period.entities.Period;
import com.bolivariano.period.service.PeriodService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/periods")
public class PeriodController {

    @Autowired
    private PeriodService periodService;

    /**
     * Get all periods
     */
    @GetMapping
    public ResponseEntity<List<PeriodResponseDTO>> getAllPeriods() {
        List<Period> periods = periodService.findAllPeriods();
        List<PeriodResponseDTO> response = periods.stream()
                .map(PeriodResponseDTO::fromEntity)
                .toList();
        return ResponseEntity.ok(response);
    }

    /**
     * Get a period by ID
     */
    @GetMapping("/{id}")
    public ResponseEntity<PeriodResponseDTO> getPeriodById(@PathVariable Long id) {
        Optional<Period> period = periodService.findPeriodById(id);
        return period.map(value -> ResponseEntity.ok(PeriodResponseDTO.fromEntity(value)))
                .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }

    /**
     * Save a new period
     */
    @PostMapping
    public ResponseEntity<PeriodResponseDTO> savePeriod(@RequestBody PeriodRequestDTO periodRequestDTO) {
        Period period = periodRequestDTO.toEntity();
        Period savedPeriod = periodService.savePeriod(period);
        return ResponseEntity.status(HttpStatus.CREATED).body(PeriodResponseDTO.fromEntity(savedPeriod));
    }

    /**
     * Update an existing period
     */
    @PutMapping("/{id}")
    public ResponseEntity<PeriodResponseDTO> updatePeriod(@PathVariable Long id, @RequestBody PeriodRequestDTO periodRequestDTO) {
        Optional<Period> updatedPeriod = periodService.updatePeriod(id, periodRequestDTO.toEntity());
        return updatedPeriod.map(value -> ResponseEntity.ok(PeriodResponseDTO.fromEntity(value)))
                .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }

    /**
     * Delete a period by ID
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePeriod(@PathVariable Long id) {
        boolean deleted = periodService.deletePeriodById(id);
        return deleted ? ResponseEntity.status(HttpStatus.NO_CONTENT).build()
                : ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }
}