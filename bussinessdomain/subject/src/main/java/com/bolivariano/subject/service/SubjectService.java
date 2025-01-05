package com.bolivariano.subject.service;

import com.bolivariano.subject.entities.SubjectCollege;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


public interface SubjectService {

    List<SubjectCollege> findAllSubjects();

    Optional<SubjectCollege> findSubjectById(Long id);

    SubjectCollege createSubject(SubjectCollege subject);

    SubjectCollege updateSubject(Long id, SubjectCollege subject);

    void deleteSubject(Long id);
}