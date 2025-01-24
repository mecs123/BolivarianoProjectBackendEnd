package com.user.securityApp.excepcion;

public class NotFoundUserException extends RuntimeException {
    private final String campo;

    public NotFoundUserException(String campo, String message) {
        super(message);  // Aquí solo pasamos el mensaje al constructor de RuntimeException
        this.campo = campo;
    }

    public String getCampo() {
        return campo;
    }
}

