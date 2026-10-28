package com.company.services;

import com.company.models.DetalleNomina;
import com.company.models.Empleado;

import java.math.BigDecimal;

public interface CalculadoraNomina<T extends Empleado> {

    BigDecimal calcularSalarioBruto(T empleado);

    BigDecimal calcularBonos(T empleado);

    BigDecimal calcularBeneficios(T empleado);

    BigDecimal calcularDeducciones(BigDecimal salarioBruto);

    BigDecimal calcularNomina(T empleado);

    default BigDecimal calcularDeducciones(T empleado) {
        return calcularDeducciones(calcularSalarioBruto(empleado));
    }

    default DetalleNomina liquidar(T empleado) {
        BigDecimal bruto = calcularSalarioBruto(empleado);
        BigDecimal bonos = calcularBonos(empleado);
        BigDecimal beneficios = calcularBeneficios(empleado);
        BigDecimal deducciones = calcularDeducciones(bruto);
        BigDecimal neto = calcularNomina(empleado);
        return new DetalleNomina(empleado, bruto, bonos, beneficios, deducciones, neto);
    }
}
