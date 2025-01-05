package com.bolivariano.teacher.service.factory.validation.impl;

import com.bolivariano.teacher.client.TeacherClient;
import com.bolivariano.teacher.constans.ApiConstants;
import com.bolivariano.teacher.dto.response.TeacherCourseResponseDto;
import com.bolivariano.teacher.dto.response.TeacherSubjectResponseDto;
import com.bolivariano.teacher.entities.TeacherCourse;
import com.bolivariano.teacher.entities.TeacherSubject;
import com.bolivariano.teacher.exception.BussinesRuleException;
import com.bolivariano.teacher.service.factory.validation.TeacherAssignmentValidation;
import org.springframework.stereotype.Component;

import java.util.Set;
import java.util.stream.Collectors;

@Component
public class TeacherAssignmentValidationImpl implements TeacherAssignmentValidation {



    private final TeacherClient teacherClient;

    public TeacherAssignmentValidationImpl(TeacherClient teacherClient) {
        this.teacherClient = teacherClient;
    }

    /**
     * @param teacher
     * @return
     */
    @Override
    public Set<TeacherCourseResponseDto> fetchCourseList(Set<TeacherCourse> teacher) {
        return teacher.stream()
                .map(c -> {
                    try {
                        String name = teacherClient.getCourseName(c.getIdCourse());
                        return new TeacherCourseResponseDto(c.getIdCourse(),name);
                    } catch (BussinesRuleException e) {
                        throw new RuntimeException(e);
                    }
                }).collect(Collectors.toSet());
    }

    /**
     * @param teacher
     * @return
     */
    @Override
    public Set<TeacherSubjectResponseDto> fetchSubjectList(Set<TeacherSubject> teacher) {
        return teacher.stream()
                .map(sb -> {
                    try {
                        String name = teacherClient.getSubjectName(sb.getIdSubject());
                        return new TeacherSubjectResponseDto(sb.getIdSubject(), name);
                    } catch (BussinesRuleException e) {
                        throw new RuntimeException(ApiConstants.SUBJECT_NAME_NOT_FOUND+ sb.getIdSubject(), e);
                    }
                })
                .collect(Collectors.toSet());
    }
}
