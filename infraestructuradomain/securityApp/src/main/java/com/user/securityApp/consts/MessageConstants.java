package com.user.securityApp.consts;

public class MessageConstants {
    // Mensajes de éxito
    public static final String USER_CREATED = "Usuario creado exitosamente";
    public static final String ROLE_REQUEST_REQUIRED = "Los roles son requeridos";
    public static final String ROLE_NOT_FOUND = "Los roles especificados no existen";
    public static final String USER_ALREADY_EXIST = "El usuario ya existe";
    public static final String EMAIL_ALREADY_EXIST = "El correo electrónico ya está registrado.";
    public static final String EMAIL_NOT_EXIST = "El correo electrónico No está registrado.";

    public static final String USER_CREATION_ERROR = "Error al crear el usuario";

    // Mensajes de error
    public static final String AUTH_TOKEN_EXPIRED = "El token ha expirado";
    public static final String AUTH_TOKEN_INVALID = "El token es inválido";

    // Códigos de estado o errores
    public static final int SUCCESS_CODE = 200;
    public static final int ERROR_CODE = 500;
    public static final int BAD_REQUEST_CODE = 400;
    public static final int UNAUTHORIZED_CODE = 401;

}

