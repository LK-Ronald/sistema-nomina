package com.company.models;

import java.math.BigDecimal;

public class DetalleNomina {
    private final Empleado empleado;
    private final BigDecimal salarioBruto;
    private final BigDecimal bonos;
    private final BigDecimal beneficios;
    private final BigDecimal deducciones;
    private final BigDecimal salarioNeto;

    public DetalleNomina(Empleado empleado, BigDecimal salarioBruto, BigDecimal bonos,
                         BigDecimal beneficios, BigDecimal deducciones, BigDecimal salarioNeto) {
        this.empleado = empleado;
        this.salarioBruto = salarioBruto;
        this.bonos = bonos;
        this.beneficios = beneficios;
        this.deducciones = deducciones;
        this.salarioNeto = salarioNeto;
    }

    public Empleado getEmpleado() {
        return empleado;
    }

    public BigDecimal getSalarioBruto() {
        return salarioBruto;
    }

    public BigDecimal getBonos() {
        return bonos;
    }

    public BigDecimal getBeneficios() {
        return beneficios;
    }

    public BigDecimal getDeducciones() {
        return deducciones;
    }

    public BigDecimal getSalarioNeto() {
        return salarioNeto;
    }

    @Override
    public String toString() {
        return String.format(
                "Detalle Nomina de %s (%s):\n" +
                        " - Salario Bruto: $%,.2f\n" +
                        " - Bonos: $%,.2f\n" +
                        " - Beneficios: $%,.2f\n" +
                        " - Deducciones: $%,.2f\n" +
                        " - Salario Neto: $%,.2f",
                empleado.getNombre(), empleado.getTipoTrabajador(),
                salarioBruto, bonos, beneficios, deducciones, salarioNeto
        );
    }
}
