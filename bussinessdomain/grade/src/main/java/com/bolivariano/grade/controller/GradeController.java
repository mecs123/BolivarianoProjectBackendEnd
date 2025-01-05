package com.bolivariano.grade.controller;

import com.bolivariano.grade.dto.request.GradeRequestDTO;
import com.bolivariano.grade.dto.response.GradeResponseDTO;
import com.bolivariano.grade.entities.Grade;
import com.bolivariano.grade.service.GradeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/grades")
public class GradeController {

    @Autowired
    private GradeService gradeService;

    /**
     * Get all grades
     */
    @GetMapping
    public ResponseEntity<List<GradeResponseDTO>> getAllGrades() {
        List<Grade> grades = gradeService.findAllGrades();
        List<GradeResponseDTO> response = grades.stream()
                .map(GradeResponseDTO::fromEntity)
                .toList();
        return ResponseEntity.ok(response);
    }

    /**
     * Get a grade by ID
     */
    @GetMapping("/{id}")
    public ResponseEntity<GradeResponseDTO> getGradeById(@PathVariable Long id) {
        Optional<Grade> grade = gradeService.findGradeById(id);
        return grade.map(value -> ResponseEntity.ok(GradeResponseDTO.fromEntity(value)))
                .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }

    /**
     * Save a new grade
     */
    @PostMapping
    public ResponseEntity<GradeResponseDTO> saveGrade(
            @RequestBody GradeRequestDTO gradeRequestDTO
    ) {
        Grade grade = gradeRequestDTO.toEntity();
        Grade savedGrade = gradeService.saveGrade(grade);
        return ResponseEntity.status(HttpStatus.CREATED).body(GradeResponseDTO.fromEntity(savedGrade));
    }

    /**
     * Update an existing grade
     */
    @PutMapping("/{id}")
    public ResponseEntity<GradeResponseDTO> updateGrade(@PathVariable Long id, @RequestBody GradeRequestDTO gradeRequestDTO) {
        Optional<Grade> updatedGrade = gradeService.updateGrade(id, gradeRequestDTO.toEntity());
        return updatedGrade.map(value -> ResponseEntity.ok(GradeResponseDTO.fromEntity(value)))
                .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }

    /**
     * Delete a grade by ID
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteGrade(@PathVariable Long id) {
        boolean deleted = gradeService.deleteGradeById(id);
        return deleted ? ResponseEntity.status(HttpStatus.NO_CONTENT).build()
                : ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }
}