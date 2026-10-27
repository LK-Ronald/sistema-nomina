package com.company.models;

import com.company.enums.TipoTrabajador;
import com.company.util.Validaciones;

import java.math.BigDecimal;

public class EmpleadoSalariado extends Empleado {
    private final BigDecimal salarioMensual;
    private final TipoTrabajador TIPO_TRABAJADOR = TipoTrabajador.ASALARIADO;

    public EmpleadoSalariado(String nombre, String cedula, int anosAntiguedad, BigDecimal salario) {
        super(nombre, cedula, anosAntiguedad);
        this.salarioMensual = Validaciones.validarSalarioNeto(salario);
    }

    public BigDecimal getSalarioMensual() {
        return salarioMensual;
    }

    @Override
    public TipoTrabajador getTipoTrabajador() {
        return TIPO_TRABAJADOR;
    }
}
