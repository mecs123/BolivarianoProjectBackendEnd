package com.bolivariano.teacher.service.factory.validation;

import com.bolivariano.teacher.dto.response.TeacherCourseResponseDto;
import com.bolivariano.teacher.dto.response.TeacherSubjectResponseDto;
import com.bolivariano.teacher.entities.TeacherCourse;
import com.bolivariano.teacher.entities.TeacherSubject;

import java.util.Set;

public interface TeacherAssignmentValidation {
    public Set<TeacherCourseResponseDto> fetchCourseList(Set<TeacherCourse> teacher);
    public Set<TeacherSubjectResponseDto> fetchSubjectList(Set<TeacherSubject> teacher);
}
