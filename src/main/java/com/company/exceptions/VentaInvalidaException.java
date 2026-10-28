package com.company.exceptions;

public class VentaInvalidaException extends DomainException {
    private static final String MENSAJE = "El valor de las ventas no puede ser nulo ni negativo";

    public VentaInvalidaException(String message) {
        super(message);
    }

    public static VentaInvalidaException cantidadVentaInvalida() {
        return new VentaInvalidaException(MENSAJE);
    }
}
