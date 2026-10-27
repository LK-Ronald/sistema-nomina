package com.company.models;

import com.company.enums.TipoTrabajador;
import com.company.util.FinanceConstants;
import com.company.util.Validaciones;

import java.math.BigDecimal;

public class EmpleadoPorHoras extends Empleado {
    private final BigDecimal tarifaPorHoras;
    private final int horasTrabajadas;
    private final TipoTrabajador TIPO_TRABAJADOR = TipoTrabajador.POR_HORAS;
    private final boolean aceptaFondoAhorro;

    public EmpleadoPorHoras(final String nombre, final String cedula, final int anosAntiguedad,
                            final int horasTrabajadas, final boolean aceptaFondoAhorro, final BigDecimal tarifaPorHoras) {
        super(nombre, cedula, anosAntiguedad);
        this.horasTrabajadas = Validaciones.validarHoras(horasTrabajadas);
        this.aceptaFondoAhorro = aceptaFondoAhorro;
        this.tarifaPorHoras = tarifaPorHoras;
    }

    public BigDecimal getTarifaPorHoras() {
        return tarifaPorHoras;
    }

    public int getHorasNormales() {
        return Math.min(horasTrabajadas, FinanceConstants.HORAS_SIN_RECARGO);
    }

    public int getHorasExtras() {
        return Math.max(Math.subtractExact(horasTrabajadas, FinanceConstants.HORAS_SIN_RECARGO), 0);
    }

    public BigDecimal getMontoPorHoras() {
        return tarifaPorHoras.multiply(BigDecimal.valueOf(getHorasNormales()));
    }

    @Override
    public boolean puedeAccederFondoAhorro() {
        return aceptaFondoAhorro && getAnosAntiguedad() > 1;
    }

    @Override
    public TipoTrabajador getTipoTrabajador() {
        return TIPO_TRABAJADOR;
    }
}
