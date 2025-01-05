package com.bolivariano.teacher.exception;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.http.HttpStatus;

/**
 * Custom exception for business rule validation errors.
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class BussinesRuleException extends Exception {

    private String code;
    private HttpStatus httpStatus;

    /**
     * Constructor for business rule exception with code and HTTP status.
     *
     * @param code The unique error code.
     * @param message The error message.
     * @param httpStatus The HTTP status to return.
     */
    public BussinesRuleException(
            String code,
            String message,
            HttpStatus httpStatus
    ) {
        super(message);
        this.code = code;
        this.httpStatus = httpStatus;
    }

    /**
     * Constructor for business rule exception with cause.
     *
     * @param message The error message.
     * @param cause The root cause of the eception.
     */
    public BussinesRuleException(
            String code,
            String message,
            Throwable cause
    ) {
        super(message, cause);
    }

    public BussinesRuleException(String teacherNotFound) {

    }
}
