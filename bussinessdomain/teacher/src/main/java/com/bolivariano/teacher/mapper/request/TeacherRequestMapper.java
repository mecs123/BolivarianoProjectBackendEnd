package com.bolivariano.teacher.mapper.request;

import com.bolivariano.teacher.dto.request.TeacherCourseRequestDto;
import com.bolivariano.teacher.dto.request.TeacherRequestDTO;
import com.bolivariano.teacher.dto.request.TeacherSubjecRequestDto;
import com.bolivariano.teacher.entities.Teacher;
import com.bolivariano.teacher.entities.TeacherCourse;
import com.bolivariano.teacher.entities.TeacherSubject;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

import java.util.List;
import java.util.Set;

@Mapper(componentModel = "spring", uses = {TeacherSubjectMapper.class, TeacherCourseMapper.class})
public interface TeacherRequestMapper {

    // Mapear de TeacherRequestDTO a Teacher
    @Mappings({
            @Mapping(source = "teacherRequestDTO.teacherSubjectRequestDto", target = "listSubjectByTeacher"),
            @Mapping(source = "teacherRequestDTO.teacherCourseRequestDto", target = "teacherCourseList")
    })
    Teacher teacherRequestDtoToTeacher(TeacherRequestDTO teacherRequestDTO);

    @Mappings({
            @Mapping(source = "teacherSubjectRequestDto.idSubject", target = "idSubject"),
            @Mapping(source = "teacherCourseRequestDto.nameSubject", target = "nameSubject")
    })
    Set<TeacherSubject> teacherSubjectRequestDtoToEntity(List<TeacherSubjecRequestDto> teacherSubjectRequestDto);

    Set<TeacherCourse> teacherCourseRequest(List<TeacherCourseRequestDto> teacherCourseRequestDto);
}
