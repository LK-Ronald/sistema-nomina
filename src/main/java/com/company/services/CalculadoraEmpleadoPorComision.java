package com.company.services;

import com.company.models.EmpleadoPorComision;
import com.company.util.FinanceConstants;
import com.company.util.Validaciones;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class CalculadoraEmpleadoPorComision implements CalculadoraNomina<EmpleadoPorComision> {

    @Override
    public BigDecimal calcularNomina(EmpleadoPorComision empleado) {
        BigDecimal salarioBruto = calcularSalarioBruto(empleado);
        BigDecimal bonos = calcularBonos(empleado);
        BigDecimal beneficios = calcularBeneficios(empleado);
        BigDecimal deducciones = calcularDeducciones(salarioBruto);

        BigDecimal salarioNeto = salarioBruto.add(bonos).add(beneficios).subtract(deducciones);
        return Validaciones.validarSalarioNetoCalculado(salarioNeto.setScale(2, RoundingMode.HALF_UP));
    }

    public BigDecimal calcularSalarioBruto(EmpleadoPorComision empleado) {
        BigDecimal comision = empleado.getVentas().multiply(BigDecimal.valueOf(empleado.getPorcentajeComision()));
        return empleado.getSalarioBase().add(comision);
    }

    public BigDecimal calcularBonos(EmpleadoPorComision empleado) {
        if (empleado.getVentas().compareTo(BigDecimal.valueOf(FinanceConstants.UMBRAL_VENTAS_BONO)) > 0) {
            return empleado.getVentas().multiply(BigDecimal.valueOf(FinanceConstants.PORCENTAJE_BONO_VENTAS));
        }
        return BigDecimal.ZERO;
    }

    public BigDecimal calcularBeneficios(EmpleadoPorComision empleado) {
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

    public BigDecimal calcularDeducciones(EmpleadoPorComision empleado) {
        return calcularDeducciones(calcularSalarioBruto(empleado));
    }
}
