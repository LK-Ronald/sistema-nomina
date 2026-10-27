package com.company.models;

import com.company.enums.TipoTrabajador;

public abstract class Empleado {
    private final String nombre;
    private final String cedula;
    private final int anosAntiguedad;

    public Empleado(final String nombre,final String cedula,final int anosAntiguedad) {
        this.nombre = nombre;
        this.cedula = cedula;
        this.anosAntiguedad = anosAntiguedad;
    }

    abstract public TipoTrabajador getTipoTrabajador();

    public boolean puedeAccederFondoAhorro(){
        return false;
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
