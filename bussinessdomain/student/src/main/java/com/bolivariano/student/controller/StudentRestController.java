package com.bolivariano.student.controller;

import com.bolivariano.student.dto.request.StudentRequestDTO;
import com.bolivariano.student.dto.response.StudentResponseDTO;
import com.bolivariano.student.entities.Student;
import com.bolivariano.student.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/students")
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
        Student student = studentService.saveStudent(requestDTO.toEntity());
        return ResponseEntity.ok(StudentResponseDTO.fromEntity(student));
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
            @PathVariable Long id
    ) {
        if (studentService.deleteStudentById(id)) {
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
