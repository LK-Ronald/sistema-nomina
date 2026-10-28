package com.company;

import com.company.models.*;
import com.company.services.ServicioNomina;

import java.math.BigDecimal;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        ServicioNomina servicioNomina = new ServicioNomina();

        List<Empleado> empleados = List.of(
                new EmpleadoAsalariado("Juan Perez", "1001", 6, new BigDecimal("3000000")),
                new EmpleadoAsalariado("Ana Gomez", "1002", 3, new BigDecimal("2500000")),
                new EmpleadoPorHoras("Carlos Ruiz", "2001", 2, 45, true, new BigDecimal("20000")),
                new EmpleadoPorHoras("Luisa Diaz", "2002", 1, 35, false, new BigDecimal("20000")),
                new EmpleadoPorComision("Ronald Pereira", "3001", 4, new BigDecimal("1500000"), 0.05, new BigDecimal("30000000")),
                new EmpleadoPorComision("Marta Rojas", "3002", 2, new BigDecimal("1200000"), 0.05, new BigDecimal("10000000")),
                new EmpleadoTemporal("Pedro Torres", "4001", 1, new BigDecimal("1800000"), 6)
        );

        System.out.println("=== SISTEMA DE LIQUIDACION DE NOMINA ===");
        for (Empleado empleado : empleados) {
            DetalleNomina detalle = servicioNomina.liquidar(empleado);
            System.out.println(detalle);
            System.out.println("----------------------------------------");
        }
    }
}
