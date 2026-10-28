package com.company.services;

import com.company.models.*;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class ServicioNomina {

    private final Map<Class<? extends Empleado>, CalculadoraNomina<?>> calculadoras;

    public ServicioNomina() {
        this.calculadoras = new HashMap<>();
        registrarCalculadorasPorDefecto();
    }

    public ServicioNomina(Map<Class<? extends Empleado>, CalculadoraNomina<?>> calculadoras) {
        Objects.requireNonNull(calculadoras, "El registro de calculadoras no puede ser nulo");
        this.calculadoras = new HashMap<>();
        this.calculadoras.putAll(calculadoras);
    }

    private void registrarCalculadorasPorDefecto() {
        registrarCalculadora(EmpleadoAsalariado.class, new CalculadoraEmpleadoAsalariado());
        registrarCalculadora(EmpleadoPorHoras.class, new CalculadoraEmpleadoPorHoras());
        registrarCalculadora(EmpleadoPorComision.class, new CalculadoraEmpleadoPorComision());
        registrarCalculadora(EmpleadoTemporal.class, new CalculadoraEmpleadoTemporal());
    }

    public <T extends Empleado> void registrarCalculadora(Class<T> tipoEmpleado, CalculadoraNomina<T> calculadora) {
        Objects.requireNonNull(tipoEmpleado, "El tipo de empleado no puede ser nulo");
        Objects.requireNonNull(calculadora, "La calculadora no puede ser nula");
        this.calculadoras.put(tipoEmpleado, calculadora);
    }

    @SuppressWarnings("unchecked")
    public <T extends Empleado> CalculadoraNomina<T> getCalculadora(Class<T> tipoEmpleado) {
        if (tipoEmpleado == null) {
            throw new IllegalArgumentException("El tipo de empleado no puede ser nulo");
        }
        CalculadoraNomina<?> calculadora = calculadoras.get(tipoEmpleado);
        if (calculadora == null) {
            throw new IllegalArgumentException("Tipo de empleado no soportado: " + tipoEmpleado.getName());
        }
        return (CalculadoraNomina<T>) calculadora;
    }

    @SuppressWarnings("unchecked")
    private <T extends Empleado> CalculadoraNomina<T> resolverCalculadora(T empleado) {
        if (empleado == null) {
            throw new IllegalArgumentException("El empleado no puede ser nulo");
        }

        Class<?> tipo = empleado.getClass();
        while (tipo != null && Empleado.class.isAssignableFrom(tipo)) {
            CalculadoraNomina<?> calculadora = calculadoras.get(tipo);
            if (calculadora != null) {
                return (CalculadoraNomina<T>) calculadora;
            }
            tipo = tipo.getSuperclass();
        }

        throw new IllegalArgumentException(
                "Tipo de empleado no soportado: " + empleado.getTipoTrabajador()
        );
    }

    public BigDecimal calcularNomina(Empleado empleado) {
        CalculadoraNomina<Empleado> calculadora = resolverCalculadora(empleado);
        return calculadora.calcularNomina(empleado);
    }

    public DetalleNomina liquidar(Empleado empleado) {
        CalculadoraNomina<Empleado> calculadora = resolverCalculadora(empleado);
        return calculadora.liquidar(empleado);
    }
}
