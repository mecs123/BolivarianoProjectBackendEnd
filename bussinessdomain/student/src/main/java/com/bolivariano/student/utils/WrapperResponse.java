package com.bolivariano.student.utils;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
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


}
