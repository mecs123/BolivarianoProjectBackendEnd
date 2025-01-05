package com.bolivariano.subject.controller;

import com.bolivariano.subject.dto.request.SubjectRequestDTO;
import com.bolivariano.subject.dto.response.SubjectResponseDTO;
import com.bolivariano.subject.entities.SubjectCollege;
import com.bolivariano.subject.service.SubjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Controller
@RestController
@RequestMapping("/subject")
public class SubjectRestController {


    @Autowired
    private SubjectService subjectService;

    /**
     * Endpoint to get a listSubject of all subjects.
     * @return a list of all subjects.
     */
    @GetMapping("/all")
    public List<SubjectResponseDTO> listSubject(
    ) {
        List<SubjectCollege> subjects = subjectService
                .findAllSubjects();
        List<SubjectResponseDTO> response = subjects
                .stream()
                .map(SubjectResponseDTO::fromEntityDtoToSubject)
                .collect(Collectors.toList());
        return response;
    }

    /**
     * Endpoint to get a subject by its ID.
     * @param id the ID of the subject.
     * @return the subject if found, or a NOT FOUND response.
     */
    @GetMapping("/{id}")
    public ResponseEntity<SubjectCollege> get(
            @PathVariable("id")
            Long id
    ) {
        Optional<SubjectCollege> subject = subjectService
                .findSubjectById(id);

        if (subject.isPresent()) {
            return ResponseEntity.ok(subject.get());
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }

    /**
     * Endpoint to update a subject.
     * @param id the ID of the subject to be updated.
     * @param input the updated subject data.
     * @return the updated subject.
     */
    @PutMapping("/{id}")
    public ResponseEntity<?> put(
            @PathVariable Long id,
            @RequestBody SubjectCollege input
    ) {
        try {
            SubjectCollege updatedSubject = subjectService.updateSubject(id, input);
            return ResponseEntity.ok(updatedSubject);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }


    /**
     * Endpoint to create a new subject.
     * @param input the subject to be created.
     * @return the created subject.
     */
    @PostMapping
    public ResponseEntity<SubjectResponseDTO> post(
            @RequestBody SubjectRequestDTO input
    ) {
        try {
            SubjectCollege subject = new SubjectCollege();
            subject.setNameSubject(input.getNameSubject());
            // Guardar la asignatura en la base de datos
            SubjectCollege savedSubject = subjectService.createSubject(subject);

            // Convertir la entidad guardada a un DTO de respuesta
            SubjectResponseDTO response = SubjectResponseDTO.fromEntityDtoToSubject(savedSubject);

            // Devolver la respuesta con un código HTTP 200 (OK)
            return ResponseEntity.ok(response);


        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }
    }

    /**
     * Endpoint to delete a subject by its ID.
     * @param id the ID of the subject to be deleted.
     * @return a success or error response.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        try {
            subjectService.deleteSubject(id);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

}