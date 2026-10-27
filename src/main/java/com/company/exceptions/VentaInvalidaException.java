package com.company.exceptions;

public class VentaInvalidaException extends RuntimeException {
    private static final String MENSAJE = "La cantidad de la venta no puede ser negativa";

    private VentaInvalidaException(String message) {
        super(message);
    }

    public static VentaInvalidaException cantidadVentaInvalida() {
        return new VentaInvalidaException(MENSAJE);
    }
}
