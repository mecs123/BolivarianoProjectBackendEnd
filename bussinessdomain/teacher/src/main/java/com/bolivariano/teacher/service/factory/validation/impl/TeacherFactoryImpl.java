package com.bolivariano.teacher.service.factory.validation.impl;

import com.bolivariano.teacher.dto.request.TeacherRequestDTO;
import com.bolivariano.teacher.dto.response.TeacherCourseResponseDto;
import com.bolivariano.teacher.dto.response.TeacherSubjectResponseDto;
import com.bolivariano.teacher.entities.Teacher;
import com.bolivariano.teacher.entities.TeacherCourse;
import com.bolivariano.teacher.entities.TeacherSubject;
import com.bolivariano.teacher.mapper.response.TeacherResponseMapper;
import com.bolivariano.teacher.service.factory.validation.TeacherFactory;
import org.springframework.stereotype.Component;

import java.util.Set;
import java.util.stream.Collectors;
@Component
public class TeacherFactoryImpl implements TeacherFactory {

    private final TeacherResponseMapper resMapper;
    private final TeacherAssignmentValidationImpl restCommunication;

    public TeacherFactoryImpl(TeacherResponseMapper resMapper, TeacherAssignmentValidationImpl restCommunication) {
        this.resMapper = resMapper;
        this.restCommunication = restCommunication;
    }


    /**
     * @param teacherRequestDTO
     * @return
     */
    @Override
    public Teacher createTeacher(TeacherRequestDTO teacherRequestDTO) {
        String codteacherWithIndexT = teacherRequestDTO.getCodTeacher();

        Teacher teacher = Teacher.builder()
                .codTeacher("T"+codteacherWithIndexT)
                .nameTeacher(teacherRequestDTO.getNameTeacher())
                .estado(teacherRequestDTO.isEstado())
                .build();
        Set<TeacherSubject> createSubjects = createSubjects(teacherRequestDTO, teacher);
        teacher.setListSubjectByTeacher(createSubjects);
        Set<TeacherCourse> createCourse = createCourse(teacherRequestDTO,teacher);
        teacher.setTeacherCourseList(createCourse);
        return teacher;
    }

    /**
     * @param teacherRequestDTO
     * @param id
     * @return
     */
    @Override
    public Teacher updateTeacher(TeacherRequestDTO teacherRequestDTO, Long id) {
        Teacher teacher = resMapper.teacherResonseDtoToTeacher(teacherRequestDTO);

        teacher.setId(id);
        Set<TeacherCourseResponseDto> courseList = restCommunication.fetchCourseList(teacher.getTeacherCourseList());
        Set<TeacherSubjectResponseDto> subjectList = restCommunication.fetchSubjectList(teacher.getListSubjectByTeacher());
        Set<TeacherCourse> teacherCourses = (Set<TeacherCourse>) courseList.stream()
                .map(
                        courseDto ->
                                new TeacherCourse(0,
                                        courseDto.getIdCourse(),
                                        courseDto.getNameCourse(),
                                        teacher))
                .collect(Collectors.toSet());


        Set<TeacherSubject> teacherSubjects = (Set<TeacherSubject>) subjectList.stream()
                .map(
                        subjectDto ->
                                new TeacherSubject(0,
                                        subjectDto.getIdSubject(),
                                        subjectDto.getNameSubject(),
                                        teacher))
                .collect(Collectors.toSet());

        teacher.setTeacherCourseList(teacherCourses);
        teacher.setListSubjectByTeacher(teacherSubjects);



        return teacher;
    }

    private Set<TeacherSubject> createSubjects(TeacherRequestDTO teacherRequestDTO, Teacher teacher) {
        return teacherRequestDTO.getTeacherSubjectRequestDto()
                .stream()
                .map(subject -> TeacherSubject.builder()
                        .idSubject(subject.getIdSubject())
                        .nameSubject(subject.getNameSubject())
                        .teacher(teacher)
                        .build())
                .collect(Collectors.toSet());

    }

    private Set<TeacherCourse> createCourse(TeacherRequestDTO teacherRequestDTO, Teacher teacher) {
        return teacherRequestDTO.getTeacherCourseRequestDto()
                .stream()
                .map(course -> TeacherCourse.builder()
                        .idCourse(course.getIdCourse())
                        .nameCourse(course.getNameCourse())
                        .teacher(teacher)
                        .build())
                .collect(Collectors.toSet());
    }

}
