package com.company.services;

import com.company.models.EmpleadoPorHoras;
import com.company.util.FinanceConstants;
import com.company.util.Validaciones;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class CalculadoraEmpleadoPorHoras implements CalculadoraNomina<EmpleadoPorHoras> {

    @Override
    public BigDecimal calcularNomina(EmpleadoPorHoras empleado) {
        BigDecimal salarioBruto = calcularSalarioBruto(empleado);
        BigDecimal bonos = calcularBonos(empleado);
        BigDecimal beneficios = calcularBeneficios(empleado, salarioBruto);
        BigDecimal deducciones = calcularDeducciones(salarioBruto);

        BigDecimal salarioNeto = salarioBruto.add(bonos).add(beneficios).subtract(deducciones);
        return Validaciones.validarSalarioNetoCalculado(salarioNeto.setScale(2, RoundingMode.HALF_UP));
    }

    public BigDecimal calcularSalarioBruto(EmpleadoPorHoras empleado) {
        int horas = empleado.getHorasTrabajadas();
        BigDecimal tarifa = empleado.getTarifaPorHoras();

        if (horas <= FinanceConstants.HORAS_SIN_RECARGO) {
            return tarifa.multiply(BigDecimal.valueOf(horas));
        }

        int horasNormales = FinanceConstants.HORAS_SIN_RECARGO;
        int horasExtras = horas - FinanceConstants.HORAS_SIN_RECARGO;

        BigDecimal pagoNormal = tarifa.multiply(BigDecimal.valueOf(horasNormales));
        BigDecimal tarifaExtra = tarifa.multiply(BigDecimal.valueOf(FinanceConstants.FACTOR_HORA_EXTRA));
        BigDecimal pagoExtra = tarifaExtra.multiply(BigDecimal.valueOf(horasExtras));

        return pagoNormal.add(pagoExtra);
    }

    public BigDecimal calcularBonos(EmpleadoPorHoras empleado) {
        return BigDecimal.ZERO;
    }

    public BigDecimal calcularBeneficios(EmpleadoPorHoras empleado, BigDecimal salarioBruto) {
        if (empleado.puedeAccederFondoAhorro()) {
            return salarioBruto.multiply(BigDecimal.valueOf(FinanceConstants.PORCENTAJE_FONDO_AHORRO));
        }
        return BigDecimal.ZERO;
    }

    public BigDecimal calcularBeneficios(EmpleadoPorHoras empleado) {
        return calcularBeneficios(empleado, calcularSalarioBruto(empleado));
    }

    public BigDecimal calcularDeducciones(BigDecimal salarioBruto) {
        BigDecimal seguroPension = salarioBruto.multiply(BigDecimal.valueOf(FinanceConstants.PORCENTAJE_SEGURO_SOCIAL_PENSION));
        BigDecimal arl = salarioBruto.multiply(BigDecimal.valueOf(FinanceConstants.PORCENTAJE_ARL_RIESGO_I));
        return seguroPension.add(arl);
    }

    public BigDecimal calcularDeducciones(EmpleadoPorHoras empleado) {
        return calcularDeducciones(calcularSalarioBruto(empleado));
    }
}
