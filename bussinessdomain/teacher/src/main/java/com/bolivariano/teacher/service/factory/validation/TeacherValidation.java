package com.bolivariano.teacher.service.factory.validation;

import com.bolivariano.teacher.entities.Teacher;
import com.bolivariano.teacher.exception.TeacherValidationException;

public interface TeacherValidation {
    public void validaCodeOfTeacher(String codTeacher) throws TeacherValidationException;
    public Teacher validateCodeTeacher(Teacher byCodeTeacher, String codTeacher) throws TeacherValidationException;

}
