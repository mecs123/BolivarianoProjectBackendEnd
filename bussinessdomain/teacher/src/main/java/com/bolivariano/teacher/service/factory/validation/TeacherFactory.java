package com.bolivariano.teacher.service.factory.validation;

import com.bolivariano.teacher.dto.request.TeacherRequestDTO;
import com.bolivariano.teacher.entities.Teacher;

public interface TeacherFactory {
    public Teacher createTeacher(TeacherRequestDTO dto);
    public Teacher updateTeacher(TeacherRequestDTO dto, Long id);


}
