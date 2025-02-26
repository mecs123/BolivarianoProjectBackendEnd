package com.bolivariano.student.controller;

import com.bolivariano.student.dto.request.StudentCourseRequestDTO;
import com.bolivariano.student.dto.request.StudentRequestDTO;
import com.bolivariano.student.dto.response.StudentCourseResponseDTO;
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
@RequestMapping("api/v1/students/course")
public class StudentAssingCourseController {

    @Autowired
    private StudentService studentService;



    @GetMapping("/{id}")
    public ResponseEntity<StudentCourseResponseDTO> getStudentCourseById(
            @PathVariable("id") Long id,
            @RequestBody(required = true)StudentCourseRequestDTO studentCourseRequestDTO
    ) {
        StudentCourseResponseDTO student = studentService.retriveStudenWithCourse(id, studentCourseRequestDTO);
        return ResponseEntity.ok(student);
    }




    @GetMapping("/all")
    public ResponseEntity<List<StudentResponseDTO>> listAllStudents(

    ) {
        List<Student> students = studentService.findAllStudents();

        List<StudentResponseDTO> response = students.stream()
                .map(StudentResponseDTO::fromEntity)
                .collect(Collectors.toList());

        return ResponseEntity.ok(response);
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
