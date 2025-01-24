package com.bolivariano.teacher.mapper.response;

import com.bolivariano.teacher.dto.request.TeacherRequestDTO;
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
import java.util.Set;

@Mapper(componentModel = "spring")
public interface TeacherResponseMapper {




    Set<TeacherSubjectResponseDto> mapSubjects(Set<TeacherSubjectResponseDto> subjects);

    @Mappings({
            @Mapping(source = "id", target = "idDto"),
    })
    Set<TeacherCourseResponseDto> mapCourses(Set<TeacherCourseResponseDto> courses);

    @Mappings({
            @Mapping(source = "id", target = "id"),
            @Mapping(source = "listSubjectByTeacher", target = "teacherSubjectResponseDto"),
            @Mapping(source = "teacherCourseList", target = "teacherCourseResponseDto"),
    })
    TeacherResponseDTO teacherToResponseDto(Teacher teacher);

    @Mappings({
            @Mapping(source = "codTeacher", target = "codTeacher"),
            @Mapping(source = "teacherSubjectRequestDto", target = "listSubjectByTeacher"),
            @Mapping(source = "teacherCourseRequestDto", target = "teacherCourseList"),
    })
    Teacher teacherResonseDtoToTeacher(TeacherRequestDTO teacherRequestDTO);


    @Mappings({
            @Mapping(source = "teacher.id", target = "id"),
            @Mapping(source = "teacher.listSubjectByTeacher", target = "teacherSubjectResponseDto"),
            @Mapping(source = "teacher.teacherCourseList", target = "teacherCourseResponseDto")
    })
    TeacherResponseDTO entityToResDto(Teacher teacher);
    @Mappings({
            @Mapping(source = "teacherSubjects.iD", target = "id"),
            @Mapping(source = "teacherSubjects.idSubject", target = "teacherSubjectResponseDto"),
            @Mapping(source = "teacherSubjects.nameSubject", target = "teacherCourseResponseDto")
    })
    List<TeacherSubjectResponseDto> teacherSubjectListToDto(List<TeacherSubject> teacherSubjects);
    List<TeacherCourseResponseDto> teacherCourseListToDto(List<TeacherCourse> teacherCourses);
}
