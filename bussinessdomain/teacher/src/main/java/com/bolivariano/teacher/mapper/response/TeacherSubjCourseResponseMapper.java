package com.bolivariano.teacher.mapper.response;

import com.bolivariano.teacher.dto.response.TeacherCourseResponseDto;
import com.bolivariano.teacher.dto.response.TeacherResponseDTO;
import com.bolivariano.teacher.dto.response.TeacherSubjectResponseDto;
import com.bolivariano.teacher.entities.Teacher;
import com.bolivariano.teacher.entities.TeacherCourse;
import com.bolivariano.teacher.entities.TeacherSubject;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

import java.util.List;

@Mapper(componentModel = "spring")
public interface TeacherSubjCourseResponseMapper {

    @Mappings({
            @Mapping(source = "codTeacher", target = "codTeacher"),
            @Mapping(source = "listSubjectByTeacher", target = "teacherSubjectRequestDto"),
            @Mapping(source = "teacherCourseList", target = "teacherCourseResponseDto"),
            // Puedes añadir más mapeos si es necesario.
    })
    TeacherResponseDTO responseDtoToTeacher(Teacher teacher);
    List<TeacherSubjectResponseDto> mapSubjects(List<TeacherSubject> listSubjectByTeacher);
    List<TeacherCourseResponseDto> mapCourses(List<TeacherCourse> teacherCourseList);



}
