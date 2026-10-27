package com.company.models;

import com.company.enums.TipoTrabajador;
import com.company.util.Validaciones;

import java.math.BigDecimal;

public class EmpleadoPorComision extends Empleado {
    private final TipoTrabajador TIPO_TRABAJADOR = TipoTrabajador.POR_COMISION;
    private final BigDecimal salarioBase;
    private final double porcentajeComision;
    private final int cantidadVentas;

    public EmpleadoPorComision(String nombre, String cedula, int anosAntiguedad,
                               BigDecimal salarioBase, double porcentajeComision, int cantidadVentas) {
        super(nombre, cedula, anosAntiguedad);
        this.salarioBase = Validaciones.validarSalarioNeto(salarioBase);
        this.porcentajeComision = porcentajeComision;
        this.cantidadVentas = Validaciones.validarCantidad(cantidadVentas);
    }

    public BigDecimal getSalarioBase() {
        return salarioBase;
    }

    public double getPorcentajeComision() {
        return porcentajeComision;
    }

    public int getCantidadVentas() {
        return cantidadVentas;
    }

    @Override
    public TipoTrabajador getTipoTrabajador() {
        return TIPO_TRABAJADOR;
    }

}
