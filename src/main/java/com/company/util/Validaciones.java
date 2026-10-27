package com.company.util;

import com.company.exceptions.HorasInvalidasException;
import com.company.exceptions.SalarioInvalidoException;
import com.company.exceptions.VentaInvalidaException;

import java.math.BigDecimal;

public class Validaciones {

    public static int validarCantidad(int cantidad) {
        if (cantidad < 0) {
            throw VentaInvalidaException.cantidadVentaInvalida();
        }
        return cantidad;
    }

    public static BigDecimal validarSalarioNeto(BigDecimal salarioNeto) {
        if (salarioNeto.signum() <= 0) {
            throw SalarioInvalidoException.salarioValorInvalido();
        }
        return salarioNeto;
    }

    public static int validarHoras(int horas) {
        if (horas < 0) {
            throw HorasInvalidasException.horasInvalidas();
        }
        return horas;
    }
}
