package com.bolivariano.student.exception;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@Data
@EqualsAndHashCode(callSuper = false)
@ResponseStatus(code = HttpStatus.BAD_REQUEST)
public class StudentAlreadyExistsException extends RuntimeException {

    private String code;
    private HttpStatus httpStatus;

    // Constructor con solo el mensaje y código (por defecto BAD_REQUEST)
    public StudentAlreadyExistsException(String code, String message) {
        super(message);  // Pasa el mensaje a la clase RuntimeException
        this.code = code;
        this.httpStatus = HttpStatus.BAD_REQUEST;  // Asignación por defecto de BAD_REQUEST
    }

    // Constructor completo, para cuando se necesita un status diferente
    public StudentAlreadyExistsException(String code, String message, HttpStatus httpStatus) {
        super(message);  // Pasa el mensaje a la clase RuntimeException
        this.code = code;
        this.httpStatus = httpStatus;
    }

    // Representación legible de la excepción
    @Override
    public String toString() {
        return "StudentAlreadyExistsException{" +
                "code='" + code + '\'' +
                ", message='" + getMessage() + '\'' +
                ", httpStatus=" + httpStatus +
                '}';
    }
}

