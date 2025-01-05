package com.bolivariano.teacher.exception;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@Data
@EqualsAndHashCode(callSuper = false)
@ResponseStatus(code = HttpStatus.BAD_REQUEST)
public class TeacherValidationException extends RuntimeException {

    private String code;
    private HttpStatus httpStatus;
    public TeacherValidationException(
            String code,
            String message,
            HttpStatus httpStatus
    ) {
        super(message);
        this.code = code;
        this.httpStatus = httpStatus;
    }
}
