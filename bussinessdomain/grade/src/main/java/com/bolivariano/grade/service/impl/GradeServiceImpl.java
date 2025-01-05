package com.bolivariano.grade.service.impl;


import com.bolivariano.grade.entities.Grade;
import com.bolivariano.grade.repository.GradeRepository;
import com.bolivariano.grade.service.GradeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class GradeServiceImpl implements GradeService {

    @Autowired
    private GradeRepository gradeRepository;

    @Override
    public List<Grade> findAllGrades() {
        return gradeRepository.findAll();
    }

    @Override
    public Optional<Grade> findGradeById(Long id) {
        return gradeRepository.findById(id);
    }

    @Override
    public Grade saveGrade(Grade grade) {
        return gradeRepository.save(grade);
    }

    @Override
    public Optional<Grade> updateGrade(Long id, Grade grade) {
        return gradeRepository.findById(id).map(existingGrade -> {
            existingGrade.setValueGrade(grade.getValueGrade());
            return gradeRepository.save(existingGrade);
        });
    }

    @Override
    public boolean deleteGradeById(Long id) {
        if (gradeRepository.existsById(id)) {
            gradeRepository.deleteById(id);
            return true;
        }
        return false;
    }
}