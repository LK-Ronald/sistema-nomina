package com.company.services;

import com.company.models.EmpleadoTemporal;
import com.company.util.FinanceConstants;
import com.company.util.Validaciones;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class CalculadoraEmpleadoTemporal implements CalculadoraNomina<EmpleadoTemporal> {

    @Override
    public BigDecimal calcularNomina(EmpleadoTemporal empleado) {
        BigDecimal salarioBruto = calcularSalarioBruto(empleado);
        BigDecimal bonos = calcularBonos(empleado);
        BigDecimal beneficios = calcularBeneficios(empleado);
        BigDecimal deducciones = calcularDeducciones(salarioBruto);

        BigDecimal salarioNeto = salarioBruto.add(bonos).add(beneficios).subtract(deducciones);
        return Validaciones.validarSalarioNetoCalculado(salarioNeto.setScale(2, RoundingMode.HALF_UP));
    }

    public BigDecimal calcularSalarioBruto(EmpleadoTemporal empleado) {
        return empleado.getSalarioFijoMensual();
    }

    public BigDecimal calcularBonos(EmpleadoTemporal empleado) {
        return BigDecimal.ZERO;
    }

    public BigDecimal calcularBeneficios(EmpleadoTemporal empleado) {
        return BigDecimal.ZERO;
    }

    public BigDecimal calcularDeducciones(BigDecimal salarioBruto) {
        BigDecimal seguroPension = salarioBruto.multiply(BigDecimal.valueOf(FinanceConstants.PORCENTAJE_SEGURO_SOCIAL_PENSION));
        BigDecimal arl = salarioBruto.multiply(BigDecimal.valueOf(FinanceConstants.PORCENTAJE_ARL_RIESGO_I));
        return seguroPension.add(arl);
    }

    public BigDecimal calcularDeducciones(EmpleadoTemporal empleado) {
        return calcularDeducciones(calcularSalarioBruto(empleado));
    }
}
