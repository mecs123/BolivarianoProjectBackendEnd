package com.user.securityApp.excepcion;

public class ConflictException extends RuntimeException {
    private final String campo;

    public ConflictException(String campo, String message) {
        super(message);  // Aquí solo pasamos el mensaje al constructor de RuntimeException
        this.campo = campo;
    }

    public String getCampo() {
        return campo;
    }
}

