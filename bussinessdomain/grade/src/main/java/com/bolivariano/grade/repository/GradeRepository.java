package com.bolivariano.grade.repository;

import com.bolivariano.grade.entities.Grade;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GradeRepository extends JpaRepository<Grade, Long> {
}