package com.bolivariano.student.service;

import com.bolivariano.student.dto.request.StudentCourseRequestDTO;
import com.bolivariano.student.dto.request.StudentRequestDTO;
import com.bolivariano.student.dto.response.StudentCourseResponseDTO;
import com.bolivariano.student.dto.response.StudentResponseDTO;
import com.bolivariano.student.entities.Student;

import java.util.List;
import java.util.Optional;

public interface StudentService {
    List<Student> findAllStudents();
    Optional<Student> findStudentById(Long id);
    StudentResponseDTO saveStudent(StudentRequestDTO student);
    Optional<Student> updateStudent(Long id, Student student);
    boolean deleteStudentById(Long id);

    StudentCourseResponseDTO retriveStudenWithCourse(Long id, StudentCourseRequestDTO studentCourseRequestDTO);
}
