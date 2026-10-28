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

    public static BigDecimal validarVentas(BigDecimal ventas) {
        if (ventas == null || ventas.compareTo(BigDecimal.ZERO) < 0) {
            throw VentaInvalidaException.cantidadVentaInvalida();
        }
        return ventas;
    }

    public static BigDecimal validarSalarioNeto(BigDecimal salario) {
        if (salario == null || salario.compareTo(BigDecimal.ZERO) <= 0) {
            throw SalarioInvalidoException.salarioValorInvalido();
        }
        return salario;
    }

    public static BigDecimal validarSalarioNetoCalculado(BigDecimal salarioNeto) {
        if (salarioNeto == null || salarioNeto.compareTo(BigDecimal.ZERO) < 0) {
            throw SalarioInvalidoException.salarioNetoInvalido();
        }
        return salarioNeto;
    }

    public static int validarHoras(int horas) {
        if (horas < 0) {
            throw HorasInvalidasException.horasInvalidas();
        }
        return horas;
    }

    public static BigDecimal validarTarifa(BigDecimal tarifa) {
        if (tarifa == null || tarifa.compareTo(BigDecimal.ZERO) <= 0) {
            throw SalarioInvalidoException.salarioValorInvalido();
        }
        return tarifa;
    }

    public static int validarAntiguedad(int anosAntiguedad) {
        if (anosAntiguedad < 0) {
            throw new IllegalArgumentException("Los años de antigüedad no pueden ser negativos");
        }
        return anosAntiguedad;
    }
}
