package com.company.models;

import com.company.enums.TipoTrabajador;
import com.company.util.Validaciones;

import java.math.BigDecimal;

public class EmpleadoTemporal extends Empleado {
    private final BigDecimal salarioFijoMensual;
    private final int mesesContrato;
    private final TipoTrabajador TIPO_TRABAJADOR = TipoTrabajador.TEMPORAL;

    public EmpleadoTemporal(String nombre, String cedula, int anosAntiguedad,
                            BigDecimal salarioFijoMensual, int mesesContrato) {
        super(nombre, cedula, anosAntiguedad);
        this.salarioFijoMensual = Validaciones.validarSalarioNeto(salarioFijoMensual);
        this.mesesContrato = mesesContrato;
    }

    public BigDecimal getSalarioFijoMensual() {
        return salarioFijoMensual;
    }

    public int getMesesContrato() {
        return mesesContrato;
    }

    @Override
    public TipoTrabajador getTipoTrabajador() {
        return TIPO_TRABAJADOR;
    }


}
