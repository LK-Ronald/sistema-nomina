package company.services;

import com.company.models.EmpleadoAsalariado;
import com.company.services.CalculadoraEmpleadoAsalariado;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

public class CalculadoraEmpleadoAsalariadoTest {

    private CalculadoraEmpleadoAsalariado calculadora;

    @BeforeEach
    public void setUp() {
        calculadora = new CalculadoraEmpleadoAsalariado();
    }

    @Test
    public void testEmpleadoAsalariadoMenosDeCincoAnos() {
        // Salario mensual: 2.000.000
        // Antigüedad: 3 años (<= 5) -> Bono Antigüedad = 0
        // Beneficio Permanente (Bono Alimentación): 1.000.000
        // Deducciones: 2.000.000 * (0.04 + 0.00522) = 80.000 + 10.440 = 90.440
        // Neto = 2.000.000 + 0 + 1.000.000 - 90.440 = 2.909.560,00
        EmpleadoAsalariado emp = new EmpleadoAsalariado("Ana", "123", 3, new BigDecimal("2000000"));

        BigDecimal bruto = calculadora.calcularSalarioBruto(emp);
        assertEquals(new BigDecimal("2000000"), bruto);

        BigDecimal bonos = calculadora.calcularBonos(emp);
        assertEquals(BigDecimal.ZERO, bonos);

        BigDecimal beneficios = calculadora.calcularBeneficios(emp);
        assertEquals(new BigDecimal("1000000"), beneficios);

        BigDecimal deducciones = calculadora.calcularDeducciones(emp);
        assertEquals(0, new BigDecimal("90440.00").compareTo(deducciones));

        BigDecimal neto = calculadora.calcularNomina(emp);
        assertEquals(new BigDecimal("2909560.00"), neto);
    }

    @Test
    public void testEmpleadoAsalariadoMasDeCincoAnos() {
        // Salario mensual: 3.000.000
        // Antigüedad: 6 años (> 5) -> Bono Antigüedad (10%) = 300.000
        // Beneficio Permanente (Bono Alimentación): 1.000.000
        // Deducciones: 3.000.000 * 0.04522 = 135.660
        // Neto = 3.000.000 + 300.000 + 1.000.000 - 135.660 = 4.164.340,00
        EmpleadoAsalariado emp = new EmpleadoAsalariado("Juan", "456", 6, new BigDecimal("3000000"));

        BigDecimal bonos = calculadora.calcularBonos(emp);
        assertEquals(0, new BigDecimal("300000").compareTo(bonos));

        BigDecimal neto = calculadora.calcularNomina(emp);
        assertEquals(new BigDecimal("4164340.00"), neto);
    }
}
