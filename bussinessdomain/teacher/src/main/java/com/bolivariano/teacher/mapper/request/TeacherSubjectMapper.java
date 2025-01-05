package com.bolivariano.teacher.mapper.request;

import com.bolivariano.teacher.dto.request.TeacherSubjecRequestDto;
import com.bolivariano.teacher.entities.TeacherSubject;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface TeacherSubjectMapper {

    TeacherSubject teacherSubjectRequestDtoToEntity(TeacherSubjecRequestDto dto);

    TeacherSubjecRequestDto teacherSubjectEntityToDto(TeacherSubject entity);

    List<TeacherSubject> teacherSubjectRequestDtoListToEntityList(List<TeacherSubjecRequestDto> dtos);

    List<TeacherSubjecRequestDto> teacherSubjectEntityListToDtoList(List<TeacherSubject> entities);
}
