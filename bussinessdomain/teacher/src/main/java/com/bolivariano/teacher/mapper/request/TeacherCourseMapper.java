package com.bolivariano.teacher.mapper.request;

import com.bolivariano.teacher.dto.request.TeacherCourseRequestDto;
import com.bolivariano.teacher.entities.TeacherCourse;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface TeacherCourseMapper {

    TeacherCourse teacherCourseRequestDtoToEntity(TeacherCourseRequestDto dto);

    TeacherCourseRequestDto teacherCourseEntityToDto(TeacherCourse entity);

    List<TeacherCourse> teacherCourseRequestDtoListToEntityList(List<TeacherCourseRequestDto> dtos);

    List<TeacherCourseRequestDto> teacherCourseEntityListToDtoList(List<TeacherCourse> entities);
}
