package com.bolivariano.grade.service;

import com.bolivariano.grade.entities.Grade;

import java.util.List;
import java.util.Optional;

public interface GradeService {
    List<Grade> findAllGrades();
    Optional<Grade> findGradeById(Long id);
    Grade saveGrade(Grade grade);
    Optional<Grade> updateGrade(Long id, Grade grade);
    boolean deleteGradeById(Long id);
}