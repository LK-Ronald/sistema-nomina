package com.company.services;

import com.company.models.EmpleadoAsalariado;
import com.company.util.FinanceConstants;
import com.company.util.Validaciones;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class CalculadoraEmpleadoAsalariado implements CalculadoraNomina<EmpleadoAsalariado> {

    @Override
    public BigDecimal calcularNomina(EmpleadoAsalariado empleado) {
        BigDecimal salarioBruto = calcularSalarioBruto(empleado);
        BigDecimal bonos = calcularBonos(empleado);
        BigDecimal beneficios = calcularBeneficios(empleado);
        BigDecimal deducciones = calcularDeducciones(salarioBruto);

        BigDecimal salarioNeto = salarioBruto.add(bonos).add(beneficios).subtract(deducciones);
        return Validaciones.validarSalarioNetoCalculado(salarioNeto.setScale(2, RoundingMode.HALF_UP));
    }

    public BigDecimal calcularSalarioBruto(EmpleadoAsalariado empleado) {
        return empleado.getSalarioMensual();
    }

    public BigDecimal calcularBonos(EmpleadoAsalariado empleado) {
        if (empleado.getAnosAntiguedad() > FinanceConstants.ANIOS_PARA_BONO_ASALARIADO) {
            return empleado.getSalarioMensual().multiply(BigDecimal.valueOf(FinanceConstants.BONO_MENSUAL));
        }
        return BigDecimal.ZERO;
    }

    public BigDecimal calcularBeneficios(EmpleadoAsalariado empleado) {
        if (empleado.esEmpleadoPermanente()) {
            return FinanceConstants.VALOR_BONO_ALIMENTACION;
        }
        return BigDecimal.ZERO;
    }

    public BigDecimal calcularDeducciones(BigDecimal salarioBruto) {
        BigDecimal seguroPension = salarioBruto.multiply(BigDecimal.valueOf(FinanceConstants.PORCENTAJE_SEGURO_SOCIAL_PENSION));
        BigDecimal arl = salarioBruto.multiply(BigDecimal.valueOf(FinanceConstants.PORCENTAJE_ARL_RIESGO_I));
        return seguroPension.add(arl);
    }

    public BigDecimal calcularDeducciones(EmpleadoAsalariado empleado) {
        return calcularDeducciones(calcularSalarioBruto(empleado));
    }
}
