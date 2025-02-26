package com.bolivariano.student.service.impl;

import com.bolivariano.student.dto.request.StudentCourseRequestDTO;
import com.bolivariano.student.dto.request.StudentRequestDTO;
import com.bolivariano.student.dto.response.StudentCourseResponseDTO;
import com.bolivariano.student.dto.response.StudentResponseDTO;
import com.bolivariano.student.entities.Student;
import com.bolivariano.student.exception.StudentAlreadyExistsException;
import com.bolivariano.student.repository.StudentRepository;
import com.bolivariano.student.service.StudentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Random;

@Slf4j
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
     * @param studentRequest
     * @return Student
     */
    @Override
    public StudentResponseDTO saveStudent(StudentRequestDTO studentRequest) {
        Optional<Student> existingStudent = studentRepository.findByCodeStudent(studentRequest.getCodeStudent());

        if (existingStudent.isPresent()) {
            throw new StudentAlreadyExistsException(
                    studentRequest.getCodeStudent(),
                    "Student with code " + studentRequest.getCodeStudent() + " already exists"
            );
        }

        try {
            log.info("Saving student with code: {}", studentRequest.getCodeStudent());
            String uniqueCode = generateStudentCode();
            studentRequest.setCodeStudent(uniqueCode);
            Student student = studentRequest.toEntity();

            Student studentEntity = studentRepository.save(student);

            // Asegurarse de que se genera correctamente el DTO
            StudentResponseDTO response = StudentResponseDTO.fromEntity(studentEntity);
            log.info("Student saved successfully, returning response: {}", response);

            return response;
        } catch (Exception e) {
            log.error("Error occurred while saving student", e);
            throw new RuntimeException("Error occurred while saving student", e);
        }
    }

    // Método para generar un código único para el estudiante
    private String generateStudentCode() {
        Random random = new Random();
        int number = 100000 + random.nextInt(900000); // Genera un número de 6 dígitos
        return "S" + number;
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

    /**
     * @param id
     * @param studentCourseRequestDTO
     * @return
     */
    @Override
    public StudentCourseResponseDTO retriveStudenWithCourse(Long id, StudentCourseRequestDTO studentCourseRequestDTO) {

        // Aquí deberías implementar la lógica para asignar un curso a un estudiante
        Student student =  studentRepository.findById(id).orElseThrow(()->
                new RuntimeException("Student not found"));





        // Puedes hacer uso de la clase CoursesStudent para obtener los datos del curso
        // y de la clase Student para obtener los datos del estudiante
        // Recuerda que debes retornar un objeto de tipo StudentCourseResponseDTO
        // con los datos del estudiante y del curso asignado



        return null;


    }
}
