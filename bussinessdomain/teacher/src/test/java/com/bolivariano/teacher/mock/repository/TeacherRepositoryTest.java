package com.bolivariano.teacher.mock.repository;

import com.bolivariano.teacher.entities.Teacher;
import com.bolivariano.teacher.entities.TeacherCourse;
import com.bolivariano.teacher.entities.TeacherSubject;
import com.bolivariano.teacher.repository.TeacherRepository;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.dao.InvalidDataAccessApiUsageException;

import java.util.Collections;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class TeacherRepositoryTest {

    @Autowired
    TeacherRepository teacherRepository;

    @Autowired
    EntityManager testEntityManager;

    @BeforeEach
    void setUp() {
        Teacher teacher = createTeacherWithCoursesAndSubjects();
        testEntityManager.persist(teacher);
    }

    @AfterEach
    void tearDown() {
        testEntityManager.clear();
    }

    @Transactional
    @Test
    public void findTeacherByIdNotFound() {
        Optional<Teacher> teacher = teacherRepository.findById(999L);
        assertTrue(teacher.isEmpty(), "No se esperaba un profesor para este ID");
    }

    @Transactional
    @Test
    public void findTeacherCode() {
        Teacher teacher = teacherRepository.findByCodeTeacher("T001");
        assertNotNull(teacher, "El profesor no debe ser nulo");
        assertEquals("T001", teacher.getCodTeacher(), "El código no es válido");
    }

    @Transactional
    @Test
    public void findTeacherCodeNotFound() {
        Teacher teacher = teacherRepository.findByCodeTeacher("INVALID_CODE");
        assertNull(teacher, "El profesor debe ser nulo");
    }

    @Transactional
    @Test
    public void validatTeacherSubject() {
        Teacher teacher = createTeacherWithCoursesAndSubjects();
        testEntityManager.persist(teacher);
        testEntityManager.flush(); // Fuerza el guardado en la base de datos
        Long teacherId = teacher.getId();

        Optional<Teacher> fetchedTeacher = teacherRepository.findById(teacherId);
        assertTrue(fetchedTeacher.isPresent(), "El profesor debería existir después de ser persistido.");
        assertEquals(1, fetchedTeacher.get().getListSubjectByTeacher().size(), "El número de materias asignadas no coincide.");

    }


    @Transactional
    @Test
    public void validatTeacherCourse() {
        Teacher teacher = createTeacherWithCoursesAndSubjects();
        testEntityManager.persist(teacher);
        testEntityManager.flush(); // Fuerza el guardado en la base de datos

        // Obtén el ID del teacher que acaba de ser persistido
        Long teacherId = teacher.getId();

        Optional<Teacher> fetchedTeacher = teacherRepository.findById(teacherId);
        assertTrue(fetchedTeacher.isPresent(), "El profesor debería existir después de ser persistido.");
        assertEquals(1, fetchedTeacher.get().getTeacherCourseList().size(), "El número de cursos asignados no coincide.");
    }


    @Transactional
    @Test
    public void findTeacherByIdWithNullShouldThrowException() {
        assertThrows(InvalidDataAccessApiUsageException.class, () -> {
            teacherRepository.findById(null);
        }, "Se esperaba una InvalidDataAccessApiUsageException al pasar un ID nulo");
    }

    @Transactional
    @Test
    public void deleteById() {
        teacherRepository.deleteById(1L);
        Optional<Teacher> teacher = teacherRepository.findById(1L);
        assertTrue(teacher.isEmpty(), "El profesor debería haber sido eliminado");
    }

    private Teacher createTeacherWithCoursesAndSubjects() {
        // Crear el curso
        TeacherCourse course = TeacherCourse.builder()
                .idCourse(1)
                .nameCourse("Math 101")
                .build();

        // Crear la asignatura
        TeacherSubject subject = TeacherSubject.builder()
                .idSubject(1)
                .nameSubject("Sociales")
                .build();

        // Crear el profesor
        Teacher teacher = Teacher.builder()
                .nameTeacher("Manuel")
                .codTeacher("T001")
                .estado(true)
                .listSubjectByTeacher(Collections.singleton(subject))
                .teacherCourseList(Collections.singleton(course))
                .build();


        course.setTeacher(teacher);
        subject.setTeacher(teacher);

        return teacher;
    }
}
