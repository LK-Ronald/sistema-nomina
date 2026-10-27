package com.company.exceptions;

public class HorasInvalidasException extends RuntimeException {
    private static final String MENSAJE = "Las horas de trabajo no pueden ser negativas";

    private HorasInvalidasException(String message) {
        super(message);
    }

    public static HorasInvalidasException horasInvalidas() {
        return new HorasInvalidasException(MENSAJE);
    }
}
