package com.bolivariano.student.config.swagger.doc;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

public class SwaggerDocumentation {

    public @interface GetByCodeStudent {
        @Operation(summary = "Obtener estudiante por código")
        @ApiResponses(value = {
                @ApiResponse(responseCode = "200", description = "Estudiante encontrado"),
                @ApiResponse(responseCode = "400", description = "Código de estudiante inválido"),
                @ApiResponse(responseCode = "404", description = "Estudiante no encontrado"),
                @ApiResponse(responseCode = "500", description = "Error de conexión con el servidor")
        })
        @interface Documentation {}
    }

    public @interface GetAllStudents {
        @Operation(summary = "Obtiene la lista de estudiantes")
        @ApiResponses(value = {
                @ApiResponse(responseCode = "200", description = "Estudiantes encontrados"),
                @ApiResponse(responseCode = "400", description = "Parámetros inválidos"),
                @ApiResponse(responseCode = "404", description = "No se encontraron estudiantes"),
                @ApiResponse(responseCode = "500", description = "Error de conexión con el servidor")
        })
        @interface Documentation {}
    }

    public @interface postStudent {
        @Operation(summary = "Crear un estudiante")
        @ApiResponses(value = {
                @ApiResponse(responseCode = "200", description = "Estudiante creado con éxito"),
                @ApiResponse(responseCode = "400", description = "Datos del estudiante inválidos"),
                @ApiResponse(responseCode = "404", description = "Estudiante no encontrado"),
                @ApiResponse(responseCode = "500", description = "Error de conexión con el servidor")
        })
        @interface Documentation {}
    }

    public @interface putStudent {
        @Operation(summary = "Actualizar los datos de un estudiante")
        @ApiResponses(value = {
                @ApiResponse(responseCode = "200", description = "Estudiante actualizado con éxito"),
                @ApiResponse(responseCode = "400", description = "Datos inválidos para actualizar el estudiante"),
                @ApiResponse(responseCode = "404", description = "Estudiante no encontrado"),
                @ApiResponse(responseCode = "500", description = "Error de conexión con el servidor")
        })
        @interface Documentation {}
    }

    public @interface getByStudent {
        @Operation(summary = "Obtener información de un estudiante por ID")
        @ApiResponses(value = {
                @ApiResponse(responseCode = "200", description = "Estudiante encontrado"),
                @ApiResponse(responseCode = "400", description = "ID de estudiante inválido"),
                @ApiResponse(responseCode = "404", description = "Estudiante no encontrado"),
                @ApiResponse(responseCode = "500", description = "Error de conexión con el servidor")
        })
        @interface Documentation {}
    }

    public @interface deleteByStudent {
        @Operation(summary = "Eliminar un estudiante por ID")
        @ApiResponses(value = {
                @ApiResponse(responseCode = "200", description = "Estudiante eliminado con éxito"),
                @ApiResponse(responseCode = "400", description = "ID de estudiante inválido"),
                @ApiResponse(responseCode = "404", description = "Estudiante no encontrado"),
                @ApiResponse(responseCode = "500", description = "Error de conexión con el servidor")
        })
        @interface Documentation {}
    }
}
