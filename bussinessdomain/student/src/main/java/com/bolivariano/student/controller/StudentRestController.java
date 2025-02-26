package com.bolivariano.student.controller;

import com.bolivariano.student.dto.request.StudentRequestDTO;
import com.bolivariano.student.dto.response.StudentResponseDTO;
import com.bolivariano.student.entities.Student;
import com.bolivariano.student.exception.StudentAlreadyExistsException;
import com.bolivariano.student.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("api/v1/students")
public class StudentRestController {

    @Autowired
    private StudentService studentService;

    @GetMapping("/all")
    public ResponseEntity<List<StudentResponseDTO>> listAllStudents(

    ) {
        List<Student> students = studentService.findAllStudents();

        List<StudentResponseDTO> response = students.stream()
                .map(StudentResponseDTO::fromEntity)
                .collect(Collectors.toList());

        return ResponseEntity.ok(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<StudentResponseDTO> getStudentById(
            @PathVariable("id") Long id
    ) {
        Optional<Student> student = studentService.findStudentById(id);
        return student
                .map(s -> ResponseEntity.ok(StudentResponseDTO.fromEntity(s)))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<StudentResponseDTO> createStudent(
            @RequestBody StudentRequestDTO requestDTO
    ) {
        try {
            // Guarda el estudiante y obtiene el DTO de respuesta

            StudentResponseDTO studentCreate = studentService.saveStudent(requestDTO);

            // Retorna la respuesta con el código 201 si todo está bien
            return ResponseEntity.status(201).body(studentCreate);
        } catch (StudentAlreadyExistsException e) {
            // Manejo del caso cuando el estudiante ya existe
            return ResponseEntity.status(400).body(null); // Aquí puedes devolver un DTO con el mensaje de error si lo deseas
        } catch (Exception e) {
            // Manejo de excepciones generales
            return ResponseEntity.status(500).body(null); // O puedes manejar el error de una mejor manera
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<StudentResponseDTO> updateStudent(
            @PathVariable Long id,
            @RequestBody StudentRequestDTO requestDTO
    ) {
        Optional<Student> updatedStudent = studentService.updateStudent(id, requestDTO.toEntity());
        return updatedStudent
                .map(s -> ResponseEntity.ok(StudentResponseDTO.fromEntity(s)))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteStudent(
            @PathVariable("id") Long id
    ) {
        if (studentService.deleteStudentById(id)) {
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
