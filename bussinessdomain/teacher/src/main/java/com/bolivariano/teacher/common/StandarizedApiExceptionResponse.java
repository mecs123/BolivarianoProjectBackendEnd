package com.bolivariano.teacher.common;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * This model follows RFC 7807, which defines a standard schema for API error responses.
 */
@Schema(description = "Standardized error response based on RFC 7807, which consists of five main components.")
@NoArgsConstructor
@Data
public class StandarizedApiExceptionResponse {

    @Schema(description = "The URI identifier that categorizes the error type.",
            example = "/errors/authentication/not-authorized",
            requiredMode = Schema.RequiredMode.REQUIRED)
    private String type;

    @Schema(description = "A brief, human-readable title describing the error.",
            example = "Authorization error",
            requiredMode = Schema.RequiredMode.REQUIRED)
    private String title;

    @Schema(description = "A unique error code for identifying the error.",
            example = "401",
            requiredMode = Schema.RequiredMode.NOT_REQUIRED)
    private String code;

    @Schema(description = "A detailed, human-readable explanation of the error.",
            example = "The user does not have the proper permissions to access the resource.",
            requiredMode = Schema.RequiredMode.REQUIRED)
    private String detail;

    @Schema(description = "A URI that identifies this specific occurrence of the error.",
            example = "/errors/authentication/not-authorized/123456",
            requiredMode = Schema.RequiredMode.REQUIRED)
    private String instance;

    @Schema(description = "The HTTP status code related to the error.",
            example = "400",
            requiredMode = Schema.RequiredMode.REQUIRED)
    private int status;  // Nuevo campo para el código de estado HTTP

    /**
     * Constructor for creating a standardized error response with status.
     *
     * @param type The type of error (URI identifier).
     * @param title A brief title for the error.
     * @param code A unique error code (optional).
     * @param detail A detailed explanation of the error.
     * @param instance A URI identifying this specific occurrence of the error.
     * @param status The HTTP status code related to the error.
     */
    public StandarizedApiExceptionResponse(String type, String title, String code, String detail, String instance, int status) {
        this.type = type;
        this.title = title;
        this.code = code;
        this.detail = detail;
        this.instance = instance;
        this.status = status;
    }

    /**
     * Overloaded constructor for responses without an instance URI and status.
     *
     * @param type The type of error (URI identifier).
     * @param title A brief title for the error.
     * @param code A unique error code (optional).
     * @param detail A detailed explanation of the error.
     * @param status The HTTP status code related to the error.
     */
    public StandarizedApiExceptionResponse(String type, String title, String code, String detail, int status) {
        this(type, title, code, detail, null, status);
    }
}
