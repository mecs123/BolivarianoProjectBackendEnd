package com.bolivariano.student.service.impl;

import com.bolivariano.student.entities.Student;
import com.bolivariano.student.repository.StudentRepository;
import com.bolivariano.student.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class StudentServiceImpl implements StudentService {

    @Autowired
    private StudentRepository studentRepository;

    /**
     * @return List<Student>
     */
    @Override
    public List<Student> findAllStudents() {
        return studentRepository.findAll();
    }

    /**
     * @param id
     * @return Optional<Student>
     */
    @Override
    public Optional<Student> findStudentById(Long id) {
        return studentRepository.findById(id);
    }

    /**
     * @param student
     * @return Student
     */
    @Override
    public Student saveStudent(Student student) {
        return studentRepository.save(student);
    }

    /**
     * @param id
     * @param student
     * @return Optional<Student>
     */
    @Override
    public Optional<Student> updateStudent(Long id, Student student) {
        return studentRepository.findById(id).map(existingStudent -> {
            existingStudent.setNameStudent(student.getNameStudent());
            existingStudent.setCodeStudent(student.getCodeStudent());  // Asumiendo que tienes una relación con el curso
            return studentRepository.save(existingStudent);
        });
    }

    /**
     * @param id
     * @return boolean
     */
    @Override
    public boolean deleteStudentById(Long id) {
        if (studentRepository.existsById(id)) {
            studentRepository.deleteById(id);
            return true;
        }
        return false;
    }
}
