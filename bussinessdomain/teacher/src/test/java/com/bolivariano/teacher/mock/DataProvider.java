package com.bolivariano.teacher.mock;

import com.bolivariano.teacher.dto.request.TeacherCourseRequestDto;
import com.bolivariano.teacher.dto.request.TeacherRequestDTO;
import com.bolivariano.teacher.dto.request.TeacherSubjecRequestDto;
import com.bolivariano.teacher.dto.response.TeacherResponseDTO;
import com.bolivariano.teacher.entities.Teacher;
import com.bolivariano.teacher.entities.TeacherCourse;
import com.bolivariano.teacher.entities.TeacherSubject;

import java.util.Collections;
import java.util.Set;

public class DataProvider {

    public static TeacherRequestDTO newTeacher(){

        Set<TeacherSubjecRequestDto> teacherSubject = Collections.singleton(TeacherSubjecRequestDto.builder()
                .idSubject(1)
                .nameSubject("Sociales")
                .build());

        Set<TeacherCourseRequestDto> teacherCourses = Collections.singleton(TeacherCourseRequestDto.builder()
                .idCourse(1)
                .nameCourse("Primero")
                .build());

        return TeacherRequestDTO.builder()
                .codTeacher("T001")
                .nameTeacher("Manuel")
                .estado(true)
                .teacherSubjectRequestDto(teacherSubject)
                .teacherCourseRequestDto(teacherCourses)
                .build();


    }

    public static Teacher persistTeacher() {
        Teacher teacherEntity = Teacher.builder()
                .codTeacher("T001")
                .nameTeacher("Juan")
                .estado(true)
                .listSubjectByTeacher(Collections.singleton(
                        TeacherSubject.builder().idSubject(1).nameSubject("Español").build()
                ))
                .teacherCourseList(Collections.singleton(
                        TeacherCourse.builder().idCourse(1).nameCourse("Once").build()
                ))
                .build();

        return teacherEntity;
    }

    public static Teacher saveTeacher() {
        Teacher saveTeacher = Teacher.builder()
                .codTeacher("T001")
                .nameTeacher("Manuel")
                .estado(true)
                .listSubjectByTeacher(Collections.emptySet())
                .teacherCourseList(Collections.emptySet())
                .build();
        return saveTeacher;
    }

    public static TeacherResponseDTO responseTeacherToDto() {
        TeacherResponseDTO responseDTO = TeacherResponseDTO.builder()
                .id(1L)
                .codTeacher("T001")
                .nameTeacher("Manuel")
                .estado(true)
                .teacherSubjectRequestDto(Collections.emptySet())
                .teacherCourseResponseDto(Collections.emptySet())
                .build();
        return responseDTO;
    }

    public static Teacher newTeacherEntityMock() {
        Teacher newTeacher = Teacher.builder()
                .id(1L)
                .codTeacher("T001")
                .nameTeacher("Manuel")
                .estado(true)
                .listSubjectByTeacher(Collections.emptySet())
                .teacherCourseList(Collections.emptySet())
                .build();
        return newTeacher;

    }

    public static TeacherResponseDTO responseTeacherToDtoMockito() {
        return TeacherResponseDTO.builder()
                .id(1L)
                .codTeacher("T001")
                .nameTeacher("Manuel")
                .estado(true)
                .teacherSubjectRequestDto(Collections.emptySet())
                .teacherCourseResponseDto(Collections.emptySet())
                .build();
    }
}
