package company.services;

import com.company.models.EmpleadoTemporal;
import com.company.services.CalculadoraEmpleadoTemporal;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

public class CalculadoraEmpleadoTemporalTest {

    private CalculadoraEmpleadoTemporal calculadora;

    @BeforeEach
    public void setUp() {
        calculadora = new CalculadoraEmpleadoTemporal();
    }

    @Test
    public void testEmpleadoTemporal() {
        // Salario Fijo: 2.000.000
        // No aplica bonos: 0
        // No aplica beneficios: 0
        // Deducciones: 2.000.000 * (0.04 + 0.00522) = 80.000 + 10.440 = 90.440
        // Neto = 2.000.000 - 90.440 = 1.909.560,00
        EmpleadoTemporal emp = new EmpleadoTemporal("Pedro", "123", 1, new BigDecimal("2000000"), 6);

        BigDecimal bruto = calculadora.calcularSalarioBruto(emp);
        assertEquals(new BigDecimal("2000000"), bruto);

        BigDecimal bonos = calculadora.calcularBonos(emp);
        assertEquals(BigDecimal.ZERO, bonos);

        BigDecimal beneficios = calculadora.calcularBeneficios(emp);
        assertEquals(BigDecimal.ZERO, beneficios);

        BigDecimal deducciones = calculadora.calcularDeducciones(emp);
        assertEquals(0, new BigDecimal("90440.00").compareTo(deducciones));

        BigDecimal neto = calculadora.calcularNomina(emp);
        assertEquals(new BigDecimal("1909560.00"), neto);
    }
}
