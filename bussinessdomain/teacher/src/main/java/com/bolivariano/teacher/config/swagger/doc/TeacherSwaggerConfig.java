package com.bolivariano.teacher.config.swagger.doc;

import com.bolivariano.teacher.dto.request.TeacherRequestDTO;
import com.bolivariano.teacher.dto.response.TeacherResponseDTO;
import com.bolivariano.teacher.exception.BussinesRuleException;
import com.bolivariano.teacher.exception.TeacherValidationException;
import com.bolivariano.teacher.utils.WrapperResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;

import java.net.UnknownHostException;

public interface TeacherSwaggerConfig {

    @Operation(summary = "Verificar la configuración del entorno")
    String check();

    @Operation(
            summary = "Obtener todos los profesores",
            description = "Este endpoint obtiene todos los profesores con soporte de paginación.",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Successfully retrieved list of teachers"),
                    @ApiResponse(responseCode = "400", description = "Invalid parameters")
            }
    )
    WrapperResponse<Page<TeacherResponseDTO>> list(
            @Parameter(description = "Número de la página (por defecto es 0)", example = "0")
            int page,

            @Parameter(description = "Tamaño de la página (por defecto es 10)", example = "10")
            int size
    );

    @Operation(summary = "Obtener un profesor por ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Teacher found"),
            @ApiResponse(responseCode = "404", description = "Teacher not found")
    })
    ResponseEntity<WrapperResponse<?>> get(Long id) throws BussinesRuleException;

    @Operation(summary = "Crear un nuevo profesor")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Teacher created"),
            @ApiResponse(responseCode = "400", description = "Request error")
    })
    ResponseEntity<WrapperResponse<?>> post(TeacherRequestDTO input) throws BussinesRuleException, UnknownHostException, TeacherValidationException;

    @Operation(summary = "Eliminar un profesor")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Teacher deleted"),
            @ApiResponse(responseCode = "404", description = "Teacher not found")
    })
    ResponseEntity<?> delete(Long id);
}
