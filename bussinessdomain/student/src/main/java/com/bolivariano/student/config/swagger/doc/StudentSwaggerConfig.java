package com.bolivariano.student.config.swagger.doc;

import com.bolivariano.student.dto.request.StudentRequestDTO;
import com.bolivariano.student.dto.response.StudentResponseDTO;
import com.bolivariano.student.exception.BussinesRuleException;

import com.bolivariano.student.utils.WrapperResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;

import java.net.UnknownHostException;

public interface StudentSwaggerConfig {

    @Operation(summary = "Verificar la configuración del entorno")
    String check();

    @Operation(
            summary = "Obtener todos los estudiantes",
            description = "Este endpoint obtiene todos los estudiantes con soporte de paginación.",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Successfully retrieved list of students"),
                    @ApiResponse(responseCode = "400", description = "Invalid parameters")
            }
    )
    WrapperResponse<Page<StudentResponseDTO>> list(
            @Parameter(description = "Número de la página (por defecto es 0)", example = "0")
            int page,

            @Parameter(description = "Tamaño de la página (por defecto es 10)", example = "10")
            int size
    );

    @Operation(summary = "Obtener un estudiante por ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Estudiante encontrado"),
            @ApiResponse(responseCode = "404", description = "Estudiante no encontrado")
    })
    ResponseEntity<WrapperResponse<?>> get(Long id) throws BussinesRuleException;

    @Operation(summary = "Crear un nuevo estudiante")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Estudiante creado"),
            @ApiResponse(responseCode = "400", description = "Error en la solicitud")
    })
    ResponseEntity<WrapperResponse<?>> post(StudentRequestDTO input) throws BussinesRuleException, UnknownHostException;

    @Operation(summary = "Eliminar un estudiante")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Estudiante eliminado"),
            @ApiResponse(responseCode = "404", description = "Estudiante no encontrado")
    })
    ResponseEntity<?> delete(Long id);
}
