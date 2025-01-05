package com.bolivariano.teacher.constans;

public class ApiConstants {
    // Mensajes generales
    public static final String TEACHER_NOT_FOUND = "Profesor no encontrado: ";
    public static final String TEACHER_EXIST_OR_NOT_VALID = "Profesor con codigo ingresado: ";
    public static final String TEACHER_EXIST = " Existe/No valido ";
    public static final String TEACHER_FOUND = "Profesor encontrado";
    public static final String TEACHER_CREATED = "Profesor creado exitosamente";
    public static final String TEACHER_SUCCES = "Solicitud exitosa";
    public static final String TEACHER_UPDATE = "Profesor actualizado exitosamente";
    public static final String TEACHER_DELETED = "Profesor eliminado";
    public static final String REQUEST_ERROR = "Error en la solicitud";
    public static final String SUCCESS_MESSAGE = "Datos del profesor obtenidos exitosamente";

    // Mensajes específicos
    public static final String INVALID_TEACHER_ID = "El código del profesor no es válido.";
    public static final String INVALID_TEACHER_CODE = "El código del profesor no es válido.";
    public static final String SUBJECT_NAME_NOT_FOUND = "El nombre de la materia no pudo ser obtenido.";
    public static final String SERVER_CONNECTION_ERROR = "Error de conexión con el servidor";
    public static final String SUBJECT_FETCH_ERROR = "Error al consultar la materia con ID: ";

    // Mensajes de error Para Teacher
    public static final String ERROR_MSG_SUBJECT_NOT_FOUND = "El nombre de la materia con ID %d no pudo ser obtenido.";
    public static final String ERROR_MSG_COURSE_NOT_FOUND = "El nombre del curso con ID %d no pudo ser obtenido.";
    public static final String WEBCLIENT_ERROR_CODE = "600";
    public static final String WEBCLIENT_ERROR_MESSAGE = "Error al realizar la solicitud al servicio externo.";

    // Códigos personalizados para no existencia de elementos
    public static final String ERROR_CODE_TEACHER_NOT_FOUND = "T-404";
    public static final String ERROR_CODE_SUBJECT_NOT_FOUND = "S-404";
    public static final String ERROR_CODE_COURSE_NOT_FOUND = "C-404";

    // Otros mensajes útiles
    public static final String EMPTY_STRING = "";
    public static final String BODY_MESSAGE = "SUCCES";

    // Códigos de respuesta HTTP
    public static final String RESPONSE_CODE_200 = "200";
    public static final String RESPONSE_CODE_201 = "201";
    public static final String RESPONSE_CODE_400 = "400";
    public static final String RESPONSE_CODE_404 = "404";
    public static final String RESPONSE_CODE_500 = "500";
}
