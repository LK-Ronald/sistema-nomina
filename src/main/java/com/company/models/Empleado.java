package com.company.models;

import com.company.enums.TipoTrabajador;
import com.company.util.Validaciones;

public abstract class Empleado {
    private final String nombre;
    private final String cedula;
    private final int anosAntiguedad;
    private final boolean esEmpleadoPermanente;

    public Empleado(final String nombre, final String cedula, final int anosAntiguedad) {
        this.nombre = nombre;
        this.cedula = cedula;
        this.anosAntiguedad = Validaciones.validarAntiguedad(anosAntiguedad);
        this.esEmpleadoPermanente = false;
    }

    public abstract TipoTrabajador getTipoTrabajador();

    public boolean puedeAccederFondoAhorro() {
        return false;
    }

    public boolean esEmpleadoPermanente() {
        return esEmpleadoPermanente;
    }

    public String getNombre() {
        return nombre;
    }

    public String getCedula() {
        return cedula;
    }

    public int getAnosAntiguedad() {
        return anosAntiguedad;
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() + "[nombre=" + nombre + ", cedula=" + cedula + ", anosAntiguedad=" + anosAntiguedad + "]";
    }
}
