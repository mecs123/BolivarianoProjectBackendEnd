package com.bolivariano.teacher.service.factory.validation.impl;

import com.bolivariano.teacher.constans.ApiConstants;
import com.bolivariano.teacher.entities.Teacher;
import com.bolivariano.teacher.exception.TeacherValidationException;
import com.bolivariano.teacher.repository.TeacherRepository;
import com.bolivariano.teacher.service.factory.validation.TeacherValidation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class TeacherRestValidationImpl implements TeacherValidation {

    public TeacherRestValidationImpl(TeacherRepository teacherRepository) {
        this.teacherRepository = teacherRepository;
    }

    private final TeacherRepository teacherRepository;

    /**
     * @param codTeacher
     * @throws TeacherValidationException
     */
    @Override
    public void validaCodeOfTeacher(
            String codTeacher
    ) {
        if (codTeacher == null ||  codTeacher.isBlank()) {
            throw new TeacherValidationException(
                    ApiConstants.ERROR_CODE_TEACHER_NOT_FOUND,
                    ApiConstants.INVALID_TEACHER_CODE,
                    HttpStatus.PRECONDITION_FAILED
            );
        }
        boolean exists = teacherRepository.findByCodeTeacher(codTeacher) != null;
        if (exists) {
            throw new TeacherValidationException(
                    ApiConstants.ERROR_CODE_TEACHER_NOT_FOUND,
                    ApiConstants.TEACHER_EXIST_OR_NOT_VALID + codTeacher + ApiConstants.TEACHER_EXIST,
                    HttpStatus.CONFLICT
            );
        }
    }

    /**
     * @param byCodeTeacher
     * @param codTeacher
     * @return byCodeTeacher
     * @throws TeacherValidationException
     */
    @Override
    public Teacher validateCodeTeacher(Teacher byCodeTeacher, String codTeacher) throws TeacherValidationException {
        if ( byCodeTeacher == null) {
            throw new TeacherValidationException(
                    ApiConstants.ERROR_CODE_TEACHER_NOT_FOUND,
                    ApiConstants.TEACHER_NOT_FOUND + codTeacher,
                    HttpStatus.NOT_FOUND
            );
        }
        return byCodeTeacher;

    }
}
