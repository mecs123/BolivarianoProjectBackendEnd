package com.bolivariano.teacher.utils;

import com.bolivariano.teacher.dto.response.TeacherResponseDTO;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class WrapperResponse<T> {
    private String mensaje;
    private String codigoError;  // Código de error (opcional)
    private T body;

    // Método para crear la respuesta con código de estado 200 (OK)
    public ResponseEntity<WrapperResponse<?>> dinamicResponse(HttpStatus status){
        return new ResponseEntity<>(this, status);  // Correcto: HttpStatus.OK
    }




    // Método de utilidad para respuestas de éxito
    public static <T> WrapperResponse<T> success(T body, String mensaje,String codigoError) {
        return new WrapperResponse<>( mensaje, codigoError, body);
    }

    // Método de utilidad para respuestas de error
    public static <T> WrapperResponse<T> error(String mensaje, String codigoError) {
        return new WrapperResponse<>( mensaje, codigoError, null);
    }


    public WrapperResponse<Page<TeacherResponseDTO>> getAllResponse(HttpStatus httpStatus, Page<TeacherResponseDTO> teachers) {
        return new WrapperResponse<Page<TeacherResponseDTO>>(mensaje,codigoError,teachers);
    }
}
