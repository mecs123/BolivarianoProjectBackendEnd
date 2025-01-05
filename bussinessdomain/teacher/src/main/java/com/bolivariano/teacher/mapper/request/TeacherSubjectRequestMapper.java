package com.bolivariano.teacher.mapper.request;

import com.bolivariano.teacher.dto.request.TeacherSubjecRequestDto;
import com.bolivariano.teacher.entities.TeacherSubject;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface TeacherSubjectRequestMapper {

    @Mappings({
            @Mapping(source = "idSubject", target = "idSubject"),
            @Mapping(source = "nameSubject", target = "nameSubject")
    })
    List<TeacherSubject> toEntityList(List<TeacherSubjecRequestDto> teacherSubjecRequestDto);

}

