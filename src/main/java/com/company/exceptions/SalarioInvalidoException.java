package com.company.exceptions;

public class SalarioInvalidoException extends DomainException {
    private static final String MENSAJE = "El salario debe ser mayor a 0";
    private static final String MENSAJE_NETO_NEGATIVO = "El salario neto no puede ser negativo";

    public SalarioInvalidoException(String message) {
        super(message);
    }

    public static SalarioInvalidoException salarioValorInvalido() {
        return new SalarioInvalidoException(MENSAJE);
    }

    public static SalarioInvalidoException salarioNetoInvalido() {
        return new SalarioInvalidoException(MENSAJE_NETO_NEGATIVO);
    }
}
