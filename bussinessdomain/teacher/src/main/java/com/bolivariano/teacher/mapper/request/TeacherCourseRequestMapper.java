package com.bolivariano.teacher.mapper.request;

import com.bolivariano.teacher.dto.request.TeacherCourseRequestDto;
import com.bolivariano.teacher.entities.TeacherCourse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface TeacherCourseRequestMapper {

    @Mappings({
            @Mapping(source = "idCourse", target = "idCourse"),
            @Mapping(source = "nameCourse", target = "nameCourse")
    })
    List<TeacherCourse> toEntityList(List<TeacherCourseRequestDto> teacherCourseRequestDtos);

}

