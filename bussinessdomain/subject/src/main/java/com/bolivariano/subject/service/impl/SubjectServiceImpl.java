package com.bolivariano.subject.service.impl;

import com.bolivariano.subject.entities.SubjectCollege;
import com.bolivariano.subject.repository.SubjectRepository;
import com.bolivariano.subject.service.SubjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Service
public class SubjectServiceImpl implements SubjectService {

    @Autowired
    private SubjectRepository subjectRepository;

    /**
     * Method that returns all subjects.
     * @return list of all subjects.
     */
    @Override
    public List<SubjectCollege> findAllSubjects() {
        return subjectRepository.findAll(); // Returns all subjects from the repository.
    }


    /**
     * Method to find a subject by its ID.
     * @param id The ID of the subject to be found.
     * @return an Optional containing the subject if found, or an empty Optional.
     */
    @Override
    public Optional<SubjectCollege> findSubjectById(Long id) {
        return subjectRepository.findById(id); // Finds the subject by ID from the repository.
    }


    /**
     * Method to create a new subject.
     * @param subject The SubjectCollege object containing the new subject.
     * @return the newly created subject.
     */
    @Override
    public SubjectCollege createSubject(SubjectCollege subject) {
        return subjectRepository.save(subject); // Saves the subject to the database.
    }


    /**
     * Method to update an existing subject.
     * @param id The ID of the subject to be updated.
     * @param subject The SubjectCollege object with the updated data.
     * @return the updated subject.
     */
    @Override
    public SubjectCollege updateSubject(Long id, SubjectCollege subject) {
        // First, check if the subject exists before updating.
        if (subject==null){
            throw new IllegalArgumentException("Subject cannot be null.");
        }

        // Check if the subject exists in the database
        Optional<SubjectCollege> existingSubject = subjectRepository.findById(id);
        if (!existingSubject.isPresent()) {
            throw new IllegalArgumentException("Subject with ID " + id + " does not exist.");
        }
        // Set the ID of the subject to ensure it is not overwritten
        subject.setId(id);

        // Save the updated subject to the database
        return subjectRepository.save(subject);
    }

    /**
     * @param id
     */
    @Override
    public void deleteSubject(Long id) {
        // Check if the subject exists
        Optional<SubjectCollege> existingSubject = subjectRepository.findById(id);
        if (!existingSubject.isPresent()) {
            throw new IllegalArgumentException("Subject with ID " + id + " does not exist.");
        }

        // Delete the subject
        subjectRepository.deleteById(id);
    }



}
