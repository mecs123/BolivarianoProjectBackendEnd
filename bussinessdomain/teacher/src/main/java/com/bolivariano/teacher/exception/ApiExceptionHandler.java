package com.bolivariano.teacher.exception;

import com.bolivariano.teacher.common.StandarizedApiExceptionResponse;
import com.bolivariano.teacher.utils.WrapperResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

/**
 * Manejador global de excepciones.
 */
@Slf4j
@RestControllerAdvice
public class ApiExceptionHandler {




    // Método auxiliar para construir la respuesta de error
    private ResponseEntity<StandarizedApiExceptionResponse> buildErrorResponse(
            String type, String userMessage, String code, String detailedMessage,
            String instanceUri, HttpStatus httpStatus) {

        StandarizedApiExceptionResponse response = new StandarizedApiExceptionResponse(
                type, userMessage, code, detailedMessage, instanceUri, httpStatus.value());

        return ResponseEntity.status(httpStatus).body(response);
    }



    @ExceptionHandler(TeacherValidationException.class)
    public ResponseEntity<WrapperResponse<?>> handleTeacherValidationException(TeacherValidationException ex) {
        log.warn("Error de validación: {}", ex.getMessage());
        WrapperResponse<?> response = WrapperResponse.error(
                ex.getMessage(), String.valueOf(ex.getHttpStatus().value())
        );
        return response.dinamicResponse(ex.getHttpStatus());
    }




    // Manejador para excepciones generales
    @ExceptionHandler(Exception.class)
    public ResponseEntity<?> all(Exception ex) {
        log.error("Unhandled exception occurred: {}", ex.getMessage(), ex);
        WrapperResponse<?> response = new WrapperResponse<>(ex.getMessage(), null, null);
        return new ResponseEntity<>(ex.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);

    }
    @ExceptionHandler(ValidateServiceExepcion.class)
    public ResponseEntity<?> validateService(ValidateServiceExepcion ex, WebRequest request) {
        log.error("Unhandled exception occurred: {}", ex.getMessage(), ex);
        WrapperResponse<?> response = new WrapperResponse<>( ex.getMessage(), null,null);
        return new ResponseEntity<>(ex.getMessage(),HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(NoDataFoundExepcion.class)
    public ResponseEntity<WrapperResponse<?>> noData(NoDataFoundExepcion ex, WebRequest request) {
        log.error("Unhandled exception occurred: {}", ex.getMessage(), ex);
        WrapperResponse<?> response = new WrapperResponse<>(ex.getMessage(), null, null);
        return new ResponseEntity<>(response, HttpStatus.NO_CONTENT);
    }


    @ExceptionHandler(GeneralServiceExpecion.class)
    public ResponseEntity<?> generalService(GeneralServiceExpecion ex, WebRequest request) {
        log.error("Unhandled exception occurred: {}", ex.getMessage(), ex);
        WrapperResponse<?> response = new WrapperResponse<>(ex.getMessage(), null, null);
        return new ResponseEntity<>(ex.getMessage(),HttpStatus.NO_CONTENT);

    }









}
