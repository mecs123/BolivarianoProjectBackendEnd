package com.bolivariano.student.service;

import com.bolivariano.student.entities.Student;

import java.util.List;
import java.util.Optional;

public interface StudentService {
    List<Student> findAllStudents();
    Optional<Student> findStudentById(Long id);
    Student saveStudent(Student student);
    Optional<Student> updateStudent(Long id, Student student);
    boolean deleteStudentById(Long id);
}
