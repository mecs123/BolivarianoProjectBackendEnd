package com.bolivariano.teacher.config.swagger.doc;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

public class SwaggerDocumentation {

    public @interface GetByCodeSubjectToTeacher {
        @Operation(summary = "Obtener profesor por código y materias asociadas")
        @ApiResponses(value = {
                @ApiResponse(responseCode = "200", description = "Profesor encontrado"),
                @ApiResponse(responseCode = "400", description = "Código de profesor inválido"),
                @ApiResponse(responseCode = "404", description = "Profesor no encontrado"),
                @ApiResponse(responseCode = "500", description = "Error de conexión con el servidor")
        })
        @interface Documentation {}
    }

    public @interface GetAllTeacher {
        @Operation(summary = "Obtiene la lista de profesores creada")
        @ApiResponses(value = {
                @ApiResponse(responseCode = "200", description = "Profesor encontrado"),
                @ApiResponse(responseCode = "400", description = "Código de profesor inválido"),
                @ApiResponse(responseCode = "404", description = "Profesor no encontrado"),
                @ApiResponse(responseCode = "500", description = "Error de conexión con el servidor")
        })
        @interface Documentation {}
    }

    public @interface postTeacher {
        @Operation(summary = "crear un profesor y asignar materias y curso")
        @ApiResponses(value = {
                @ApiResponse(responseCode = "200", description = "Profesor encontrado"),
                @ApiResponse(responseCode = "400", description = "Código de profesor inválido"),
                @ApiResponse(responseCode = "404", description = "Profesor no encontrado"),
                @ApiResponse(responseCode = "500", description = "Error de conexión con el servidor")
        })
        @interface Documentation {}
    }
    public @interface putTeacher {
        @Operation(summary = "Actualiza un profesor y asignar materias y curso")
        @ApiResponses(value = {
                @ApiResponse(responseCode = "200", description = "Profesor encontrado"),
                @ApiResponse(responseCode = "400", description = "Código de profesor inválido"),
                @ApiResponse(responseCode = "404", description = "Profesor no encontrado"),
                @ApiResponse(responseCode = "500", description = "Error de conexión con el servidor")
        })
        @interface Documentation {}
    }
    public @interface getByTeacher {
        @Operation(summary = "Lista dato del profesor solo con id's")
        @ApiResponses(value = {
                @ApiResponse(responseCode = "200", description = "Profesor encontrado"),
                @ApiResponse(responseCode = "400", description = "Código de profesor inválido"),
                @ApiResponse(responseCode = "404", description = "Profesor no encontrado"),
                @ApiResponse(responseCode = "500", description = "Error de conexión con el servidor")
        })
        @interface Documentation {}
    }
    public @interface deleteByTeacher {
        @Operation(summary = "Lista dato del profesor solo con id's")
        @ApiResponses(value = {
                @ApiResponse(responseCode = "200", description = "Profesor encontrado"),
                @ApiResponse(responseCode = "400", description = "Código de profesor inválido"),
                @ApiResponse(responseCode = "404", description = "Profesor no encontrado"),
                @ApiResponse(responseCode = "500", description = "Error de conexión con el servidor")
        })
        @interface Documentation {}
    }



}
