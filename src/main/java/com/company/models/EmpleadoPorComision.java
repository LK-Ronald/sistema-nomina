package com.company.models;

import com.company.enums.TipoTrabajador;
import com.company.util.Validaciones;

import java.math.BigDecimal;

public class EmpleadoPorComision extends Empleado {
    private static final TipoTrabajador TIPO_TRABAJADOR = TipoTrabajador.POR_COMISION;
    private final BigDecimal salarioBase;
    private final double porcentajeComision;
    private final BigDecimal ventas;
    private final boolean empleadoPermanente;

    public EmpleadoPorComision(String nombre, String cedula, int anosAntiguedad,
                               BigDecimal salarioBase, double porcentajeComision, BigDecimal ventas) {
        super(nombre, cedula, anosAntiguedad);
        this.salarioBase = Validaciones.validarSalarioNeto(salarioBase);
        this.porcentajeComision = porcentajeComision;
        this.ventas = Validaciones.validarVentas(ventas);
        this.empleadoPermanente = true;
    }

    @Override
    public boolean esEmpleadoPermanente() {
        return empleadoPermanente;
    }

    public BigDecimal getSalarioBase() {
        return salarioBase;
    }

    public double getPorcentajeComision() {
        return porcentajeComision;
    }

    public BigDecimal getVentas() {
        return ventas;
    }

    @Override
    public TipoTrabajador getTipoTrabajador() {
        return TIPO_TRABAJADOR;
    }
}
