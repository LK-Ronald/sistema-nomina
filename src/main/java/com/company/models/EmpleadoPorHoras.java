package com.company.models;

import com.company.enums.TipoTrabajador;
import com.company.util.FinanceConstants;
import com.company.util.Validaciones;

import java.math.BigDecimal;

public class EmpleadoPorHoras extends Empleado {
    private final BigDecimal tarifaPorHoras;
    private final int horasTrabajadas;
    private static final TipoTrabajador TIPO_TRABAJADOR = TipoTrabajador.POR_HORAS;
    private final boolean aceptaFondoAhorro;

    public EmpleadoPorHoras(final String nombre, final String cedula, final int anosAntiguedad,
                            final int horasTrabajadas, final boolean aceptaFondoAhorro, final BigDecimal tarifaPorHoras) {
        super(nombre, cedula, anosAntiguedad);
        this.horasTrabajadas = Validaciones.validarHoras(horasTrabajadas);
        this.aceptaFondoAhorro = aceptaFondoAhorro;
        this.tarifaPorHoras = Validaciones.validarTarifa(tarifaPorHoras);
    }

    public BigDecimal getTarifaPorHoras() {
        return tarifaPorHoras;
    }

    public int getHorasTrabajadas() {
        return horasTrabajadas;
    }

    public boolean isAceptaFondoAhorro() {
        return aceptaFondoAhorro;
    }

    @Override
    public boolean puedeAccederFondoAhorro() {
        return aceptaFondoAhorro && getAnosAntiguedad() > FinanceConstants.ANIOS_PARA_FONDO_AHORRO;
    }

    @Override
    public TipoTrabajador getTipoTrabajador() {
        return TIPO_TRABAJADOR;
    }
}
