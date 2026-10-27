package com.company.exceptions;

public class SalarioInvalidoException extends DomainException {
    private static final String MENSAJE = "El salario debe ser mayor a 0";

    private SalarioInvalidoException(String message) {
        super(message);
    }

    public static SalarioInvalidoException salarioValorInvalido() {
        return new SalarioInvalidoException(MENSAJE);
    }
}
